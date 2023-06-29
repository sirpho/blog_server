package com.sirpho.blog.modules.article.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sirpho.blog.common.utils.Query;
import com.sirpho.blog.modules.article.dao.*;
import com.sirpho.blog.modules.article.entity.*;
import com.sirpho.blog.modules.article.service.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.jsoup.Jsoup;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import com.sirpho.blog.common.enums.WhetherEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, ArticleEntity> implements ArticleService {

    @Resource
    TagDao tagDao;
    @Resource
    CategoryDao categoryDao;
    @Resource
    TagRelationshipsDao tagRelationshipsDao;
    @Resource
    CategoryRelationshipsDao categoryRelationshipsDao;
    @Resource
    TagService tagService;
    @Resource
    TagRelationshipsService tagRelationshipsService;
    @Resource
    CategoryService categoryService;
    @Resource
    CategoryRelationshipsService categoryRelationshipsService;

    @Override
    public IPage<ArticleEntity> queryList(Map<String, Object> params) {
        IPage<ArticleEntity> page = new Query<ArticleEntity>().getPage(params);
        IPage<ArticleEntity> pages = baseMapper.queryList(page, params);
        return pages;
    }

    @Override
    public String editArticle(ArticleEntity entity) {
        markdown2Summary(entity);
        entity.setDeleted(WhetherEnum.NO.getValue());
        if (StringUtils.hasLength(entity.getId())) {
            updateById(entity);
        } else {
            save(entity);
        }
        String articleID = entity.getId();
        // 删除与标签的关联关系
        LambdaQueryWrapper<TagRelationshipsEntity> tagRelationshipsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tagRelationshipsLambdaQueryWrapper.eq(TagRelationshipsEntity::getArticleId, articleID);
        tagRelationshipsDao.delete(tagRelationshipsLambdaQueryWrapper);
        // 删除与分类的关联关系
        LambdaQueryWrapper<CategoryRelationshipsEntity> categoryRelationshipsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryRelationshipsLambdaQueryWrapper.eq(CategoryRelationshipsEntity::getArticleId, articleID);
        categoryRelationshipsDao.delete(categoryRelationshipsLambdaQueryWrapper);
        // 处理标签
        if (!CollectionUtils.isEmpty(entity.getTags())) {
            LambdaQueryWrapper<TagEntity> tagEntityWrapper = new LambdaQueryWrapper<>();
            tagEntityWrapper.in(TagEntity::getName, entity.getTags());
            // 已在数据库中的标签
            List<TagEntity> savedTags = tagDao.selectList(tagEntityWrapper);
            // 不在数据库中的标签
            List<TagEntity> unsavedTags = new ArrayList<>();
            entity.getTags().forEach(item -> {
                if (!savedTags.stream().map(TagEntity::getName).toList().contains(item)) {
                    unsavedTags.add(new TagEntity(null, item));
                }
            });
            // 批量插入新标签
            if (!unsavedTags.isEmpty()) {
                tagService.saveBatch(unsavedTags);
            }

            List<TagRelationshipsEntity> tagShips = new ArrayList<>();
            savedTags.addAll(unsavedTags);
            for (int i = 0; i < savedTags.size(); i++) {
                var item = savedTags.get(i);
                var tempShip = new TagRelationshipsEntity();
                tempShip.setArticleId(articleID);
                tempShip.setTagId(item.getId());
                tempShip.setSortOrder(i);
                tagShips.add(tempShip);
            }
            if (!tagShips.isEmpty()) {
                tagRelationshipsService.saveBatch(tagShips);
            }

        }
        // 处理分类
        if (!CollectionUtils.isEmpty(entity.getCategories())) {
            LambdaQueryWrapper<CategoryEntity> categoriesEntityWrapper = new LambdaQueryWrapper<>();
            categoriesEntityWrapper.in(CategoryEntity::getName, entity.getCategories());
            // 已在数据库中的类别
            List<CategoryEntity> savedCategories = categoryDao.selectList(categoriesEntityWrapper);
            // 不在数据库中的类别
            List<CategoryEntity> unsavedCategories = new ArrayList<>();
            entity.getCategories().forEach(item -> {
                if (!savedCategories.stream().map(CategoryEntity::getName).toList().contains(item)) {
                    unsavedCategories.add(new CategoryEntity(null, item));
                }
            });
            // 批量插入新类别
            if (!unsavedCategories.isEmpty()) {
                categoryService.saveBatch(unsavedCategories);
            }

            List<CategoryRelationshipsEntity> categoryShips = new ArrayList<>();
            savedCategories.addAll(unsavedCategories);
            for (int i = 0; i < savedCategories.size(); i++) {
                var item = savedCategories.get(i);
                var tempShip = new CategoryRelationshipsEntity();
                tempShip.setArticleId(articleID);
                tempShip.setCategoryId(item.getId());
                tempShip.setSortOrder(i);
                categoryShips.add(tempShip);
            }
            if (!categoryShips.isEmpty()) {
                categoryRelationshipsService.saveBatch(categoryShips);
            }
        }
        return entity.getId();
    }

    @Override
    public Object findById(String id) {
        ArticleEntity article = baseMapper.selectById(id);
        if (Objects.nonNull(article)) {
            article.setSummary(null);
            ArrayList<String> tags = (ArrayList<String>) tagDao.getTagsByArticleId(id);
            ArrayList<String> categories = (ArrayList<String>) categoryDao.getCategoriesByArticleId(id);
            article.setTags(tags);
            article.setCategories(categories);
        }
        return article;
    }

    @Override
    public void removeArticle(String id) {
        ArticleEntity article = baseMapper.selectById(id);
        article.setDeleted(WhetherEnum.YES.getValue());
        baseMapper.updateById(article);
    }

    /**
     * markdown文本提取摘要
     *
     * @param entity 文章
     */
    private void markdown2Summary(ArticleEntity entity) {
        String markdown = entity.getContent();
        // markdown转为html
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String htmlContent = renderer.render(parser.parse(markdown));
        // 提取富文本的内容 并取最多256个字符作为摘要
        var pureText = Jsoup.parse(htmlContent).body().text();
        var summary = pureText.substring(0, Math.min(256, pureText.length()));
        int wordCount = pureText.length();
        entity.setSummary(summary);
        entity.setWordCount(wordCount);
    }
}

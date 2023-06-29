package com.sirpho.blog.modules.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sirpho.blog.modules.article.entity.ArticleEntity;

import java.util.Map;

public interface ArticleService extends IService<ArticleEntity> {

    IPage<ArticleEntity> queryList(Map<String, Object> params);

    /**
     * 编辑文章
     *
     * @param entity 文章信息
     * @return boolean
     */
    String editArticle(ArticleEntity entity);


    Object findById(String id);

    void removeArticle(String id);
}

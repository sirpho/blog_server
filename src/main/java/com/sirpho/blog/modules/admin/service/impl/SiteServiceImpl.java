package com.sirpho.blog.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sirpho.blog.common.enums.WhetherEnum;
import com.sirpho.blog.modules.article.dao.ArticleDao;
import com.sirpho.blog.modules.admin.dao.SiteDao;
import com.sirpho.blog.modules.article.entity.ArticleEntity;
import com.sirpho.blog.modules.admin.entity.SiteEntity;
import com.sirpho.blog.modules.admin.service.SiteService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SiteServiceImpl extends ServiceImpl<SiteDao, SiteEntity> implements SiteService {

    @Resource
    SiteDao siteDao;
    @Resource
    ArticleDao articleDao;

    @Override
    public SiteEntity getSiteInfo() {
        Wrapper<SiteEntity> wrapper = new LambdaQueryWrapper<>();
        var result = siteDao.selectOne(wrapper);

        if (result != null) {
            var queryWrapper = new LambdaQueryWrapper<ArticleEntity>();
            queryWrapper.eq(ArticleEntity::getDeleted, WhetherEnum.NO.getValue());
            var count = articleDao.selectCount(queryWrapper);
            result.setArticlesCount(count);
        }
        return result;
    }
}

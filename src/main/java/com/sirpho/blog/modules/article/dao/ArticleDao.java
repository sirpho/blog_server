package com.sirpho.blog.modules.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sirpho.blog.modules.article.entity.ArticleEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface ArticleDao extends BaseMapper<ArticleEntity> {
    IPage<ArticleEntity> queryList(IPage<ArticleEntity> page,
                                   @Param("params") Map<String, Object> params);
}

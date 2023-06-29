package com.sirpho.blog.modules.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sirpho.blog.modules.article.entity.TagRelationshipsEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagRelationshipsDao extends BaseMapper<TagRelationshipsEntity> {
}

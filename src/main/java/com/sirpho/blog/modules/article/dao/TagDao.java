package com.sirpho.blog.modules.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sirpho.blog.modules.article.entity.TagEntity;
import com.sirpho.blog.modules.article.vo.TagVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagDao extends BaseMapper<TagEntity> {

    List<String> getTagsByArticleId(@Param("articleId") String articleId);

    List<TagVO> getTagList();
}

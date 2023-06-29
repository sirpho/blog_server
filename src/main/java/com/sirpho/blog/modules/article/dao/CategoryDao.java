package com.sirpho.blog.modules.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sirpho.blog.modules.article.entity.CategoryEntity;
import com.sirpho.blog.modules.article.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
    List<String> getCategoriesByArticleId(@Param("articleId") String articleId);

    List<CategoryVO> getCategoryList();

}

package com.sirpho.blog.modules.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sirpho.blog.modules.article.entity.CategoryEntity;
import com.sirpho.blog.modules.article.vo.CategoryVO;

import java.util.List;

public interface CategoryService extends IService<CategoryEntity> {
    List<CategoryVO> getCategoryList();
}

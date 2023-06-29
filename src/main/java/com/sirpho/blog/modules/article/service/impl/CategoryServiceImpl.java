package com.sirpho.blog.modules.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sirpho.blog.modules.article.dao.CategoryDao;
import com.sirpho.blog.modules.article.entity.CategoryEntity;
import com.sirpho.blog.modules.article.service.CategoryService;
import com.sirpho.blog.modules.article.vo.CategoryVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Resource
    CategoryDao categoryDao;

    @Override
    public List<CategoryVO> getCategoryList() {
        return categoryDao.getCategoryList();
    }
}

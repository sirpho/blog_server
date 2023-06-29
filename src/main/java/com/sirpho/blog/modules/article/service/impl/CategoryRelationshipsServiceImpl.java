package com.sirpho.blog.modules.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sirpho.blog.modules.article.dao.CategoryRelationshipsDao;
import com.sirpho.blog.modules.article.entity.CategoryRelationshipsEntity;
import com.sirpho.blog.modules.article.service.CategoryRelationshipsService;
import org.springframework.stereotype.Service;

@Service
public class CategoryRelationshipsServiceImpl extends ServiceImpl<CategoryRelationshipsDao, CategoryRelationshipsEntity>
        implements CategoryRelationshipsService {
}

package com.sirpho.blog.modules.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sirpho.blog.modules.article.dao.TagRelationshipsDao;
import com.sirpho.blog.modules.article.entity.TagRelationshipsEntity;
import com.sirpho.blog.modules.article.service.TagRelationshipsService;
import org.springframework.stereotype.Service;

@Service
public class TagRelationshipsServiceImpl extends ServiceImpl<TagRelationshipsDao, TagRelationshipsEntity>
        implements TagRelationshipsService {
}

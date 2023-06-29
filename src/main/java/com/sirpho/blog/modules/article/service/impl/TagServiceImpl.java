package com.sirpho.blog.modules.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sirpho.blog.modules.article.dao.TagDao;
import com.sirpho.blog.modules.article.entity.TagEntity;
import com.sirpho.blog.modules.article.service.TagService;
import com.sirpho.blog.modules.article.vo.TagVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagDao, TagEntity> implements TagService {

    @Resource
    TagDao tagDao;

    @Override
    public List<TagVO> getTagList() {
        return tagDao.getTagList();
    }
}

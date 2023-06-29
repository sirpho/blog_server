package com.sirpho.blog.modules.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sirpho.blog.modules.article.entity.TagEntity;
import com.sirpho.blog.modules.article.vo.TagVO;

import java.util.List;

public interface TagService extends IService<TagEntity> {
    List<TagVO> getTagList();
}

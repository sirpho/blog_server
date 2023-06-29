package com.sirpho.blog.modules.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sirpho.blog.modules.admin.entity.SiteEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SiteDao extends BaseMapper<SiteEntity> {
}

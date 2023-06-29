package com.sirpho.blog.modules.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sirpho.blog.modules.admin.entity.SiteEntity;

public interface SiteService extends IService<SiteEntity> {

    SiteEntity getSiteInfo();
}

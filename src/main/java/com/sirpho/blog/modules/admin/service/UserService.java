package com.sirpho.blog.modules.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sirpho.blog.modules.admin.entity.UserEntity;

public interface UserService extends IService<UserEntity> {

    UserEntity login(UserEntity user);

    boolean verifyToken(String token);
}

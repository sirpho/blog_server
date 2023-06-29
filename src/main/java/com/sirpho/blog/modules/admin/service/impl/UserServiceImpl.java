package com.sirpho.blog.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sirpho.blog.common.utils.CommonUtils;
import com.sirpho.blog.modules.admin.dao.UserDao;
import com.sirpho.blog.modules.admin.entity.UserEntity;
import com.sirpho.blog.modules.admin.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.UUID;
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Resource
    UserDao userDao;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public UserEntity login(UserEntity user) {
        var wrapper = new LambdaQueryWrapper<UserEntity>();
        wrapper.eq(UserEntity::getName, user.getName()).eq(UserEntity::getPassword, CommonUtils.encrypt(user.getPassword()));
        UserEntity loginEntity = userDao.selectOne(wrapper);
        if(loginEntity != null) {
            UUID uuid = UUID.randomUUID();
            String time = dateFormat.format(System.currentTimeMillis() + 1000 * 60 * 60 * 3);
            loginEntity.setToken(uuid.toString());
            loginEntity.setExpired(time);
            userDao.updateById(loginEntity);
            loginEntity.setPassword(null);
        }
        return loginEntity;
    }

    @Override
    public boolean verifyToken(String token) {
        String time = dateFormat.format(System.currentTimeMillis());
        var wrapper = new LambdaQueryWrapper<UserEntity>();
        wrapper.eq(UserEntity::getToken, token).gt(UserEntity::getExpired, time);
        return userDao.exists(wrapper);
    }
}

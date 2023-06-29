package com.sirpho.blog.modules.admin.controller;

import com.sirpho.blog.common.enums.ConstantEnum;
import com.sirpho.blog.common.utils.R;
import com.sirpho.blog.modules.admin.entity.UserEntity;
import com.sirpho.blog.modules.admin.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public R login(@RequestBody UserEntity entity) {
        var result = new HashMap<String, Object>();
        var user = userService.login(entity);
        if(user != null) {
            result.put(ConstantEnum.RESULT_FIELD.getValue(), user);
            return R.ok(result);
        } else {
            return R.error("账号或密码错误");
        }
    }
}

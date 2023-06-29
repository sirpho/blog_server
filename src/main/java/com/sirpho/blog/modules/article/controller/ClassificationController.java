package com.sirpho.blog.modules.article.controller;

import com.sirpho.blog.common.enums.ConstantEnum;
import com.sirpho.blog.common.utils.R;
import com.sirpho.blog.modules.admin.service.UserService;
import com.sirpho.blog.modules.article.entity.CategoryEntity;
import com.sirpho.blog.modules.article.entity.TagEntity;
import com.sirpho.blog.modules.article.service.CategoryService;
import com.sirpho.blog.modules.article.service.TagService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/classification")
public class ClassificationController {

    @Resource
    private CategoryService categoryService;
    @Resource
    private TagService tagService;
    @Resource
    private UserService userService;

    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
    public R categoryList() {
        var result = new HashMap<String, Object>();
        var list = categoryService.getCategoryList();
        result.put(ConstantEnum.RESULT_FIELD.getValue(), list);
        return R.ok(result);
    }

    @RequestMapping(value = "/category/update", method = RequestMethod.POST)
    public R categoryUpdate(HttpServletRequest request, @RequestBody CategoryEntity entity) {
        String token = request.getHeader("token");
        if(!userService.verifyToken(token)) {
            return R.error(HttpStatus.UNAUTHORIZED.value(), "登录过期");
        }
        var result = new HashMap<String, Object>();
        categoryService.saveOrUpdate(entity);
        result.put(ConstantEnum.RESULT_FIELD.getValue(), entity.getId());
        return R.ok(result);
    }

    @RequestMapping(value = "/category/delete", method = RequestMethod.DELETE)
    public R categoryDelete(HttpServletRequest request, @RequestBody CategoryEntity entity) {
        String token = request.getHeader("token");
        if(!userService.verifyToken(token)) {
            return R.error(HttpStatus.UNAUTHORIZED.value(), "登录过期");
        }
        categoryService.removeById(entity.getId());
        return R.ok();
    }

    @RequestMapping(value = "/tag/list", method = RequestMethod.GET)
    public R tagList() {
        var result = new HashMap<String, Object>();
        var list = tagService.getTagList();
        result.put(ConstantEnum.RESULT_FIELD.getValue(), list);
        return R.ok(result);
    }

    @RequestMapping(value = "/tag/update", method = RequestMethod.POST)
    public R tagUpdate(HttpServletRequest request, @RequestBody TagEntity entity) {
        String token = request.getHeader("token");
        if(!userService.verifyToken(token)) {
            return R.error(HttpStatus.UNAUTHORIZED.value(), "登录过期");
        }
        var result = new HashMap<String, Object>();
        tagService.saveOrUpdate(entity);
        result.put(ConstantEnum.RESULT_FIELD.getValue(), entity.getId());
        return R.ok(result);
    }

    @RequestMapping(value = "/tag/delete", method = RequestMethod.DELETE)
    public R tagDelete(HttpServletRequest request, @RequestBody TagEntity entity) {
        String token = request.getHeader("token");
        if(!userService.verifyToken(token)) {
            return R.error(HttpStatus.UNAUTHORIZED.value(), "登录过期");
        }
        tagService.removeById(entity.getId());
        return R.ok();
    }
}

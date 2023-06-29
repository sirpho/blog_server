package com.sirpho.blog.modules.article.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sirpho.blog.common.enums.ConstantEnum;
import com.sirpho.blog.common.utils.R;
import com.sirpho.blog.modules.admin.service.UserService;
import com.sirpho.blog.modules.article.entity.ArticleEntity;
import com.sirpho.blog.modules.article.service.ArticleService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;
    @Resource
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public R list(@RequestParam Map<String, Object> params) {
        var result = new HashMap<String, Object>();
        IPage<ArticleEntity> dataList = articleService.queryList(params);
        result.put(ConstantEnum.RESULT_FIELD.getValue(), dataList);
        return R.ok(result);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R update(HttpServletRequest request, @RequestBody ArticleEntity entity) {
        String token = request.getHeader("token");
        if(!userService.verifyToken(token)) {
            return R.error(HttpStatus.UNAUTHORIZED.value(), "登录过期");
        }
        var entityId = articleService.editArticle(entity);
        return R.ok().put(ConstantEnum.RESULT_FIELD.getValue(), entityId);
    }

    @RequestMapping(value = "/browse/{id}", method = RequestMethod.GET)
    public R browse(@PathVariable String id) {
        var entity = articleService.findById(id);
        return R.ok().put(ConstantEnum.RESULT_FIELD.getValue(), entity);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public R remove(HttpServletRequest request, @PathVariable String id) {
        String token = request.getHeader("token");
        if(!userService.verifyToken(token)) {
            return R.error(HttpStatus.UNAUTHORIZED.value(), "登录过期");
        }
        articleService.removeArticle(id);
        return R.ok();
    }
}

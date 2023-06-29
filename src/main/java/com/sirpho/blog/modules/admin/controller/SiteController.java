package com.sirpho.blog.modules.admin.controller;

import com.sirpho.blog.common.enums.ConstantEnum;
import com.sirpho.blog.common.utils.R;
import com.sirpho.blog.modules.admin.service.SiteService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/site")
public class SiteController {
    @Resource
    SiteService siteService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public R getSiteInfo() {
        var result = new HashMap<String, Object>();
        var entity = siteService.getSiteInfo();
        result.put(ConstantEnum.RESULT_FIELD.getValue(), entity);
        return R.ok(result);
    }
}

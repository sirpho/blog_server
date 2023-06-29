package com.sirpho.blog.modules.file.controller;

import com.sirpho.blog.common.enums.ConstantEnum;
import com.sirpho.blog.common.exception.BusinessException;
import com.sirpho.blog.common.utils.R;
import com.sirpho.blog.modules.admin.service.UserService;
import com.sirpho.blog.modules.file.service.FileService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;
    @Resource
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public R list(@RequestParam Map<String, Object> params) {
        var result = new HashMap<String, Object>();
        var pages = fileService.queryList(params);
        result.put(ConstantEnum.RESULT_FIELD.getValue(), pages);
        return R.ok(result);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public R uploadFile(HttpServletRequest request,
                        @RequestParam(value = "file", required = false) MultipartFile file,
                        @RequestParam(value = "files", required = false) MultipartFile[] files,
                        @RequestParam(value = "keepName", required = false) String keepName) {
        String token = request.getHeader("token");
        if(!userService.verifyToken(token)) {
            return R.error(HttpStatus.UNAUTHORIZED.value(), "登录过期");
        }
        R result = new R();
        var fileList = new ArrayList<MultipartFile>();
        if (Objects.nonNull(files)) {
            Collections.addAll(fileList, files);
        }
        if (Objects.nonNull(file)) {
            fileList.add(file);
        }
        try {
            var fileMap = fileService.upload(fileList, keepName);
            result.put(ConstantEnum.RESULT_FIELD.getValue(), fileMap);
        } catch (BusinessException e) {
            logger.error(e.getMessage());
            result.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.put("msg", "文件上传失败");
        }
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public R delete(HttpServletRequest request, @RequestParam Map<String, Object> params) {
        String token = request.getHeader("token");
        if(!userService.verifyToken(token)) {
            return R.error(HttpStatus.UNAUTHORIZED.value(), "登录过期");
        }
        var result = fileService.delete(params);
        return R.ok().put(ConstantEnum.RESULT_FIELD.getValue(), result);
    }
}

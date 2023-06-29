package com.sirpho.blog.modules.file.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sirpho.blog.modules.file.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileService extends IService<FileEntity> {
    IPage<FileEntity> queryList(Map<String, Object> params);

    List<Map<String, String>> upload(List<MultipartFile> files, String keepName);

    boolean delete(Map<String, Object> params);
}

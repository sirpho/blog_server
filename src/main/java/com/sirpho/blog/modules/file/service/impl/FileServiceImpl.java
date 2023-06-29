package com.sirpho.blog.modules.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sirpho.blog.common.enums.WhetherEnum;
import com.sirpho.blog.common.exception.BusinessException;
import com.sirpho.blog.common.utils.CommonUtils;
import com.sirpho.blog.common.utils.Query;
import com.sirpho.blog.modules.file.dao.FileDao;
import com.sirpho.blog.modules.file.entity.FileEntity;
import com.sirpho.blog.modules.file.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class FileServiceImpl extends ServiceImpl<FileDao, FileEntity> implements FileService {

    @Value("${upload.path}")
    private String uploadFilePath;
    @Value("${upload.host}")
    private String uploadFileHost;

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    @Resource
    private FileDao fileDao;

    @Override
    public IPage<FileEntity> queryList(Map<String, Object> params) {
        IPage<FileEntity> page = new Query<FileEntity>().getPage(params);
        var queryWrapper = new LambdaQueryWrapper<FileEntity>();
        if (params.containsKey("name")) {
            queryWrapper.like(FileEntity::getName, params.get("name"));
        }
        queryWrapper.eq(FileEntity::getDeleted, WhetherEnum.NO.getValue());
        queryWrapper.orderByDesc(FileEntity::getLastUpdateTime);
        var result = fileDao.selectPage(page, queryWrapper);
        result.getRecords().forEach(item -> {
            item.setUrl(getFileURL(item.getFullName()));
        });
        return result;
    }

    @Override
    public List<Map<String, String>> upload(List<MultipartFile> files, String keepName) {

        List<Map<String, String>> result = new ArrayList<>();
        String time = dateFormat.format(System.currentTimeMillis());

        for (MultipartFile file : files) {
            Map<String, String> itemResult = new HashMap<>();

            var fileEntity = transferFile(file, keepName, time);
            // 新增数据库记录 考虑批量插入数据库
            fileDao.insert(fileEntity);
            itemResult.put("name", fileEntity.getName());
            itemResult.put("url", getFileURL(fileEntity.getFullName()));
            result.add(itemResult);
        }
        return result;
    }

    /**
     * 删除文件
     *
     * @param params 参数 id: 文件id deleteSource: Y：删除源文件
     */
    public boolean delete(Map<String, Object> params) {
        boolean result = false;
        try {
            String id = (String) params.get("id");
            var fileEntity = fileDao.selectById(id);
            fileEntity.setDeleted(WhetherEnum.YES.getValue());
            fileDao.updateById(fileEntity);
            String deleteSource = (String) params.get("deleteSource");
            if (Objects.equals(deleteSource, WhetherEnum.YES.getValue())) {
                // 获取文件对象
                File file = new File(this.uploadFilePath + fileEntity.getFullName());
                // 删除文件
                result = file.delete();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 处理单个文件
     *
     * @return entity
     */
    private FileEntity transferFile(MultipartFile file, String keepName, String time) {
        try {
            String[] arr = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
            String suffix = arr.length > 1 ? arr[arr.length - 1] : "";
            String path = FILE_SEPARATOR + time;
            String uploadFileFolder = this.uploadFilePath + path;
            CommonUtils.createFolder(uploadFileFolder);

            String encryptFileName, fileFullName;
            if (Objects.equals(keepName, WhetherEnum.YES.getValue())) {
                encryptFileName = file.getOriginalFilename();
                fileFullName = encryptFileName;
            } else {
                encryptFileName = CommonUtils.encrypt(file.getOriginalFilename() + System.currentTimeMillis() + Math.random());
                fileFullName = StringUtils.hasLength(suffix) ? encryptFileName + '.' + suffix : encryptFileName;
            }

            String fileFullPath = path + FILE_SEPARATOR + fileFullName;

            File pFile = new File(this.uploadFilePath + fileFullPath);
            // 复制文件到指定目录
            file.transferTo(pFile);

            long size = file.getSize();

            FileEntity fileEntity = new FileEntity();
            fileEntity.setName(file.getOriginalFilename());
            fileEntity.setPath(path);
            fileEntity.setSuffix(StringUtils.hasLength(suffix) ? suffix : "");
            fileEntity.setFullName(fileFullPath);
            fileEntity.setSize(size);
            return fileEntity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 拼接文件访问地址
     *
     * @param fullPath 文件全路径
     * @return 访问地址
     */
    private String getFileURL(String fullPath) {
        var url = uploadFileHost + fullPath;
        return url.replaceAll("//", "/");
    }

}

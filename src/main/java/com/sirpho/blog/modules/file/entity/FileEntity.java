package com.sirpho.blog.modules.file.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sirpho.blog.common.utils.BaseEntity;
import lombok.*;


/**
 * 文件类
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("file")
public class FileEntity extends BaseEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 路径
     */
    private String path;

    /**
     * 文件名
     */
    private String name;

    /**
     * 后缀名
     */
    private String suffix;

    /**
     * 完整文件名
     */
    private String fullName;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 访问地址
     */
    @TableField(exist = false)
    private String url;
}

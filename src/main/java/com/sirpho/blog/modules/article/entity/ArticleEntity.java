package com.sirpho.blog.modules.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sirpho.blog.common.utils.BaseEntity;
import lombok.*;

import java.util.ArrayList;

/**
 * 用户实体类
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("article")
public class ArticleEntity extends BaseEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 内容
     */
    private String content;

    /**
     * 字数
     */
    private Integer wordCount;

    /**
     * 作者
     */
    private String author;

    /**
     * 是否发布 Y:是 N:否
     */
    private String published;

    /**
     * 创作类型 原创 | 转载
     */
    private String creationType;

    /**
     * 转载的来源地址
     */
    private String source;

    /**
     * 标签
     */
    @TableField(exist = false)
    private ArrayList<String> tags;

    /**
     * 分类
     */
    @TableField(exist = false)
    private ArrayList<String> categories;
}

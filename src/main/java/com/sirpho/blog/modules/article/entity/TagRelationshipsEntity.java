package com.sirpho.blog.modules.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;


/**
 * 文章-标签关联关系类
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("tag_relationships")
public class TagRelationshipsEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 文章主键
     */
    private String articleId;

    /**
     * 标签主键
     */
    private String tagId;

    /**
     * 排序
     */
    private Integer sortOrder;
}

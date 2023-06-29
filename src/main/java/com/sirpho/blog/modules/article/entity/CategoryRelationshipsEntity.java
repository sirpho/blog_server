package com.sirpho.blog.modules.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;


/**
 * 文章-类别关联关系类
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("category_relationships")
public class CategoryRelationshipsEntity {

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
     * 类别主键
     */
    private String categoryId;

    /**
     * 排序
     */
    private Integer sortOrder;
}

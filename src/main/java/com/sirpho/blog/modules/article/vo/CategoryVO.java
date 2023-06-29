package com.sirpho.blog.modules.article.vo;

import com.sirpho.blog.modules.article.entity.CategoryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryVO extends CategoryEntity {
    /**
     * 文章数量
     */
    private Integer count;
}

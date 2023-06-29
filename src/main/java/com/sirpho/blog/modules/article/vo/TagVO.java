package com.sirpho.blog.modules.article.vo;

import com.sirpho.blog.modules.article.entity.TagEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TagVO extends TagEntity {
    /**
     * 文章数量
     */
    private Integer count;
}

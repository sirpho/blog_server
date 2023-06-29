package com.sirpho.blog.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("site")
public class SiteEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1686297045055L;

    /**
     * 头像
     */
    private String avatar;
    /**
     * 签名
     */
    private String motto;
    /**
     * github
     */
    private String github;
    /**
     * email
     */
    private String email;

    /**
     * 文章数
     */
    @TableField(exist = false)
    private Long articlesCount;
}

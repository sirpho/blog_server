package com.sirpho.blog.common.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;
import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT_UPDATE;

@Data
public class BaseEntity  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1686297045055L;

    /**
     * 创建时间
     */
    @TableField(fill = INSERT)
    private Date createTime;

    /**
     * 上次更新时间
     */
    @TableField(fill = INSERT_UPDATE)
    private Date lastUpdateTime;

    /**
     * 删除状态，Y：删除 N：未删除
     */
    private String deleted;
}

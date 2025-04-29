package com.example.java01jwt.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 资源（接口）表
 *
 * @TableName ums_resource
 */
@Data
@Schema(title = "资源（接口）实体类")
@TableName(value = "ums_resource")
public class Resource implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @Schema(title = "")
    private Long id;

    /**
     * 资源分类id
     */
    @Schema(title = "资源分类id")
    private Long categoryId;

    /**
     * 资源名称
     */
    @Schema(title = "资源名称")
    private String name;

    /**
     * 资源（接口）URL
     */
    @Schema(title = "资源（接口）URL")
    private String url;

    /**
     * 描述
     */
    @Schema(title = "描述")
    private String description;

    /**
     * 创建时间
     */
    @Schema(title = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    @Schema(title = "最后更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
package com.example.java01jwt.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色表
 *
 * @TableName ums_role
 */
@Data
@Schema(title = "角色实体类")
@TableName(value = "ums_role")
public class Role implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(title = "主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 名称
     */
    @Schema(title = "名称")
    private String name;

    /**
     * 描述
     */
    @Schema(title = "描述")
    private String description;

    /**
     * 启用状态 0-禁用 1-启用
     */
    @Schema(title = "启用状态 0-禁用 1-启用")
    private Integer status;

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
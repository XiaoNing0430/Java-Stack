package com.example.java01jwt.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 菜单表
 *
 * @TableName ums_menu
 */
@Data
@Schema(title = "菜单实体类")
@TableName(value = "ums_menu")
public class Menu implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(title = "主键")
    private Long id;

    /**
     * 父级菜单id
     */
    @Schema(title = "父级菜单id")
    private Long parentId;

    /**
     * 菜单名称
     */
    @Schema(title = "菜单名称")
    private String title;

    /**
     * 菜单等级
     */
    @Schema(title = "菜单等级")
    private Integer level;

    /**
     * 菜单排序
     */
    @Schema(title = "菜单排序")
    private Integer sort;

    /**
     * 前端路由
     */
    @Schema(title = "前端路由")
    private String route;

    /**
     * 前端图标
     */
    @Schema(title = "前端图标")
    private String icon;

    /**
     * 前端是否隐藏
     */
    @Schema(title = "前端是否隐藏")
    private Integer hidden;

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
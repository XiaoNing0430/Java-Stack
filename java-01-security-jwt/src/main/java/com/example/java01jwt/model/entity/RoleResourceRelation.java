package com.example.java01jwt.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色资源关系表
 *
 * @TableName ums_role_resource_relation
 */
@Data
@Schema(title = "角色资源关系实体类")
@TableName(value = "ums_role_resource_relation")
public class RoleResourceRelation implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(title = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色id
     */
    @Schema(title = "角色id")
    private Long roleId;

    /**
     * 资源id
     */
    @Schema(title = "资源id")
    private Long resourceId;
}
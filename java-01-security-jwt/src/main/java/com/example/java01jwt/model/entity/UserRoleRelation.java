package com.example.java01jwt.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户角色关联表
 *
 * @TableName ums_user_role_relation
 */
@Data
@Schema(title = "用户角色关联实体类")
@TableName(value = "ums_user_role_relation")
public class UserRoleRelation implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(title = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @Schema(title = "用户id")
    private Long userId;

    /**
     * 角色id
     */
    @Schema(title = "角色id")
    private Long roleId;
}
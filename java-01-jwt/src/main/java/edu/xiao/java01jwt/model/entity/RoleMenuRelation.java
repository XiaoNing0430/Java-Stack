package edu.xiao.java01jwt.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色菜单关系表
 *
 * @TableName ums_role_menu_relation
 */
@Data
@Schema(title = "角色菜单关系实体类")
@TableName(value = "ums_role_menu_relation")
public class RoleMenuRelation implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @Schema(title = "主键")
    private Long id;

    /**
     * 角色id
     */
    @Schema(title = "角色id")
    private Long roleId;

    /**
     * 菜单id
     */
    @Schema(title = "菜单id")
    private Long menuId;
}
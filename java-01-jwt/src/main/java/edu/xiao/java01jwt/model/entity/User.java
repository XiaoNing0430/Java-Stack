package edu.xiao.java01jwt.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户表
 *
 * @TableName ums_user
 */
@Data
@Schema(title = "用户实体类")
@TableName(value = "ums_user")
public class User implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(title = "主键")
    private Long id;

    /**
     * 用户名
     */
    @Schema(title = "用户名")
    private String username;

    /**
     * 密码
     */
    @Schema(title = "密码")
    private String password;

    /**
     * 头像
     */
    @Schema(title = "头像")
    private String icon;

    /**
     * 邮箱
     */
    @Schema(title = "邮箱")
    private String email;

    /**
     * 昵称
     */
    @Schema(title = "昵称")
    private String nickName;

    /**
     * 账号启用状态 0-禁用 1-启用
     */
    @Schema(title = "账号启用状态 0-禁用 1-启用")
    private Integer status;

    /**
     * 注册时间
     */
    @Schema(title = "注册时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    @Schema(title = "最后更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
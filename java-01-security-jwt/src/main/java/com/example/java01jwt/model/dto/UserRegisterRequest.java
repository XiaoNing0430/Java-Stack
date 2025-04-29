package com.example.java01jwt.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaoning
 * @since 2025/4/25
 */
@Data
@Schema(title = "用户实体类")
@TableName(value = "ums_user")
public class UserRegisterRequest implements Serializable {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Schema(title = "用户名")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
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

}

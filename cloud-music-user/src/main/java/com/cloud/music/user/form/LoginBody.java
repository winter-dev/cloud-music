package com.cloud.music.user.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 用户登录对象
 * 
 * @author cgf
 */
@Data
public class LoginBody
{
    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    /**
     * 用户密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;
}

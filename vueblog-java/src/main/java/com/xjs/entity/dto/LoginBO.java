package com.xjs.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @创建人 xjs
 * @类描述
 * @创建时间 2020/8/5 0005
 */
@Data
public class LoginBO implements Serializable {
    private static final long serialVersionUID = -4137043829561613973L;

    @NotBlank(message = "用户名不能为空！")
    private String username;

    @NotBlank(message = "密码不能为空！")
    private String password;

}

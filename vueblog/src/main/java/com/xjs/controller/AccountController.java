package com.xjs.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjs.common.lang.AjaxMessage;
import com.xjs.common.utils.JwtUtil;
import com.xjs.entity.User;
import com.xjs.entity.dto.LoginBO;
import com.xjs.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @创建人 xjs
 * @类描述
 * @创建时间 2020/8/5 0005
 */
@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("login")
    public AjaxMessage login(@RequestBody @Validated LoginBO loginBO, HttpServletResponse response){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginBO.getUsername()));
        Assert.notNull(user,"用户名不存在！");

        if(!user.getPassword().equals(SecureUtil.md5(loginBO.getPassword()))){
            return AjaxMessage.error("密码不正确!");
        }

        String token = jwtUtil.generateToken(user.getUsername(),user.getPassword());
        response.setHeader("Authorization",token);
        response.setHeader("Access-control-Expose-Headers","Authorization");

        return AjaxMessage.success().data(user);
    }

    @RequiresAuthentication
    @GetMapping("logout")
    public AjaxMessage logout(){
        SecurityUtils.getSubject().logout();
        return AjaxMessage.success();
    }


}

package com.xjs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjs.common.lang.AjaxCode;
import com.xjs.common.lang.AjaxMessage;
import com.xjs.common.utils.JwtUtil;
import com.xjs.entity.User;
import com.xjs.service.UserService;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiejs
 * @since 2020-08-05
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 空接口用来校验token
     * @return
     */
    @GetMapping("tokenAuth")
    public AjaxMessage tokenAuth(){

        return AjaxMessage.success();
    }



}

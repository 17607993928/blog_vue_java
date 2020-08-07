package com.xjs.shrio;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjs.common.utils.JwtUtil;
import com.xjs.entity.User;
import com.xjs.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.shiro.realm.AuthorizingRealm;

/**
 * @创建人 xjs
 * @类描述 登录或者权限校验的逻辑
 * @创建时间 2020/8/5 0005
 */
@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    /**
     * shiro默认supports的是UsernamePasswordToken，而我们现在采用了jwt的方式，所以这里我们自定义一个JwtToken，来完成shiro的supports方法。
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * doGetAuthenticationInfo登录认证这个方法，
     * 可以看到我们通过jwt获取到用户信息，判断用户的状态，最后异常就抛出对应的异常信息，否者封装成SimpleAuthenticationInfo返回给shiro。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;

        //从token中获取到用户账号密码
        String userName = jwtUtil.getClaimByToken((String) jwtToken.getPrincipal()).getId();
        String passWord = jwtUtil.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

//        String[] info = jwtUtil.getInfo(jwtUtil.getClaimByToken((String) jwtToken.getPrincipal()));

        User user = userService.getOne(new QueryWrapper<User>().eq("username", userName).eq("password", passWord));
        if (user == null) {
            throw new UnknownAccountException("账户不存在!");
        }
        if (user.getStatus() == -1) {
            throw new LockedAccountException("账户已被锁定！");
        }

        AccountProfile profile = new AccountProfile();
        BeanUtils.copyProperties(user, profile);

        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }
}

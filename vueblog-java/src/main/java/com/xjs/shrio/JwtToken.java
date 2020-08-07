package com.xjs.shrio;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @创建人 xjs
 * @类描述 获取twt
 * @创建时间 2020/8/5 0005
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

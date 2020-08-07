package com.xjs.common.utils;

import com.xjs.shrio.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @创建人 xjs
 * @类描述 shiro工具
 * @创建时间 2020/8/6 0006
 */
public class ShiroUtil {
    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}

package com.xjs.service.impl;

import com.xjs.entity.User;
import com.xjs.mapper.UserMapper;
import com.xjs.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiejs
 * @since 2020-08-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

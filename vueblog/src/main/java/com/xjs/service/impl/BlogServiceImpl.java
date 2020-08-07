package com.xjs.service.impl;

import com.xjs.entity.Blog;
import com.xjs.mapper.BlogMapper;
import com.xjs.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {


}

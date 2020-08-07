package com.xjs.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjs.common.lang.AjaxMessage;
import com.xjs.common.utils.ShiroUtil;
import com.xjs.entity.Blog;
import com.xjs.service.BlogService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiejs
 * @since 2020-08-05
 */
@RestController
@RequestMapping("blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping
    public AjaxMessage page(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "5")Integer size) {
        Page page = new Page<>(currentPage, size);

        IPage iPage = blogService.getBaseMapper().selectPage(page, new QueryWrapper<Blog>().orderByDesc("created"));

//        IPage iPage = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));

        return AjaxMessage.success().data(iPage);
    }

    @GetMapping("{id}")
    public AjaxMessage detail(@PathVariable(name = "id")Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客已经被删除！");
        return AjaxMessage.success().data(blog);
    }

    @RequiresAuthentication
    @PostMapping("edit")
    public AjaxMessage edit(@Validated @RequestBody Blog blog){
        Blog temp=null;
        if(blog.getId()!=null){
            temp= blogService.getById(blog.getId());

            //只能编辑自己的文章
            Assert.isTrue(temp.getUserId().longValue()== ShiroUtil.getProfile().getId().longValue(),"没有权限编辑！");
        }else{
            temp=new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtils.copyProperties(blog,temp,"id","userId","created","status");
        blogService.saveOrUpdate(temp);

        return AjaxMessage.success();
    }

}

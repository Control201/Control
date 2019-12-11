package com.blog.service.impl;

import com.blog.dao.BloggerDao;
import com.blog.entity.Blogger;
import com.blog.service.BloggerService;
import com.blog.util.Const;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {
    @Resource
    private BloggerDao bloggerDao;
    @Override
    public Blogger getByUserName(String userName) {
        return bloggerDao.getByUserName(userName);
    }

    public Integer update(Blogger blogger){
        //加载内存
        SecurityUtils.getSubject().getSession().setAttribute(Const.CURRENT_USER,blogger);
        return bloggerDao.update(blogger);
    }

    @Override
    public Blogger find() {
        return bloggerDao.find();
    }

}

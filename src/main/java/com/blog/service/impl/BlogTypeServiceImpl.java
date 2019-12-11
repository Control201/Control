package com.blog.service.impl;

import com.blog.dao.BlogTypeDao;
import com.blog.dao.BloggerDao;
import com.blog.entity.BlogType;
import com.blog.service.BlogTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service(value ="blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {

    @Resource
    private BlogTypeDao blogTypeDao;

    @Override
    public List<BlogType> countList() {
        return blogTypeDao.countList();
    }

    @Override
    public BlogType findById(Integer id) {
        return blogTypeDao.findById(id);
    }

    @Override
    public List<BlogType> list(Map<String, Object> paramMap) {
        return blogTypeDao.list(paramMap);
    }

    @Override
    public Long getTotal(Map<String, Object> paramMap) {
        return blogTypeDao.getTotal(paramMap);
    }

    @Override
    public Integer add(BlogType blogType) {
        return blogTypeDao.add(blogType);
    }

    @Override
    public Integer update(BlogType blogType) {
        return blogTypeDao.update(blogType);
    }

    @Override
    public Integer delete(Integer id) {
        return blogTypeDao.delete(id);
    }
}

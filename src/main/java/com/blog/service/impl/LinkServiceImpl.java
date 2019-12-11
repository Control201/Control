package com.blog.service.impl;

import com.blog.dao.LinkDao;
import com.blog.entity.Link;
import com.blog.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 友情链接
 */
@Service(value = "linkService")
public class LinkServiceImpl implements LinkService {
    @Resource
    private LinkDao linkDao;

    @Override
    public Link findById(Integer id) {
        return linkDao.findById(id);
    }

    @Override
    public List<Link> list(Map<String, Object> paramMap) {
        return linkDao.list(paramMap);
    }

    @Override
    public Long getTotal(Map<String, Object> paramMap) {
        return linkDao.getTotal(paramMap);
    }

    @Override
    public Integer add(Link link) {
        return linkDao.add(link);
    }

    @Override
    public Integer update(Link link) {
        return linkDao.update(link);
    }

    @Override
    public Integer delete(Integer id) {
        return linkDao.delete(id);
    }
}

package com.blog.service;

import com.blog.entity.Link;

import java.util.List;
import java.util.Map;

public interface LinkService {

    /**
     * 根据id查询一条友情链接
     * @param id
     * @return
     */
    public Link findById(Integer id);

    /**
     * 根据不固定参数查询一条友情链接
     * @param paramMap
     * @return
     */
    public List<Link> list(Map<String,Object> paramMap);

    /**
     * 根据不固定参数查询友情链接
     * @param paramMap
     * @return
     */
    public Long getTotal(Map<String,Object> paramMap);

    /**
     * 添加一条友情链接
     * @param link
     * @return
     */
    public Integer add(Link link);
    /**
     * 修改一条友情链接
     * @param link
     * @return
     */
    public Integer update(Link link);

    /**
     * 删除一条友情链接
     * @param id
     * @return
     */
    public Integer delete(Integer id);
}

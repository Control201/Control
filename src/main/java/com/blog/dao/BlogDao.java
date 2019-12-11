package com.blog.dao;

import com.blog.entity.Blog;

import java.util.List;
import java.util.Map;

/**
 * 博客接口
 */
public interface BlogDao {
    /**
     * 无惨查询博客列表
     * @return
     */
    public List<Blog> countList();

    /**
     * 有参查询博客列表
     * @param map
     * @return
     */
    public List<Blog> list(Map<String,Object> map);

    /**
     * 获取博客数量
     * @param map
     * @return
     */
    public Long getTotal(Map<String,Object> map);

    /**
     * 根据主键查询博客信息
     * @param id
     * @return
     */
    public Blog findById(Integer id);

    /**
     * 添加博客一条
     * @return
     */
    public Integer add(Blog blog);

    /**
     * 修改一条博客
     */
    public Integer update(Blog blog);

    /**
     * id删除博客
     * @param id
     * @return
     */
    public Integer delete(Integer id);

    /**
     * 根据类型查询博客数量
     * @param typeId
     * @return
     */
    public Integer getBlogByTypeId(Integer typeId);


    /**
     * 上一篇
     * @param id
     * @return
     */
    public Blog getLastBlog(Integer id);

    /**
     * 下一篇
     * @param id
     * @return
     */
    public Blog getNextBlog(Integer id);

}

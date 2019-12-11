package com.blog.dao;

import com.blog.entity.BlogType;

import java.util.List;
import java.util.Map;


public interface BlogTypeDao {
    /**
     * 查询所有博客类型
     * @return
     */
    public List<BlogType> countList();

    /**
     * 根据id查询一条博客类型
     * @param id
     * @return
     */
    public BlogType findById(Integer id);

    /**
     * 根据不固定参数查询一条博客类型
     * @param paramMap
     * @return
     */
    public List<BlogType> list(Map<String,Object> paramMap);

    /**
     * 根据不固定参数查询博客类型
     * @param paramMap
     * @return
     */
    public Long getTotal(Map<String,Object> paramMap);

    /**
     * 添加一条博客类型
     * @param blogType
     * @return
     */
    public Integer add(BlogType blogType);
    /**
     * 修改一条博客类型
     * @param blogType
     * @return
     */
    public Integer update(BlogType blogType);

    /**
     * 删除一条博客类型
     * @param id
     * @return
     */
    public Integer delete(Integer id);
}

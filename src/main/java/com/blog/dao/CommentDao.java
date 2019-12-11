package com.blog.dao;

import com.blog.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * 评论
 */
public interface CommentDao {
    /**
     * 添加一条评论
     * @param comment
     * @return
     */
    public Integer add(Comment comment);

    /**
     * 更新一条评论
     * @param comment
     * @return
     */
    public Integer update(Comment comment);

    /**
     * 查询所有评论
     * @param map
     * @return
     */
    public List<Comment> list(Map<String,Object> map);

    /**
     * 评论总数
     * @param map
     * @return
     */
    public Long getTotal(Map<String,Object> map);
    /**
     * 删除评论
     */
    public Integer delete(Integer id);
    /**
     * 根据博客id删除评论
     */
    public Integer deleterByBlogId(Integer blogId);

}

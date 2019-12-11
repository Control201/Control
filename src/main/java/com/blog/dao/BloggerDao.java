package com.blog.dao;

import com.blog.entity.Blogger;
import org.apache.ibatis.annotations.Param;

public interface BloggerDao {
    /**
     * 根据用户名---查询用户信息
     * @param paramString
     * @return
     */
    public Blogger getByUserName(@Param("userName") String paramString);

    /**
     * 更新博主对象
     * @param blogger
     * @return
     */
    public Integer update(Blogger blogger);


    /**
     * 查找博客信息
     */
    public Blogger find();




}

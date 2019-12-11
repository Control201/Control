package com.blog.service;

import com.blog.entity.Blogger;

public interface BloggerService {
    /**
     * 根据用户名查询账户
     * @param userName
     * @return
     */
    public Blogger getByUserName(String userName);

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

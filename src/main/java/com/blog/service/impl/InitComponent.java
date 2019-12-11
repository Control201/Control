package com.blog.service.impl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.blog.entity.Blog;
import com.blog.entity.BlogType;
import com.blog.entity.Blogger;
import com.blog.entity.Link;
import com.blog.service.BlogService;
import com.blog.service.BlogTypeService;
import com.blog.service.BloggerService;
import com.blog.service.LinkService;
import com.blog.util.Const;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 提提前初始化数据
 */
@Component
public class InitComponent implements ServletContextListener, ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //aop提前加载bean  可以缓存到内存中  减少多次查询  浪费资源
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();
        //博客类型
        BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
        List<BlogType> blogTypeList = blogTypeService.countList();
        application.setAttribute(Const.BLOG_TYPE_COUNT_LIST, blogTypeList);
        //博主信息
        BloggerService bloggerService = (BloggerService) applicationContext.getBean("bloggerService");
        Blogger blogger = bloggerService.find();
        blogger.setPassword(null);//用户安全---清空密码信息
        application.setAttribute(Const.BLOGGER, blogger);
        //博客数量按年月日分类
        BlogService blogService = (BlogService) applicationContext.getBean("blogService");
        List<Blog> blogs = blogService.countList();
        application.setAttribute(Const.BLOG_COUNT_LIST, blogs);
        //友情链接
		LinkService linkService = (LinkService) applicationContext.getBean("linkService");
		List<Link> list = linkService.list(null);
		application.setAttribute(Const.Link_LIST,list);
	}
    public void contextDestroyed(ServletContextEvent sce) {
    }
}

package com.blog.controller.admin;

import com.blog.entity.Blog;
import com.blog.entity.BlogType;
import com.blog.entity.Blogger;
import com.blog.entity.Link;
import com.blog.service.BlogService;
import com.blog.service.BlogTypeService;
import com.blog.service.BloggerService;
import com.blog.service.LinkService;
import com.blog.util.Const;
import com.blog.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {

    @Resource
    private BlogTypeService blogTypeService;

    @Resource
    private BlogService blogService;
    @Resource
    private LinkService linkService;
    @Resource
    private BloggerService bloggerService;


    /**
     * 刷新系统缓存
     */
    @RequestMapping({"/refreshSystem"})
    public String refreshSystem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取上下文
        ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        List<BlogType> list = blogTypeService.countList();
        servletContext.setAttribute(Const.BLOG_TYPE_COUNT_LIST, list);

        //博主信息
        Blogger blogger = bloggerService.find();
        blogger.setPassword(null);//用户安全---清空密码信息
        servletContext.setAttribute(Const.BLOGGER, blogger);
        //博客数量按年月日分类
        List<Blog> blogs = blogService.countList();
        servletContext.setAttribute(Const.BLOG_COUNT_LIST, blogs);
        //友情链接
        List<Link> linkList = linkService.list(null);
        servletContext.setAttribute(Const.Link_LIST,linkList);

        JSONObject result = new JSONObject();
        result.put("success", Boolean.valueOf(true));
        ResponseUtil.write(response, result);
        return null;
    }
}

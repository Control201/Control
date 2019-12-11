package com.blog.controller;

import com.blog.entity.Blog;
import com.blog.lucene.BlogIndex;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import com.blog.util.StringUtil;
import com.github.pagehelper.Page;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/blog"})
public class BlogController {
    @Resource
    private BlogService blogService;

    @Resource
    private CommentService commentService;

    private BlogIndex blogIndex = new BlogIndex();

    @RequestMapping({"/articles/{id}"})
    public ModelAndView details(@PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        //根据主键查询博客信息
        Blog blog = blogService.findById(id);
        mav.addObject("blog", blog);
        //阅读数加1
        blog.setClickHit(blog.getClickHit() + 1);
        blogService.update(blog);

        mav.addObject("mainPage", "foreground/blog/view.jsp");
        mav.addObject("pageTitle", blog.getTitle() + "_大众博客系统");
        //上一篇下一篇
        mav.addObject("pageCode", UpAndDownPageCode(blogService.getLastBlog(id), blogService.getNextBlog(id), request.getServletContext().getContextPath()));
        //查询评论
        Map<String, Object> map = new HashMap<>();
        map.put("blogId", blog.getId());
        map.put("state", 1);
        mav.addObject("commentList", commentService.list(map));

        //处理关键字
        String keyWord = blog.getKeyWord();
        if (StringUtil.isEmpty(keyWord)) {
            mav.addObject("keyWords", null);
        } else {
            String[] arr = keyWord.split(" ");
            mav.addObject("keyWords", StringUtil.fileterWhite(Arrays.asList(arr)));
        }
        mav.setViewName("index");
        return mav;
    }

    /**
     * 上一篇下一篇
     */
    private String UpAndDownPageCode(Blog lastBlog, Blog nextBlog, String projectContext) {
        StringBuffer pageCode = new StringBuffer();
        if (lastBlog == null || lastBlog.getId() == null) {
            pageCode.append("<P>上一篇：没有了</p>");
        } else {
            pageCode.append("<p>上一篇：<a href = '" + projectContext + "/blog/articles/" + lastBlog.getId() + ".html'>" + lastBlog.getTitle() + "</a></p>");
        }
        if (nextBlog == null || nextBlog.getId() == null) {
            pageCode.append("<P>下一篇：没有了</p>");
        } else {
            pageCode.append("<p>下一篇：<a href = '" + projectContext + "/blog/articles/" + nextBlog.getId() + ".html'>" + nextBlog.getTitle() + "</a></p>");
        }
        return pageCode.toString();
    }

    /**
     * 根据关键字查询
     */
    @RequestMapping({"/q"})
    public ModelAndView q(@RequestParam(value = "q", required = false) String q,
                          @RequestParam(value = "page", required = false) String page,
                          HttpServletRequest request) throws ParseException, InvalidTokenOffsetsException, IOException {
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("mainPage", "foreground/blog/result.jsp");
        //lucene
        List<Blog> blogList = blogIndex.searchBlog(q.trim());

        int toIndex = 0;
        if (blogList.size()>=Integer.parseInt(page)*10){
            toIndex = Integer.parseInt(page)*10;
        }else {
            toIndex = blogList.size();
        }

        mav.addObject("blogList",blogList.subList((Integer.parseInt(page)-1)*10,toIndex));

        mav.addObject("pageCode",genUpAndDownPageCode(Integer.parseInt(page),blogList.size(),q,10,request.getServletContext().getContextPath()));
        mav.addObject("q", q);
        mav.addObject("resultTotal", blogList.size());
        mav.addObject("pageTitle", "搜索关键字" + q + "结果页面_个人博客");
        mav.setViewName("index");
        return mav;
    }


    /**
     * 查询结果翻页功能
     */

    private String genUpAndDownPageCode(int page, int totalNum, String q, int pageSize, String projectContext) {
        StringBuffer pageCode = new StringBuffer();
        //总页数
        int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        if (totalPage == 0) {
            return "";
        }
        pageCode.append("<nav>");
        pageCode.append("<ur class='pager'>");
        if (page > 1) {//当前大于1
            pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page" + (page - 1) + "&q=" + q + "'>上一页</a></li>");
        } else {
            pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
        }
        if (page < totalPage) {//不是最后一页
            pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page" + (page + 1) + "&q=" + q + "'>下一页</a></li>");
        } else {
            pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
        }
        pageCode.append("</nav>");
        pageCode.append("</ur>");
        return pageCode.toString();
    }
}

package com.blog.controller;

import com.blog.entity.Blog;
import com.blog.entity.PageBean;
import com.blog.service.BlogService;
import com.blog.util.PageUtil;
import com.blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 首页
 */
@Controller
public class IndexController {
    @Resource
    private BlogService blogService;


    @RequestMapping({"/index"})
    public ModelAndView index(@RequestParam(value = "page", required = false) String page,
                              @RequestParam(value = "typeId", required = false) String typeId,
                              @RequestParam(value = "releaseDateStr", required = false) String releaseDateStr,
                              HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "大众博客");
        //如果为第一页
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
        HashMap<String, Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("typeId", typeId);
        map.put("releaseDateStr", releaseDateStr);

        List<Blog> list = blogService.list(map);

        StringBuffer param = new StringBuffer();
        if (StringUtil.isNotEmpty(typeId)) {
            param.append("typeId=" + typeId + "&");
        }
        if (StringUtil.isNotEmpty(releaseDateStr)) {
            param.append("releaseDateStr=" + releaseDateStr + "&");
        }
        mav.addObject("mainPage","foreground/blog/list.jsp");
        String pagCode = PageUtil.genPagination(request.getContextPath() + "/index.html", blogService.getTotal(map), Integer.parseInt(page), 10, param.toString());
        mav.addObject("pageCode", pagCode);
        mav.addObject("blogList", list);
        return mav;
    }

}

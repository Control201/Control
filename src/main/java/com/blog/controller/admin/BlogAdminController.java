package com.blog.controller.admin;

import com.blog.entity.Blog;
import com.blog.entity.PageBean;
import com.blog.lucene.BlogIndex;
import com.blog.service.BlogService;
import com.blog.util.DateJsonValueProcessor;
import com.blog.util.ResponseUtil;
import com.blog.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonValueProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/admin/blog"})
public class BlogAdminController {
    @Resource
    public BlogService blogService;
    private BlogIndex blogIndex =new BlogIndex();

    /**
     * 保存博客
     *
     * @param blog
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping({"/save.do"})
    public String save(Blog blog, HttpServletResponse response) throws IOException {
        Integer resultTotal = 0;
        //修改blog
        if (blog.getId() != null) {
            resultTotal = blogService.update(blog);
            blogIndex.updateIndex(blog);
        } else {
            //添加blog
            resultTotal = blogService.add(blog);
            blogIndex.addIndex(blog);
        }
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", Boolean.valueOf(true));
        } else {
            result.put("success", Boolean.valueOf(false));
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 查询博客信息列表
     */
    @RequestMapping({"/list.do"})
    public String list(@RequestParam(value = "page", required = false) String page,
                       @RequestParam(value = "rows", required = false) String rows,
                       Blog blog, HttpServletResponse response) throws IOException {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("title", StringUtil.formatLike(blog.getTitle()));
        //分页查询博客信息列表
        List<Blog> list = blogService.list(map);
        //获取共有多少条博客信息
        Long total = blogService.getTotal(map);
        //封装到json
        JSONObject result = new JSONObject();
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));

        JSONArray jsonArray = JSONArray.fromObject(list, config);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 根据id查询博客信息
     */
    @RequestMapping({"findById.do"})
    public String findById(@RequestParam("id") String id, HttpServletResponse response) throws IOException {
        Blog blog = blogService.findById(Integer.parseInt(id));
        JSONObject jsonObject = JSONObject.fromObject(blog);
        ResponseUtil.write(response, jsonObject);
        return null;
    }

    /**
     * 删除博客根据id
     */
    @RequestMapping({"delete.do"})
    public String delete(@RequestParam("ids") String ids, HttpServletResponse response) throws IOException {
        String[] strings = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < strings.length; i++) {
            blogService.delete(Integer.parseInt(strings[i]));
            blogIndex.deleteIndex(strings[i]);
        }
        result.put("success", Boolean.valueOf(true));
        ResponseUtil.write(response, result);
        return null;
    }
}

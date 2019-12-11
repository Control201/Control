package com.blog.controller.admin;

import com.blog.entity.BlogType;
import com.blog.entity.PageBean;
import com.blog.service.BlogService;
import com.blog.service.BlogTypeService;

import com.blog.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博客类型管理
 */
@Controller
@RequestMapping({"/admin/blogType"})
public class BlogTypeAdminController {

    /**
     * 博客类型列表
     */
    @Resource
    private BlogTypeService blogTypeService;

    @Resource
    private BlogService blogService;

    /**
     * 博客类型列表
     *
     * @return
     */
    @RequestMapping({"/list"})
    public String list(@RequestParam(value = "page", required = false) String page,
                       @RequestParam(value = "rows", required = false) String rows,
                       HttpServletResponse response) throws IOException {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        //查询博客类型列表
        List<BlogType> blogTypeList = blogTypeService.list(map);
        //查询总共有多少条数据
        Long total = blogTypeService.getTotal(map);
        //将数据写入response
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(blogTypeList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 保存和更新博客类别信息
     *
     * @param blogType
     * @param response
     * @return
     */
    @RequestMapping({"/save"})
    public String save(BlogType blogType, HttpServletResponse response) throws IOException {
        int resultTotal = 0;
        //添加
        if (blogType.getId() == null) {
            resultTotal = blogTypeService.add(blogType).intValue();
        } else {
            resultTotal = blogTypeService.update(blogType).intValue();
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
     * 删除博客类型
     */
    @RequestMapping("/delete.do")
    public String delete(@RequestParam("ids") String ids, HttpServletResponse response) throws IOException {
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
        int sum = blogService.getBlogByTypeId(Integer.valueOf(idsStr[i]));
            if (sum > 0) {
                result.put("exist", "博客类别下有博客，不能删除");
            } else {
                blogTypeService.delete(Integer.valueOf(idsStr[i]));
            }
        }
        result.put("success", Boolean.valueOf(true));
        ResponseUtil.write(response, result);
        return null;
    }

}

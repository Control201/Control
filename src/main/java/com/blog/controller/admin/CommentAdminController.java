package com.blog.controller.admin;

import com.blog.entity.Comment;
import com.blog.entity.PageBean;
import com.blog.service.CommentService;
import com.blog.util.DateJsonValueProcessor;
import com.blog.util.ResponseUtil;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
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

/**
 * 评论管理
 */
@Controller
@RequestMapping({"/admin/comment"})
public class CommentAdminController {

    @Resource
    private CommentService commentService;

    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", required = false) String page,
                       @RequestParam(value = "rows", required = false) String rows,
                       @RequestParam(value = "state", required = false) String state,
                       HttpServletResponse response) throws IOException {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        //加载翻页，状态参数
        Map<String, Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("state", state);
        //查询评论列表
        List<Comment> commentList = commentService.list(map);
        //查询评论列表数字
        Long total = commentService.getTotal(map);
        //放入json
        JSONObject result = new JSONObject();
        JsonConfig config = new JsonConfig();
        //时间参数类型转换
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(commentList, config);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 删除评论
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam("ids") String ids, HttpServletResponse response) throws IOException {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            commentService.delete(Integer.valueOf(idsStr[i]));
        }
        JSONObject result = new JSONObject();
        result.put("success", Boolean.valueOf(true));
        ResponseUtil.write(response, result);
        return null;
    }
    /**
     * 审核评论
     */
    @RequestMapping("/review")
    public String review(@RequestParam("ids") String ids,@RequestParam("state")String state, HttpServletResponse response) throws IOException {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            Comment comment = new Comment();
            comment.setId(Integer.valueOf(idsStr[i]));
            comment.setState(Integer.parseInt(state));
            commentService.update(comment);
        }
        JSONObject result = new JSONObject();
        result.put("success", Boolean.valueOf(true));
        ResponseUtil.write(response, result);
        return null;
    }
}

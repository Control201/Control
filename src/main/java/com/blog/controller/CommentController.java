package com.blog.controller;

import com.blog.entity.Blog;
import com.blog.entity.Comment;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import com.blog.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 前端提交评论
 */
@Service
@RequestMapping({"/comment"})
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private BlogService blogService;

    /**
     * 提交评论
     *
     * @param comment
     * @param imageCode
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping({"/save"})
    public String save(Comment comment, @RequestParam("imageCode") String imageCode,
                       HttpServletRequest request, HttpServletResponse response,
                       HttpSession session) throws IOException {
        String sRand = (String) session.getAttribute("sRand");
        JSONObject result = new JSONObject();

        int resultTotal = 0;
        if (!imageCode.equals(sRand)) {
            result.put("success", Boolean.FALSE);
            result.put("erroInfo", "验证码错误！");
        } else {
            String userIp = request.getRemoteAddr();
            comment.setUserIp(userIp);
            if (comment.getId() == null) {
                resultTotal = commentService.add(comment);

                //对应评论数加1
                Blog blog = blogService.findById(comment.getBlog().getId());
                blog.setReplyHit(blog.getReplyHit() + 1);
                blogService.update(blog);
            }
        }

        if (resultTotal > 0) {
            result.put("success", Boolean.TRUE);
        } else {
            result.put("success", Boolean.FALSE);
        }
        ResponseUtil.write(response, result);
        return null;
    }
}

package com.blog.controller.admin;

import com.blog.entity.Blogger;
import com.blog.service.BlogService;
import com.blog.service.BloggerService;
import com.blog.util.Const;
import com.blog.util.CryptographyUtil;
import com.blog.util.DateUtil;
import com.blog.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping({"/admin/blogger"})
public class BloggerAdminController {

    @Resource
    private BloggerService bloggerService;


    @RequestMapping({"/save"})
    public String save(@RequestParam("imageFile") MultipartFile imageFile,
                       Blogger blogger, HttpServletRequest request, HttpServletResponse
                               response) throws IOException {
        if (!imageFile.isEmpty()) {
            //给文件命唯一名
            String filePath = request.getServletContext().getRealPath("/");
            String imageName = DateUtil.getCurrentDateStr() + "." + imageFile.getOriginalFilename().split("\\.")[1];
            imageFile.transferTo(new File(filePath + "static/userImages/" + imageName));
            ;
            blogger.setImageName(imageName);
        }
        int resultTotal = bloggerService.update(blogger);
        //测试。。。
        StringBuffer result = new StringBuffer();
        if (resultTotal > 0) {
            result.append("<script language='javascript'>alert('修改成功');</script>");
        } else {
            result.append("<script language='javascript'>alert('修改失败');</script>");
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * json格式获取博主
     *
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping({"/find"})
    public String find(HttpServletResponse response) throws IOException {
        Blogger blogger = (Blogger) SecurityUtils.getSubject().getSession().getAttribute(Const.CURRENT_USER);
        JSONObject jsonObject = JSONObject.fromObject(blogger);
        ResponseUtil.write(response, jsonObject);
        return null;
    }

    /**
     * 修改密码
     *
     * @return
     * @throws IOException
     */
    @RequestMapping({"/modifyPassword"})
    public String modifyPassword(@RequestParam("id") String id, @RequestParam("newPassword") String newPassword, HttpServletResponse response) throws IOException {
        Blogger blogger = new Blogger();
        blogger.setId(Integer.valueOf(id));
        blogger.setPassword(CryptographyUtil.md5(newPassword, "salt"));
        Integer resultTotal = bloggerService.update(blogger);
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
     * 退出登录
     * @return
     */
    @RequestMapping({"/logout"})
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/login.jsp";
    }
}

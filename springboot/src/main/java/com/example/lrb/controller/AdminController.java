package com.example.lrb.controller;

import com.example.lrb.pojo.Node;
import com.example.lrb.pojo.User;
import com.example.lrb.service.AdminService;
import com.example.lrb.service.NodeService;
import com.example.lrb.service.UserService;
import com.example.lrb.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * web管理员登陆接口
     *
     * @param user     当前登陆用户
     * @param response 用户设置token
     * @return 返回用户的信息
     */
    @PostMapping("/login")
    @ResponseBody
    public JsonResult<User> login(
            @RequestBody User user,
            HttpServletResponse response
    ) {
        try {
            return adminService.login(user, response);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.badRequest("登陆失败，请重试");
        }
    }

}

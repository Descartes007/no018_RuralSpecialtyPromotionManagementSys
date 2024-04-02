package com.example.lrb.controller;

import com.example.lrb.vo.JsonResult;
import com.example.lrb.pojo.User;
import com.example.lrb.service.UserService;
import com.example.lrb.vo.RegisterUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户/管理员登陆接口
     *
     * @param user     当前登陆用户
     * @param response 用户设置token
     * @return 返回用户的信息
     */
    @PostMapping("/login")
    public JsonResult<User> login(
            @RequestBody User user,
            HttpServletResponse response
    ) {
        try {
            return userService.login(user, response);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.badRequest("登陆失败，请重试");
        }
    }

    /**
     * 用户/商户注册接口
     *
     * @param registerUser     注册用户
     * @return 返回用户的信息
     */
    @PostMapping("/register")
    public JsonResult<Object> register(
            @RequestBody RegisterUserVo registerUser
    ) {
        try {
            return userService.register(registerUser);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.badRequest("注册失败，请重试");
        }
    }


    /**
     * 加载全部用户信息
     *
     * @param name 用户搜索
     * @return 返回用户信息列表
     */
    @GetMapping("/list")
    public JsonResult<List<User>> getUserAll(
            @RequestParam(required = false) String name
    ) {
        try {
            return userService.getUserAll(name);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.badRequest("登陆失败，请重试");
        }
    }

    /**
     * 删除某个用户
     *
     * @param id 用户ID
     * @return 返回处理结果
     */
    @DeleteMapping("{id}")
    public JsonResult<User> delete(
            @PathVariable Integer id
    ) {
        try {
            return userService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.badRequest("登陆失败，请重试");
        }
    }

    /**
     * 获取当前登陆用户信息
     *
     * @param user 当前登陆用户
     * @return 返回对应结果
     */
    @GetMapping("/info")
    public JsonResult<User> getUserInfo(
            @RequestAttribute("user") User user
    ) {
        try {
            return userService.getUserInfo(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.badRequest("登陆失败，请重试");
        }
    }

    /**
     * 更新用户密码
     *
     * @param data 用户提交的旧密码、新密码
     * @return 返回对应结果
     */
    @PutMapping("/password")
    public JsonResult<User> updatePassword(
            @RequestBody Map<String, String> data
    ) {
        return userService.updatePassword(Integer.parseInt(data.get("userId")), data.get("oldPassword"), data.get("newPassword"));
    }

}

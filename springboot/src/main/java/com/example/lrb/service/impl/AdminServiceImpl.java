package com.example.lrb.service.impl;

import com.example.lrb.common.utils.JwtUtils;
import com.example.lrb.common.utils.ResponseJsonUtils;
import com.example.lrb.dao.UserDao;
import com.example.lrb.enums.RoleEnums;
import com.example.lrb.pojo.User;
import com.example.lrb.service.AdminService;
import com.example.lrb.vo.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserDao userDao;

    @Override
    public User loginDeal(String name, String password) {
        // 查找该用户
        if (name == null) {
            return null;
        }
        User user = userDao.selectOne(User.builder().name(name).build());
        if (user == null) {
            return null;
        } else if (!user.getPassword().equals(password)) {
            return null;
        }
        // 生成token，并返回
        return user;
    }

    @Override
    public JsonResult<User> login(User params, HttpServletResponse response) {
        if (params == null
                || StringUtils.isBlank(params.getName())
                || StringUtils.isBlank(params.getPassword())
                || params.getRole() == null
        ) {
            return JsonResult.badRequest("登陆失败，缺少参数");
        }

        // 查找该用户
        User user = userDao.selectOne(User.builder().name(params.getName()).build());
        if (user == null) {
            return JsonResult.badRequest("用户不存在");
        } else if (!user.getPassword().equals(params.getPassword())) {
            return JsonResult.badRequest("登陆失败，密码错误");
        } else if (!user.getRole().equals(RoleEnums.ADMIN)) {
            return JsonResult.badRequest("请使用管理员账号登陆");
        }

        // 生成token，并返回
        String token = JwtUtils.generateToken(user.getId().toString(), user);
        ResponseJsonUtils.setToken(response, token);
        return JsonResult.ok("登陆成功", user);
    }
}

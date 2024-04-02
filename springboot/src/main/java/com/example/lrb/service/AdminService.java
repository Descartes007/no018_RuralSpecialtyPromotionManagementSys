package com.example.lrb.service;

import com.example.lrb.pojo.User;
import com.example.lrb.vo.JsonResult;

import javax.servlet.http.HttpServletResponse;

public interface AdminService {
    User loginDeal(String name, String password);

    JsonResult<User> login(User user, HttpServletResponse response);

}

package com.example.lrb.service;

import com.example.lrb.vo.JsonResult;
import com.example.lrb.pojo.User;
import com.example.lrb.vo.RegisterUserVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService extends CrudService<User>{
    JsonResult<User> login(User data, HttpServletResponse response);

    JsonResult<User> getUserInfo(Integer id);

    JsonResult<User> updatePassword(Integer id, String oldPassword, String newPassword);

    JsonResult<List<User>> getUserAll(String name);

    JsonResult<Object> register(RegisterUserVo registerUser);

}
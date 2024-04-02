package com.example.lrb.dao;

import com.example.lrb.pojo.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User> {

    List<User> queryUserAll(String name);

}

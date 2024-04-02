package com.example.lrb.dao;

import com.example.lrb.pojo.Node;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @date 2022-03-20 22:03
 */
public interface NodeDao extends Mapper<Node> {
    List<Node> queryListByUserId(Integer userId);

    int increasePageview(Integer nodeId);

    Node queryById(@Param("nodeId") Integer nodeId, @Param("userId") Integer userIdInteger);

    List<Node> queryListByCategoryId(@Param("userId") Integer userId, @Param("categoryId") Integer categoryId, @Param("title") String title, @Param("city") String city);

    List<Node> queryListAll(@Param("userId") Integer userId, @Param("categoryId") Integer categoryId, @Param("title") String title, @Param("city") String city);
}

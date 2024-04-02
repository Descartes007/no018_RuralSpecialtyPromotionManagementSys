package com.example.lrb.dao;

import com.example.lrb.pojo.Feedback;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 *
 * @date 2022-03-21 21:09
 */
public interface FeedbackDao extends Mapper<Feedback> {
    @Select("SELECT\n" +
            "\tf.*,\n" +
            "\tu.`name` \n" +
            "FROM\n" +
            "\tfeedback f,\n" +
            "\tUSER u \n" +
            "WHERE\n" +
            "\tf.user_id = u.id" +
            "\tORDER BY f.create_time DESC")
    List<Feedback> queryList();

}

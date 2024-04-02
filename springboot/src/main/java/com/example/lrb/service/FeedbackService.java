package com.example.lrb.service;

import com.example.lrb.pojo.Feedback;
import com.example.lrb.vo.JsonResult;

import java.util.List;

/**
 *
 * @date 2022-03-21 21:08
 */
public interface FeedbackService extends CrudService<Feedback> {
    JsonResult<List<Feedback>> listByUserId(Integer id);
}

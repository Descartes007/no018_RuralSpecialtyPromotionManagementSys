package com.example.lrb.service.impl;

import com.example.lrb.dao.FeedbackDao;
import com.example.lrb.pojo.Feedback;
import com.example.lrb.service.FeedbackService;
import com.example.lrb.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @date 2022-03-21 21:08
 */
@Service
@Transactional
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public JsonResult<List<Feedback>> list(Feedback query) {
        return JsonResult.ok(feedbackDao.queryList());
    }

    @Override
    public JsonResult<Feedback> query(Integer id) {
        return null;
    }

    @Override
    public JsonResult<Feedback> update(Feedback feedback) {
        if (feedback.getId() == null) {
            return JsonResult.badRequest("回复失败");
        }
        if (StringUtils.isBlank(feedback.getReplyContent())) {
            return JsonResult.badRequest("请输入回复内容");
        }
        feedback.setReplyTime(LocalDateTime.now());

        feedbackDao.updateByPrimaryKeySelective(feedback);
        return JsonResult.ok("回复成功", feedback);
    }

    @Override
    public JsonResult<Feedback> insert(Feedback feedback) {
        if (StringUtils.isBlank(feedback.getContent())) {
            return JsonResult.badRequest("请输入反馈内容");
        }
        feedback.setCreateTime(LocalDateTime.now());

        feedbackDao.insertSelective(feedback);
        return JsonResult.ok("反馈成功", feedback);
    }

    @Override
    public JsonResult<Feedback> delete(Integer id) {
        feedbackDao.deleteByPrimaryKey(id);
        return JsonResult.ok("删除成功");
    }

    @Override
    public JsonResult<List<Feedback>> listByUserId(Integer id) {
        Example e = new Example(Feedback.class);
        e.orderBy("createTime").desc();
        e.createCriteria().andEqualTo("userId", id);
        return JsonResult.ok(feedbackDao.selectByExample(e));
    }
}

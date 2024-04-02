package com.example.lrb.controller;

import com.example.lrb.pojo.Feedback;
import com.example.lrb.pojo.User;
import com.example.lrb.service.FeedbackService;
import com.example.lrb.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * 添加反馈
     *
     * @param user     当前登陆用户
     * @param feedback 反馈内容
     * @return 返回对应结果
     */
    @PostMapping
    public JsonResult<Feedback> insert(@RequestAttribute("user") User user, @RequestBody Feedback feedback) {
        feedback.setUserId(user.getId());
        return feedbackService.insert(feedback);
    }

    /**
     * 更新反馈信息，用于管理员回复
     *
     * @param feedback 回复内容
     * @return 返回对应结果
     */
    @PutMapping
    public JsonResult<Feedback> update(@RequestBody Feedback feedback) {
        return feedbackService.update(feedback);
    }

    /**
     * 删除反馈
     *
     * @param id 反馈ID
     * @return 返回对应结果
     */
    @DeleteMapping("{id}")
    public JsonResult<Feedback> delete(@PathVariable("id") Integer id) {
        return feedbackService.delete(id);
    }

    /**
     * 加载全部反馈列表
     *
     * @return 返回对应结果
     */
    @GetMapping("list")
    public JsonResult<List<Feedback>> list() {
        return feedbackService.list(null);
    }

    /**
     * 根据userId，加载对应的反馈信息
     *
     * @param user 当前登陆用户
     * @return 返回对应结果
     */
    @GetMapping("list/user")
    public JsonResult<List<Feedback>> listByUserId(@RequestAttribute("user") User user) {
        return feedbackService.listByUserId(user.getId());
    }
}

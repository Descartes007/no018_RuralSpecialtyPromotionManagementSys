package com.example.lrb.controller;

import com.example.lrb.pojo.NodeComment;
import com.example.lrb.pojo.User;
import com.example.lrb.service.NodeCommentService;
import com.example.lrb.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @date 2022-03-20 21:59
 */
@RestController
@RequestMapping("node/comment")
public class NodeCommentController {


    @Autowired
    private NodeCommentService nodeCommentService;

    /**
     * 添加笔记的评论
     *
     * @param user        当前登陆用户
     * @param nodeComment 用户的评论信息
     * @return 返回对应结果
     */
    @PostMapping
    public JsonResult<NodeComment> insert(@RequestAttribute("user") User user, @RequestBody NodeComment nodeComment) {
        nodeComment.setFromId(user.getId());
        return nodeCommentService.insert(nodeComment);
    }


}

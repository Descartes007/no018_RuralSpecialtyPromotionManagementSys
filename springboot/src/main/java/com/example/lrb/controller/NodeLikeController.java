package com.example.lrb.controller;

import com.example.lrb.pojo.NodeLike;
import com.example.lrb.pojo.User;
import com.example.lrb.service.NodeLikeService;
import com.example.lrb.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @date 2022-03-21 12:56
 */
@RestController
@RequestMapping("node/like")
public class NodeLikeController {

    @Autowired
    private NodeLikeService nodeLikeService;

    /**
     * 添加对用户创作的诗词喜欢
     *
     * @param user     当前登陆用户
     * @param nodeLike 包含对应的用户创作诗词的ID
     * @return 返回对应结果
     */
    @PostMapping
    public JsonResult<NodeLike> insert(@RequestAttribute("user") User user, @RequestBody NodeLike nodeLike) {
        nodeLike.setUserId(user.getId());
        return nodeLikeService.insert(nodeLike);
    }

}

package com.example.lrb.controller;

import com.example.lrb.pojo.Node;
import com.example.lrb.pojo.User;
import com.example.lrb.service.NodeService;
import com.example.lrb.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @date 2022-03-20 21:58
 */
@RestController
@RequestMapping("node")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    /**
     * 审核通过用户的笔记
     *
     * @param node 包含用户的笔记ID
     * @return 返回对应结果
     */
    @PutMapping("waiting")
    public JsonResult<Node> waitingAuthNode(@RequestBody Node node) {
        return nodeService.waitingAuthNode(node);
    }

    /**
     * 审核通过用户的笔记
     *
     * @param node 包含用户的笔记ID
     * @return 返回对应结果
     */
    @PutMapping("release")
    public JsonResult<Node> releasePoem(@RequestBody Node node) {
        return nodeService.releasePoem(node);
    }

    /**
     * 审核通过用户的笔记
     *
     * @param node 包含用户的笔记ID
     * @return 返回对应结果
     */
    @PutMapping("audit_release")
    public JsonResult<Node> auditReleaseNode(@RequestBody Node node) {
        return nodeService.auditReleaseNode(node);
    }

    /**
     * 审核通过用户的笔记
     *
     * @param node 包含用户的笔记ID
     * @return 返回对应结果
     */
    @PutMapping("audit_defeat")
    public JsonResult<Node> auditDefeatNode(@RequestBody Node node) {
        return nodeService.auditDefeatNode(node);
    }

    /**
     * 拒绝通过用户的笔记
     *
     * @param node 包含用户的笔记ID
     * @return 返回对应结果
     */
    @PutMapping("defeat")
    public JsonResult<Node> defeatPoem(@RequestBody Node node) {
        return nodeService.defeatPoem(node);
    }

    /**
     * 保存用户的笔记
     *
     * @param user 当前登陆用户
     * @param node 用户的笔记
     * @return 返回对应结果
     */
    @PostMapping
    public JsonResult<Node> insert(@RequestAttribute("user") User user, @RequestBody Node node) {
        node.setUserId(user.getId());
        return nodeService.insert(node);
    }

    /**
     * 根据userId，加载对应的笔记
     *
     * @param user 当前登陆用户
     * @return 返回对应结果
     */
    @GetMapping("list/user")
    public JsonResult<List<Node>> listByUserId(@RequestAttribute("user") User user) {
        return nodeService.listByUserId(user.getId());
    }
    

    /**
     * 加载全部笔记的信息，（管理员管理）
     *
     * @param user  当前登陆用户
     * @param title 搜索的笔记标题
     * @return 返回对应结果
     */
    @GetMapping("list/all")
    public JsonResult<List<Node>> listAll(
            @RequestAttribute("user") User user,
            @RequestParam(value = "city",required = false) String city,
            @RequestParam(value = "title",required = false) String title
    ) {
        Node node = new Node();
        if (user != null) {
            node.setUserId(user.getId());
        }
        node.setTitle(title);
        node.setCity(city);
        return nodeService.listAll(node);
    }

    /**
     * 根据分类或搜索加载笔记的信息，（首页）
     *
     * @param request    request对象
     * @param title      搜索的笔记标题
     * @return 返回对应结果
     */
    @GetMapping("list")
    public JsonResult<List<Node>> list(
            HttpServletRequest request,
            @RequestParam("city") String city,
            @RequestParam("title") String title
    ) {

        Object user = request.getAttribute("user");
        Node node = new Node();
        if (user != null) {
            node.setUserId(((User) user).getId());
        }
        node.setTitle(title);
        node.setCity(city);
        return nodeService.list(node);
    }

    /**
     * 获取笔记的详细信息
     *
     * @param nodeId 的笔记
     * @return 返回对应结果
     */
    @GetMapping("{nodeId}")
    public JsonResult<Node> query(@RequestAttribute("user") User user, @PathVariable("nodeId") Integer nodeId) {
        return nodeService.query(nodeId, user.getId());
    }

    /**
     * 删除用户的笔记
     *
     * @param nodeId 的笔记id
     * @return 返回对应结果
     */
    @DeleteMapping("{nodeId}")
    public JsonResult<Node> delete(@PathVariable("nodeId") Integer nodeId) {
        return nodeService.delete(nodeId);
    }

}

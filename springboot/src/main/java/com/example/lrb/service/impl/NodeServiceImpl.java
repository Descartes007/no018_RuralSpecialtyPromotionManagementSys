package com.example.lrb.service.impl;

import com.example.lrb.dao.NodeDao;
import com.example.lrb.dao.NodeGoodsDao;
import com.example.lrb.enums.NodeEnums;
import com.example.lrb.pojo.Node;
import com.example.lrb.pojo.NodeComment;
import com.example.lrb.pojo.NodeGoods;
import com.example.lrb.service.NodeService;
import com.example.lrb.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeDao nodeDao;
    @Autowired
    private NodeGoodsDao nodeGoodsDao;

    @Override
    public JsonResult<List<Node>> list(Node query) {
        List<Node> list = nodeDao.queryListByCategoryId(query.getUserId(), null, query.getTitle(), query.getCity());
        return JsonResult.ok(list);
    }

    @Override
    public JsonResult<Node> query(Integer id) {
        return null;
    }

    @Override
    public JsonResult<Node> query(Integer id, Integer userId) {
        // 浏览数+1
        nodeDao.increasePageview(id);
        Node node = nodeDao.queryById(id, userId);

        List<NodeComment> newCommentList = node.getCommentList()
                .stream()
                .filter(v -> v.getParentId() == null)
                .peek(v ->
                        v.setChildrenComment(node.getCommentList().stream().filter(v2 -> v2.getParentId() == v.getId()).sorted(Comparator.comparing(NodeComment::getCreateTime)).collect(Collectors.toList()))
                ).sorted(Comparator.comparing(NodeComment::getCreateTime)).collect(Collectors.toList());

        node.setCommentList(newCommentList);

        return JsonResult.ok(node);
    }

    @Override
    public JsonResult<Node> update(Node node) {
        return null;
    }

    @Override
    public JsonResult<Node> insert(Node node) {
        if (
                StringUtils.isBlank(node.getTitle()) ||
                        StringUtils.isBlank(node.getContent()) ||
                        StringUtils.isBlank(node.getAddress()) ||
                        node.getUserId() == null ||
                        node.getStatus() == null
        ) {
            return JsonResult.badRequest("请上传完整所有数据");
        }
        if (
                StringUtils.isBlank(node.getImages())
        ) {
            return JsonResult.badRequest("请上传一张图片作为封面");
        }
        node.setPageview(0);
        node.setCreateTime(LocalDateTime.now());
        node.setAuditStatus(NodeEnums.WAITING_AUDIT);

        nodeDao.insertSelective(node);
        if (node.getGoodsList() != null && !node.getGoodsList().isEmpty()) {
            nodeGoodsDao.insertList(node.getGoodsList().stream().map(v -> new NodeGoods(null, node.getId(), v.getId())).collect(Collectors.toList()));
        }

        return JsonResult.ok("发布成功，等待管理员审核", node);
    }

    @Override
    public JsonResult<Node> delete(Integer id) {
        nodeDao.deleteByPrimaryKey(id);
        return JsonResult.ok("删除成功");
    }

    @Override
    public JsonResult<List<Node>> listByUserId(Integer id) {
        List<Node> list = nodeDao.queryListByUserId(id);
        return JsonResult.ok(list);
    }


    @Override
    public JsonResult<List<Node>> listAll(Node node) {
        List<Node> list = nodeDao.queryListAll(node.getUserId(), null, node.getTitle(), node.getCity());
        return JsonResult.ok(list);
    }

    @Override
    public JsonResult<Node> releasePoem(Node node) {
        if (
                node.getId() == null
        ) {
            return JsonResult.badRequest("保存失败");
        }
        node.setStatus(NodeEnums.AUTHORIZED);
        nodeDao.updateByPrimaryKeySelective(node);
        return JsonResult.ok("保存成功");
    }

    @Override
    public JsonResult<Node> defeatPoem(Node node) {
        if (
                node.getId() == null
        ) {
            return JsonResult.badRequest("保存失败");
        }
        node.setStatus(NodeEnums.DEFEAT_CERTIFICATION);
        nodeDao.updateByPrimaryKeySelective(node);
        return JsonResult.ok("保存成功");
    }

    @Override
    public JsonResult<Node> auditReleaseNode(Node node) {
        if (
                node.getId() == null
        ) {
            return JsonResult.badRequest("保存失败");
        }
        node.setAuditStatus(NodeEnums.AUDIT_PASS);
        nodeDao.updateByPrimaryKeySelective(node);
        return JsonResult.ok("保存成功");
    }

    @Override
    public JsonResult<Node> auditDefeatNode(Node node) {
        if (
                node.getId() == null
        ) {
            return JsonResult.badRequest("保存失败");
        }
        node.setAuditStatus(NodeEnums.AUDIT_NO_PASS);
        nodeDao.updateByPrimaryKeySelective(node);
        return JsonResult.ok("保存成功");
    }

    @Override
    public JsonResult<Node> waitingAuthNode(Node node) {

        if (
                node.getId() == null
        ) {
            return JsonResult.badRequest("保存失败");
        }
        node.setStatus(NodeEnums.WAITING_CERTIFICATION);
        nodeDao.updateByPrimaryKeySelective(node);
        return JsonResult.ok("保存成功");
    }

}

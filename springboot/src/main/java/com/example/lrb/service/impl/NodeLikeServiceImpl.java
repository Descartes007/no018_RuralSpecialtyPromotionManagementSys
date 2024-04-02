package com.example.lrb.service.impl;

import com.example.lrb.dao.NodeLikeDao;
import com.example.lrb.pojo.NodeLike;
import com.example.lrb.service.NodeLikeService;
import com.example.lrb.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @date 2022-03-21 12:57
 */
@Service
@Transactional
@Slf4j
public class NodeLikeServiceImpl implements NodeLikeService {

    @Autowired
    private NodeLikeDao nodeLikeDao;

    @Override
    public JsonResult<List<NodeLike>> list(NodeLike query) {
        return null;
    }

    @Override
    public JsonResult<NodeLike> query(Integer id) {
        return null;
    }

    @Override
    public JsonResult<NodeLike> update(NodeLike nodeLike) {
        return null;
    }

    @Override
    public JsonResult<NodeLike> insert(NodeLike nodeLike) {
        if (nodeLike.getNodeId() == null || nodeLike.getScore() == null) {
            return JsonResult.badRequest("保存失败");
        }
        nodeLikeDao.insertSelective(nodeLike);
        return JsonResult.ok(nodeLike);
    }

    @Override
    public JsonResult<NodeLike> delete(Integer id) {
        return null;
    }

    @Override
    public JsonResult<NodeLike> delete(Integer poemId, Integer userId) {
        nodeLikeDao.delete(NodeLike.builder().nodeId(poemId).userId(userId).build());
        return JsonResult.ok(null);
    }
}

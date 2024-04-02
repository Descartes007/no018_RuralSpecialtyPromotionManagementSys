package com.example.lrb.service.impl;

import com.example.lrb.dao.NodeCommentDao;
import com.example.lrb.pojo.NodeComment;
import com.example.lrb.service.NodeCommentService;
import com.example.lrb.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @date 2022-03-20 22:01
 */
@Service
@Transactional
@Slf4j
public class NodeCommentServiceImpl implements NodeCommentService {

    @Autowired
    private NodeCommentDao nodeCommentDao;

    @Override
    public JsonResult<List<NodeComment>> list(NodeComment query) {
        return null;
    }

    @Override
    public JsonResult<NodeComment> query(Integer id) {
        return null;
    }

    @Override
    public JsonResult<NodeComment> update(NodeComment nodeComment) {
        return null;
    }

    @Override
    public JsonResult<NodeComment> insert(NodeComment nodeComment) {
        if (StringUtils.isBlank(nodeComment.getContent())) {
            return JsonResult.badRequest("请输入评论");
        }
        nodeComment.setCreateTime(LocalDateTime.now());
        nodeCommentDao.insertSelective(nodeComment);
        return JsonResult.ok(nodeComment);
    }

    @Override
    public JsonResult<NodeComment> delete(Integer id) {
        return null;
    }
}

package com.example.lrb.service;

import com.example.lrb.pojo.Node;
import com.example.lrb.vo.JsonResult;

import java.util.List;

/**
 * @date 2022-03-20 21:59
 */
public interface NodeService extends CrudService<Node> {
    JsonResult<Node> query(Integer id, Integer userId);

    JsonResult<List<Node>> listByUserId(Integer id);

    JsonResult<List<Node>> listAll(Node node);

    JsonResult<Node> releasePoem(Node node);

    JsonResult<Node> defeatPoem(Node node);

    JsonResult<Node> auditReleaseNode(Node node);

    JsonResult<Node> auditDefeatNode(Node node);

    JsonResult<Node> waitingAuthNode(Node node);
}

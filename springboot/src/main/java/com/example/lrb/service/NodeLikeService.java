package com.example.lrb.service;

import com.example.lrb.pojo.NodeLike;
import com.example.lrb.vo.JsonResult;

/**
 * @date 2022-03-20 22:00
 */
public interface NodeLikeService extends CrudService<NodeLike> {

    JsonResult<NodeLike> delete(Integer poemId, Integer userId);

}

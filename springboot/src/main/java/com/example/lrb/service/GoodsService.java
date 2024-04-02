package com.example.lrb.service;

import com.example.lrb.pojo.Goods;
import com.example.lrb.vo.JsonResult;

import java.util.List;

/**
 * @date 2022-03-25 21:54
 */
public interface GoodsService extends CrudService<Goods> {
    JsonResult<List<Goods>> listByUserId(Integer id);

    JsonResult<Goods> updateInventory(Goods goods);
}

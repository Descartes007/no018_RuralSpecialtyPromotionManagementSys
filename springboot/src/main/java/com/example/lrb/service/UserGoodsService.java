package com.example.lrb.service;

import com.example.lrb.pojo.User;
import com.example.lrb.pojo.UserGoods;
import com.example.lrb.vo.JsonResult;

import java.util.List;

public interface UserGoodsService extends CrudService<UserGoods> {
    JsonResult<List<UserGoods>> listByPayUserId(Integer id);

    JsonResult<List<UserGoods>> listByMerchantId(Integer id);
}

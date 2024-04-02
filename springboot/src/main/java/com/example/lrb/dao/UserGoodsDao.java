package com.example.lrb.dao;

import com.example.lrb.pojo.UserGoods;
import com.example.lrb.vo.JsonResult;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserGoodsDao extends Mapper<UserGoods> {
    List<UserGoods> selectListByPayUserId(Integer id);

    List<UserGoods> selectListByMerchantId(Integer id);
}

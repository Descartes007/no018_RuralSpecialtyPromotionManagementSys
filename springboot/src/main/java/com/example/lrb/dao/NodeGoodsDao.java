package com.example.lrb.dao;

import com.example.lrb.pojo.NodeGoods;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @date 2022-03-26 22:39
 */
public interface NodeGoodsDao extends Mapper<NodeGoods>, InsertListMapper<NodeGoods> {
}

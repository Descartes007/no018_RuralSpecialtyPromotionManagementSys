package com.example.lrb.service.impl;

import com.example.lrb.dao.GoodsDao;
import com.example.lrb.dao.UserGoodsDao;
import com.example.lrb.pojo.Goods;
import com.example.lrb.pojo.User;
import com.example.lrb.pojo.UserGoods;
import com.example.lrb.service.UserGoodsService;
import com.example.lrb.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserGoodsServiceImpl implements UserGoodsService {

    @Autowired
    private UserGoodsDao userGoodsDao;
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public JsonResult<List<UserGoods>> list(UserGoods query) {
        return null;
    }

    @Override
    public JsonResult<UserGoods> query(Integer id) {
        return null;
    }

    @Override
    public JsonResult<UserGoods> update(UserGoods userGoods) {
        return null;
    }

    @Override
    public JsonResult<UserGoods> insert(UserGoods userGoods) {
        // 商品库存减一
        Goods goods = goodsDao.selectByPrimaryKey(userGoods.getGoodsId());
        if (goods == null) {
            return JsonResult.badRequest("购买失败");
        }
        if (goods.getInventory() <= 0) {
            return JsonResult.badRequest("购买失败，库存不足");
        }
        goods.setInventory(goods.getInventory() - 1);
        goodsDao.updateByPrimaryKeySelective(goods);

        userGoods.setCreateTime(LocalDateTime.now());
        userGoodsDao.insertSelective(userGoods);
        return JsonResult.ok("购买成功");
    }

    @Override
    public JsonResult<UserGoods> delete(Integer id) {
        return null;
    }

    @Override
    public JsonResult<List<UserGoods>> listByPayUserId(Integer id) {
        return JsonResult.ok(userGoodsDao.selectListByPayUserId(id));
    }

    @Override
    public JsonResult<List<UserGoods>> listByMerchantId(Integer id) {
        return JsonResult.ok(userGoodsDao.selectListByMerchantId(id));
    }
}

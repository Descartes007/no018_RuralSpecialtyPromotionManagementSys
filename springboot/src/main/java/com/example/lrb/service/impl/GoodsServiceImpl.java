package com.example.lrb.service.impl;

import com.example.lrb.dao.GoodsDao;
import com.example.lrb.dao.MerchantDao;
import com.example.lrb.dao.UserDao;
import com.example.lrb.pojo.Goods;
import com.example.lrb.pojo.Merchant;
import com.example.lrb.service.GoodsService;
import com.example.lrb.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @date 2022-03-25 21:54
 */

@Service
@Transactional
@Slf4j
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private MerchantDao merchantDao;
    @Autowired
    private UserDao userDao;

    @Override
    public JsonResult<List<Goods>> list(Goods query) {
        List<Goods> goods = goodsDao.selectAll();
        for (Goods good : goods) {
            good.setMerchant(merchantDao.selectOne(Merchant.builder().userId(good.getUserId()).build()));
        }
        return JsonResult.ok(goods);
    }

    @Override
    public JsonResult<Goods> query(Integer id) {
        return JsonResult.ok(goodsDao.selectByPrimaryKey(id));
    }

    @Override
    public JsonResult<Goods> update(Goods goods) {
        return null;
    }

    @Override
    public JsonResult<Goods> insert(Goods goods) {
        if (
                StringUtils.isBlank(goods.getTitle()) ||
                        StringUtils.isBlank(goods.getContent()) ||
                        StringUtils.isBlank(goods.getImages()) ||
                        goods.getUserId() == null ||
                        goods.getPrice() == null
        ) {
            return JsonResult.badRequest("请填完整所有数据");
        }
        goods.setCreateTime(LocalDateTime.now());
        goodsDao.insertSelective(goods);
        return JsonResult.ok("保存成功", goods);
    }

    @Override
    public JsonResult<Goods> delete(Integer id) {
        goodsDao.deleteByPrimaryKey(id);
        return JsonResult.ok("删除成功");
    }

    @Override
    public JsonResult<List<Goods>> listByUserId(Integer id) {
        return JsonResult.ok(goodsDao.select(Goods.builder().userId(id).build()));
    }

    @Override
    public JsonResult<Goods> updateInventory(Goods goods) {
        goodsDao.updateByPrimaryKeySelective(goods);
        return JsonResult.ok("保存成功", goods);
    }

}

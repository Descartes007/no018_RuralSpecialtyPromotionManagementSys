package com.example.lrb.controller;

import com.example.lrb.pojo.Goods;
import com.example.lrb.pojo.Node;
import com.example.lrb.pojo.User;
import com.example.lrb.service.GoodsService;
import com.example.lrb.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @date 2022-03-25 21:53
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 保存用户提交的商品
     *
     * @param user  当前登陆用户
     * @param goods 用户提交的商品
     * @return 返回对应结果
     */
    @PostMapping
    public JsonResult<Goods> insert(@RequestAttribute("user") User user, @RequestBody Goods goods) {
        goods.setUserId(user.getId());
        return goodsService.insert(goods);
    }
    /**
     * 保存用户提交的商品
     *
     * @param goods 用户提交的商品
     * @return 返回对应结果
     */
    @PutMapping("inventory")
    public JsonResult<Goods> updateInventory(@RequestBody Goods goods) {
        return goodsService.updateInventory(goods);
    }

    /**
     * 根据userId，加载对应的商品
     *
     * @param user 当前登陆用户
     * @return 返回对应结果
     */
    @GetMapping("list/user")
    public JsonResult<List<Goods>> listByUserId(@RequestAttribute("user") User user) {
        return goodsService.listByUserId(user.getId());
    }

    /**
     * 加载全部商品
     *
     * @return 返回对应结果
     */
    @GetMapping("list")
    public JsonResult<List<Goods>> list() {
        return goodsService.list(null);
    }

    /**
     * 获取商品的详细信息
     *
     * @param shopId 商品id
     * @return 返回对应结果
     */
    @GetMapping("{shopId}")
    public JsonResult<Goods> query(@PathVariable("shopId") Integer shopId) {
        return goodsService.query(shopId);
    }


    /**
     * 删除商品
     *
     * @param shopId 商品id
     * @return 返回对应结果
     */
    @DeleteMapping("{shopId}")
    public JsonResult<Goods> delete(@PathVariable("shopId") Integer shopId) {
        return goodsService.delete(shopId);
    }


}

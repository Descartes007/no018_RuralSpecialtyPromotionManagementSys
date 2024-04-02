package com.example.lrb.controller;

import com.example.lrb.pojo.Goods;
import com.example.lrb.pojo.Node;
import com.example.lrb.pojo.User;
import com.example.lrb.pojo.UserGoods;
import com.example.lrb.service.UserGoodsService;
import com.example.lrb.service.UserService;
import com.example.lrb.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/goods")
public class UserGoodsController {

    @Autowired
    private UserGoodsService userGoodsService;

    /**
     * 保存用户的笔记
     *
     * @param user      当前登陆用户
     * @param userGoods 商品id
     * @return 返回对应结果
     */
    @PostMapping
    public JsonResult<UserGoods> insert(@RequestAttribute("user") User user, @RequestBody UserGoods userGoods) {
        userGoods.setUserId(user.getId());
        return userGoodsService.insert(userGoods);
    }


    /**
     * 根据userId，加载已经下单的商品
     *
     * @param user 当前登陆用户
     * @return 返回对应结果
     */
    @GetMapping("list/pay_user")
    public JsonResult<List<UserGoods>> listByPayUserId(@RequestAttribute("user") User user) {
        return userGoodsService.listByPayUserId(user.getId());
    }

    /**
     * 根据userId，加载已经售出的商品
     *
     * @param user 当前登陆用户
     * @return 返回对应结果
     */
    @GetMapping("list/merchant_id")
    public JsonResult<List<UserGoods>> listByMerchantId(@RequestAttribute("user") User user) {
        return userGoodsService.listByMerchantId(user.getId());
    }
}

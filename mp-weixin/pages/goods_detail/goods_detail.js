import { getById } from "../../api/goods";
import { insertUserGoods } from "../../api/userGoods";
import regeneratorRuntime from "../../lib/runtime/runtime";

const app = getApp();

Page({
  /**
   * 页面的初始数据
   */
  data: {
    goodsDetail: {},
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onShow() {
    const pages = getCurrentPages();
    const options = pages[pages.length - 1].options;
    const { goods_id } = options;
    this.getGoodsDetail(goods_id);
  },
  async getGoodsDetail(goods_id) {
    const res = await getById({ id: goods_id })
    console.log(res);

    const goodsDetail = res.data
    goodsDetail.imageList = goodsDetail.images.split(";")
    this.setData({
      goodsDetail,
    });
  },
  handleSwiperItem(e) {
    const urls = this.data.goodsDetail.imageList;
    wx.previewImage({
      current: urls[e.currentTarget.dataset.index],
      urls,
    });
  },
  async purchase() {
    const res = await insertUserGoods({ goodsId: this.data.goodsDetail.id })
    app.handleRes(res,()=>{
      this.onShow()
    })
  },

  handleAddCart() {
    const cart = wx.getStorageSync("cart") || [];
    const index = cart.findIndex((v) => v.goods_id === this.goodsInfo.goods_id);
    if (index === -1) {
      this.goodsInfo.num = 1;
      this.goodsInfo.checked = true;
      cart.push(this.goodsInfo);
    } else {
      cart[index].num++;
    }
    wx.setStorageSync("cart", cart);
    wx.showToast({
      title: "加入成功",
      icon: "success",
      mask: true,
    });
  },
  handleCollect() {
    const collect = wx.getStorageSync("collect") || [];
    const index = collect.findIndex(
      (v) => v.goods_id === this.goodsInfo.goods_id
    );
    let isCollect = false;
    if (index != -1) {
      collect.splice(index, 1);
      wx.showToast({
        title: "取消成功",
        icon: "success",
        mask: true,
      });
    } else {
      isCollect = true;
      collect.push(this.goodsInfo);
      wx.showToast({
        title: "收藏成功",
        icon: "success",
        mask: true,
      });
    }
    wx.setStorageSync("collect", collect);
    this.setData({
      isCollect,
    });
  },
});

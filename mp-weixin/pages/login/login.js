// pages/login/login.js

const app = getApp();

import { login } from "../../api/user"
Page({

  /**
   * 页面的初始数据
   */
  data: {
    role: "USER"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  radioChange(e) {
    this.setData({
      role: e.detail.value
    })
  },

  async login(e) {
    const res = await login({ ...e.detail.value, role: this.data.role })
    app.handleRes(res, "", () => {
      wx.setStorageSync("user", res.data);
      wx.switchTab({
        url: '/pages/index/index',
        success: (result) => {
        },
        fail: () => { },
        complete: () => { }
      });
    })
  }
})
// pages/profile/profile.js
const app = getApp();
import regeneratorRuntime from "../../lib/runtime/runtime";
import { login as loginByData } from "../../api/user"

Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {},
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  init() {
    const user = wx.getStorageSync("user") || {}
    if (!user || !user.id) {
      wx.navigateTo({
        url: '/pages/login/login',
        success: (result) => {

        },
        fail: () => { },
        complete: () => { }
      });
    } else {
      this.setData({ user })
      wx.stopPullDownRefresh()
    }
  },

  logout() {
    wx.showModal({
      title: '提示',
      content: '是否退出小程序',
      showCancel: true,
      cancelText: '取消',
      cancelColor: '#000000',
      confirmText: '确定',
      confirmColor: '#3CC51F',
      success: (result) => {
        if (result.confirm) {
          wx.clearStorageSync();
          wx.switchTab({
            url: '/pages/index/index',
          });
        }
      },
      fail: () => { },
      complete: () => { }
    });
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
    this.init()
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
    this.init()
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

  }
})
// pages/feedback_manage/feedback_manage.js
import { list as getUserList, updatePassword, deleteUser } from "../../../api/user"
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  async init() {
    const res = await getUserList()
    const { data: list } = res
    this.setData({ list })
  },
  updatePassword(e) {
    wx.showModal({
      title: '请输入新密码',
      editable: true,
      showCancel: true,
      cancelText: '取消',
      cancelColor: '#000000',
      confirmText: '确定',
      confirmColor: '#3CC51F',
      success: async (result) => {
        if (result.confirm) {
          const { index } = e.currentTarget.dataset
          const { list } = this.data;
          //找回密码
          const res = await updatePassword({
            userId: list[index].id,
            oldPassword: list[index].password, newPassword: result.content
          });
          app.handleRes(res, () => {
            this.init()
          })
        }
      },
      fail: () => { },
      complete: () => { }
    });
  },
  deleteUser(e) {
    wx.showModal({
      title: "提示",
      content: "是否删除这个用户？",
      showCancel: true,
      cancelText: "取消",
      cancelColor: "#000000",
      confirmText: "确定",
      confirmColor: "#3CC51F",
      success: async (result) => {
        if (result.confirm) {
          const { userId } = e.currentTarget.dataset;
          const res = await deleteUser(userId);
          app.handleRes(res, () => {
            this.init()
          });
        }
      },
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
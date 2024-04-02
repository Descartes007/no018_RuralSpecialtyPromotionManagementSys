// pages/feedback_manage/feedback_manage.js
import { deleteFeedback, listFeedback, updateFeedback } from "../../../api/feedback"
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
    const res = await listFeedback()
    const { data: list } = res
    this.setData({ list })
    wx.stopPullDownRefresh()
  },

  updateFeedback(e) {
    const { id, replyContent } = e.currentTarget.dataset;
    if (!replyContent) {
      wx.showModal({
        title: '请输入反馈内容',
        content: '',
        editable: true,
        showCancel: true,
        cancelText: '取消',
        cancelColor: '#000000',
        confirmText: '确定',
        confirmColor: '#3CC51F',
        success: async (result) => {
          if (result.confirm) {
            const res = await updateFeedback({ id, replyContent: result.content })
            app.handleRes(res, () => {
              this.init()
            })
          }
        },
        fail: () => { },
        complete: () => { }
      });
    }
  },
  deleteFeedback(e) {
    wx.showModal({
      title: "提示",
      content: "是否删除这条记录？",
      showCancel: true,
      cancelText: "取消",
      cancelColor: "#000000",
      confirmText: "确定",
      confirmColor: "#3CC51F",
      success: async (result) => {
        if (result.confirm) {
          const { id } = e.currentTarget.dataset;
          const res = await deleteFeedback(id);
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
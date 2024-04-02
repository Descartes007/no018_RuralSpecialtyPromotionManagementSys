
const app = getApp();

import { insertFeedback, listByUserId, deleteFeedback } from "../../api/feedback"

Page({
  /**
   * 页面的初始数据
   */
  data: {
    content: "",
    feedbackList: [],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    //到服务器加载提交的bug信息
    //1.请求最新的bug信息
    this.init()
  },
  async init() {
    const res = await listByUserId();
    this.setData({
      content: "",
      feedbackList: res.data
    });
  },

  async submit(e) {
    const {
      content
    } = e.detail.value;
    if (!content || !content.trim()) {
      wx.showToast({
        title: "请输入反馈内容",
        icon: "none",
      });
    } else {
      const res = await insertFeedback({ content })
      app.handleRes(res, () => {
        this.init()
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
          const { feedbackList } = this.data;
          const {
            index
          } = e.currentTarget.dataset;
          const feedback = feedbackList[index];
          const res = await deleteFeedback(feedback.id);
          app.handleRes(res, () => {
            this.init()
          });
        }
      },
    });
  },
});
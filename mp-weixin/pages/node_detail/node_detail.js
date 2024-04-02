// pages/node_detail/node_detail.js

import {
  getById
} from "../../api/node"
import {
  insert as insertNodeLike
} from "../../api/nodeLike"

import {
  insert as insertNodeComment
} from "../../api/nodeComment"

const app = getApp();


Page({

  /**
   * 页面的初始数据
   */
  data: {
    node: {},
    replyContent: "",
    replyObj: {
      replyHint: "评论",
      isParent: true,
      toId: "",
      parentId: "" // 第一层评论，无需父节点标识，第二层评论，父节点为第一层id
    },
    swiperHeight: "",
    modelData: {
      showModel: false
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function ({
    id
  }) {
    this.init(id)
  },
  async init(id) {
    const res = await getById({
      id
    })
    console.log(res);
    const {
      data: node
    } = res;
    node.imageList = node.images && node.images.split(";")
    node.videoList = node.videos && node.videos.split(";")
    this.setData({
      node: node
    })
  },
  copyInvitationCode(e) {
    wx.setClipboardData({
      data: e.currentTarget.dataset.text.replace(/<\/?.*>/ig, ''),
      success: function (res) {
        wx.showToast({
          title: "复制成功",
          icon: "none",
        });
      },
    });
  },
  replyComment(e) {
    const {
      isParent,
      replyName: replyHint,
      parentId,
      toId
    } = e.currentTarget.dataset
    this.setData({
      replyObj: {
        isParent,
        replyHint: "@" + replyHint,
        parentId,
        toId
      }
    })
  },
  replyNodeComment() {
    this.setData({
      replyObj: {
        isParent: true,
        replyHint: "评论",
        parentId: "",
        toId: ""
      }
    })
  },
  async handleLike() {
    this.setData({
      modelData: {
        showModel: true
      }
    })
  },
  async handleConfirm(e) {
    const { redScore: score } = e.detail
    const res = await insertNodeLike({ nodeId: this.data.node.id, score: score , })
    app.handleRes(res, () => {
      this.setData({
        modelData: {
          showModel: false
        }
      })
      this.init(this.data.node.id)
    })
  },
  async sendComment() {
    const {
      replyContent,
      replyObj,
      node
    } = this.data;
    if (replyContent && replyContent.trim()) {
      let data = {}
      if (replyObj.isParent) {
        data = {
          parentId: null,
          nodeId: node.id,
          content: replyContent
        }
      } else {
        data = {
          parentId: replyObj.parentId,
          nodeId: node.id,
          content: replyContent,
          toId: replyObj.toId
        }
      }
      const res = await insertNodeComment(data)
      app.handleRes(res, () => {
        this.init(node.id)
        this.setData({
          replyContent: ""
        })
      })

    } else {
      wx.showToast({
        title: '请输入内容',
        icon: 'none',
      });
    }
  },
  inputComment(e) {
    this.setData({
      replyContent: e.detail.value
    })
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

  }
})
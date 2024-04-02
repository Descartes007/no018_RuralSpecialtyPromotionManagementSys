// index.js
const app = getApp();

import { listAll as getNodeList, deleteNode, auditReleaseNode, auditDefeatNode } from "../../../api/node"

Page({
  data: {
    nodeList: [],
    selectCity: ""
  },
  searchTimer: -1,
  reqParams: {
    title: "",
    city: ""
  },

  selectCityReturn: "", // switch_city返回的值
  onLoad() {
    const selectCity = wx.getStorageSync("selectCity") || "全国";
    this.setData({ selectCity })
  },
  onShow() {
    if (this.selectCityReturn) {
      this.setData({ selectCity: this.selectCityReturn })
    }

    this.init()
  },
  async init() {
    if (this.data.selectCity != "全国") {
      this.reqParams.city = this.data.selectCity
    }

    this.queryNodeList()
    wx.stopPullDownRefresh()
  },
  async queryNodeList() {
    const res = await getNodeList(this.reqParams)
    const { data: nodeList } = res;
    if (nodeList) {
      nodeList.forEach(v => v.showAuditControl = true)
    }
    this.setData({ nodeList })
  },
  handleSearch(e) {
    const { value: title } = e.detail;
    clearTimeout(this.searchTimer);
    this.searchTimer = setTimeout(() => {
      this.reqParams.title = title
      this.queryNodeList()
    }, 1000);
  },
  handleDeleteNode(e) {
    wx.showModal({
      title: '提示',
      content: '是否删除这个笔记',
      showCancel: true,
      cancelText: '取消',
      cancelColor: '#000000',
      confirmText: '确定',
      confirmColor: '#3CC51F',
      success: async (result) => {
        if (result.confirm) {
          const { id } = e.detail
          const res = await deleteNode({ id })
          app.handleRes(res, () => {
            this.queryNodeList()
          })
        }
      },
      fail: () => { },
      complete: () => { }
    });
  },

  handleAuditReleaseNode(e) {
    wx.showModal({
      title: '提示',
      content: '是否通过审核',
      showCancel: true,
      cancelText: '取消',
      cancelColor: '#000000',
      confirmText: '确定',
      confirmColor: '#3CC51F',
      success: async (result) => {
        if (result.confirm) {
          const res = await auditReleaseNode({ id: e.detail.id })
          app.handleRes(res, () => {
            this.queryNodeList()
          })
        }
      },
      fail: () => { },
      complete: () => { }
    });
  },
  handleAuditDefeatNode(e) {
    wx.showModal({
      title: '提示',
      content: '是否禁止发布',
      showCancel: true,
      cancelText: '取消',
      cancelColor: '#000000',
      confirmText: '确定',
      confirmColor: '#3CC51F',
      success: async (result) => {
        if (result.confirm) {
          const res = await auditDefeatNode({ id: e.detail.id })
          app.handleRes(res, () => {
            this.queryNodeList()
          })
        }
      },
      fail: () => { },
      complete: () => { }
    });
  },
  onHide() {
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.init()
  },

})
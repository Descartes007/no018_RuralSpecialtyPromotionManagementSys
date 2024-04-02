// index.js
const app = getApp();

import { listAll as getNodeList, deleteNode, releaseNode, defeatNode } from "../../../api/node"

Page({
  data: {
    tabs: [{
      id: 1,
      name: "全部",
      prop: "ALL",
      isActive: true,
    },
    {
      id: 2,
      name: "待认证",
      prop: "REQUIRE_CERTIFICATION",
      isActive: false,
    },
    {
      id: 3,
      name: "已认证",
      prop: "AUTHORIZED",
      isActive: false,
    },
    {
      id: 4,
      name: "未认证",
      prop: "UNAUTHORIZED",
      isActive: false,
    },
    {
      id: 5,
      name: "未通过",
      prop: "DEFEAT_CERTIFICATION",
      isActive: false,
    },
    ],
    nodeList: [],
    selectCity: "",
    tabStyle: {
      tabBarStyle: "background-color:#cd8062",
      activeStyle: "color:#fff;font-weight: 700;",
    },
  },
  nodeListAll: [],
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
    let { data: nodeList } = res;
    if (nodeList) {
      nodeList.forEach(v => v.showAdminControl = true)
    }
    this.nodeListAll = nodeList
    const tab = this.data.tabs.find(v => v.isActive)
    if (tab.prop != "ALL") {
      nodeList = this.nodeListAll.filter(v => v.status == tab.prop)
    }

    this.setData({ nodeList })
  },
  selectTab(e) {
    const {
      index
    } = e.detail;
    const {
      tabs
    } = this.data;
    if (!tabs[index].isActive) {
      tabs.forEach((v) => (v.isActive = false));
      tabs[index].isActive = true;
      this.setData({
        tabs,
      });
      this.queryNodeList()
    }
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

  handleReleaseNode(e) {
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
          const res = await releaseNode({ id: e.detail.id })
          app.handleRes(res, () => {
            this.queryNodeList()
          })
        }
      },
      fail: () => { },
      complete: () => { }
    });
  },
  handleDefeatNode(e) {
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
          const res = await defeatNode({ id: e.detail.id })
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
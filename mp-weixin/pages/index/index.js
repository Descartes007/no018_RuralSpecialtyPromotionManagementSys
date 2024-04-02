// index.js
const app = getApp();

import { list as getNodeList } from "../../api/node"

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
    let selectCity = wx.getStorageSync("selectCity") || "全国";

    const user = wx.getStorageSync("user");
    // 设置地区
    if (user && user.location) {
      selectCity = user.location.substring(user.location.indexOf('省') + 1, user.location.indexOf('市') + 1)
    }

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
    console.log(res);
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
  onHide() {
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.init()
  },

})
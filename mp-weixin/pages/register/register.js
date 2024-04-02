// pages/register/register.js
import { register } from "../../api/user"
const app = getApp();

Page({

  /**
   * Page initial data
   */
  data: {
    registerType: "USER",
    form: {
      name: "",
      password: "",
      location: "",
      sex: "",
      age: "",
      mobile: "",
      hometown: "",
      idcard: "",
      address: "",
      storeName: "",
      confirmPass: "",
      products: "",
    },
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function (options) {

  },
  registerTypeChange(e) {
    const { value: registerType } = e.detail
    this.setData({ registerType })
  },
  selectLocation() {
    wx.chooseLocation({
      success: ({
        address
      }) => {
        if (!address) {
          wx.showToast({
            title: '请选择一个具体的位置',
            icon: 'none',
          });
          return
        }
        const {
          form
        } = this.data;
        form.location = address;
        this.setData({
          form
        })
      },
      fail: () => { },
      complete: () => { }
    });
  },

  selectHometown() {
    wx.chooseLocation({
      success: ({
        address
      }) => {
        if (!address) {
          wx.showToast({
            title: '请选择一个具体的位置',
            icon: 'none',
          });
          return
        }
        const {
          form
        } = this.data;
        form.hometown = address;
        this.setData({
          form
        })
      },
      fail: () => { },
      complete: () => { }
    });
  },

  selectAddress() {
    wx.chooseLocation({
      success: ({
        address
      }) => {
        if (!address) {
          wx.showToast({
            title: '请选择一个具体的位置',
            icon: 'none',
          });
          return
        }
        const {
          form
        } = this.data;
        form.address = address;
        this.setData({
          form
        })
      },
      fail: () => { },
      complete: () => { }
    });
  },
  bindInput(e) {
    const { value } = e.detail;
    const { prop } = e.target.dataset;
    const { form } = this.data;
    form[prop] = value
    this.setData({ form })
  },
  sexChange(e) {
    const { value } = e.detail;
    const { form } = this.data;
    form.sex = value;
    this.setData({ form })
  },
  async submit() {
    const { form } = this.data;
    if (!form.name || !form.password || !form.confirmPass) {
      wx.showToast({
        title: '请检查信息是否正确',
        icon: 'none',
      });
    } else if (form.password != form.confirmPass) {
      wx.showToast({
        title: '两次密码不正确',
        icon: 'none',
      });
    }
    form.registerType=this.data.registerType;
    const res = await register(form)
    app.handleRes(res, () => {
      wx.navigateBack({
        delta: 1
      });
    })
  },
  /**
   * Lifecycle function--Called when page is initially rendered
   */
  onReady: function () {

  },

  /**
   * Lifecycle function--Called when page show
   */
  onShow: function () {

  },

  /**
   * Lifecycle function--Called when page hide
   */
  onHide: function () {

  },

  /**
   * Lifecycle function--Called when page unload
   */
  onUnload: function () {

  },

  /**
   * Page event handler function--Called when user drop down
   */
  onPullDownRefresh: function () {

  },

  /**
   * Called when page reach bottom
   */
  onReachBottom: function () {

  },

  /**
   * Called when user click on the top right corner to share
   */
  onShareAppMessage: function () {

  }
})
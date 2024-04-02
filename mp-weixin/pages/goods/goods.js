// pages/creation/creation.js

import { insertGoods, listByUserId, deleteGoods } from "../../api/goods"
import { uploadImage } from "../../api/upload"


const app = getApp();

Page({
  /**
   * 页面的初始数据
   */
  data: {
    tabs: [
      {
        id: 1,
        name: "发布商品",
        isActive: true,
      },
      {
        id: 2,
        name: "我的商品",
        isActive: false,
      },
    ],
    categoryList: [],
    tabStyle: {
      tabBarStyle: "background-color:#cd8062",
      activeStyle: "color:#fff;font-weight: 700;",
    },
    list: [],
    form: {
      title: "",
      content: "",
      price: ""
    },
    imageList: [],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.init()
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

  },
  previewChooseImg(e) {
    // const {
    //   src
    // } = e.currentTarget.dataset;
    // const {
    //   imageList,
    //   videoList
    // } = this.data;
    // let index = [...imageList, ...videoList].findIndex(v => v == src)
    // if (index == -1) {
    //   index = 0
    // }
    // wx.previewMedia({
    //   current: index,
    //   sources: [...imageList.map(v => {
    //     return { url: v }
    //   }), ...videoList.map(v => {
    //     return { url: v, type: "video" }
    //   })], //所有要预览的图片的地址集合 数组形式
    // });
  },
  //
  inputInventory(e) {
    const { form } = this.data;
    form.inventory = e.detail.value;
    this.setData({ form })
  },
  inputPrice(e) {
    const { form } = this.data;
    form.price = e.detail.value;
    this.setData({ form })
  },
  inputTitle(e) {
    const { form } = this.data;
    form.title = e.detail.value;
    this.setData({ form })
  },

  inputContent(e) {
    const { form } = this.data;
    form.content = e.detail.html;
    this.setData({ form })
  },

  removeImage(e) {
    const {
      index
    } = e.currentTarget.dataset;
    const {
      imageList
    } = this.data;
    imageList.splice(index, 1);
    this.setData({
      imageList,
    });
  },


  clearForm() {
    this.createSelectorQuery().select('#editor').context(r => {
      if (!r) return
      r.context.setContents({
        html: ""
      })
    }).exec()
    this.setData({
      form: {
        title: "",
        content: "",
        price: "",
        inventory: ""
      },
      imageList: [],
    })
  },

  async creationSubmit(e) {

    // TODO 上传图片、视频
    const { form, imageList } = this.data;
    if (!imageList || imageList.length == 0) {
      wx.showToast({
        title: '必须上传一张图片',
        icon: 'none',
      });
      return
    }
    if (!form.content || !form.title || !form.price) {
      wx.showToast({
        title: '请输入标题和内容',
        icon: 'none',
      });
      return
    }

    const imagesUrl = []
    //上传图片与视频
    for (let i = 0; i < imageList.length; i++) {
      let path = imageList[i];
      const res = await uploadImage({ path })
      if (res.status === 200) {
        imagesUrl.push(res.data);
      }
      form.images = imagesUrl.join(";")
    }

    const res = await insertGoods(form)
    app.handleRes(res, "", () => {
      this.clearForm()
      this.init()
    })
  },
  selectTab(e) {
    const { index } = e.detail;
    const { tabs } = this.data;
    if (!tabs[index].isActive) {
      tabs.forEach((v) => (v.isActive = false));
      tabs[index].isActive = true;
      this.setData({
        tabs,
      });
    }
  },
  chooseImage() {
    const {
      imageList,
      videoList
    } = this.data;

    wx.chooseMedia({
      mediaType: ['image'],
      success: ({ tempFiles, type }) => {
        tempFiles.forEach((v) => {
          const { tempFilePath, fileType } = v;
          if (fileType == 'image') {
            imageList.push(tempFilePath);
          } else if (fileType == 'video') {
            videoList.push(tempFilePath);
          }
        });
        this.setData({
          imageList,
          videoList
        });
      },
    });
  },

  async init() {
    let res = await listByUserId()

    this.setData({ list: res.data })
    wx.stopPullDownRefresh()
  },
  deleteGoods(e) {
    wx.showModal({
      title: '提示',
      content: '是否删除该商品',
      showCancel: true,
      cancelText: '取消',
      cancelColor: '#000000',
      confirmText: '确定',
      confirmColor: '#3CC51F',
      success: async (result) => {
        if (result.confirm) {
          const { index } = e.currentTarget.dataset
          const { list } = this.data;
          const res = await deleteGoods({ id: list[index].id })
          app.handleRes(res, () => {
            list.splice(index, 1)
            this.setData({ list })
          })
        }
      },
      fail: () => { },
      complete: () => { }
    });
  }
})
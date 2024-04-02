// pages/creation/creation.js

import {
  insertNode,
  listByUserId,
  deleteNode,
} from "../../api/node"
import {
  listByUserId as goodsListByUserId,
} from "../../api/goods"
import {
  uploadImage,
  uploadVideo
} from "../../api/upload"


const app = getApp();

Page({
  /**
   * 页面的初始数据
   */
  data: {
    tabs: [{
      id: 1,
      name: "发表笔记",
      isActive: true,
    },
    {
      id: 2,
      name: "我的笔记",
      isActive: false,
    },
    ],
    statusList: [{
      value: "REQUIRE_CERTIFICATION",
      name: "需要认证"
    }, {
      value: "UNAUTHORIZED",
      name: "无需认证"
    }],
    tabStyle: {
      tabBarStyle: "background-color:#cd8062",
      activeStyle: "color:#fff;font-weight: 700;",
    },
    list: [],
    goodsList: [],
    form: {
      title: "",
      content: "",
    },
    imageList: [],
    videoList: [],
    selectShopTips: "",
    isShowContainer: false,
    user: {}
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
        form.address = address;
        this.setData({
          form
        })
      },
      fail: () => { },
      complete: () => { }
    });
  },
  previewChooseImg(e) {
    const {
      src
    } = e.currentTarget.dataset;
    const {
      imageList,
      videoList
    } = this.data;
    let index = [...imageList, ...videoList].findIndex(v => v == src)
    if (index == -1) {
      index = 0
    }
    wx.previewMedia({
      current: index,
      sources: [...imageList.map(v => {
        return {
          url: v
        }
      }), ...videoList.map(v => {
        return {
          url: v,
          type: "video"
        }
      })], //所有要预览的图片的地址集合 数组形式
    });
  },
  inputTitle(e) {
    const {
      form
    } = this.data;
    form.title = e.detail.value;
    this.setData({
      form
    })
  },
  inputContent(e) {
    const {
      form
    } = this.data;
    form.content = e.detail.html;
    this.setData({
      form
    })
  },
  removeVideo(e) {
    const {
      index
    } = e.currentTarget.dataset;
    const {
      videoList
    } = this.data;
    videoList.splice(index, 1);
    this.setData({
      videoList,
    });
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
  selectGoods() {
    this.setData({
      isShowContainer: true
    })
  },
  unSelectGoods() {
    this.setData({
      isShowContainer: false
    })
  },

  clearForm() {
    const {
      goodsList
    } = this.data;
    goodsList.forEach(v => v.check = false)

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
      },
      selectShopTips: "",
      imageList: [],
      videoList: [],
      goodsList
    })
  },

  async creationSubmit(e) {
    const {
      form,
      imageList,
      videoList,
      goodsList
    } = this.data;

    if (!imageList || imageList.length == 0) {
      wx.showToast({
        title: '请上传一张图片，作为封面',
        icon: 'none',
      });
      return
    }
    if (!form.title || !form.content || !form.address || !form.status) {
      wx.showToast({
        title: '请输入完整内容',
        icon: 'none',
      });
      return
    }

    const imagesUrl = []
    const videosUrl = []
    //上传图片与视频
    for (let i = 0; i < imageList.length; i++) {
      let path = imageList[i];
      const res = await uploadImage({
        path
      })
      if (res.status === 200) {
        imagesUrl.push(res.data);
      }
      form.images = imagesUrl.join(";")
    }
    if (videoList && videoList.length > 0) {
      for (let i = 0; i < videoList.length; i++) {
        let path = videoList[i];
        const res = await uploadVideo({
          path
        })
        if (res.status === 200) {
          videosUrl.push(res.data);
        }
      }
      form.videos = videosUrl.join(";")
    }
    form.goodsList = goodsList && goodsList.filter(v => v.check)

    const res = await insertNode(form)
    app.handleRes(res, "", () => {
      this.clearForm()
      this.init()
    })
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
    }
  },
  chooseImage() {
    const {
      imageList,
      videoList
    } = this.data;
    wx.chooseMedia({
      success: ({
        tempFiles,
        type
      }) => {
        tempFiles.forEach((v) => {
          const {
            tempFilePath,
            fileType
          } = v;
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
    let res = await goodsListByUserId()
    const goodsList = res.data
    if (goodsList) {
      goodsList.forEach(v => v.showMyNodeStatus = true)
    }
    res = await listByUserId()
    const list = res.data
    if (list) {
      list.forEach(v => v.showMyNodeStatus = true)
    }
    this.setData({
      list,
      goodsList,
      user: wx.getStorageSync("user")
    })
    wx.stopPullDownRefresh()
  },
  changeRadio(e) {
    const {
      goodsList
    } = this.data
    const {
      index
    } = e.currentTarget.dataset
    goodsList[index].check = !goodsList[index].check;

    this.setData({
      goodsList,
      selectShopTips: `已选中${goodsList.filter(v => v.check).length}个商品`
    })
  },
  statusChange(e) {
    const {
      value
    } = e.detail;
    const {
      statusList,
      form
    } = this.data;
    form.status = statusList[value].value
    form.statusName = statusList[value].name
    this.setData({
      form
    })
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
          const {
            id
          } = e.detail
          const res = await deleteNode({
            id
          })
          app.handleRes(res, () => {
            this.init()
          })
        }
      },
      fail: () => { },
      complete: () => { }
    });
  }
})
import { updatePassword } from "../../api/user"
const app = getApp();

Page({
  /**
   * 页面的初始数据
   */
  data: {
    password: "",
    checkPassword: "",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  async onLoad() { },

  async submit({ detail }) {
    if (this.checkForm(detail.value)) {
      return;
    }
    const { oldPassword, password: newPassword } = detail.value;
    //找回密码
    var user1 = wx.getStorageSync('user');
    var userId = user1.id;
    const res = await updatePassword({
      userId,oldPassword, newPassword
    });
    app.handleRes(res, () => {
      wx.clearStorageSync();
      wx.navigateTo({
        url: '/pages/login/login',
      });
    })
  },

  checkForm({ oldPassword, password, checkPassword }) {
    let title;
    if (!oldPassword || !oldPassword.trim()) {
      title = "请输入旧密码";
    } else if (!password || !password.trim()) {
      title = "请输入密码";
      this.setData({
        password: "",
        checkPassword: "",
      });
    } else if (!checkPassword) {
      title = "请确认密码";
    } else if (password !== checkPassword) {
      title = "两次密码不一致";
      this.setData({
        password: "",
        checkPassword: "",
      });
    }
    if (title) {
      wx.showToast({
        title: title,
        icon: "none",
      });
    }
    return title;
  },
});

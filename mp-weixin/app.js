// app.js
import {
  handleRes
} from './utils/handleRes';

import { login } from "./api/user"

App({
  onLaunch() {
    const user = wx.getStorageSync("user");
    if (user && user.id) {
      wx.login({
        timeout: 10000,
        success: async (result) => {
          const res = await login(user)
          if (res.status == 200) {

            wx.setStorageSync("user", res.data);
          }
        },
        fail: () => { },
        complete: () => { },
      });
    }

  },
  globalData: {
    BASE_URL: "http://localhost:8080"
  },

  handleRes
})
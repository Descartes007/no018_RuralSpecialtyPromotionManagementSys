let loadTimes = 0;
export const request = (params) => {
  if (!params.hideLoading) {
    loadTimes++;
    wx.showLoading({
      title: "努力加载中",
      mask: true,
    });
  }

  const TOKEN = wx.getStorageSync("TOKEN");
  if (TOKEN) {
    params.header = { Authorization: TOKEN };
  }
  // }
  const { BASE_URL } = getApp().globalData;
  return new Promise((resolve, reject) => {
    wx.request({
      ...params,
      url: BASE_URL + params.url,
      success: (result) => {
        if (result.data.status === 401 || result.data.status === 403) {
          //用户无权限
          wx.showModal({
            title: "提示",
            content: result.data.message,
            showCancel: false,
            confirmColor: "#3CC51F",
            success: (result) => {
              if (result.confirm) {
                wx.redirectTo({
                  url: '/pages/login/login',
                });
              }
            },
          });
          reject(result);
        } else {
          resolve(result.data);
        }

        // 判断请求头中是否存在新token
        const token = result.header.SET_TOKEN;
        if (token) {
          wx.setStorageSync("TOKEN", token);
        }
      },
      timeout: 150000,
      fail: (error) => {
        reject(reject);
      },
      complete() {
        if (!params.hideLoading) {
          loadTimes--;
          if (loadTimes === 0) {
            wx.hideLoading();
          }
        }
      },
    });
  });
};


export const upload = (params) => {
  loadTimes++;
  wx.showLoading({
    title: "努力加载中",
    mask: true,
  });
  const TOKEN = wx.getStorageSync("TOKEN");
  if (TOKEN) {
    params.header = { Authorization: TOKEN };
  }
  const { BASE_URL } = getApp().globalData;
  return new Promise((resolve, reject) => {
    wx.uploadFile({
      ...params,
      url: BASE_URL + params.url,
      filePath: params.path,
      name: "file",
      success: (result) => {
        const res = JSON.parse(result.data);
        resolve(res);
      },
      fail: (error) => {
        reject(reject);
      },
      complete() {
        loadTimes--;
        if (loadTimes === 0) {
          wx.hideLoading();
        }
      },
    });
  });
};

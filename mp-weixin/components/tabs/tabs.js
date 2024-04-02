// components/Tabs/Tabs.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    tabs: {
      type: Array,
      value: [],
    },
    tabStyle: {
      type: Object,
      value: {
        tabBarStyle: "background-color:#cd8062",
        activeStyle: "color:#fff;font-weight: 700;",
      },
    },
  },

  /**
   * 组件的初始数据
   */
  data: {},

  /**
   * 组件的方法列表
   */
  methods: {
    bindChange(e) {
      this.triggerEvent("tabsChange", { index: e.currentTarget.dataset.index });
    },
  },
});

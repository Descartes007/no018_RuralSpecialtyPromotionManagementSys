// components/node/node.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    list: {
      type: Array,
      default: []
    }
  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {
    handleDeleteNode(e) {
      this.triggerEvent('handleDeleteNode', { id: e.currentTarget.dataset.id })
      return false
    },
    handleReleaseNode(e) {
      this.triggerEvent('handleReleaseNode', { id: e.currentTarget.dataset.id })
      return false
    },
    handleDefeatNode(e) {
      this.triggerEvent('handleDefeatNode', { id: e.currentTarget.dataset.id })
      return false
    },
    handleAuditReleaseNode(e) {
      this.triggerEvent('handleAuditReleaseNode', { id: e.currentTarget.dataset.id })
      return false
    },
    handleAuditDefeatNode(e) {
      this.triggerEvent('handleAuditDefeatNode', { id: e.currentTarget.dataset.id })
      return false
    },
    handleNavDetail(e) {
      const { id } = e.currentTarget.dataset
      wx.navigateTo({
        url: '/pages/node_detail/node_detail?id=' + id,
      });
    }
  }
})

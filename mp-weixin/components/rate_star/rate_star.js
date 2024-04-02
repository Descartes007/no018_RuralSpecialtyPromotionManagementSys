// components/rateStar.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    rateObject: {
      type: [Number, String],
      default: 0
    },
    modelData: {
      type: Object,
      default: {
        showModel: true,
        showCancel: true,
        showNextRemind: true,
        showInput: true,
        inputPlaceholder: ""
      }
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    grayScore: 5,
    redScore: 0,
    inputText: "",
    nextShowCheck: false
  },

  /**
   * 组件的方法列表
   */
  methods: {
    showCancelOrder: function () {
      this.setData({
        showModal: true
      })
    },
    cancel() {
      // const { modelData } = this.data;
      // modelData.showModel = false
      // this.setData({ modelData })
      this.triggerEvent('modelCancel', this.data)
    },
    // 确定
    sure() {
      // const { modelData } = this.data;
      // modelData.showModel = false
      // this.setData({ modelData })
      this.triggerEvent('modelConfirm', this.data)
    },
    click() {


    },
    changeInput: function (e) {
      this.setData({
        inputText: e.detail.value
      })
    },
    changeRadio(e) {
      const {
        nextShowCheck
      } = this.data
      this.setData({
        nextShowCheck: !nextShowCheck
      })

    },
    giveScore(e) {
      var redIndex = e.currentTarget.dataset.redindex;
      var greyIndex = e.currentTarget.dataset.greyindex;

      var m_redScore = this.data.redScore;
      var m_grayScore = this.data.greyScore;

      if (greyIndex != undefined) {
        m_redScore += greyIndex + 1;
        m_grayScore = 5 - m_redScore;
      }
      if (redIndex != undefined) {
        m_redScore = redIndex + 1;
        m_grayScore = 5 - m_redScore;
      }

      this.setData({
        redScore: m_redScore,
        grayScore: m_grayScore
      })

      // 选中的项目
      // console.log(this.properties.rateObject)

      // // console.log('score:',m_redScore)
      // this.$emit('change', {
      // 	value: m_redScore,
      // 	rateObj: this.rateObject,
      // })

      this.triggerEvent('change', {
        value: this.data.redScore,
        rateObj: this.properties.rateObject
      })
    },
  }
})
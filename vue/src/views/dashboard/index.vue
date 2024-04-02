<template>
  <div class="dashboard-container" :style="{backgroundImage:`url(${login1_bg})`}">
    <h1 align="center"><h2 class="white" ><font color=white>墟里烟 后台管理系统</font></h2></h1>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import adminDashboard from './admin'
import editorDashboard from './editor'

export default {
  name: 'Dashboard',
  components: { adminDashboard, editorDashboard },
  data() {
    return {
      login1_bg:require('@/assets/images/login_bg2.jpg'),
      currentRole: 'adminDashboard'
    }
  },
  computed: {
    ...mapGetters([
      'roles'
    ])
  },
  created() {
    if (!this.roles.includes('admin')) {
      this.currentRole = 'editorDashboard'
    }
  }
}
</script>
<style scoped>
.dashboard-container{
  width: 100%;
  height: 100vh;
  background-size: cover;
  overflow: hidden;
}

</style>

<template>
  <div>
    <tool-bar></tool-bar>
    <div id="wrap">
      <div id="header">
        <!-- 백쪽에서 디비 건들고 그 다음에 작업 하자 -->
        <!-- fetched.notice_id, -->
      </div>
      <div id="content">

      </div>
    </div>
    <footer-area id="footer"></footer-area>
  </div>
</template>

<script>

import ToolBar from '../components/ToolBar.vue'
import FooterArea from '../components/FooterArea.vue'

export default {
components:{
    ToolBar,
    FooterArea,
  },
computed:{
  fetched(){
    return this.$store.state.notice;
  },
},
created(){
  const notice_id = this.$route.params.id;
  this.$store.dispatch('FETCH_NOTICE_DETAIL', notice_id);
},

}
</script>

<style>

</style>
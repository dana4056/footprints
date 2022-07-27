<template>
  <div>
    <tool-bar></tool-bar>
    <div id="wrap">
      <div id="header">
        {{ this.$store.state.notice.title }}
        <div id="date">
          {{ this.$store.state.notice.post_time }}  
        </div>
      </div>
      <div id="content">
        {{ this.$store.state.notice.content }}
      </div>
      <div id="view_count">
        {{ this.$store.state.notice.view_num}}
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
created(){
  const notice_id = this.$route.params.id;
  this.$store.dispatch('FETCH_NOTICE_DETAIL', notice_id);
  setTimeout(() => { 
    return this.$store.state.notice;
  }, 100); 	
},

}
</script>

<style>

</style>
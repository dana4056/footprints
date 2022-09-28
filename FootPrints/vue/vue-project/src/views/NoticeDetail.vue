<template>
  <div>
    <tool-bar></tool-bar>
    <div id="wrap">
      <div class="header">
        <img src="../assets/notice.png">
        <h2 id="title">{{ this.$store.state.notice.title }}</h2>
        <div class="info">
          <p id="view">{{ this.$store.state.notice.post_time }}</p>
          <p id="date">조회수 {{ this.$store.state.notice.view_num }}</p>
        </div>
      </div>
      <div class="content"> 
        <p v-for="content in this.$store.state.notice.content.split('\n')" v-bind:key="content">{{content}}</p>
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
  data() {
    return {
      content: ""
    }
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

<style scoped>
#wrap {
  width: 700px;
  height: 600px;
  margin: 50px auto;
  border: 2px solid rgba(0, 0, 0, 0.558);
  border-radius: 40px;
}
img {
  width: 40px;
  height: 40px;
  margin: 32px 0 0 25px;
}
.header {
  height: 100px;
  border-bottom: 2px solid rgba(0, 0, 0, 0.558);
}
.header > * {
  float: left;
}
#title {
  width: 450px;
  margin: 33px 0 0 20px;
  font-family: 'Noto Sans KR', sans-serif;
  text-align: left;
}
.info {
  width: 150px;
}
#view, #date {
  width: 100%;
  margin: 22px 0 0 0 ;
  color: #666;
  font-size: 14px;
}
.content {
  height: 460px;
  padding: 5px 20px;
  text-align: left;
  overflow: auto;
}
</style>
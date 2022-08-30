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
        <p v-for="c in content" v-bind:key="c">{{c}}</p>
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

  this.content = this.$store.state.notice.content.split('\n');
  
  this.$store.dispatch('FETCH_NOTICE_DETAIL', notice_id);
  setTimeout(() => { 
    return this.$store.state.notice;
  }, 100); 	
},

}
</script>

<style scoped>
#wrap {
  margin: 50px auto;
  width: 700px;
  height: 600px;
  border: 2px solid rgba(0, 0, 0, 0.558);
  border-radius: 40px;
}
img {
  margin: 32px 0 0 25px;
  height: 40px;
  width: 40px;
}
.header {
  height: 100px;
  border-bottom: 2px solid rgba(0, 0, 0, 0.558);
}
.header > * {
  float: left;
}
#title {
  font-family: 'Noto Sans KR', sans-serif;
  width: 450px;
  margin: 33px 0 0 20px;
  text-align: left;
}
.info {
  width: 150px;
}
#view, #date {
  width: 100%;
  margin: 22px 0 0 0 ;
  font-size: 14px;
  color: #666;
}
.content {
  padding: 5px 20px;
  text-align: left;
  height: 460px;
  overflow: auto;
}
</style>
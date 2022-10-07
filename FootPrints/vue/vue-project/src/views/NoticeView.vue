<template>
  <div>
    <tool-bar></tool-bar>

    <div class="wrap">
      <div id="header">
        <h1>공지사항</h1>
        <!--------------- 관리자 계정만 볼 수 있음 ----------------->
        <div class="add-btn" v-if="authority==='ROLE_ADMIN'?true:false">
            <router-link to="/notice/new-notice" class="link">
              <i class="fa-solid fa-circle-plus fa-2x"></i>
            </router-link>
        </div>
        <!-------------------------------------------------------->
      </div>

      <div class="notice-content">
        <div class="listbox" id="table-header">
          <h4 class="num">번호</h4>
          <h4 class="title">제목</h4>
          <h4 class="date">등록일</h4>
          <h4 class="view">조회수</h4>
        </div>
        <div v-for="(notice, index) in this.$store.state.noticeList" v-bind:key="notice">
          <router-link v-bind:to="`/notice/${notice.id}`" class="listbox" v-if="(page - 1) * 10 <= index && index < page * 10">
            <p class="num">{{ notice.id }}</p>
            <p class="title" id="title">{{ notice.title }}</p>
            <p class="date" id="date">{{notice.post_time.split(' ')[0]}}</p> 
            <p class="view" id="view">{{notice.view_num}}</p>
          </router-link>
        </div>
      </div>
      <div class="pageSelect" v-bind:style="{width: (range * 30) + 'px'}">
        <div v-for ="index in range" v-bind:key="index" v-on:click="movePage" :class="index == page ? 'checked' : 'none'">
            {{index}}
        </div>
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
      page: 1,
      range: 1,
      authority:this.$store.state.authority,
    }
  },
  created(){
    this.$store.dispatch('FETCH_NOTICE_LIST');
    this.range = parseInt(this.$store.state.noticeList.length/10) + 1;
  },
  methods: {
    movePage(event) {
      this.page = event.target.textContent;
    }
  }
}
</script>

<style scoped>
.wrap{
  width: 900px;
  height: 835px;
  margin: 0 auto;
}
#header{
  display: flex;
  padding: 30px 0;
  border-bottom: 2px solid black;
  align-items: center;
}
.notice-content {
  height: 670px;
}
.notice-content .listbox {
  float: left;
}
h1 {
  width: 100%;
  margin: 0 0;
  display: inline;
  font-family: 'Noto Sans KR', sans-serif;
  text-align: left;
}
#table-header:hover {
  background-color: white;
  pointer-events: none;
}
.listbox {
  border-bottom: 1px solid rgba(0, 0, 0, 0.205);
}
.listbox *{
  height: 42px;
  margin: 16px 0 0 0;
  float: left;
}
.listbox:hover {
  background-color: #ddd;
  cursor: pointer;
}
.num {
  width: 100px;
}
.title {
  width: 500px;
}
#title {
  box-sizing: border-box; 
  padding-left: 50px;
  text-align: left;
}
.date {
  width: 150px;
}
.view {
  width: 150px;
}
#date, #view {
  color: rgb(122, 122, 122);
  font-size: 15px;
}
.add-btn{
  display: inline;
  -webkit-box-flex:1;
  text-align: right;
}
a {
  color: black;
  text-decoration: none;
}
.pageSelect {
  height: 30px;
  margin: 0 auto;
}
.pageSelect > *{
  width: 30px;
  margin: 0 0;
  font-size: 17px;
  float: left;
  cursor: pointer;
}
.checked {
  font-weight: bold;
  text-decoration: underline;
}
</style>
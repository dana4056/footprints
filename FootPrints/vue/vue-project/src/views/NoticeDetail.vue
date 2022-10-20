<template>
  <div>
    <tool-bar></tool-bar>
    <div id="wrap">
      <div class="header">
        <!-- <img src="../assets/notice.png"> -->
        <span class="badge">공지</span>
        <h2 id="title">{{ this.$store.state.notice.title }}</h2>
        <div class="info">
          <p id="view">{{ this.$store.state.notice.post_time }}</p>
          <p id="date">조회수 {{ this.$store.state.notice.view_num }}</p>
        </div>
      </div>
      <div class="content">
        <p v-for="content in this.$store.state.notice.content.split('\n')" v-bind:key="content">{{content}}</p>
      </div>
      <!-- 관리자만 수정 삭제 보이도록 -->
      <div id="adminBox" v-if="fetched.nick == this.admin_id">
        <button class="btn" type="button" id="delete" v-on:click="deleteNotice">삭제</button>
        <button class="btn" type="button" id="amend" v-on:click="amendNotice">수정</button>
      </div>
      <router-link to="/notice/post"><button class="btn">목록으로</button></router-link>

    </div>
    <footer-area id="footer"></footer-area>
  </div>
</template>

<script>

import ToolBar from '../components/ToolBar.vue'
import FooterArea from '../components/FooterArea.vue'
import Swal from 'sweetalert2';

export default {
  components:{
    ToolBar,
    FooterArea,
  },
  data() {
    return {
      notice_id: 0,
      content: "",
      admin_id : "admin",
    }
  },
  computed:{
    fetched(){
      return this.$store.getters.GET_MEMBER;
    },
  },
  created(){
    this.notice_id = this.$route.params.id;
    this.$store.dispatch('FETCH_NOTICE_DETAIL', this.notice_id);
    setTimeout(() => { 
      return this.$store.state.notice;
    }, 100); 	
  },
  methods: {
    amendNotice() {
      this.$router.replace("/notice/" + this.notice_id + "/amend");
    },
    deleteNotice() {
      this.$store.dispatch('DELETE_NOTICE', this.notice_id);

      Swal.fire({
        icon: 'success',
        title: '공지사항 삭제 완료!',
        confirmButtonText: '공지사항 목록 보러가기',
      }).then(() => {
        this.$router.replace("/notice/post");
      })
    },
  }
}
</script>

<style scoped>
#wrap {
  width: 700px;
  height: 600px;
  margin: 50px auto;
  position: relative;
  /* border: 2px solid rgba(0, 0, 0, 0.558);
  border-radius: 40px; */
}
/* img {
  width: 40px;
  height: 40px;
  margin: 32px 0 0 25px;
} */
.header {
  height: 100px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.558);
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

#join, #chat, #amend, #delete {
  border: none;
}

.button{
  height: 40px;
  margin: 0 5px;
  padding: 0 18px;
  border-radius: 20px;
  font-weight: bold;
  float: right;
  cursor: pointer;
}

.badge{
  background: #db5959;
  font-size: 12px;
  color: #fff;
  width: 50px;
  height: 22px;
  line-height: 22px;
  border-radius: 3px;
  margin: 42px 0 0 25px;
}
.btn{
  margin: auto 3px;
  position: absolute;
  right: 15px;
  width: 89px;
  height: 32px;
  background-color: #afafaf;
  border: 1px solid #afafaf;
  border-radius: 10px;
  color: #fff;
}
#delete{
  /* position: absolute; */
  background-color: #db5959;
  border: 1px solid #db5959;
  right: 205px;
}
#amend{
  /* position: absolute; */
  background-color: #f3f3f3;
  border: 1px solid #afafaf;
  color: #afafaf;
  right: 110px;
}
</style>
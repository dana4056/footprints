<template>
  <div>
    <tool-bar></tool-bar>
    <div id="wrap">
      <div class="inputArea">
        <div class="header">
          <input v-model="notice_title" id="title" placeholder="제목을 입력하세요.">
        </div>
        <div class="content">
          <textarea v-model="notice_content" id="notice_content" type="text" placeholder="공지사항 내용 적기"></textarea>
        </div>
      </div>
      <button type="submit" v-on:click.prevent="authorization">등록하기</button>
    </div>
    <footer-area id="footer"></footer-area>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import FooterArea from '../components/FooterArea.vue'
import dayjs from "dayjs";

export default {
  components:{
      ToolBar,
      FooterArea,
  },
  data() {
    return {
      notice_title: "",     // 글 제목
      notice_content: "",  // 글 내용
    }
  },
  methods: {
    authorization(){
      if (this.submitData()){
        setTimeout(() => { 
            this.register();
        }, 500);   
      }
      else {
        alert("입력이 올바른지 확인해주세요.")
      }
    },
    register() {
        const noticeDTO = {
          title: this.notice_title,           // 글 제목
          author: "admin",
          post_time: dayjs().format("YYYY.MM.DD"),
          content: this.notice_content,     // 글 내용
          view_num: 0,
        }
        this.$store.dispatch('POST_NOTICE', noticeDTO);
        this.$router.replace(`/home`);
			},
      submitData(){
        if(this.notice_title != "" && this.notice_content != ""){
          return true;
        }
        else{
          return false;
        }
      },
    },
}
</script>

<style scoped>
#wrap {
  width: 700px;
  margin: 50px auto;
}
.inputArea {
  height: 600px;
  border: 2px solid rgba(0, 0, 0, 0.558);
  border-radius: 40px;
}
.header {
  height: 100px;
  border-bottom: 2px solid rgba(0, 0, 0, 0.558);
}
#title {
  box-sizing: border-box; 
  width: 95%;
  margin-top: 20px;
  padding: 15px 2px;
  border: none;
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 25px;
  font-weight: bold;
  text-align: left;
  outline: none;
}
.content {
  height: 460px;
  text-align: left;
}
textarea {
  box-sizing: border-box; 
  height: 100%;
  width: 100%;
  padding: 20px 22px;
  border:none;
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 16px;
  outline: none;
  resize: none;
}
button {
  width: 150px;
  height: 50px;
  margin-top: 20px;
  background-color:#a4caac;
  border: none;
  border-radius: 13px;
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 15px;
  cursor: pointer;
}
</style>
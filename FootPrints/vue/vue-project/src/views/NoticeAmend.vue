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
      <button type="submit" v-on:click.prevent="authorization">수정하기</button>
    </div>
    <footer-area id="footer"></footer-area>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import FooterArea from '../components/FooterArea.vue'
import Swal from 'sweetalert2';
// import dayjs from "dayjs";

export default {
  components:{
      ToolBar,
      FooterArea,
  },
  data() {
    return {
      id: this.$store.state.notice.id,
      notice_title: this.$store.state.notice.title,
      author: this.$store.state.notice.author,
      post_time: this.$store.state.notice.post_time,
      notice_content: this.$store.state.notice.content,
      view_num: this.$store.state.notice.view_num,
    }
  },
  beforeCreate() {
    this.$store.dispatch('FETCH_USER');
    const notice_id = this.$route.params.id;
    this.$store.dispatch('FETCH_NOTICE_DETAIL', notice_id);

    setTimeout(() => {
      let notice = this.$store.state.notice;
      this.id = notice.id;
      this.notice_title = notice.title;     // 글 제목
      this.author = notice.author;  // 글 내용
      this.post_time = notice.post_time;      // 음식 카테고리
      this.notice_content = notice.content;      // 음식 나눌 장소
      this.view_num = notice.view_num;

      console.log("fetch nick: ",this.$store.state.notice.author);
      if(this.$store.state.notice.author !== "admin"){ // 관리자라 가정
        alert("게시물을 수정할 수 있는 권한이 없습니다.");
        this.$router.replace(`/notice/${this.notice_id}`);
      }
    }, 800);
  },
  methods: {
    authorization(){
      if (this.submitData()){
        setTimeout(() => { 
            this.register();
        }, 100);   
      }
      else {
        alert("입력이 올바른지 확인해주세요.")
      }
    },
    register() {
        const noticeDTO = {
          id : this.id,
          title: this.notice_title,           // 글 제목
          author: this.author,
          post_time: this.post_time,
          content: this.notice_content,     // 글 내용
          view_num: this.view_num,
        }
        console.log("NOTICEDTO\n",noticeDTO);
        this.$store.dispatch('AMEND_NOTICE', noticeDTO);
        Swal.fire({
          icon: 'success',
          title: '공지사항이 수정되었습니다.',
          confirmButtonText: '공지사항 목록 보러가기',
        }).then(() => {
          this.$router.replace(`/notice/post`);
        })
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
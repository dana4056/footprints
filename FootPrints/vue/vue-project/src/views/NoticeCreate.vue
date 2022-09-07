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
import Swal from 'sweetalert2';
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
        // this.$store.dispatch('FETCH_USER')//말고 권한을 가져오는 api 하나 생성
        setTimeout(() => { 
          // if(권한이 관리자 권한이라면)
            this.register();
        }, 100);   
      }
      else {
        alert("입력이 올바른지 확인해주세요.")
      }
    },
    register() {
        const noticeDTO = {
          title: this.notice_title,           // 글 제목
          // author: this.$store.state.member.nick,
          author: "임시로 넣어봤다네",
          post_time: dayjs().format("YYYY-MM-DD HH:mm:ss"),
          content: this.notice_content,     // 글 내용
          view_num: 0,
        }
        console.log("NOTICEDTO\n",noticeDTO);
        this.$store.dispatch('POST_NOTICE', this.noticeDTO);

				Swal.fire({
          icon: 'success',
          title: '공지사항이 등록되었습니다.',
          confirmButtonText: '공지사항으로 가기',
        }).then(() => {
          this.$router.replace("/notice/post");
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
    font-family: 'Noto Sans KR', sans-serif;
    box-sizing: border-box; 
    width: 95%;
    margin-top: 20px;
    padding: 15px 2px;
    font-size: 25px;
    border: none;
    outline: none;
    font-weight: bold;
    text-align: left;
  }
  .content {
    text-align: left;
    height: 460px;
  }
  textarea {
    font-family: 'Noto Sans KR', sans-serif;
    box-sizing: border-box; 
    height: 100%;
    width: 100%;
    padding: 20px 22px;
    font-size: 16px;
    border:none;
    outline: none;
    resize: none;
  }
  button {
  font-family: 'Noto Sans KR', sans-serif;
  width: 150px;
  height: 50px;
  margin-top: 20px;
  background-color:#a4caac;
  font-size: 15px;
  border: none;
  border-radius: 13px;
  cursor: pointer;
}
  </style>
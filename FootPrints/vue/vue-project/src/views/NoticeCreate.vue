<template>
  <div>
    <tool-bar></tool-bar>
    <div id="wrap">
      <input v-model="notice_title" id="notice_title" placeholder="제목을 입력하세요.">
      <hr>
      <textarea v-model="notice_content" id="notice_content" type="text" placeholder="공지사항 내용 적기"></textarea>
      <button type="submit" v-on:click.prevent="authorization">등록</button>
    </div>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import Swal from 'sweetalert2';
import dayjs from "dayjs";

export default {
  components:{
      ToolBar,
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
          author: this.$store.state.member.nick,
          post_time: dayjs(),
          content: this.notice_content,     // 글 내용
          view_num: 0,
        }
        console.log("NOTICEDTO\n",noticeDTO);
        this.$store.dispatch('POST_NOTICE', noticeDTO)
        // 동기처리가 필요없어 어차피 페이지 넘어가는 사이에 다 처리되고 나니까 즉시 보이는게 문제가 생기는게 아니니까
				Swal.fire({
          icon: 'success',
          title: '공지사항이 등록되었습니다.',
          confirmButtonText: '홈페이지로 가기',
        }).then(() => {
          this.$router.replace("/home");
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
#notice_title{
  font-weight: bold;
  font-size: 25px;
  color: #555;
  border: none;
  outline: none;
  margin-top: 5px;
  padding-left: 5px;
}
#notice_content{
  font-size: 15px;
  height: 400px;
  width: 300px;
  padding: 5px;
  border: none;
  outline: none;
}
#wrap {
  background-color: #f2f2f2;
  height: 800px;
  padding: 80px 0;
}
</style>
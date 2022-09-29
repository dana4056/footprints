<template>
  <div>
    <tool-bar></tool-bar>
    <div class = "container">
      <div class="room">
        <div class="roomHeader">
          <img src="../assets/people.png">
          <h2>밥프렌즈</h2>
        </div>
        <div class="chatRoom">
          <ul v-if="this.$store.state.postIdList[0] != 0">
            <li v-for="(room, index) in this.$store.state.roomList" :key=room v-bind:id="[`${index}`]" v-on:mousedown.left="clickRoom" class="room_info" v-bind:class="{ 'on': chekcedArr[index] }">
            <img :src="require('../assets/' + room.category + '.png')" id="roomImg">
            <h3>{{ room.post_name }}</h3>
            <!-- 마지막 메세지 구현 필요 -->
            </li>
          </ul>
        </div>
      </div>

      <div class="chat">
        <div class="chatHeader">
          <div v-if="this.$store.state.postIdList[0] != 0">
            <p id="title">{{ this.$store.state.roomList[this.$store.state.roomIndex].post_name }}</p>
            <p id="userList" v-for="user in this.$store.state.userList" v-bind:key="user"> {{ user }}</p>
            <button v-if="this.$store.state.roomList[this.$store.state.roomIndex].member.nick != this.$store.state.member.nick" v-on:click="exitPost">나가기</button>
          </div>
        </div>

        <div class="chat__body" id="chat__body">
          <div v-for="(chat) in this.$store.state.chatLogs" :key="chat">
            <div v-if="chat.from_name === this.my_nick" class="chat__mymessage" :class="[isSame ? '' : 'chat__first']">
              <p class="chat__yourmessage__time">{{ chat.time.slice(10, 16) }}</p>
              <p class="chat__mymessage__paragraph">{{ chat.message }}</p>
            </div>
            <div v-else class="chat__yourmessage" :class="[isSame ? '' : 'chat__first']">
              <!-- <div class="chat__yourmessage__avartar">
              프로필 사진 - 추후 수정 필요
              <img :src="avatar" alt="" v-if="!isSame" class="chat__yourmessage__img"/>
              </div> -->
              <div>
                <p class="chat__yourmessage__user" v-if="!isSame">  {{ chat.from_name }} </p>
                <div class="chat__yourmessage__p">
                  <p class="chat__yourmessage__paragraph">  {{ chat.message }} </p>
                  <p class="chat__yourmessage__time"> {{  chat.time.slice(10, 16) }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="chatForm">
          <input class="form__input" type="text" placeholder="메세지를 입력하세요."  v-model.trim="msg" @keyup.enter="submitMessage"/>
          <div @click="submitMessage" class="form__submit">
            <svg  width="30" height="30" viewBox="0 0 68 68" fill="#CCCCCC" xmlns="http://www.w3.org/2000/svg">
              <g clip-path="url(#clip0_26_10)">
                <path fill-rule="evenodd" clip-rule="evenodd" d="M48.0833 19.799C48.619 20.3347 48.806 21.127 48.5665 21.8457L35.8385 60.0294C35.5946 60.7614 34.9513 61.2877 34.1855 61.382C33.4198 61.4763 32.6681 61.1217 32.2539 60.4707L22.593 45.2893L7.41158 35.6285C6.76065 35.2142 6.40604 34.4625 6.50031 33.6968C6.59458 32.931 7.12092 32.2878 7.85287 32.0438L46.0366 19.3159C46.7553 19.0763 47.5476 19.2633 48.0833 19.799ZM26.5903 44.1204L33.3726 54.7782L42.0926 28.6181L26.5903 44.1204ZM39.2642 25.7897L23.7619 41.292L13.1041 34.5097L39.2642 25.7897Z" fill=""/>
              </g>
              <defs>
                <clipPath id="clip0_26_10">
                  <rect width="48" height="48" fill="white" transform="translate(33.9412) rotate(45)" />
                </clipPath>
              </defs>
            </svg>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import { router } from '../routes/index.js';
import Swal from 'sweetalert2';
// import Stomp from 'webstomp-client'
// import SockJS from 'sockjs-client'

export default {
  data() {
    return {
      dataNum: 0,
      chekcedArr: [],
      isSame: false,
      my_nick: "",
      msg: "",
      post_id: 0,
      recvList : [],
    }
  },
  components: {
    ToolBar,
  },
  created() {
    this.my_nick = this.$store.state.member.nick;
    if(localStorage.getItem('jwt') == null) {
      router.replace("/home");
    }
    else if(this.$store.state.postIdList[0] != 0) {
      let post_id = this.$store.state.postIdList[this.$store.state.roomIndex];
      this.$store.dispatch('FIND_USER', post_id);
      this.$store.dispatch('FIND_CHAT_LOGS', post_id);

      this.$store.state.roomIndex = 0;
      this.dataNum = this.$store.state.postIdList.length;
      for (let i = 0; i < this.dataNum; i++) {
        if (i != 0) {
        this.chekcedArr[i] = false;
        }
        else {
          this.chekcedArr[i] = true;
        }
      }
    }
  },
  mounted() {
    setTimeout(() => {
      const element = document.getElementById("chat__body");
      element.scrollTop = element.scrollHeight;

      const e = document.getElementsByClassName("room__list")
      e.scrollTop = e.scrollHeight;
    }, 0);
  },
  methods: {
    // connect(){
    //  const serverURL = "/socket-open"
    //  let socket = new SockJS(serverURL);  // 소켓 열 주소
    //  console.log("소켓 열기 : serverURL");
    //  this.stompClient = Stomp.over(socket);
    //  this.stompClient.connect({}, function (frame) {
    //     console.log('Connected: ' + frame);
    //     this.stompClient.subscribe('/sub/send', res => {
    //       console.log('구독으로 받은 메시지 입니다.', res.body);
    //       this.recvList.push(JSON.parse(res.body))
    //       console.log(this.recvList);
    //     })
    //  });
    // },
    clickRoom(li) {
      this.chekcedArr[this.$store.state.roomIndex] = false;
      this.$store.state.roomIndex = li.currentTarget.id;
      this.chekcedArr[this.$store.state.roomIndex] = true;

      let post_id = this.$store.state.postIdList[this.$store.state.roomIndex];
      this.$store.dispatch('FIND_USER', post_id);
      this.$store.dispatch('FIND_CHAT_LOGS', post_id);
    },
    exitPost() {
      const roomInfo = {
        nick: this.$store.state.member.nick,
        post_id: this.$store.state.roomList[this.$store.state.roomIndex].post_id
      };
      this.$store.dispatch('EXIT_DELIVERY_POST', roomInfo);

      Swal.fire({
        icon: 'success',
        title: '채팅방 나가기 성공!',
        confirmButtonText: '배달 모집 목록 보러가기',
      }).then(() => {
        this.$router.replace("/delivery/post");
      })
    },
    submitMessage() {
      if (this.msg) {
        setTimeout(() => {
          const element = document.getElementById("chat__body");
          element.scrollTop = element.scrollHeight;
        }, 0);
        const post_id = this.$store.state.postIdList[this.$store.state.roomIndex];

        const date = new Date();
        const year = date.getFullYear();
        const month = ('0' + (date.getMonth() + 1)).slice(-2);
        const day = ('0' + date.getDate()).slice(-2);
        const dateString = year + '-' + month  + '-' + day;
        const hours = ('0' + date.getHours()).slice(-2); 
        const minutes = ('0' + date.getMinutes()).slice(-2);
        const seconds = ('0' + date.getSeconds()).slice(-2); 
        const timeString = hours + ':' + minutes  + ':' + seconds;

        const chatData = {
          from_name: this.$store.state.member.nick,
          time: dateString + " " + timeString,
          message: this.msg,
          post_id: post_id
        };
        this.$store.dispatch('POST_CHAT_DATA', chatData);

        // if (this.stompClient && this.stompClient.connected) {
        //     this.stompClient.send("/receive", JSON.stringify(this.chatData), {});
        // }
        this.$store.dispatch('FIND_CHAT_LOGS', post_id);

        this.msg = "";
      }
    },
  }
}
</script>

<style scoped>

.container {
  width: 1200px;
  margin: 3rem auto 65rem;
  background-color: #ffffff;
  border-radius: 1.5rem;
  box-shadow: 0px 1px 20px #7a7a9c55;
}
.room {
  width: 400px;
  height: 900px;
  background: #ffffff;
  border-radius: 24px 0px 0px 24px;
  box-shadow: 0px 3px 35px 0px rgba(0, 0, 0, 0.05);
  float: left;
}
.roomHeader {
  height: 96px;
  padding-top: 15px;
  border-bottom: 1px solid rgb(206, 206, 206);
}
.roomHeader > img {
  height: 40px;
  width: 40px;
  margin: 20px 0 0 125px;
  float: left;
}
.roomHeader > h2 {
  margin-left: 30px;
  float: left;
}
.chat {
  width: 800px;
  display: flex;
  float: right;
  flex-direction: column;
  justify-content: space-between;
}
.chat__header {
  padding: 0px 1.8rem 20px 1.8rem;
  background: #ffffff;
  border-radius: 0px 24px 0px 0px;
  box-shadow: 0px 3px 10px 0px rgba(0, 0, 0, 0.05);
  text-align: left;
}

ul {
  height: 770px;
  margin: 0 0 ;
  padding: 0 0;
  overflow: scroll;
  scroll-behavior: smooth;
}
ul::-webkit-scrollbar {
  display: none;
}
.room_info {
  height: 130px;
  border-bottom: 1px solid rgb(235, 235, 235);
  cursor: pointer;
}
.room_info > * {
  margin-left: 50px;
  float: left;
}
#roomImg {
  width: 60px;
  height: 60px;
  margin-top: 30px;
}
.chatRoom h3 {
  width:200px;
  margin-top: 30px;
  text-align: left;
}
.chatRoom p {
  margin-top: 0px;
  color: rgb(180, 180, 180);
}
.chatRoom .on {
  background-color: rgb(234, 234, 234);
}

.chatHeader {
  height: 91px;
  padding: 0px 1.8rem 20px 1.8rem;
  background: #ffffff;
  border-radius: 0px 24px 0px 0px;
  text-align: left;
  box-shadow: 0px 3px 10px 0px rgba(0, 0, 0, 0.05);
}
#title {
  font-size: 20px;
  font-weight: 700;
}
#userList {
  margin: 0 20px 0 0;
  padding: 0 0;
  font-size: 14px;
  float: left;
}
.chatHeader button {
  padding: 7px;
  border: none;
  border-radius: 15%;
  background-color: rgba(255, 103, 103, 0.526);
  font-size: 14px;
  font-weight: bold;
  float:right;
  cursor: pointer;
}

.chat__body {
  height:650px;
  padding: 2rem;
  border-right: 1px solid rgb(235, 235, 235);
  overflow: scroll;
  scroll-behavior: smooth;
}
.chat__body::-webkit-scrollbar {
  display: none;
}
.chat__first {
  margin-top: 2rem;
}
.chat__mymessage {
  min-height: 40px;
  margin: 0;
  line-break: anywhere;
  display: flex;
  justify-content: right;
  align-items: flex-end;
}
.chat__mymessage__paragraph {
  max-width: 180px;
  margin: 0.4rem 0 0 1rem;
  padding: 0.8rem;
  background-color: #7aab85;
  border-radius: 20px 20px 0px 20px;
  color: #ffffff;
  font-size: 14px;
  text-align: right;
}
.chat__yourmessage {
  display: flex;
}
.chat__yourmessage {
  display: flex;
}
.chat__yourmessage__user {
  margin-top: 0;
  margin-block-end: 0rem;
  color: #292929;
  font-size: 14px;
  font-weight: 700;
  text-align: left;
}
.chat__yourmessage__p {
  display: flex;
  align-items: flex-end;
  line-break: anywhere;
}
.chat__yourmessage__paragraph {
  max-width: 180px;
  margin: 0.4rem 1rem 0 0;
  padding: 0.8rem;
  background-color: #f3f3f3;
  border-radius: 0px 20px 20px 20px;
  color: #414141;
  font-size: 14px;
  text-align: left;
}
.chat__yourmessage__time {
  margin: 0;
  color: #9c9c9c;
  font-size: 12px;
}

.chatForm {
  padding: 1.3rem;
  background: #ffffff;
  border-radius: 0px 0px 24px 0px;
  box-shadow: 0px -5px 30px rgba(0, 0, 0, 0.05);
  display: flex;
  justify-content: space-between;
}
.form__input {
  width: calc(100% - 60px);
  padding: 0.5rem;
  border: none;
  font-size: 16px;
}
.form__input:focus {
  outline: none;
}
.form__submit {
  display: flex;
  align-items: center;
  cursor: pointer;
}
svg {
  transition: 0.3s;
}
svg:hover {
  fill: #999999;
}
</style>

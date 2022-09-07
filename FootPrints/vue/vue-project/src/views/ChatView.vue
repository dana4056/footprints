<template>
  <div>
    <tool-bar></tool-bar>
    <div class = "container">
      <div class="room">
        <div class="room__header">
          <img src="../assets/people.png">
          <h2>밥프렌즈</h2>
        </div>
        <chat-room class="chat__room"></chat-room>
      </div>

      <div class="chat">
        <chat-header class="chat__header"></chat-header>
        <chat-list></chat-list>
        <chat-form @submitMessage="sendMessage"></chat-form>
      </div>
    </div>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import ChatRoom from '../components/ChatRoom.vue'
import ChatHeader from '../components/ChatHeader.vue'
import ChatList from '../components/ChatList.vue'
import ChatForm from '../components/ChatForm.vue'
import { router } from '../routes/index.js';

export default {
  components: {
    ToolBar,
    ChatRoom,
    ChatHeader,
    ChatList,
    ChatForm,
  },
  created() {
    if(localStorage.getItem('jwt') == null) {
      router.replace("/home");
    }
    else if(this.$store.state.postIdList[0] != 0) {
      let post_id = this.$store.state.postIdList[this.$store.state.roomIndex];
      this.$store.dispatch('FIND_USER', post_id);
      this.$store.dispatch('FIND_CHAT_LOGS', post_id);
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
    sendMessage() {
      setTimeout(() => {
        const element = document.getElementById("chat__body");
        element.scrollTop = element.scrollHeight;
      }, 0);
    }
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
.room__header {
  height: 96px;
  padding-top: 15px;
  border-bottom: 1px solid rgb(206, 206, 206);
}
.room__header > img {
  height: 40px;
  width: 40px;
  margin: 20px 0 0 125px;
  float: left;
}
.room__header > h2 {
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
</style>

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

export default {
  components: {
    ToolBar,
    ChatRoom,
    ChatHeader,
    ChatList,
    ChatForm,
  },
  created() {
    this.$store.dispatch('FIND_POST_ID', this.$store.state.member.nick);
  },
  mounted() {
    setTimeout(() => {
        const element = document.getElementById("chat__body");
        element.scrollTop = element.scrollHeight;

        const e = document.getElementsByClassName("room__list")
        e.scrollTop = element.scrollHeight;
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
  background-color: #ffffff;
  margin: 3rem auto 65rem;
  border-radius: 1.5rem;
  box-shadow: 0px 1px 20px #7a7a9c55;
}
.room {
  border-radius: 24px 0px 0px 24px;
  float: left;
  width: 400px;
  height: 900px;
  background: #ffffff;
  box-shadow: 0px 3px 35px 0px rgba(0, 0, 0, 0.05);
}

.room__header {
  height: 96px;
  padding-top: 15px;
  border-bottom: 1px solid rgb(206, 206, 206);
}

.room__header > img {
  float: left;
  margin: 20px 0 0 125px;
  height: 40px;
  width: 40px;
}

.room__header > h2 {
  margin-left: 30px;
  float: left;
}
.chat {
  float: right;
  width: 800px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.chat__header {
  border-radius: 0px 24px 0px 0px;
  background: #ffffff;
  box-shadow: 0px 3px 10px 0px rgba(0, 0, 0, 0.05);
  padding: 0px 1.8rem 20px 1.8rem;
  text-align: left;
}
</style>

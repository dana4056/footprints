<template>
  <div id="wrap">
    <div v-if="this.$store.state.postIdList[0] != 0">
      <p id="title">{{ this.$store.state.roomList[this.$store.state.roomIndex].post_name }}</p>
      <p id="userList" v-for="user in this.$store.state.userList" v-bind:key="user"> {{ user }}</p>
      <button v-if="this.$store.state.roomList[this.$store.state.roomIndex].member.nick != this.$store.state.member.nick" v-on:click="exitPost">나가기</button>
    </div>
  </div>
</template>

<script>
import Swal from 'sweetalert2';

export default {
  methods: {
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
        this.$router.replace("/delivery/posta");
      })
    },
  }
}
</script>

<style scoped>
#wrap {
  border-radius: 0px 24px 0px 0px;
  background: #ffffff;
  box-shadow: 0px 3px 10px 0px rgba(0, 0, 0, 0.05);
  padding: 0px 1.8rem 20px 1.8rem;
  text-align: left;
  height: 91px;
}
#title {
  font-size: 20px;
  font-weight: 700;
}
#userList {
  font-size: 14px;
  margin: 0 0;
  padding: 0 0;
  float: left;
  margin-right: 20px;
}
button {
  font-size: 14px;
  font-weight: bold;
  float:right;
  border: none;
  border-radius: 15%;
  background-color: rgba(255, 103, 103, 0.526);
  padding: 7px;
}
</style>
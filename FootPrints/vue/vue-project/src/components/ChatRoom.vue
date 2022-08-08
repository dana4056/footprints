<template>
  <div>
    <ul v-if="this.$store.state.postIdList[0] != 0">
       <li v-for="(room, index) in this.$store.state.roomList" :key=room
              v-bind:id="[`${index}`]" v-on:mousedown.left="clickRoom"
              class="room_info"        v-bind:class="{ 'on': chekcedArr[index] }">
          <img :src="require('../assets/' + room.category + '.png')">
          <h3>{{ room.post_name }}</h3>
          <!-- 마지막 메세지 구현 필요 -->
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
    dataNum: 0,
    chekcedArr: [],
    }
  },
  created() {
    this.dataNum = this.$store.state.postIdList.length;
    this.chekcedArr[this.$store.state.roomIndex] = true;
    for (let i = 0; i < this.dataNum; i++) {
      if (i != 0) {
        this.chekcedArr[i] = false;
      }
    }
  },
  methods: {
    clickRoom(li) {
      this.chekcedArr[this.$store.state.roomIndex] = false;
      this.$store.state.roomIndex = li.currentTarget.id;
      this.chekcedArr[this.$store.state.roomIndex] = true;

      let post_id = this.$store.state.postIdList[this.$store.state.roomIndex];
      this.$store.dispatch('FIND_USER', post_id);
      this.$store.dispatch('FIND_CHAT_LOGS', post_id);
    }
  }
}
</script>

<style scoped>
ul {
  padding: 0 0;
  margin: 0 0 ;
  overflow: scroll;
  scroll-behavior: smooth;
  height: 770px;
}
ul::-webkit-scrollbar {
  display: none;
}
.room_info {
  border-bottom: 1px solid rgb(235, 235, 235);
  cursor: pointer;
  height: 130px;
}
.room_info > * {
  float: left;
  margin-left: 50px;
}
img {
  width: 60px;
  height: 60px;
  margin-top: 30px;
}
h3 {
  width:200px;
  text-align: left;
  margin-top: 30px;
}
p {
  margin-top: 0px;
  color: rgb(180, 180, 180);
}
.on {
  background-color: rgb(234, 234, 234);
}
</style>
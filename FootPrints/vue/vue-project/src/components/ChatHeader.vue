<template>
  <div>
    <p id="title">{{ post_name }}</p>
    <p id="userList" v-for="user in this.$store.state.userList" v-bind:key="user"> {{ user }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      post_name: " ",
      post_id: 0
    }
  },
  created() {
    let checkedNum = this.$store.state.roomIndex;
    this.post_name = this.$store.state.roomList[checkedNum].post_name;
    this.post_id = this.$store.state.postIDList[checkedNum];
    this.$store.dispatch('FIND_USER', this.post_id);
  },
}
</script>

<style scoped>
div {
  border-radius: 0px 24px 0px 0px;
  background: #ffffff;
  box-shadow: 0px 3px 10px 0px rgba(0, 0, 0, 0.05);
  padding: 0px 1.8rem 20px 1.8rem;
  text-align: left;
}
#title {
  font-size: 20px;
  font-weight: 700;
}
#userList {
  font-size: 16px;
  margin: 0 0;
  padding: 0 0;
  float: left;
  margin-right: 20px;
}
</style>
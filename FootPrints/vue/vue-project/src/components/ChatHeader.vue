<template>
  <div>
    <p id="title">{{ this.$store.state.roomList[this.$store.state.roomIndex].post_name }}</p>
    <p id="userList" v-for="user in this.$store.state.userList" v-bind:key="user"> {{ user }}</p>
  </div>
</template>

<script>
export default {
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
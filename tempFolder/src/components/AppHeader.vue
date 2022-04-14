<template>
  <div id="Header">
    <img alt="logo" id = "ourLogo" v-on:click="goToMain" src="../assets/bal.png">
    <h1 id="headerText">발자취</h1>
  </div>
</template>

<script>

export default {
  methods: {
    // 수정 - 메인 페이지로 이동
    goToMain: function() {
      location.reload();
    }
  }
}
</script>

<style>
#Header {
  margin: 0 0 ;
  padding-top: 10px;
  height: 50px;
  background-color: rgb(231, 231, 231);
}
#ourLogo {
  height: 30px;
  width: 40px;
  margin: 0 0 0 20px;
  float: left;
  cursor: pointer;
}
#headerText {
  float: left;
  font-size: 25px;
  padding: 0 20px;
  margin: 0 0;
}
</style>
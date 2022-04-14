<template>
  <div id="Sns">
    <h2>SNS로 간편 회원가입</h2>
    <ul>
        <li><img v-on:click="kakaoLogin" class="brandLogo" src="../assets/kakaoLogo.png"></li>
        <li><img v-on:click="naveroLogin" class="brandLogo" src="../assets/NaverLogo.png"></li>
        <li><img v-on:click="facebookLogin" class="brandLogo" src="../assets/facebookLogo.png"></li>
    </ul>
    <hr id="line">
  </div>
</template>

<script>
export default {
  methods: {
    // 수정 - 각각의 로그인 팝업창 띄우기
    kakaoLogin: function() {

    },
    naveroLogin: function() {

    },
    facebookLogin: function() {

    }
  }
}
</script>

<style>
  #Sns {
    padding: 40px 0px 0 0;
  }
  ul{
    padding-top: 30px;
  }
  li{
    display: inline;
  }
  .brandLogo{
    width: 3%;
    border-radius: 10px;
    margin: 0 3%;
    cursor: pointer;
  }
  #line {
    width: 50%;
    height: 2px;
    background-color: black;
    margin: 5% auto;
  }
</style>
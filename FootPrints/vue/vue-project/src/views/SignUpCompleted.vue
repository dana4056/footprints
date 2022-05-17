<template>
  <div id="wrap">
    <div id='completed'>
      <img src="../assets/congratulation.png">
      <h3>회원가입 완료</h3>
      <p><span class="nick">{{GET_MEMBER.nick}}</span>님, 환영합니다.</p>
      <router-link to="/home" class="item"><button id="home">홈페이지</button></router-link>
      <router-link to="/login" class="item"><button id="login">로그인</button></router-link>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  computed:{
    ...mapGetters([
      'GET_MEMBER'
    ])
  }
}

</script>

<style scoped>

#wrap{
  display: flex;
  height: 100vh;
  flex-direction: column;
  justify-content: center;
}
#completed{
  width: 400px;
  margin: 0 auto;
  padding: 15px;
}
img{
  width: 100px;
}
.nick{
  font-weight: 700;
  color:#4a44cd;
}
button{
  margin: 0px 5px;
  width: 100px;
  height: 32px;
  box-sizing: border-box;
  color: white;
  font-family: 'Noto Sans KR', sans-serif;
  border-radius: 10px;
}
#login{
  background-color: #7aab85;
  border: 1px solid #7aab85;
}
#home{
  background-color: #797979;
  border: 1px solid #797979;
}

</style>
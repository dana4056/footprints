<template>
<div id="page">
  <div id="wrap">
		<router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
		<div class="Div">
      <div v-if="!isFind">
        <label>회원정보에 등록한 이메일을 입력해주세요.</label>
        <div id="btnBox" v-bind:class="{errorType:this.cannotFind}">
          <input v-on:keyup.enter="findID" id="email" v-model="email" type="text" autocomplete="off" placeholder="이메일 입력" required>
          <span v-if="this.cannotFind" class="errorType">가입되지 않은 회원입니다.</span>
        </div>
        <button type="submit" v-on:click="findID">이메일로 아이디 찾기</button>
      </div>
      <div v-else>
        <label id="showID">회원님의 아이디는 <span>{{ GET_FIND_MEMBER_NICK }}</span> 입니다.</label>
        <div id="btnBox">
          <router-link to="/home" class="item"><button id="home">홈페이지</button></router-link>
          <router-link to="/login" class="item"><button id="login">로그인</button></router-link>
        </div>
      </div>
		</div>
	</div>
</div>

</template>

<script>
import { mapGetters } from 'vuex';

export default {
	data() {
		return {
			email: "",
      isFind: false,
      cannotFind: false,
		}
	},
  created() {
		this.$store.state.find_nick = "CANNOT_FIND_ID";
  },
	computed:{
    ...mapGetters([
      'GET_FIND_MEMBER_NICK'
    ]),
  },
	methods: {
    findID() {
      this.$store.dispatch('FIND_NICK', this.email);
      setTimeout(() => { 
        this.represent() 
        }, 500);    
    },
    represent(){
      if (this.GET_FIND_MEMBER_NICK == "CANNOT_FIND_ID"){
        this.cannotFind = true;
      }
      else{
        this.isFind = true;
      }
    },
  }
}
</script>

<style scoped>
#page{
  background-color: #f8f8f8;
}
#wrap{
  width: 330px;
  height: 650px;
  margin: 0 auto;
  padding: 239px 0;
}
#completed{
  width: 400px;
  margin: 0 auto;
  padding: 15px;
}
.nick{
  color:#4a44cd;
  font-weight: 700;
}
button{
  box-sizing: border-box;
  width: 100px;
  height: 32px;
  margin: 0px 5px;
  border-radius: 10px;
  color: white;
  font-family: 'Noto Sans KR', sans-serif;
}
.logo {
  margin: 15px 0px;
  color: black;
  font-weight: bold;
  text-decoration: none;
  display: flex;
  align-items: center;
	justify-content: center;
}
.logo img{
  margin: 3px 8px 3px 0px;
}
.Div{
  margin: 0;
  padding: 8px 15px 9px;
  justify-content: space-between;
}
label{
  margin-bottom: 12px;
  font-size: 13px;
  color: #292929;
  display: block;
  text-align: left;
}
input{
  box-sizing: border-box;
  width: 100%;
  height: 50px;
  padding: 8px 15px 9px;
  background: #FaFaFa;
  border: 1px solid #BDBDBD;
  border-radius: 10px;
  font-family: Noto Sans KR,sans-serif;
  outline: none;
}
input:focus {
  background: #F3F3F3;
  border-color: #999999;
  outline: none;
}
input:hover {
  background: #F3F3F3;
}
input::placeholder {
  color: #BDBDBD;
  font-weight: 100;
}
.errorType input {
  background: #fff6f6;
  border-color: #eb7373;
  outline: none;
}
.errorType span {
  color: #eb7373;
  font-size: 12px;
  text-align: left;
}
button{
  box-sizing: border-box;
  width: 100%;
  height: 40px;
  margin: 15px 0;
  padding: 8px 15px 9px;
  background: #7aab85;
  border: 1px solid #7aab85;
  border-radius: 13px;
  color: #ffffff;
  font-weight: bold;
  font-family: Noto Sans KR,sans-serif;
  cursor: pointer;
}
button:hover {
  background: #669270;
}
#showID{
  padding: 28px 0;
  border-top: 1px solid #d9d9d9;
  border-bottom: 1px solid #d9d9d9;
  color: #666666;
}
#showID span{
  color: #4a44cd;
  font-weight: bold;
}
#btnBox button{
  width: 45%;
  margin: 15px 5px;
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
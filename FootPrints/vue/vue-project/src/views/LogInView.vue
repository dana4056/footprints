<template>
  <div id="page">
    <div id="wrap">
      <router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
      <form>
        <div class="Div">
          <div v-bind:class="{errorType:!isValidNick()}">
            <input id="Nick" autocomplete="off" v-model="Nick" type="text" placeholder="닉네임" required>
          </div>

        <div v-bind:class="{errorType:!isValidPassword}">
          <input id="Pw" v-on:focusout="checkPassword" autocomplete="off" maxlength="20" v-model="Pw" type="password" v-on:keyup.enter="checkLogin" placeholder="비밀번호" required>
        </div>
          <button type="submit" v-on:click.prevent="checkLogin">로그인</button>
        </div>
      </form>

      <div class="Div">
        <router-link to="/find-id" class="link">아이디 찾기</router-link>|
        <router-link to="/find-password" class="link">비밀번호 찾기</router-link>| 
        <router-link to="/signup" class="link">회원가입</router-link> 
      </div>
      <!-- <hr> -->
      <!-- <div class="SnsBox">
        <p>SNS 계정으로 간편 로그인</p>
        <ul>
          <li><img class="brandLogo" src="../assets/kakaoLogo.png"></li>
          <li><img class="brandLogo" src="../assets/NaverLogo.png"></li>
          <li><img class="brandLogo" src="../assets/facebookLogo.png"></li>
        </ul>
      </div> -->
    </div>
  </div>
</template>

<script>
export default {
	data() {
		return {
			Nick: "",
			Pw: "",
      isValidEmail: true,
      isValidPassword: true,
		}
	},
  methods: {
    checkLogin(){
      if(this.PostValid()){
        const loginMember = {
          nick: this.Nick,
          pw: this.Pw,
        }
        this.$store.dispatch('POST_LOGIN', loginMember);
      }
    },
    PostValid(){
      if(this.Nick != "" && this.Pw != ""){
        if(this.isValidPassword)
          return true;
        else{
          alert("비밀번호는 영어, 숫자, 특수문자 포함 8~20자입니다.");
        }
      }
      else{
        alert("빈 칸을 두면 로그인이 안돼요.");
      }
    },
    checkPassword() {
      const pattern1 = /[0-9]/;
			const pattern2 = /[a-zA-Z]/;
			const pattern3 = /[~!@#\\$%<>^&*]/;
      const pwd = this.Pw;
      //비밀번호: 영어, 숫자, 특수문자 포함 8~20자
      if (pwd != ""){
        if (pwd.length < 8 || !pattern1.test(pwd) || !pattern2.test(pwd) || !pattern3.test(pwd))
          this.isValidPassword = false;
        else
          this.isValidPassword = true;
      }
    },
    isValidNick() {   // 닉네임 형식 판단
      if (this.Nick != "") {
        if (this.Nick.length >= 2 && this.Nick.length <= 8) {
          return true;
        } else {
          this.isUniqNick = false;
          return false;
        }
      } else {
        return true;
      }
    },
   }
}
</script>

<style scoped>
#page{
  background-color: #f8f8f8;
}
#wrap { 
  width: 330px;
  height: 100vh; 
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
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
img{
  widows: 25;
}
.Div{
  margin: 0;
  padding: 8px 15px 9px;
  justify-content: space-between;
}
.errorType input{
  background: #fff6f6;
  border-color:#eb7373;
  outline: none;
}
input{
  box-sizing: border-box;
  width: 100%;
  height: 50px;
  padding: 8px 15px 9px;
  background: #FaFaFa;
  border: 1px solid #BDBDBD;
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
#Nick{
  border-bottom: 1px;
  border-radius: 10px 10px 0px 0px;
}
#Pw{
  border-radius: 0px 0px 10px 10px;
}
button{
  box-sizing: border-box;
  width: 100%;
  height: 40px;
  margin: 15px 0 5px 0;
  padding: 8px 15px 9px;
  background: #7aab85;
  border: 1px solid #7aab85;
  border-radius: 13px;
  color: #ffffff;
  font-family: Noto Sans KR,sans-serif;
  font-weight: bold;
  cursor: pointer;
}
button:hover {
  background: #669270;
}
.link{
  margin: 0px 10px;
  color: black;
  font-family: Noto Sans KR,sans-serif;
  font-size: 12px;
  text-decoration: none;
}
hr{
  height: 1px;
  margin: 25px 0;
  background-color: #cdcdcd;
  border: none;
}
.SnsBox p{
  color: #606060;
  font-family: Noto Sans KR,sans-serif;
  font-size: 13px;
}
.SnsBox ul{
  padding: 0;
  list-style: none;
}
.SnsBox li{
  display: inline;
}
.SnsBox .brandLogo{
  border-radius: 7px;
}
</style>
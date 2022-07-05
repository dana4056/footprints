<template>
  <div id="page">
    <div id="wrap">
      <router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>

      <div class="Div">
        <div v-bind:class="{errorType:!isValidNick()}">
          <input id="Nick" autocomplete="off" v-model="Nick" type="text" placeholder="닉네임" required>
        </div>

        <div v-bind:class="{errorType:!isValidPassword}">
          <input id="Pw" v-on:focusout="checkPassword" autocomplete="off" maxlength="20" v-model="Pw" type="password" placeholder="비밀번호" required>
        </div>

        <button type="submit" v-on:click.prevent="checkLogin">로그인</button>
      </div>

      <div class="Div">
        <router-link to="/findID" class="link">아이디 찾기</router-link>|
        <router-link to="/findPW" class="link">비밀번호 찾기</router-link>| 
        <router-link to="/signup" class="link">회원가입</router-link> 
      </div>
      <hr>
      <div class="SnsBox">
        <p>SNS 계정으로 간편 로그인</p>
        <ul>
          <li><img class="brandLogo" src="../assets/kakaoLogo.png"></li>
          <li><img class="brandLogo" src="../assets/NaverLogo.png"></li>
          <li><img class="brandLogo" src="../assets/facebookLogo.png"></li>
        </ul>
      </div>
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
        const member = {
          nick: this.Nick,
          email: 'email',
          pw: this.Pw,
          area: 'area'
        }
        this.$store.dispatch('POST_LOGIN', member)
        // console.log(this.Pw); 
      }
    },
    PostValid(){
      if(this.Nick != "" && this.Pw != ""){
        if(this.isValidPassword)
          return true;
        else
          alert("비밀번호는 영어, 숫자, 특수문자 포함 8~20자입니다.");
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
    margin: 0 auto;
    padding: 143px 0;
}
.logo {
   display: flex;
	justify-content: center;
   margin: 15px 0px;
   color: black;
   text-decoration: none;
   align-items: center;
   font-weight: bold;
}
.logo img{
    margin: 3px 8px 3px 0px;
}
img{
  widows: 25px;
}
.Div{
    margin: 0;
    padding: 8px 15px 9px;
    justify-content: space-between;
}
.errorType input{
  background: #fff6f6;
  outline: none;
  border-color:#eb7373;
}
input{
    font-family: Noto Sans KR,sans-serif;
    outline: none;
    width: 100%;
    height: 50px;
    background: #FaFaFa;
    border: 1px solid #BDBDBD;
    box-sizing: border-box;
    padding: 8px 15px 9px;
}

input:focus {
  outline: none;
  border-color: #999999;
  background: #F3F3F3;
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
    font-family: Noto Sans KR,sans-serif;
    width: 100%;
    height: 40px;
    color: #ffffff;
    font-weight: bold;
    background: #7aab85;
    border: 1px solid #7aab85;
    box-sizing: border-box;
    border-radius: 13px;
    cursor: pointer;
    margin: 15px 0 5px 0;
    padding: 8px 15px 9px;    
}

button:hover {
  background: #669270;
}


.link{
    font-family: Noto Sans KR,sans-serif;
    font-size: 12px;
    margin: 0px 10px;
    color: black;
    text-decoration: none;
}
hr{
    margin: 25px 0;
    background-color: #cdcdcd;
    height: 1px;
    border: none;
}
.SnsBox p{
   font-family: Noto Sans KR,sans-serif;
   font-size: 13px;
   color: #606060;
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
<template>
	<div>
	<div id="wrap">
		<router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
    
      <div class="Div">
        <div v-bind:class="{errorType:!isValidEmail}">
          <input id="Id" autocomplete="off" v-model="Id" type="text" placeholder="이메일" required>
        </div>

        <div v-bind:class="{errorType:!isValidPassword}">
          <input id="Pw" v-on:focusout="checkPassword" autocomplete="off" maxlength="20" v-model="Pw" type="password" placeholder="비밀번호" required>
        </div>
        
        <button type="submit" v-on:click.prevent="checkLogin">로그인</button>
      </div>
      <div class="Div">
         <router-link to="/findID" class="link">아이디 찾기</router-link>
         <router-link to="/findPW" class="link">비밀번호 찾기</router-link> 
         <router-link to="/signup" class="link">회원가입</router-link> 
      </div>
      <hr>
      <div class="SnsBox">
         <div>SNS 계정으로 간편 로그인</div>
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
			Id: "",
			Pw: "",
      isValidEmail: true,
      isValidPassword: true,
		}
	},
  methods: {
    checkLogin(){
      if(this.PostValid()){
        const member = {
          email: this.Id,
          pwd: this.Pwd,
          nick: 'nick',
          phone: 'phone',
          area: 'area'
        }
        this.$store.dispatch('POST_LOGIN', member)
        console.log(this.Pwd);
      }
    },
    PostValid(){
      if(this.Id != "" && this.Pwd != ""){
        return true;
      }
      else{
        alert("빈 칸을 두면 로그인이 안돼요.")
      }
    },
    // login() {
		// 	if (this.submitData()){
		// 		alert("로그인이 완료되었습니다.")
		// 		this.$router.replace("/home");
		// 	}
    //   else 
		// 		alert("[로그인 실패]\n이메일 또는 비밀번호 오류입니다.")
    // },
		// submitData() {	// 이 함수 코드를 구현해야 합니다!!
		// 	console.log(this.Id, this.Pw);
		// 	return true;
    // },
    // checkEmail() {  // 임시로 구현한 이메일 형식 검사
    //   const Id = this.Id;
    //   if (Id != ""){
    //     if (Id.includes('@') && Id.includes('.'))
    //       this.isValidEmail = true;
    //     else
    //       this.isValidEmail = false;
    //   }
    // },
    checkPassword() {
      const pattern1 = /[0-9]/;
			const pattern2 = /[a-zA-Z]/;
			const pattern3 = /[~!@#\\$%<>^&*]/;
      const pwd = this.Pw;
      if (pwd != ""){
        if (pwd.length < 8 || !pattern1.test(pwd) || !pattern2.test(pwd) || !pattern3.test(pwd))
          this.isValidPassword = false;
        else
          this.isValidPassword = true;
      }
    }
   }
}
</script>

<style scoped>
#wrap {
    width: 450px;
    margin: 0 auto;
    padding: 150px 0;
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
    margin: 3px 10px;
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
    outline: none;
    width: 100%;
    height: 60px;
    background: #F8F8F8;
    border: 2px solid #BDBDBD;
    box-sizing: border-box;
    padding: 8px 15px 9px;
}
#Id{
    border-bottom: 1px;
    border-radius: 10px 10px 0px 0px;
}
#Pw{
    border-radius: 0px 0px 10px 10px;
}
button{
    width: 100%;
    height: 50px;
    background: #cdcdcd;
    border: 1px solid #cdcdcd;
    box-sizing: border-box;
    border-radius: 10px;
    padding: 8px 15px 9px;
    margin: 15px 2px 5px 2px;
    cursor: pointer;
}
.link{
    margin: 0px 15px;
    color: black;
    text-decoration: none;
}
hr{
    margin: 25px 0;
    background-color: #cdcdcd;
    height: 2px;
    border: none;
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
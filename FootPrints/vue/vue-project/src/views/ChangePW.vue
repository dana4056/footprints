<template>
  <div id="page">
    <div id="wrap">
      <router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
      <div class="Div" v-bind:class="{errorType:!isValidPw}">
        <label>새로운 비밀번호</label>
        <input id='password1' v-on:focusout="checkPw"  autocomplete="off" maxlength="20"
        v-model="Pw1" type="password" placeholder="비밀번호 (영어, 숫자, 특수문자 포함 8~20자)" required>
        <span class="errortype" v-if="!isValidPw">비밀번호는 영어, 숫자, 특수문자 포함 8~20자여야 합니다.</span>
      </div>

      <div class="Div" v-bind:class="{errorType:isDiffrentPw}">
        <label>새로운 비밀번호 확인</label>
        <input id='password2' v-on:focusout="compPw"  autocomplete="off" maxlength="20"
        v-model="Pw2" type="password" placeholder="비밀번호 재입력" required>
        <span class="errortype" v-if="isDiffrentPw">비밀번호가 일치하지 않습니다.</span>
      </div>

      <button type="submit" v-on:click.prevent="change">비밀번호 변경</button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
	data() {
		return {
      memberDTO:{
        nick: "",
        email: "",
        pw: "",
        area: "",
      },
			Pw1: "",
      Pw2: "",
      isValidPw: true,
      isDiffrentPw: false,
		}
	},
  computed:{
    ...mapGetters([
      'GET_FINDPWD'
    ])
  },
	methods: {
		checkPw() {   // 비밀번호 형식이 올바른지 확인
      const pattern1 = /[0-9]/;
      const pattern2 = /[a-zA-Z]/;
      const pattern3 = /[~!@#\\$%<>^&*]/;
      const pwd = this.Pw1;
      if(pwd != ""){
        if (pwd.length < 8 || !pattern1.test(pwd) || !pattern2.test(pwd) || !pattern3.test(pwd))
          this.isValidPw = false;
        else
          this.isValidPw = true;
      }
    },
    compPw(){      // 비밀번호 & 비밀번호 확인란 일치 여부
      if(this.Pw1 != "" && this.Pw2 != ""){
        this.isDiffrentPw = (this.Pw1 != this.Pw2) ? true : false;
        this.memberDTO.pw = this.Pw1;
        this.memberDTO.email = this.GET_FINDPWD;
      }
    },
    change() {
      if (this.submitData()){
        console.log("비밀번호 변경해보자");
        this.$store.dispatch('CHANGE_PWD', this.memberDTO);
        //제대로 와야 이후에 밑에 두줄 수행해야지
				alert("비밀번호가 성공적으로 변경되었습니다.")
				this.$router.replace("/login");
			}
      else 
				alert("비밀번호를 다시 확인해주세요.")
    },
		submitData() {	// 이 함수 코드를 구현해야 합니다!!
      if (!this.isDiffrentPw && this.isValidPw)
        return true;
      else 
        return false;
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
  margin: 0 auto;
  padding: 211px 0;
}
.logo {
   display: flex;
	justify-content: center;
   margin: 10px 0px;
   color: black;
   text-decoration: none;
   align-items: center;
   font-weight: bold;
}
.logo img{
    margin: 3px 8px 3px 0px;
}
.Div{
  padding-bottom: 15px;
}
label{
  text-align: left;
  display: block;
  font-size: 13px;
  color: #292929;
  margin-bottom: 7px;
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
    border-radius: 10px;
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
    margin: 6px 0;
    padding: 8px 15px 9px;    
}

button:hover {
  background: #669270;
}
.errorType input{
  background: #fff6f6;
  outline: none;
  border-color:#eb7373;
}
.errorType span{
  text-align: left;
  font-size: 12px;
  color: #eb7373;
}
</style>
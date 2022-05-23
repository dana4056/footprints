<template>
  <div id="wrap">
		<router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
		<div class="Div" v-bind:class="{errorType:!isValidPw}">
      <label>비밀번호</label>
      <input id='password1' v-on:focusout="checkPw"  autocomplete="off" maxlength="20"
      v-model="Pw1" type="password" placeholder="비밀번호 (영어, 숫자, 특수문자 포함 8~20자)" required>
      <span class="errortype" v-if="!isValidPw">비밀번호는 영어, 숫자, 특수문자 포함 8~20자여야 합니다.</span>
    </div>

    <div class="Div" v-bind:class="{errorType:isDiffrentPw}">
      <label>비밀번호 확인</label>
      <input id='password2' v-on:focusout="compPw"  autocomplete="off" maxlength="20"
      v-model="Pw2" type="password" placeholder="비밀번호 재입력" required>
      <span class="errortype" v-if="isDiffrentPw">비밀번호가 일치하지 않습니다.</span>
    </div>

    <button type="submit" v-on:click.prevent="change">비밀번호 변경</button>
	</div>
</template>

<script>
export default {
	data() {
		return {
			Pw1: "",
      Pw2: "",
      isValidPw: true,
      isDiffrentPw: false,
		}
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
      }
    },
    change() {
      if (this.submitData()){
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
label{
	text-align: left;
  display: block;
  font-size: 15px;
  font-weight: bold;
  color: #292929;
  margin: 20px 0 12px;
}
input{
  outline: none;
  width: 100%;
  height: 60px;
  background: #F8F8F8;
  border: 2px solid #BDBDBD;
  box-sizing: border-box;
  padding: 8px 15px 9px;
	margin: 0 0 7px 0;
	border-radius: 10px;
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
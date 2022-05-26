<template>
  <div id="wrap">
		<router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
		<div class="Div">
			<label>가입한 이메일 주소를 입력해주세요.</label>
			<input id="phone" autocomplete="off" v-model="email" type="text" placeholder="이메일" required>
			<input id="userCode" v-if="inputVisible" autocomplete="off" maxlength="6" v-model="userCode" type="text" placeholder="인증번호" required>
			<button type="submit" v-if="getBtnVisible" v-on:click="getCode">이메일로 인증코드 받기</button>
			<button type="submit" v-if="chkBtnVisible" v-on:click="checkCode">확인</button>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			email: "",
			sysCode: "",
			userCode: "",
			inputVisible: false,
			getBtnVisible: true,
			chkBtnVisible: false
		}
	},
	methods: {
		getCode() {
			if (this.email != "") {
				// 1. 존재하는 메일인지 확인 후 이메일로 인증번호 보내는 코드로 수정 -----------------------------
				this.sysCode = Math.floor(Math.random() * 900001) + 100000;
				console.log(this.email, this.sysCode);
				// ------------------------------------------------------------------------------------------------
				this.inputVisible = true;
				this.getBtnVisible = false;
				this.chkBtnVisible = true;
			}
			else {
				alert("유효하지 않은 이메일입니다.");
			}
		},
		checkCode() {
			console.log(this.sysCode, this.userCode);
			if (this.sysCode == this.userCode && this.userCode != "") {
				// 2.getPW 뷰로 넘어갈 때 입력받은 이메일도 넘겨야함---------------------------------------
				this.$router.replace("/changePW");
			}
			else
				alert("인증번호를 다시 입력해주세요.");
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
</style>
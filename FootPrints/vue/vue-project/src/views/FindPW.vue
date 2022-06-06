<template>
  <div id="wrap">
		<router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
		<div class="Div">
			<label v-if="emailtext">가입한 이메일 주소를 입력해주세요.</label>
			<input id="phone" autocomplete="off" v-model="email" type="text" v-if="emailtext" placeholder="이메일" required>
			<div v-if="CAN_infoVisible">{{ GET_FINDPWD }}로 메일 전송드렸습니다.</div>
			<div v-if="CANNOT_infoVisible">{{email}}은 없는 회원 메일이므로 전송 불가합니다.</div>
			<input id="userCode" v-if="inputVisible" autocomplete="off" maxlength="6" v-model="userCode" type="text" placeholder="인증번호" required>
			<button type="submit" v-if="getBtnVisible" v-on:click="getCode">이메일로 인증코드 받기</button>
			<button type="submit" v-if="ChkBtnVisible" v-on:click="checkCode">확인</button>
		</div>
	</div>
</template>

<script>
// import emailjs from 'emailjs-com';
import { mapGetters } from 'vuex';
export default {
	data() {
		return {
			email: "",
			sysCode: "",
			userCode: "",
			emailtext: true,
			inputVisible: false,
			getBtnVisible: true,
			CAN_infoVisible : false,
			CANNOT_infoVisible : false,
			ChkBtnVisible: false
		}
	},
	computed:{
    ...mapGetters([
      'GET_FINDPWD'
    ])
  },
	methods: {
		getCode() {
			if (this.email != "") {
				//이메일 입력시 
				this.$store.dispatch('FIND_ID', this.email);
				//비동기 처리 필요
				if(this.GET_FINDPWD != "CANNOT_FIND_ID"){
					// 이메일 디비에 있는게 확인 됐다면
					this.sysCode = Math.floor(Math.random() * 900001) + 100000;
					console.log(this.sysCode);
					// let templateParams  = { 
					// 	user_email: this.email,
					// 	sys_code: this.sysCode,
					// }
					// emailjs.init('REMuhzEQAisDSZ2hk');
					// emailjs.send('email', 'template_q0r3oy4', templateParams)
					// 	.then((result) => {
          //   console.log('SUCCESS!', result.text);
					// 	}, (error) => {
          //   console.log('FAILED...', error.text);
					// 	});
					this.emailtext = false;
					this.inputVisible = true;
					this.getBtnVisible = false;
					this.CAN_infoVisible = true;
					this.CANNOT_infoVisible = false;
					this.ChkBtnVisible = true;
				}
				else{
					//이메일 확인 실패시
					this.CANNOT_infoVisible = true;
					this.CAN_infoVisible = false;
					this.getBtnVisible = false; //할지 말지 고민중
				}
			}
			else{
				//이메일 입력 안했을 시
				alert("메일을 입력해주세요.");
			}
		},
		checkCode() {
			if (this.sysCode == parseInt(this.userCode) && this.userCode != "") {
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
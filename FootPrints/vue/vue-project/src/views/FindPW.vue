<template>
  <div id="wrap">
		<router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
		<div class="Div">
			<label v-if="emailtext">가입한 이메일 주소를 입력해주세요.</label>
			<input id="phone" autocomplete="off" v-model="email" type="text" v-if="emailtext" placeholder="이메일" required>
			<div v-if="CAN_infoVisible">{{ GET_FINDPWD }}로 메일 전송드렸습니다.</div>
			<div v-if="CANNOT_infoVisible">{{email}}은 없는 회원 메일이므로 전송 불가합니다.</div>
			<div id="user" v-if="inputVisible">
				<input id="userCode" v-if="inputVisible" autocomplete="off" maxlength="6" v-model="userCode" type="text" placeholder="인증번호" required>
				<p id="TimerStr">{{TimerStr}}</p>
			</div>
			<button type="submit" v-if="getBtnVisible" v-on:click="getCode">이메일로 인증코드 받기</button>
			<button type="submit" v-if="ChkBtnVisible" v-on:click="checkCode">확인</button>
		</div>
	</div>
</template>

<script>
//import emailjs from 'emailjs-com';
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
			ChkBtnVisible: false,
			// 타이머를 위해 필요한 데이터들 
			TimerName: null,
      TimeCounter: 10,
      TimerStr: "03:00",
			isTimeRemain: true,
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

					// getCode 함수 수정한 부분 (+ 함수 3개 추가하였습니다!) --------------------------
					// let templateParams  = { 
					// 	user_email: this.email,
					// 	sys_code: this.sysCode,
					// }
					// emailjs.init('REMuhzEQAisDSZ2hk');
					// emailjs.send('email', 'template_q0r3oy4', templateParams)
					// // emailjs.init('vRkhLOuF_vA7-HcM9'); 													-> 테스트용으로 사용하시고 코드 완성되면 삭제해주세요!
					// // emailjs.send('email', 'template_ecvcwyw', templateParams) 	-> 이 줄도 같이 삭제해주세요!
					// .then(() => {
					// 	this.emailtext = false;
					// 	this.inputVisible = true;
					// 	this.getBtnVisible = false;
					// 	this.CAN_infoVisible = true;
					// 	this.CANNOT_infoVisible = false;
					// 	this.ChkBtnVisible = true;

					// 	if(this.TimerName != null) {
					// 		this.timerStop(this.TimerName);
					// 		this.timerName = null;
					// 	}
					// 	this.TimerName = this.timerStart();
					// }, () => {
					// 	alert("이메일을 다시 확인해주세요.")
					// });
					// -------------------------------------------------------------------------------

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
		timerStart() {
			this.TimeCounter = 180;
			var interval = setInterval(() => {
				this.TimeCounter--;
				this.TimerStr = this.prettyTime();
				if (this.TimeCounter <= 0)
				this.timerStop(interval);
			}, 1000);
			return interval;
		},
		timerStop(TimerName) {
			clearInterval(TimerName);
			this.sysCode="TimeOutCode";
			this.TimeCounter = 0;
			this.isTimeRemain = false;
		},
		prettyTime() {
			let time = this.TimeCounter / 60;
			let minutes = parseInt(time);
			let secondes = Math.round((time - minutes) * 60);
			return (minutes.toString().padStart(2, "0") + ":" + secondes.toString().padStart(2, "0"));
		},
		checkCode() {
			if (this.isTimeRemain) {
				if (this.sysCode == parseInt(this.userCode) && this.userCode != "") {
					// 2.getPW 뷰로 넘어갈 때 입력받은 이메일도 넘겨야함---------------------------------------
					this.$router.replace("/changePW");
				}
				else
					alert("인증번호를 다시 입력해주세요.");
			}
			else {
				alert("인증번호 유효시간이 초과되었습니다.");
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
label{
	text-align: left;
  display: block;
  font-size: 15px;
  font-weight: bold;
  color: #292929;
  margin: 20px 0 12px;
}
#phone, #user{
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
#user {
	padding: 10px;
}
#userCode{
	width: 80%;
	float: left;
	margin: 10px 0px 0 3px;
	background-color: #F8F8F8;
	border:none;
	outline: none;
}
#TimerStr{
	color: #595959;
	float: right;
	margin: 5px 14px 0 0;
}
</style>
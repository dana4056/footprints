<template>
<div id="page">
	<div id="wrap">
		<router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
		<div class="Div">
			<label v-if="emailtext">가입한 이메일 주소를 입력해주세요.</label>
			<input id="phone" autocomplete="off" v-model="email" type="text" v-if="emailtext" v-on:keyup.enter="getCode" placeholder="이메일" required>
			<label id="sendMail" v-if="CAN_infoVisible"><span>{{ GET_FIND_MEMBER_EMAIL }}</span>에게 인증번호를 보냈습니다.</label>
			<label id="error" v-if="CANNOT_infoVisible">가입 되지 않은 회원입니다.</label>
			<input id="userCode" v-if="inputVisible" autocomplete="off" maxlength="6" v-model="userCode" type="text" v-on:keyup.enter="checkCode" placeholder="인증번호" required>
			<button type="submit" v-if="getBtnVisible" v-on:click="getCode">이메일로 인증코드 받기</button>
			<button type="submit" v-if="ChkBtnVisible" v-on:click="checkCode">확인</button>
		</div>
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
		'GET_FIND_MEMBER_EMAIL'
    ])
  },
	methods: {
		getCode() {
			if (this.email != "") {
				//이메일 입력시 
				this.$store.dispatch('FIND_PWD', this.email);
				setTimeout(() => { 
        // console.log("3");
        this.represent() ;
        }, 100); 	
			}
			else{
				//이메일 입력 안했을 시
				alert("메일을 입력해주세요.");
			}
		},
		represent() {
			if(this.GET_FIND_MEMBER_EMAIL != "CANNOT_FIND_ID"){
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
				this.getBtnVisible = true;
			}
		},
		checkCode() {
			if (this.sysCode == parseInt(this.userCode) && this.userCode != "") {
				// 2.getPW 뷰로 넘어갈 때 입력받은 이메일도 넘겨야함---------------------------------------
				localStorage.setItem('email', this.email);
				this.$router.replace("/change-password");
			}
			else
				alert("인증번호를 다시 입력해주세요.");
		}
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
  padding: 239px 0;
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
.Div{
  margin: 0;
  padding: 8px 15px 9px;
  justify-content: space-between;
}
label{
  text-align: left;
  display: block;
  font-size: 13px;
  color: #292929;
  margin-bottom: 12px;
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
    margin: 15px 0;
    padding: 8px 15px 9px;    
}

button:hover {
  background: #669270;
}
#sendMail span{
  color: #4a44cd;
}

</style>
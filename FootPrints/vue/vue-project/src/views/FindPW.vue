<template>
<div id="page">
	<div id="wrap">
		<router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
		<div class="Div">
			<div v-if="!isSend">
        <label>가입한 이메일 주소를 입력해주세요.</label>
        <div id="btnBox" v-bind:class="{errorType:this.cannotFind}">
          <input v-on:keyup.enter="findID" id="email" v-model="email" type="text" autocomplete="off" placeholder="이메일 입력" required>
          <span v-if="this.cannotFind" class="errorType">가입 되지 않은 회원입니다.</span>
        </div>
        <button type="submit" v-on:click="getCode">이메일로 인증코드 받기</button>
      </div>
      <div v-else>
				<label id="sendMail"><span>{{ GET_FIND_MEMBER_EMAIL }}</span>에게 인증번호를 보냈습니다.</label>
				<input id="userCode" autocomplete="off" maxlength="6" v-model="userCode" type="text" v-on:keyup.enter="checkCode" placeholder="인증번호" required>
				<button type="submit" v-on:click="checkCode">확인</button>
      </div>
		</div>
	</div>
</div>

</template>

<script>
import { mapGetters } from 'vuex';
import emailjs from 'emailjs-com';

export default {
	data() {
		return {
			email: "",
			sysCode: "",
			userCode: "",
			isSend: false,
      cannotFind: false,
		}
	},
	created() {
    this.$store.state.find_email = "CANNOT_FIND_EMAIL";
  },
	computed:{
    ...mapGetters([
		'GET_FIND_MEMBER_EMAIL'
    ])
  },
	methods: {
		getCode() {
			if (this.email != "") {
				this.$store.dispatch('FIND_PWD', this.email);
				setTimeout(() => { 
        this.represent() ;
        }, 100); 	
			}
			else{
				alert("메일을 입력해주세요.");
			}
		},
		represent() {
			if(this.GET_FIND_MEMBER_EMAIL == "CANNOT_FIND_EMAIL"){
        this.cannotFind = true;
      }
      else{
				this.sysCode = Math.floor(Math.random() * 900001) + 100000;

				let templateParams  = { 
					user_email: this.email,
					sys_code: this.sysCode,
				}
				emailjs.init('W7k47_dvkdb6q5-5Y');
				emailjs.send('email', 'template_ecvcwyw', templateParams)

        this.isSend = true;
      }
		},
		checkCode() {
			if (this.sysCode == parseInt(this.userCode) && this.userCode != "") {
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
	height: 650px;
  margin: 0 auto;
  padding: 239px 0;
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
	color: #292929;
  font-size: 13px;
	text-align: left;
	display: block;
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
	font-family: Noto Sans KR,sans-serif;
  font-weight: bold;
  cursor: pointer;
}
button:hover {
  background: #669270;
}
#sendMail span{
  color: #4a44cd;
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
</style>
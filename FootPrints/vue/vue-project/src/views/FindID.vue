<template>
  <div id="wrap">
		<router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
		<div class="Div">
			<label v-if="inputtext">회원정보에 등록한 이메일을 입력해주세요.</label>
			<input id="email" autocomplete="off" v-model="email" type="text" v-if="inputemail" placeholder="이메일 입력" required>
			<button type="submit" v-if="getBtnVisible" v-on:click="findID">이메일로 아이디 찾기</button>
      <div>
        <div v-if="getIDVisible">회원님의 아이디는 {{ GET_FINDID }} 입니다.</div>
        <div v-if="canNotFindID">회원님의 아이디를 찾을 수 없습니다.</div>
      </div>
			<router-link to="/home" class="item"><button id="home" v-if="chkBtnVisible">홈페이지</button></router-link>
      <router-link to="/login" class="item"><button id="login" v-if="chkBtnVisible">로그인</button></router-link>
		</div>
	</div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
	data() {
		return {
			email: "",
      inputtext: true,
			inputemail: true,
			getBtnVisible: true,
			chkBtnVisible: false,
			getIDVisible: false,
      canNotFindID: false,
		}
	},
	computed:{
    ...mapGetters([
      'GET_FINDID'
    ])
  },
	methods: {
		findID() {
			console.log("아이디를 찾습니다");
			this.$store.dispatch('FIND_ID', this.email);
			this.getBtnVisible = false;
      // 비동기처리 때문에 제대로 못 가져와서 그냥 찍어버리는 현상 발생 후처리 필요
      // console.log(this.GET_FINDID);
      // if( this.GET_FINDID == "CANNOT_FIND_ID"){
      //   this.canNotFindID = true;
      // }
      // else{
      //   this.getIDVisible = true;
      // }
      this.getIDVisible = true;  //이 줄 지우고 처리 해야함
			this.chkBtnVisible = true;
      this.inputtext = false;
      this.inputemail = false;
		},
	}
}
</script>

<style scoped>
#wrap{
  display: flex;
  height: 100vh;
  flex-direction: column;
  justify-content: center;
}
#completed{
  width: 400px;
  margin: 0 auto;
  padding: 15px;
}
img{
  width: 100px;
}
.nick{
  font-weight: 700;
  color:#4a44cd;
}
button{
  margin: 0px 5px;
  width: 100px;
  height: 32px;
  box-sizing: border-box;
  color: white;
  font-family: 'Noto Sans KR', sans-serif;
  border-radius: 10px;
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
#login{
  background-color: #7aab85;
  border: 1px solid #7aab85;
}
#home{
  background-color: #797979;
  border: 1px solid #797979;
}
</style>
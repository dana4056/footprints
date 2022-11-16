<template>
  <div class="container">
    <div class="header">
      <h2>회원정보 수정</h2>
    </div>

    <div class="inputDiv">
      <form  class="imageField"> 
        <img v-bind:src=Image class="profileImage">
        <input type="file" name="file" ref="file" accept="image/*" v-on:change="fileSelect" style="display:none">
        <!-- <button class="btn1" type="button" v-on:click="setImage">선택</button> -->
      </form >
    </div>

    <div class="inputDiv" v-bind:class="{errorType:!isValidNick() | this.isDupliNick, correctType:this.isUniqNick}">
      <label>닉네임</label>
      <input id='nickname' v-model="Nick" v-on:focus="ignoreInputN" v-on:focusout="checkNick()" type="text" placeholder="별명 (2~8자)" autoComplete="off" readonly required>
        <span class="errorType" v-if="!isValidNick()">닉네임은 2~8글자이어야 합니다.</span>
        <span class="errorType" v-if="this.isDupliNick">이미 사용 중인 닉네임입니다.</span>
        <span class="correctType" v-if="this.isUniqNick">사용 가능한 닉네임입니다.</span>
    </div>

    <div class="inputDiv" v-bind:class="{errorType:this.isDupliEmail, correctType:this.isUniqEmail}">
      <label>이메일</label>
      <div class="emailGroup">
        <span> <input class="emailItem1" v-model="Email1" v-on:focus="ignoreInputE" type="text" autoComplete="off" 
        placeholder="아이디" v-on:focusout="checkEmail" required> 
        </span>
        <span><span class="emailItem2">@</span></span>
        <span>
          <select v-if="isSelectBox" v-on:change="directInput" class="emailItem3" name="domain" id='userId2' v-model="Email2" v-on:focus="ignoreInputE" v-on:focusout="checkEmail">
            <option value="" selected="selected" disabled hidden>----- 선택 -----</option>
            <option value="naver.com">naver.com</option>
            <option value="gmail.com">gmail.com</option>
            <option value="skuniv.ac.kr">skuniv.ac.kr</option>
            <option value="direct">직접입력</option>
          </select>
          <span id="directInputBox" v-if="!isSelectBox">
            <input autofocus type="text" class="emailItem3" v-on:focus="ignoreInputE" v-model="Email2" autoComplete="off" placeholder="직접입력" required>
            <i v-on:click="changeSelectBox" class="fa-solid fa-xmark"></i>
          </span>
        </span>
      </div>
      <span class="errorType" v-if="this.isDupliEmail">이미 사용 중인 이메일입니다. </span>
      <span class="correctType" v-if="this.isUniqEmail">사용 가능한 이메일입니다. </span>
    </div>

    <div class="inputDiv" v-bind:class="{errorType:!isValidPw()}">
      <label>비밀번호</label>
      <input id='password1' autoComplete="off" v-on:focusout="isValidPw" v-model="Pw1" type="password" placeholder="비밀번호 (영어, 숫자, 특수문자 포함 8~20자)" required>
      <span class="errorType" v-if="!isValidPw()">비밀번호는 영어, 숫자, 특수문자 포함 8~20자여야 합니다.</span>
    </div>

    <div class="inputDiv" v-bind:class="{errorType:isDiferrentPw()}">
      <label>비밀번호 확인</label>
      <input id='password2' autoComplete="off" v-model="Pw2" type="password" placeholder="비밀번호 재입력" required>
      <span class="errorType" v-if="isDiferrentPw()">비밀번호가 일치하지 않습니다.</span>
    </div>

    <div class="inputDiv">
      <label>지역설정</label>
      <input id='userArea' v-model="Area" type="text" readOnly placeholder="지역명(ex. 성북구 정릉동)">
      <button type="button" class="btn2" v-on:click="searchArea">지역 검색</button>
    </div>

    <button type="submit" class="submitBtn" v-on:click.prevent="change" v-on:keyup.enter="change">수정하기</button>
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
      Image: require("../assets/user.png"),
      Nick: this.$store.state.member.nick,
      Email1: "",
      Email2: "",
      sun_Email1: this.$store.state.member.email.split('@')[0],
      sun_Email2: this.$store.state.member.email.split('@')[1],
      Pw1: "",
      Pw2: "",
      Area: this.$store.state.member.area,
      isUniqNick:false,
      isDupliNick:false,
      isUniqEmail:false,
      isDupliEmail:false,
      isSelectBox:true
    }
  },
  created() {
    // this.Image = require("../assets/" + this.$store.state.member.nick +".png");
    this.Image = require('../assets/user.png');
    this.Email1 = this.sun_Email1;
    this.Email2 = this.sun_Email2;
  },
  computed: {
    email() {
      return this.Email1 + "@" + this.Email2;
    },
    ...mapGetters([
      'GET_MEMBER',
      'GET_MEMBER_CHANGE_DONE'
    ])
  },
  methods: {
    setImage() {
      this.$refs.file.click();
    },
    fileSelect(data) {
      const file = data.target.files;
      const url = URL.createObjectURL(file[0]);
      this.Image = url;
    },
    ignoreInputN() {
      this.isUniqNick = false;
      this.isDupliNick = false;
    },
    ignoreInputE(){
      this.isUniqEmail = false;
      this.isDupliEmail = false;
    },
    checkNick() { 
      if (this.Nick != "" && this.Nick != this.$store.state.member.nick) {
        this.$store.dispatch('POST_NICK', this.Nick);
        setTimeout(() => { 
          this.isDupliNick =  this.$store.state.isDuplicateNick;
          this.isUniqNick = !this.$store.state.isDuplicateNick;
        }, 300); 
      }
    },
    directInput(){
      if(this.isSelectBox){
        if(this.Email2=="direct"){
          this.changeSelectBox();
        }
      }
    },
    changeSelectBox(){
      this.Email2 = "";
      this.isSelectBox = !this.isSelectBox;
    },
    checkEmail() {
      if (this.Email1 != "" && this.Email2 != "" && this.email != this.$store.state.member.email) {
        this.$store.dispatch('POST_EMAIL', this.email);
        setTimeout(() => { 
          this.isDupliEmail =  this.$store.state.isDuplicateEmail;
          this.isUniqEmail = !this.$store.state.isDuplicateEmail;
        }, 300); 
      }
    },
    isDiferrentPw() {      // 비밀번호 & 비밀번호 확인란 일치 여부
      if (this.Pw1 != "" && this.Pw2 != "") {
        if (this.Pw1 != this.Pw2) {
          return true;
        } else {
          return false;
        }
      }
    },
    isValidNick() {
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
    isValidPw() {   // 비밀번호 형식 판단
      const pattern1 = /[0-9]/;
      const pattern2 = /[a-zA-Z]/;
      const pattern3 = /[~!@#\\$%<>^&*]/;
      const pwd = this.Pw1;
      if(pwd != ""){
        if (pwd.length < 8 || !pattern1.test(pwd) || !pattern2.test(pwd) || !pattern3.test(pwd))
          return false;
        else
          return true;
      }else{
        return true;
      }
    },
    searchArea() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          const sido = data.sido;
          const sigoongu = data.sigungu;
          const eupmyeondong = data.bname;

          let a = eupmyeondong.lastIndexOf("읍");
          let b = eupmyeondong.lastIndexOf("면");
          let c = eupmyeondong.lastIndexOf("동");
          let max = Math.max(a,b,c);
          const sliceUpmyeondong = eupmyeondong.slice(0, max+1);
          
          this.Area = sido+" "+sigoongu+" "+ sliceUpmyeondong;
        }
      }).open();
    },
    isValidAll() {
      if (!this.isDupliEmail && this.Id1 != "" && this.Id2 != "" && this.Pw1 != "" && this.Pw2 != "" && this.Nick != "" && this.Area != "") {
        if (this.isValidNick() && !this.isDiferrentPw()) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    },
    change() {
      if (this.isValidAll()){
        this.memberDTO.nick = this.Nick;
        this.memberDTO.email = this.email;
        this.memberDTO.pw = this.Pw1;
        this.memberDTO.area = this.Area;

        this.$store.dispatch('CHANGE_MEMBER', this.memberDTO);
        
        setTimeout(() => { 
        this.represent() ;
        }, 300); 	
			}
      else {
        if(!this.isDupliNick){
          alert("사용할 수 없는 이메일입니다.");
        }
        else{
          alert("형식을 다시 확인해주세요.")
        }
      }
    },
    represent() {
      if(this.GET_MEMBER_CHANGE_DONE == 'SUCCESS'){
        alert("회원정보가 성공적으로 변경되었습니다.")
        this.$router.replace("/home");
        this.$store.state.memberChange_done = 'FAILED';
      }
    }
  }
}
</script>

<style scoped>
.container {
  width: 800px;
  margin: 0 auto;
  padding-bottom: 200px;
}
.header {
  width: 100%;
  margin-top: 50px;
  text-align: left;
}
.imageField {
  margin-bottom: 70px;
  position : relative;
}
.profileImage {
  width: 150px;
  height: 150px;
  margin: 0px auto;
  border-radius: 50%;
}
.btn1 {
  height: 40px;
  color: rgba(0, 0, 0, 0.688);
  font-size: 13px;
  position: absolute;
  top: 100px;
  left : 250px;
  width: 50px;
}
.setImage {
  margin-bottom: 70px;
}
#inputData {
  padding: 50px 0 0 0;
  border-top: 1px solid rgb(226, 226, 226);
}
.inputDiv {
  width: 450px;
  margin: 40px auto;
}
label {
  margin: 0 0 12px;
  color: #292929;
  font-size: 15px;
  font-weight: bold;
  text-align: left;
  display: block;
}
input, select {
  box-sizing: border-box;
  width: 100%;
  height: 40px;
  margin: 0 0 7px 0;
  padding: 8px 15px 9px;
  background: #FAFAFA;
  border: 1px solid #BDBDBD;
  border-radius: 8px;
  font-family: 'Noto Sans KR', sans-serif;
  outline: none;
}
#directInputBox{
  position: relative;
}
#directInputBox i{
  position: absolute;
  left: 139px;
  bottom: 2px;
  color:#BDBDBD;
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
.errorType input, .errorType select{
  background: #fff6f6;
  border-color: #eb7373;
  outline: none;
}
.errorType span {
  color: #eb7373;
  font-size: 12px;
  text-align: left;
}
.correctType input, .correctType select{
  background: #e8f0fe;
  border-color: #6ea7f2;
  outline: none;
}
.correctType span {
  color: #6ea7f2;
  font-size: 12px;
  text-align: left;
}
button {
  box-sizing: border-box;
  width: 450px;
  height: 40px;
  background: #ffffff;
  border: 2px solid #7aab85;
  border-radius: 13px;
  color: #7aab85;
  font-family: 'Noto Sans KR', sans-serif;
}
.btn1 {
  padding: 0 0;
}
.btn2 {
  padding: 8px 15px 9px;
}
.btn1:hover {
  background: #c5e6cd;
  color: rgba(0, 0, 0, 0.715);
}
.btn2:hover {
  background: #c5e6cd54;
}
.submitBtn {
  background: #7aab85;
  border: 1px solid #7aab85;
  color: #ffffff;
  font-weight: bold;
}
.submitBtn:hover {
  background: #669270;
}
.emailItem1 {
  width:45%;
}
.emailItem2 {
  width:10%;
  padding:0 12px 0 12px;
}
.emailItem3 {
  width:45%;
}
.select-box{
  padding: 10px 10px 20px 10px;
}
i {
  margin-left: 65px;
}
</style>
<template>
  <div class="body">
    <header>
      <div class="header">
        <!-- router-link는 자동으로 a태그로 변환하고 부가적인 기능 제공 -->
        <router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
      </div>
    </header>
    <div id="wrap">
      <h3>회원가입</h3>
      <!-- 일반 회원가입 form -->
      <div id="inputData">
        <form>
          <div class="inputDiv" v-bind:class="{errorType:!isValidNick() | this.isDupliNick, correctType:this.isUniqNick}">
            <label>닉네임</label>
            <input class="input" id='nickname' v-model="Nick" v-on:focus="ignoreInputN" v-on:focusout="checkNick()" 
                   type="text" placeholder="별명 (2~8자)" autoComplete="off" required>
            <span class="errorType" v-if="!isValidNick()">닉네임은 2~8글자이어야 합니다.</span>
            <span class="errorType" v-if="this.isDupliNick">이미 사용 중인 닉네임입니다.</span>
            <span class="correctType" v-if="this.isUniqNick">사용 가능한 닉네임입니다.</span>
          </div>

          <div class="inputDiv" v-bind:class="{errorType:this.isDupliEmail,
                                                   correctType:this.isUniqEmail}">
            <label>이메일</label>
            <div class="emailGroup">
              <span>
                  <input class="emailItem1 input" v-on:focus="ignoreInputE" v-model="Email1" type="text" 
                  autoComplete="off" placeholder="아이디" v-on:focusout="checkEmail" required>
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
                  <input autofocus type="text" class="emailItem3 input" v-on:focus="ignoreInputE" v-model="Email2" autoComplete="off" placeholder="직접입력" required>
                  <i v-on:click="changeSelectBox" class="fa-solid fa-xmark"></i>
                </span>

              </span>
            </div>
            <span class="errorType" v-if="this.isDupliEmail">이미 사용 중인 이메일입니다. </span>
            <span class="correctType" v-if="this.isUniqEmail">사용 가능한 이메일입니다. </span>
          </div>

          <div class="inputDiv" v-bind:class="{errorType:!isValidPw()}">
            <label>비밀번호</label>
            <input class="input" id='password1' autoComplete="off" v-on:focusout="isValidPw"
                   v-model="Pw1" type="password" placeholder="비밀번호 (영어, 숫자, 특수문자 포함 8~20자)" required>
            <span class="errorType" v-if="!isValidPw()">비밀번호는 영어, 숫자, 특수문자 포함 8~20자여야 합니다.</span>
          </div>

          <div class="inputDiv" v-bind:class="{errorType:isDiferrentPw()}">
            <label>비밀번호 확인</label>
            <input class="input" id='password2' autoComplete="off" v-model="Pw2" type="password" placeholder="비밀번호 재입력" required>
            <span class="errorType" v-if="isDiferrentPw()">비밀번호가 일치하지 않습니다.</span>
          </div>

          <div class="inputDiv">
            <label>지역설정</label>
            <input class="input" id='userArea' v-model="Area" type="text" readOnly placeholder="지역명(ex. 성북구 정릉동)">
            <button type="button" class="btn2" v-on:click="searchArea">지역 검색</button>
          </div>

          <div class="inputDiv">
            <label>개인정보 수집 및 이용 안내</label>
            <div id="textBox">
              <small><b>개인정보 수집·이용 안내</b></small>
              <small><br>발자취 서비스에서는 아래와 같이 개인정보를 수집 및 이용합니다.</small>
              <small><br>- 개인정보 수집 목적:  회원관리</small>
              <small><br>- 개인정보 수집 항목:  아이디, 비밀번호, 이메일</small>
              <small><br>- 보유 및 이용기간:  회원 탈퇴시까지</small><br><br>
              <div id="agreeBox">
                
                <label for="agree"><input id="agree" type="checkbox" v-model="isAgree" @change="agreeBox">개인정보 수집 및 이용에 동의합니다.</label> 
              </div>
            </div>
          </div>

          <button type="submit" class="submitBtn" v-on:click.prevent="submitData" v-on:keyup.enter="submitData">회원가입하기</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      Nick: "",
      Email1: "",
      Email2: "",
      Pw1: "",
      Pw2: "",
      Area: "",
      visable:false,
      isUniqNick:false,
      isDupliNick:false,
      isUniqEmail:false,
      isDupliEmail:false,
      isSelectBox:true,
      isAgree:false,
    }
  },
  computed: {
    email() {
      return this.Email1 + "@" + this.Email2;
    },
  },
  methods: {
    ignoreInputN() {
      this.isUniqNick = false;
      this.isDupliNick = false;
    },
    ignoreInputE(){
      this.isUniqEmail = false;
      this.isDupliEmail = false;
    },
    checkNick() {
      if (this.Nick != "") {
        this.$store.dispatch('POST_NICK', this.Nick);
        setTimeout(() => { 
          this.isDupliNick =  this.$store.state.isDuplicateNick;
          this.isUniqNick = !this.$store.state.isDuplicateNick;
        }, 500); 
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
      if (this.Email1 != "" && this.Email2 != "") {
        this.$store.dispatch('POST_EMAIL', this.email);
        setTimeout(() => { 
          this.isDupliEmail =  this.$store.state.isDuplicateEmail;
          this.isUniqEmail = !this.$store.state.isDuplicateEmail;
        }, 500);    
      }
    },
    submitData() {
      if (this.isValidAll() && this.isAgree) {
        const member = {
          nick: this.Nick,
          email: this.email,
          pw: this.Pw1,
          area: this.Area
        }
        this.$store.dispatch('POST_MEMBER', member); 
      }
      else if(!this.isValidAll()){
        alert("입력한 정보들을 확인해주세요");
      }
      else {
        alert("개인정보 수집 및 이용 내용을 확인해주세요.");
      }
    },
    isValidAll() {
      if (this.Id1 != "" && this.Id2 != "" && this.Pw1 != "" && this.Pw2 != "" &&
          this.Nick != "" && this.Phone != "" && this.Area != "") {
        if (this.isValidNick() && !this.isDiferrentPw()) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    },
    isDiferrentPw() {
      if (this.Pw1 != "" && this.Pw2 != "") {
        if (this.Pw1 != this.Pw2) {
          return true;
        } else {
          return false;
        }
      }
    },
    isValidNick() {   // 닉네임 형식 판단
      if (this.Nick !== "") {
        const nick = this.Nick.trim();
        if (nick.length >= 2 && nick.length <= 8) {
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
    }
  }
}
</script>

<style scoped>
body {
  font-family: 'Noto Sans KR', sans-serif;
  text-align: center;
}
.header {
  width: 100%;
  max-width: 1024px;
  margin: 0 auto;
  padding: 15px 45px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.logo {
  margin: 0px 10px;
  color: black;
  font-weight: bold;
  text-decoration: none;
  display: flex;
  align-items: center;
}
.logo img {
  margin: 3px 10px;
}
h3 {
  display: block;
}
#wrap {
  width: 360px;
  margin: 0 auto;
  padding: 50px 0 100px 0;
}
.SnsBox ul {
  padding: 0;
  list-style: none;
}
.SnsBox li {
  display: inline;
}
.SnsBox .brandLogo {
  border-radius: 7px;
}
.SnsBox .tinytext {
  font-size: 12px;
  font-weight: lighter;
}

/* 일반 회원가입 form */
#inputData {
  padding: 50px 0 0 0;
  border-top: 1px solid rgb(226, 226, 226);
}
.inputDiv {
  margin: 0 0 30px;
}
label {
  margin: 0 0 12px;
  color: #292929;
  font-size: 15px;
  font-weight: bold;
  text-align: left;
  display: block;
}
.input, select {
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
    position:relative;
}
#directInputBox i{
    position: absolute;
    left: 139px;
    bottom: 2px;
    color:#BDBDBD;
}
#textBox{
  padding: 30px;
  font-size: 13px;
  border-radius: 10px;
  background-color: #f8f8f8;
  display: inline-block;
  text-align: left;
}
#agreeBox label {
  font-size: 12px;
  display: inline-block;
  margin:0;
}
.input:focus {
  background: #F3F3F3;
  border-color: #999999;
  outline: none;
}
.input:hover {
  background: #F3F3F3;
}
.input::placeholder {
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
.correctType .input, .correctType select{
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
  width: 100%;
  height: 40px;
  background: #ffffff;
  border: 1px solid #7aab85;
  border-radius: 13px;
  color: #7aab85;
  font-family: 'Noto Sans KR', sans-serif;
}
.btn1, .btn2 {
  padding: 8px 15px 9px;
}
.btn1 {
  margin: 0 0 7px 5px;
}
.btn1:hover, .btn2:hover {
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
  padding:0 10px 0 10px;
}
.emailItem3 {
  width:45%;
}
.search{
  margin: 0 0 30px;
  background-color: #dfebe2;
  border-radius: 10px;
}
.search p{
  padding: 10px;
  background-color: #669270;
  border-radius: 10px 10px 0 0;
  color: white;
  font-size: 14px;
  font-weight: bold;
}
.search select{
  margin: 0 0 10px;
}
.search .sido{
  width: 48%;
  float: left;
}
.search .sigoon{
  width: 48%;
  float: right;
}
.select-box{
  padding: 10px 10px 20px 10px;
}
</style>

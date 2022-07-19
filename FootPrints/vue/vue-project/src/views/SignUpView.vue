<template>
  <div class="body">
    <!-- <NavigationBar></NavigationBar> -->
    <header>
      <div class="header">
        <!-- router-link는 자동으로 a태그로 변환하고 부가적인 기능 제공 -->
        <router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
      </div>
    </header>
    <div id="wrap">
      <!-- SNS 회원가입 -->
      <h3>회원가입</h3>
      <div class="SnsBox">
        <div class="tinytext">SNS로 간편 회원가입</div>
        <ul>
          <li><img class="brandLogo" src="../assets/kakaoLogo.png"></li>
          <li><img class="brandLogo" src="../assets/NaverLogo.png"></li>
          <li><img class="brandLogo" src="../assets/facebookLogo.png"></li>
        </ul>
      </div>
      <!-- 일반 회원가입 form -->
      <div id="inputData">
        <form>
          <div class="inputDiv" v-bind:class="{errorType:!isValidNick() | this.isDupliNick, 
                                                   correctType:this.isUniqNick}">
            <label>닉네임</label>
            <input id='nickname' v-model="Nick" v-on:focus="ignoreInputN" v-on:focusout="checkNick()" 
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
                  <input class="emailItem1" v-on:focus="ignoreInputE" v-model="Email1" type="text" 
                  autoComplete="off" placeholder="아이디" required>
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

          <div class="inputDiv" v-bind:class="{errorType:isDiferrentPw()|!isValidPw()}">
            <label>비밀번호</label>
            <input id='password1' autoComplete="off" v-on:focusout="isValidPw"
                   v-model="Pw1" type="password" placeholder="비밀번호 (영어, 숫자, 특수문자 포함 8~20자)" required>
            <span class="errorType" v-if="!isValidPw()">비밀번호는 영어, 숫자, 특수문자 포함 8~20자여야 합니다.</span>
          </div>

          <div class="inputDiv" v-bind:class="{errorType:isDiferrentPw()}">
            <label>비밀번호 확인</label>
            <input id='password2' autoComplete="off"
                   v-model="Pw2" type="password" placeholder="비밀번호 재입력" required>
            <span class="errorType" v-if="isDiferrentPw()">비밀번호가 일치하지 않습니다.</span>
          </div>

          <div class="inputDiv">
            <label>지역설정</label>
            <input id='userArea' v-model="Area" type="text" readOnly placeholder="지역명(ex. 성북구 정릉동)">
            <button type="button" class="btn2" v-on:click="searchArea">지역 검색</button>
          </div>
          <!-- <div v-if="visable" class="search">
            <p>지역 검색</p>
            <div class="select-box">
              <select v-model="sido" v-on:change="serachSigoongu" class="sido">
                <option value="시/도" selected="selected">시/도</option>
                <option v-for="i in this.$store.state.sidoList" v-bind:key="i.properties.ctprvn_cd" v-bind:value="i.properties">
                  {{ i.properties.ctp_kor_nm }}
                </option>
              </select>
              <select v-model="sigoongu" v-on:change="serachEupmyeondong" class="sigoon">
                <option value="시/군/구" selected="selected">시/군/구</option>
                <option v-for="i in this.$store.state.sigoonguList" v-bind:key="i.properties.sig_cd" 
                v-bind:value="i.properties">{{ i.properties.sig_kor_nm }}</option>
              </select>
              <select v-model="eupmyeondong">
                <option value="읍/면/동" selected="selected" >읍/면/동</option>
                <option v-for="i in this.$store.state.eupmyeondongList" v-bind:key="i.properties.emd_cd" 
                v-bind:value="i.properties">{{ i.properties.emd_kor_nm }}</option>
              </select>
              <button type="button" class="btn2" v-on:click="fixArea">확인</button>
            </div>
          </div> -->

          <button type="submit" class="submitBtn" v-on:click.prevent="submitData">회원가입하기</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
// import axios from 'axios';

export default {
  data() {
    return {
      Nick: "",
      Email1: "",
      Email2: "",
      Pw1: "",
      Pw2: "",
      Area: "",
      // sido:{},
      // sigoongu:{},
      // eupmyeondong:{},
      visable:false,
      isUniqNick:false,
      isDupliNick:false,
      isUniqEmail:false,
      isDupliEmail:false,
      isSelectBox:true
    }
  },
  computed: {
    email() {
      return this.Email1 + "@" + this.Email2;
    },
    // area(){
    //   return this.sido.ctp_kor_nm + " " + this.sigoongu.sig_kor_nm + " " + this.eupmyeondong.emd_kor_nm;
    // }
  },
  created(){
    // this.searchSido();
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
    checkNick() {   //닉네임 중복체크 위해 보냄
      if (this.Nick != "") {
        this.$store.dispatch('POST_NICK', this.Nick);

        this.isDupliNick =  this.$store.state.isDuplicateNick;
        this.isUniqNick = !this.isDupliNick;
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
    checkEmail() {   //이메일 중복체크 위해 보냄
      if (this.Email1 != "" && this.Email2 != "") {
        this.$store.dispatch('POST_EMAIL', this.email);
        this.isDupliEmail =  this.$store.state.isDuplicateEmail;
        this.isUniqEmail = !this.isDupliEmail;
      }
    },
    sendAuthenticCode() {
      if (this.Phone != "") {
        this.$store.dispatch('POST_PHONE', this.Phone);
      }
    },
    submitData() {
      if (this.isValidAll()) {
        const member = {
          nick: this.Nick,
          email: this.email,
          pw: this.Pw1,
          area: this.Area
        }
        this.$store.dispatch('POST_MEMBER', member);
      }
      else {
        alert("입력한 정보들을 확인해주세요");
      }
    },
    isValidAll() {  // 최종 양식 확인
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
    isDiferrentPw() {      // 비밀번호 & 비밀번호 확인란 일치 여부
      if (this.Pw1 != "" && this.Pw2 != "") {
        if (this.Pw1 != this.Pw2) {
          return true;
        } else {
          return false;
        }
      }
    },
    isValidNick() {   // 닉네임 형식 판단
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
    // searchSido(){     
    //   this.$store.dispatch('FETCH_SIDO');
    // },
    // serachSigoongu(){
    //   this.$store.dispatch('FETCH_SIGOONGU', this.sido.ctprvn_cd);
    // },
    // serachEupmyeondong(){   
    //   this.$store.dispatch('FETCH_EUPMYEONDONG', this.sigoongu.sig_cd);
    // },
    // fixArea(){
    //   this.Area = this.area;
    // },
    // searchBox(){
    //   this.visable = !this.visable;
    // },
    searchArea() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          console.log("API:카카오맵 주소받기 성공",data);
          const sido = data.sido;
          const sigoongu = data.sigungu;
          const eupmyeondong = data.bname;
          
          this.Area = sido+" "+sigoongu+" "+eupmyeondong;
        }
      }).open();
    }
  }
}
</script>

<style scoped>
body {
  text-align: center;
  font-family: 'Noto Sans KR', sans-serif;
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
  display: flex;
  margin: 0px 10px;
  color: black;
  text-decoration: none;
  align-items: center;
  font-weight: bold;
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
  padding: 100px 0;
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
  font-weight: lighter;
  font-size: 12px;
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
  text-align: left;
  display: block;
  font-size: 15px;
  font-weight: bold;
  color: #292929;
  margin: 0 0 12px;
}

input, select {
  font-family: 'Noto Sans KR', sans-serif;
  outline: none;
  width: 100%;
  height: 40px;
  background: #FAFAFA;
  border: 1px solid #BDBDBD;
  box-sizing: border-box;
  border-radius: 8px;
  padding: 8px 15px 9px;
  margin: 0 0 7px 0;
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

.errorType input, .errorType select{
  background: #fff6f6;
  outline: none;
  border-color: #eb7373;
}

.errorType span {
  text-align: left;
  font-size: 12px;
  color: #eb7373;
}
.correctType input, .correctType select{
  background: #e8f0fe;
  outline: none;
  border-color: #6ea7f2;
}
.correctType span {
  text-align: left;
  font-size: 12px;
  color: #6ea7f2;
}
button {
  font-family: 'Noto Sans KR', sans-serif;
  width: 100%;
  height: 40px;
  background: #ffffff;
  color: #7aab85;
  border: 1px solid #7aab85;
  box-sizing: border-box;
  border-radius: 13px;
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
  border-radius: 10px;
  background-color: #dfebe2;
}
.search p{
  color: white;
  font-weight: bold;
  font-size: 14px;
  padding: 10px;
  border-radius: 10px 10px 0 0;
  background-color: #669270;
}
.search select{
  margin: 0 0 10px;
}

.search .sido{
  float: left;
  width: 48%;
}
.search .sigoon{
  float: right;
  width: 48%;
}
.select-box{
  padding: 10px 10px 20px 10px;
}
</style>

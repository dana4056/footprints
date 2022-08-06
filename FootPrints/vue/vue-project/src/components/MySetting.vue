<template>
  <div class="container">
    <div class="header">
      <h2>회원정보 수정</h2>
    </div>

    <div class="inputDiv">
      <form  class="imageField"> 
        <img v-bind:src=Image class="profileImage">
        <input type="file" name="file" ref="file" accept="image/*" v-on:change="fileSelect" style="display:none">
        <button class="btn1" type="button" v-on:click="setImage">선택</button>
      </form >
    </div>

    <div class="inputDiv" v-bind:class="{errorType:!isValidNick() | this.isDupliNick, correctType:this.isUniqNick}">
      <label>닉네임 - create시 비밀번호 빼고 모두 자동 입력 상태</label>
      <input id='nickname' v-model="Nick" v-on:focus="ignoreInputN" v-on:focusout="checkNick()" type="text" placeholder="별명 (2~8자)" autoComplete="off" required>
        <span class="errorType" v-if="!isValidNick()">닉네임은 2~8글자이어야 합니다.</span>
        <span class="errorType" v-if="this.isDupliNick">이미 사용 중인 닉네임입니다.</span>
        <span class="correctType" v-if="this.isUniqNick">사용 가능한 닉네임입니다.</span>
    </div>

    <div class="inputDiv" v-bind:class="{errorType:this.isDupliEmail, correctType:this.isUniqEmail}">
      <label>이메일</label>
      <div class="emailGroup">
        <span> <input class="emailItem1" v-model="Email1" v-on:focus="ignoreInputE" type="text" autoComplete="off" placeholder="아이디" required> </span>
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
      <label>비밀번호 - 채워져 있으면 안 됨! 무조건 한 번은 입력해야함</label>
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

    <button type="submit" class="submitBtn" v-on:click.prevent="submitData" v-on:keyup.enter="submitData">수정하기</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      Image: require("../assets/user.png"),
      Nick: this.$store.state.member.nick,
      Email1: this.$store.state.member.email.split('@')[0],
      Email2: this.$store.state.member.email.split('@')[1],
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
  computed: {
    email() {
      return this.Email1 + "@" + this.Email2;
    }
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
      if (this.Nick != "") {
        this.$store.dispatch('POST_NICK', this.Nick);
        setTimeout(() => { 
          this.isDupliNick =  this.$store.state.isDuplicateNick;
          this.isUniqNick = !this.$store.state.isDuplicateNick;
        }, 100); 
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
        }, 100);    
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
          console.log("API:카카오맵 주소받기 성공",data);
          const sido = data.sido;
          const sigoongu = data.sigungu;
          const eupmyeondong = data.bname;
          
          this.Area = sido+" "+sigoongu+" "+eupmyeondong;
        }
      }).open();
    },
    isValidAll() {
      if (this.Id1 != "" && this.Id2 != "" && this.Pw1 != "" && this.Pw2 != "" && this.Nick != "" && this.Area != "") {
        if (this.isValidNick() && !this.isDiferrentPw()) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
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
        console.log(member);
        // this.$store.dispatch('POST_MEMBER', member); 
      }
      else {
        alert("입력한 정보들을 확인해주세요");
      }
    },
  }
}
</script>

<style scoped>
.container {
  margin: 0 auto;
  width: 800px;
}
.header {
  width: 100%;
  margin-top: 50px;
  text-align: left;
}
.imageField {
  position : relative;
  margin-bottom: 70px;
}
.profileImage {
  margin: 0px auto;
  width: 200px;
  height: 200px;
  border-radius: 50%;
}
.btn1 {
  position: absolute;
  top: 130px;
  left : 300px;
  width: 50px;
  height: 45px;
  font-size: 15px;
  color: rgba(0, 0, 0, 0.688);
}
.setImage {
  margin-bottom: 70px;
}
#inputData {
  padding: 50px 0 0 0;
  border-top: 1px solid rgb(226, 226, 226);
}
.inputDiv {
  width: 500px;
  margin: 40px auto;
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
  width: 500px;
  height: 40px;
  background: #ffffff;
  color: #7aab85;
  border: 2px solid #7aab85;
  box-sizing: border-box;
  border-radius: 13px;
}
.btn1 {
  padding: 0 0;
}
.btn2 {
  padding: 8px 15px 9px;
}
.btn1:hover {
  color: rgba(0, 0, 0, 0.715);
  background: #c5e6cd;
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
  padding:0 15px 0 15px;
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
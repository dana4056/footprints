<template>
  <div id="Input">
    <div class="id">
      <h2 class="textCls">아이디</h2>
      <input class="inputCls" v-model="fid" type="text" Placeholder="이메일">
      <span>@</span>
      <select class="inputCls" v-model="bid">
        <!-- 직접 입력 선택시의 기능 추가-->
        <option value="naver.com">naver.com</option>
        <option value="gmail.com">gmail.com</option>
        <option value="daum.net">daum.net</option>
        <option value="kakao.com">kakao.com</option>
        <option value="icloud.com">icloud.com</option>
        <option value=1>직접 입력</option>
      </select>
      <button v-on:click="compID">중복 확인</button>
    </div>

    <div class="pw1">
      <h2 class="textCls">비밀번호</h2>
      <input class="inputCls" v-model="pw1" type="password" Placeholder="비밀번호 (영어, 숫자, 특수문자 포함)">
    </div>

    <div class="pw2">
      <h2 class="textCls">비밀번호 확인</h2>
      <input class="inputCls" v-model="pw2" type="password" Placeholder="비밀번호 확인">
    </div>

    <div class="nick">
      <h2 class="textCls">닉네임</h2>
      <input class="inputCls" v-model="nick" type="text" Placeholder="별명 (2~8자)">
    </div>

    <div class="phoneNum">
      <h2 class="textCls">휴대폰 번호</h2>
      <div>
        <input id="num" class="inputCls" v-model="phone" type="text" Placeholder="전화번호">
        <button id="numBtn" v-on:click="getCerti">인증번호 받기</button>
      </div>
      <div>
        <input id="num" class="inputCls" v-model="area" type="text" Placeholder="인증번호">
        <button id="numBtn" v-on:click="compCerti">확인</button>
      </div>
    </div>

    <div class="area">
      <h2 class="textCls">지역 설정</h2>
      <div>
        <!-- readonly -->
        <input id="areaName" class="inputCls" type="text" Placeholder="성북구 정릉1동" readonly>
        <button id="searchBtn" v-on:click="searchMap">돋보기</button>
      </div>
    </div>

    <div class="confirm">
      <h2 class="textCls">약관 동의</h2>
      <div>
        <!-- 여긴 텍스트 파일을 불러오는 식으로 수정 -->
        <textarea name="" id="confirmCon">제 1조 (목적)
본 약관은 대한민국역사박물관 사이트(이하 "당 사이트")가 제공하는 모든 서비스(이하 "서비스")의 이용조건 및 절차, 이용자와 당 사이트의 권리, 의무, 책임사항과 기타 필요한 사항을 규정함을 목적으로 합니다.

제 2조 (약관의 명시와 개정)
당 사이트는 이 약관의 내용과 주소지, 관리자의 성명, 개인정보 보호책임자의 성명, 연락처(전화, 팩스, 전자우편 주소 등) 등을 이용자가 알 수 있도록 당 사이트의 초기 서비스화면(전면)에 게시합니다.
당 사이트는 약관의 규제에 관한 법률, 전자거래기본법, 전자서명법, 개인정보보호법 등 관련법을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.
당 사이트가 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행약관과 함께 당 사이트의 초기화면에 그 적용일자 7일 이전부터 적용일자 전일까지 공지합니다.
당 사이트는 이용자가 본 약관 내용에 동의하는 것을 조건으로 이용자에게 서비스를 제공할 것이며, 이용자가 본 약관의 내용에 동의하는 경우, 당 사이트의 서비스 제공 행위 및 이용자의 서비스 사용 행위에는 본 약관이 우선적으로 적용될 것입니다.
이 약관에 동의하는 것은 정기적으로 웹을 방문하여 약관의 변경사항을 확인하는 것에 동의함을 의미합니다. 변경된 약관에 대한 정보를 알지 못해 발생하는 이용자의 피해는 당 사이트에서 책임지지 않습니다.
본 약관에 명시되지 않은 사항은 전기통신기본법, 전기통신사업법, 방송통신심의위원회 정보통신에 관한 심의규정, 저작권법 및 기타 관련 법령의 규정에 의합니다.
        </textarea>
      </div>
      <div class="clearfix">
         <span><input type="checkbox" id="confirmChk"></span>
         <span><p id="confirmText">(필수)내용을 확인하였습니다.</p></span>
      </div>
    </div>

    <button id="finalBtn" v-on:click="signUp">회원가입 하기</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      fid: "",
      bid: "",
      pw1: "",
      pw2: "",
      nick: "",
      phone: "",
      area: ""
    }
  },
  methods:{
    // 아이디 중복 & 형식 확인 함수
    compID: function() {
      // 아래의 두 값을 조정하는 것이 관건
      let isExist = false
      let isForm = true

      let isSuccess = false
      if (~isExist && isForm) {
        alert("이용 가능한 아이디입니다.");
        isSuccess = true
      }
      else if(isExist) {
        alert("이미 존재하는 아이디입니다.");
      }
      else {
        alert("형식에 맞게 다시 작성해주세요.");
      }
      console.log(isSuccess);
    },
    // 두 비밀번호가 일치하는지 확인
    compPW: function() {
      if (this.pw1 != this.pw2) {
        // 오류를 표시하는 기능 추가
        console.log("fail");
      }
    },
    // 휴대폰으로 인증번호 보내기
    getCerti: function() {

    },
    // 휴대폰으로 보낸 인증번호가 맞는지 확인
    compCerti: function() {
      let isSame = true
      if (isSame) {
        alert("성공적으로 인증되었습니다.");
      }
      else {
        alert("인증번호가 일치하지 않습니다.");
      }
    },
    // 지역을 정하기 위한 팝업창 띄우기
    searchMap: function() {

    },
    // 모든 조건 부합할 때 회원가입 / 실패시 팝업창 
    signUp: function() {
      // 1.아이디 중복  2.비밀번호 일치  3.닉네임 중복
      // 4.휴대폰 번호 인증  5.지역 설정 여부  6.약관 동의 체크
      let isSuccess = true;
      if(isSuccess) {
        // 만들어진 멤버의 정보 데이터를 잘 감싸서 DB로 보내주기..!?
        console.log("..");
      }
      else {
        alert("조건에 맞게 다시 입력해주세요.");
      }
    }
  }
}
</script>

<style>
  #Input {
    width: 1000px;
    margin: 0 auto 300px auto;
  }
  .inputCls {
    height: 60px;
    border-color:rgb(138, 138, 138);
    background-color: rgb(250, 250, 250);
    border-radius: 10px;
    font-size: 20px;
    box-sizing: border-box;
    padding: 0px 20px;
  }
  .textCls {
  text-align: left;
  padding: 0px 60px;
  margin: 60px 0 15px 0;
  }
  .id > .inputCls {
    width: 350px;
  }
  span {
    font-size: 30px;
    margin: 0px 10px;
  }
  .pw1 > .inputCls, .pw2 > .inputCls, .nick > .inputCls {
    width: 880px;
  }
  #num {
    width: 700px;
    margin: 10px 5px 0 0 ;
  }
  button {
    width: 130px;
    height: 60px;
    border-radius: 10px;
    font-size: 20px;
    background-color: rgb(207, 207, 207);
    border: none;
    cursor: pointer;
    margin-left: 5px;
  }
  #numBtn {
    width: 180px;
  }
  #areaName {
    width: 780px;
    margin: 10px 5px 0 0 ;
    background-color: rgb(227, 227, 227);
  }
  #searchBtn {
    width: 100px;
  }
  #confirmCon {
    width: 880px;
    height: 150px;
  }
  #confirmChk {
    width: 20px;
    height: 25px;
    cursor: pointer;
    float:left;
    margin-left: 55px;
  }
  #confirmText {
    float: left;
    font-size: 20px;
    margin: 0 3px;
  }
  #finalBtn {
    width: 250px;
    height: 80px;
    font-size: 25px;
    margin-top: 60px;
  }
</style>
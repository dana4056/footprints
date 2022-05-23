<template>
  <div id="wrap">
    <div class="Div">
      <label>고객님의 정보와 일치하는 아이디입니다.</label>

      <!------------------------------- store를 사용한 코드로 수정 필요 ----------------------------------->
      <p>{{email}}</p>
      
      <router-link id="link" to="/login">로그인 하러 가기 ></router-link>
    </div>
	</div>
</template>
<script>
export default {
	data() {
		return {
      email: "abcd@naver.com"
		}
	},
	methods: {
	}
}
</script>
<style scoped>
#wrap {
    width: 450px;
    margin: 0 auto;
    padding: 220px 0;
}
.Div{
    margin: 0;
    padding: 8px 15px 9px;
    justify-content: space-between;
}
label{
    display: block;
    font-size: 15px;
    font-weight: bold;
    color: #292929;
    margin: 0 0 12px;
}
p{
    outline: none;
    width: 100%;
    height: 100px;
    background: #F8F8F8;
    border: 2px solid #BDBDBD;
    box-sizing: border-box;
    padding: 35px 15px;
		margin: 30px 0px 20px;
		border-radius: 30px;
}
#link{
  text-decoration: none;
  color:black;
}
</style>
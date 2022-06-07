<template>
  <div>
    <tool-bar></tool-bar>
    <div id="wrap">
      <div id="div1">
        <h2>{{ title }}</h2>
        <img v-bind:src=foodImg>
        <p id="foodCtg">{{foodCtg}}</p>
        <p id="views">조회 {{views}}</p>
      </div>
      <div id="div2">
        <h1>(이미지)</h1>
        <h3 id="userName">{{userName}}</h3>
        <p id="postTime">{{postTime}}분 전</p>
      </div>
      <div id="div3">
        <p>{{contents}}</p>
        <p id="orderTime">오늘 {{orderTime}}에 주문할거에요!</p>
      </div>
      <hr>
      <div id="div4">
        <h4>(이미지)</h4>
        <h4>{{curNum}} / {{maxNum}}</h4>
        <button type="button" id="join">참여하기</button>
        <button type="button" id="seePlace">나눔 장소 보기</button>
      </div>
    </div>
    <footer-area id="footer"></footer-area>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import FooterArea from '../components/FooterArea.vue'

export default {
  components:{
    ToolBar,
    FooterArea,
  },
  data() {
    return {
      title: "던킨도너츠 같이 드실분~",
      foodImg: require("../assets/donut.png"),
      foodCtg: "디저트",
      views: 0,
      userName: "사용자",
      postTime: "13",
      contents: "배달팁 3800원, 최소주문금액 13000원입니다. 맛집입니다~",
      orderTime: "17:30",
      curNum: 1,
      maxNum: 3,
    }
  },
}
</script>

<style scoped>
#wrap {
  width: 650px;
  height: 570px;
  padding: 20px 30px 30px;
  margin: 80px auto;
  border-radius: 30px;
  /* background-color: #f7f7f7; */
  text-align: left;
  font-size: 15px;
}
#div1, #div2, #div4 {
  height: 40px;
  margin-bottom: 30px;
}
#div1 > *, #div2 > *, #div4 > * {
  float: left;
  box-sizing: border-box;
  margin: 0;
  margin-right: 17px;
}
#foodCtg {
  background-color: #bbb;
  padding: 2px 5px;
}
#views {
  float: right;
  color: #777;
}
#remTime {
  font-size: 3px;
}
#div3 {
  position:relative;
  height: 400px;
}
#orderTime {
  color: rgba(255, 23, 23, 0.635);
  position:absolute;
  bottom:0px;
  right:30px;
}
hr {
  margin-bottom: 25px;
}
#div4 > button {
  float: right;
  width: 120px;
  height: 40px;
  border-radius: 20px;
  font-weight: bold;
  cursor: pointer;
}
#seePlace {
  border: 1px solid #aaa;
  background-color: #fff;
}
#join {
  border: none;
  background-color: #ccc;
}
#footer{
    height: 300px;
    position: absolute;  
    width: 100%;
    left: 0;
}
</style>
<template>
  <div>
    <tool-bar></tool-bar>
    <div id="wrap">
      <div id="headBox">
        <h2>{{ fetched.post_name }}</h2>
        
        <div id="categoryBox">
          <img v-bind:src="require(`../assets/${fetched.category}.png`)">
          <span class="category" v-bind:class="fetched.category">{{this.categories[fetched.category]}}</span>
        </div>
        <p id="views">조회 {{views}}</p>
      </div>

      <div id="userBox">
        <img src="../assets/user.png">
        <div id="userBoxEl">
          <span>{{fetched.user_name}}</span>
          <small>{{caltime(fetched.createdDate)}} 전</small>
        </div>
      </div>

      <div id="contentBox">
        <p>{{fetched.post_content}}</p>
        <p id="orderTime">{{calDay()}}, {{fetched.valid_time.get("hour")}}시 {{fetched.valid_time.get("minute")}}분에 주문할거에요!</p>
      </div>

      <div id="footBox">
        <div id="parti">
          <img src="../assets/people.png" alt="">
          <span>{{fetched.participant_num}} / {{fetched.max_person_num}}</span>
        </div>
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
import dayjs from 'dayjs'

export default {
  components:{
    ToolBar,
    FooterArea,
  },
  computed:{
    fetched(){
      return this.$store.getters.GET_DELIVERY_POST;
    },
  },
  data() {
    return {
      categories:{
        'KOR': '한식',
        'CHI': '중식',
      },
      // views: 0,
      // userName: "사용자",
      // postTime: "13",

    }
  },
  created(){
    const post_id = this.$route.params.id;
    this.$store.dispatch('FETCH_DELIVERY_DETAIL', post_id);
  },
  methods:{
    calDay(){
      const created = this.fetched.createdDate;
      const valid = this.fetched.valid_time;

      if(created.isSame(valid,"day")){
        return "오늘";
      }
      else{
        const month = String(Number(valid.get("month"))+1)
        return month +"월 "+valid.get("date")+"일";
      }
    },
    caltime(created){

      const now = dayjs();

      if(created.isSame(now,"day")){
          const ago_H = now.diff(created,"h");
          const ago_M = now.diff(created,"m");
          if(ago_H == 0){
            return String(ago_M)+"분 ";
          }
          return String(ago_H)+"시간 "+String(ago_M)+"분 ";
         }
      else{
          const ago_D = now.diff(created,"d");
          return String(ago_D)+"일 ";
      }
    }
  }
}
</script>

<style scoped>
#wrap {
  width: 650px;
  height: 570px;
  padding: 20px 30px 30px;
  margin: 80px auto;
  border-radius: 30px;
  text-align: left;
  font-size: 15px;
}
#headBox, #userBox, #footBox {
  height: 40px;
  margin-bottom: 30px;
  width: 100%;
}
#headBox > *, #footBox > * {
  float: left;
}
#headBox {
  line-height: 40px;
}
#headBox h2, #headBox p{
  margin: 0;
}
#categoryBox{
  line-height: 40px;
  margin-left: 15px;
}
#categoryBox *{
  vertical-align: middle;
}

#userBox{
  display: -webkit-box;
}

#userBoxEl{
  -webkit-box-flex:1;
  margin-left: 5px;
}

#userBoxEl *{
  display: block;
}

#userBoxEl span{
  font-weight: bold;
}
#contentBox {
  position:relative;
  height: 400px;
  border-bottom: rgb(223, 222, 222) solid 1px;
  margin-bottom: 30px;
}

#footBox img{
  width:25px;
  margin-right: 10px;
}
#parti{
  margin-top: 4px;
}
#parti > *{
  vertical-align: middle;
}

#footBox > button {
  float: right;
  height: 40px;
  border-radius: 20px;
  font-weight: bold;
  cursor: pointer;
  margin: 0 5px;
  padding: 0 18px;
}
.category{
  font-size: xx-small;
  margin: 0px 7px;
  padding: 3px 7px;
  border-radius: 3px;
  color: #ffffff;
  vertical-align:middle;
}

/* ----- 카테고리 태그 색상 지정 -------*/
.KOR{
  background-color: #6157cf;
}
.CHI{
  background-color: #ff6e6c;
}
.ETC{
  background-color: #8c8c8c;
}
/* ---------------------------------- */
#views {
  float: right;
  color: #777;
}
#remTime {
  font-size: 3px;
}

#orderTime {
  color: rgba(255, 23, 23, 0.635);
  position:absolute;
  bottom:0px;
  right:30px;
}
/* hr {
  margin-bottom: 25px;
} */

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
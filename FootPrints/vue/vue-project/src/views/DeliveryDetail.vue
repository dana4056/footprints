<template>
  <div class="scrollWrapper" :class="{fixedWrapper:isShowmap, scrollWrapper:!isShowmap}">

    <tool-bar></tool-bar>

    <!-- 나눔 장소 보기 모달 -->
    <show-map ref="showMap" v-on:change="change()"></show-map>
    <div id="wrap">
      <div id="headBox">
        <h2>{{ fetched.post_name }}</h2>
        
        <div id="categoryBox">
          <img v-bind:src="require(`../assets/category/${fetched.category}.png`)">
          <span class="category" v-bind:class="fetched.category">{{this.categories[fetched.category]}}</span>
        </div>
        <p id="views">조회 {{fetched.view_num}}</p>
      </div>

      <div id="userBox">
        <img src="../assets/user.png">
        <div id="userBoxEl">
          <span>{{fetched.member.nick}}</span>
          <small>{{caltime(fetched.createdDate)}} 전</small>
        </div>
      </div>

      <div id="contentBox">
        <p>{{fetched.post_content}}</p>
        <p id="orderTime">{{calDay()}}, {{fetched.valid_time.get("hour")}}시 {{fetched.valid_time.get("minute")}}분에 주문해요!</p>
      </div>

      <div id="footBox">
        <div id="parti">
          <img src="../assets/people.png" alt="">
          <span>{{fetched.participant_num}} / {{fetched.max_person_num}}</span>
        </div>

        <div v-if="this.$store.state.member.nick == fetched.member.nick">
          <button type="button" id="delete" v-on:click="deletePost">삭제</button>
          <button type="button" id="amend" v-on:click="amendPost">수정</button>
        </div>
        <div v-else>
          <div v-if="isJoin">
            <button type="button" id="chat" v-on:click="moveChat">채팅방 이동</button>
            <button type="button" id="exit" v-on:click="exitPost">나가기</button>
          </div>
          <div v-else>
            <div v-if="fetched.max_person_num > fetched.participant_num">
              <button type="button" id="join" v-on:click="joinPost">참여하기</button>
            </div>
            <div v-else>
              <button id="block">참여 불가</button>
            </div>
          </div>
        </div>
        <button type="button" id="seePlace" v-on:click="this.$refs.showMap.showMap(), change()">나눔 장소 보기</button>
      </div>
      
    </div>
    <to-delivery id="toDelivery"></to-delivery>
    <footer-area id="footer"></footer-area>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import FooterArea from '../components/FooterArea.vue'
import ToDelivery from '../components/ToDelivery.vue'
import ShowMap from '../components/ShowMap.vue'
import Swal from 'sweetalert2';
import dayjs from 'dayjs'

export default {
  components:{
    ToolBar,
    FooterArea,
    ToDelivery,
    ShowMap,
  },
  computed:{
    fetched(){
      return this.$store.getters.GET_DELIVERY_POST;
    },
  },
  data() {
    return {
      isJoin: false,
      post_id: 0,
      categories:{
        'KOR': '한식',
        'CHI': '중식',
        'JAP': '일식',
        'PIZ': '피자',
        'FRI': '치킨',
        'BUN': '분식',
        'FAS': '패스트푸드',
        'JOK': '족발/보쌈',
        'DES': '디저트',
        'ETC': '기타',
      },
      isShowmap : false,
      take_loc: "",      // 음식 나눌 장소
      latitude: 0,
      longtitude: 0,
    }
  },
  created(){
    this.post_id = this.$route.params.id;
    this.$store.dispatch('FETCH_DELIVERY_DETAIL', this.post_id);

    for(let i = 0; i < this.$store.state.postIdList.length; i++){
      if(this.$store.state.roomList[i].post_id == this.post_id) {
        this.isJoin = true;
      }
    }
  },
  methods:{
    change(){
        this.isShowmap =  this.$refs.showMap.openMap;
    },
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
      // 콘솔창에 시간 객체 찍을 때 표시되는 속성명과 dayjs객체 속성명 다름
      // ex) 시간(hour) -> 콘솔에는 H로 dayjs에는 h로 표시  dayjs로 다뤄야함
      const now = dayjs();
      const diffH = now.diff(created,"hour", true);

      if(diffH<24){
        const ago = now.diff(created, "minute");
        const ago_H = parseInt(ago / 60);
        const ago_M = ago - (ago_H * 60);
        if(ago_H === 0){
          return String(ago_M)+"분 ";
        }else{
          return String(ago_H)+"시간 "+String(ago_M)+"분 ";
        }
      }else{
        const ago_D = now.diff(created, "day");
        return String(ago_D)+"일 ";
      } 
    },
    amendPost() {
      // 수정했는데 안 되면 바꿔야함
      this.$router.replace("/delivery/post/" + this.post_id + "/amend"); 
    },
    exitPost() {
      //************* 채팅데이터 초기화부분 *************
      // 채팅관련 초기화(채팅방 들어갈때 알아서 다시 가져와 필요한거? 아마두)
      this.$store.state.chatLogs = [];
      this.$store.state.postIdList = [];
      this.$store.state.roomIndex = "0";
      this.$store.state.roomList = [];
      this.$store.state.userList= [];
      //***********************************************


      const roomInfo = {
        nick: this.$store.state.member.nick,
        post_id: this.$route.params.id
      };
      this.$store.dispatch('EXIT_DELIVERY_POST', roomInfo);

      Swal.fire({
        icon: 'success',
        title: '참여 취소 성공!',
        confirmButtonText: '배달 모집 목록 보러가기',
      }).then(() => {
        this.$router.replace("/delivery/post");
      })
    },
    deletePost() {
      this.$store.dispatch('DELETE_DELIVERY_POST', this.post_id);

      Swal.fire({
        icon: 'success',
        title: '글 삭제 완료!',
        confirmButtonText: '배달 모집 목록 보러가기',
      }).then(() => {
        this.$router.replace("/delivery/post");
      })
    },
    joinPost() {
      const roomInfo = {
        nick: this.$store.state.member.nick,
        post_id: this.post_id
      };

      if(roomInfo.nick != ""){
        this.$store.dispatch('JOIN_DELIVERY_POST', roomInfo);
      }else{
        alert("로그인 후 참여 가능합니다.");
        this.$router.replace("/delivery/post");
      }

    },
    moveChat() {
      this.$router.replace("/chat/" + this.$store.state.member.nick);
    },
  }
}
</script>

<style scoped>
.fixedWrapper{
  width: 100%;
  height: 100%;
  position: fixed;
  overflow: hidden;
}
.scrollWrapper{
  min-height: 100%;
  position: relative;
}
#wrap {
  width: 650px;
  height: 570px;
  margin: 80px auto;
  padding: 20px 30px 30px;
  border-radius: 30px;
  font-size: 15px;
  text-align: left;
}
#headBox, #userBox, #footBox {
  width: 100%;
  height: 40px;
  margin-bottom: 30px;
}
#headBox > * {
  float: left;
}
#footBox > * {
  float: right;
}
#headBox {
  line-height: 40px;
}
#headBox h2, #headBox p{
  margin: 0;
}
#categoryBox{
  margin-left: 15px;
  line-height: 40px;
}
#categoryBox *{
  vertical-align: middle;
}
#userBox{
  display: -webkit-box;
}
#userBoxEl{
  margin-left: 5px;
  -webkit-box-flex:1;
}
#userBoxEl *{
  display: block;
}
#userBoxEl span{
  font-weight: bold;
}
#contentBox {
  height: 400px;
  margin-bottom: 30px;
  border-bottom: rgb(223, 222, 222) solid 1px;
  position:relative;
}
#footBox img{
  width:25px;
  margin-right: 10px;
}
#parti{
  float: left;
  margin-top: 4px;
}
#parti > *{
  vertical-align: middle;
}
#footBox button{
  height: 40px;
  margin: 0 5px;
  padding: 0 18px;
  border-radius: 20px;
  font-weight: bold;
  float: right;
  cursor: pointer;
}
.category{
  margin: 0px 7px;
  padding: 3px 7px;
  border-radius: 3px;
  color: #ffffff;
  font-size: xx-small;
  vertical-align:middle;
}

/* ----- 카테고리 태그 색상 지정 -------*/
.KOR{
  background-color: #6157cf;
}
.CHI{
  background-color: #ff6e6c;
}
.JAP{
  background-color: #ff0400;
}
.PIZ{
  background-color: #7cc66bbd;
}
.FRI{
  background-color: #ffde5b;
}
.BUN{
  background-color: #e55e5e;
}
.FAS{
  background-color: #ffaa00;
}
.JOK{
  background-color: #c28156;
}
.DES{
  background-color: #ff67949f;
}
.ETC{
  background-color: #8c8c8c;
}
/* ---------------------------------- */

#views {
  color: #777;
  float: right;
}
#remTime {
  font-size: 3px;
}
#orderTime {
  bottom: 0px;
  color: rgba(255, 23, 23, 0.635);
  position: absolute;
  right: 30px;
}
#seePlace {
  background-color: #fff;
  border: 1px solid #aaa;
}
#join, #chat, #amend, #exit, #delete {
  border: none;
}
#block {
  background-color: rgba(255, 127, 127, 0.826);
  border: none;
  pointer-events: none;
}
#footer{
  width: 100%;
  height: 300px;
  position: absolute;
  left: 0;
}
#toDelivery{
  height: 100px;
  bottom: 0;
  position: fixed;
  right: 200px;
}

</style>
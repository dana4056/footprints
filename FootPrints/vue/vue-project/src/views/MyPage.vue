<template>
  <div>
    <tool-bar></tool-bar>
    <div class="wrap">
      <p :id="[clickDelivery ? 'isClicked' : ' ']"  v-on:click.prevent="goDelivery">배달</p>
      <p :id="[clickLike ? 'isClicked' : ' ']"      v-on:click.prevent="goLike">좋아요</p>
      <p :id="[clickSetting ? 'isClicked' : ' ']"   v-on:click.prevent="goSetting">설정</p>
    </div>
    <div class="wrap2">
      <div v-if="clickSetting === false" class="profile">
        <div id="profileBox">
          <img id="profileImage" src="../assets/donut.png">
          <p id="profileNick">{{this.$store.state.member.nick}}</p>
          <div id="profileArea">
            <img id="areaImage" src="../assets/placeholder.png">
            <p id="areaName">{{this.$store.state.member.area}}</p>
          </div>
          <div class="router">
            <router-link to="/home" id="like">
              <img id="likeImage" src="../assets/heart.png">
              <p>좋아요</p>
            </router-link>
            <router-link to="/chat" id="chat">
              <img id="chatImage" src="../assets/chatting.jpg">
              <p>채팅</p>
            </router-link>
          </div>
        </div>
      </div>
      <div class="component">
        <my-delivery v-if="clickDelivery"></my-delivery>
        <my-like v-if="clickLike"></my-like>
        <my-setting v-if="clickSetting"></my-setting>
      </div>
    </div>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import MyDelivery from '../components/MyDelivery.vue'
import MyLike from '../components/MyLike.vue'
import MySetting from '../components/MySetting.vue'

export default {
  components:{
    ToolBar,
    MyDelivery,
    MyLike,
    MySetting
  },
 created(){
    // 뭔가 엄청 해야겠지..?
  },
  data() {
    return {
      clickDelivery: true,
      clickLike: false,
      clickSetting: false,
    }
  },
  methods: {
    goDelivery() {
      console.log("배달 뷰 클릭");
      this.clickDelivery = true;
      this.clickLike = false;
      this.clickSetting = false;
    },
    goLike() {
      console.log("좋아요 뷰 클릭");
      this.clickDelivery = false;
      this.clickLike = true;
      this.clickSetting = false;
    },
    goSetting() {
      console.log("설정 뷰 클릭");
      this.clickDelivery = false;
      this.clickLike = false;
      this.clickSetting = true;
    }
  }
}
</script>

<style scoped>
.wrap {
  height: 50px;
  background-color: rgb(243, 243, 243);
  padding-left: 775px;
  padding-top: 200px;
}
.wrap > p {
  width: 120px;
  margin: 0 60px 0 0;
  float: left;
  font-size: 18px;
  cursor: pointer;
}
#isClicked {
  padding-bottom: 7px;
  border-bottom:#7aab85 solid 15px;
}

.wrap2 {
  background-color: rgb(255, 255, 255);
  height: 1000px;
}
.wrap2 > div {
  float: left;
}

.profile {
  width: 775px;
  height: 100%; 
  /* background-color: aqua; */
}
#profileBox {
  margin: 50px 100px 0px 320px;
  width: 350px;
  height: 420px;
  background-color: rgb(255, 255, 255);
  border-radius: 40px;
  box-shadow: 1px 1px 2px 6px #98d0a443;
}
#profileImage {
  margin-top: 30px;
  width: 120px;
  height: 120px;
  border-radius: 50%;
}
#profileNick {
  font-size: 23px;
  font-weight: bold;
  margin: 10px 0 20px 0
}
#profileArea {
  text-align: center;
  display: inline-block;
}
#profileArea > * {
  float: left;
}
#areaImage {
  width: 30px;
  opacity: 0.7;
}
#areaName {
  font-size: 18px;
  margin: 2px 0;
  color: rgb(166, 166, 166);
}
.router{
  padding: 0 50px 0 70px;
  margin-top: 30px;
  width: 180px;
  height: 120px;
  /* background-color: aqua; */
  text-align: center;
  display: inline-block;
}
.router > * {
  float: left;
  color: black;
  text-decoration: none;
  font-weight: bold;
  font-size: 17px;
}
#like {
  margin: 0 0;
}
#likeImage{
  margin-top: 5px;
  width: 40px;
  height: 35px;
}
#chat {
  margin: 0 0 0 75px;
}
#chatImage {
  width: 40px;
  height: 40px;
}
#chat > p {
  margin: 13px 0px 0 0px;
}

.component {
  height: 100%;
  
}
</style>
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
            <img id="AreaImage" src="../assets/placeholder.png">
            <p id="AreaName">{{this.$store.state.member.area}}</p>
          </div>
          <div class="router">
            
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
  width: 100px;
  margin: 0 25px;
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
  width: 800px;
  height: 100%; 
}
#profileBox {
  margin: 50px 100px 0px 300px;
  width: 350px;
  height: 450px;
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
  margin: 10px 0 15px 0
}
#profileArea {
  text-align: center;
  display: inline-block;
}
#profileArea > * {
  float: left;
}
#AreaImage {
  width: 30px;
  opacity: 0.7;
}
#AreaName {
  font-size: 18px;
  margin: 2px 0;
  color: rgb(166, 166, 166);
}
.router{
  
}


.component {
  height: 100%;
}
</style>
<template>
  <div class="wrap" :class="{fixedWrapper:isShowmap, scrollWrapper:!isShowmap}">

      <tool-bar></tool-bar>
      <show-map ref="showMap" v-on:change="change()"></show-map>
      <button type="button" id="seePlace" v-on:click="this.$refs.showMap.showMap(), change()">나눔 장소 보기</button>
      <div id="content">
        HI
      </div>
      <up-button id="up_button"></up-button>
      <!-- footer 지역에 닿으면 사라지는 이슈 -->
      <footer-area id="footer"></footer-area>
  </div> 
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import FooterArea from '../components/FooterArea.vue'
import UpButton from '../components/UpButton.vue'
import ShowMap from '../components/ShowMap.vue'
// import NoticeArea from '../components/NoticeArea.vue'

export default {
    components:{
        ToolBar,
        FooterArea,
        UpButton,
        ShowMap,
        // NoticeArea,
    },
    data() {
      return {
        isShowmap : false,
      }
    },
    methods:{
      change(){
          console.log("change");
          this.isShowmap =  this.$refs.showMap.openMap;
      },
    }
}
</script>

<style scoped>


.fixedWrapper{
  position: fixed;
  width: 100%;
  height: 100%;
  overflow: hidden;
}

.scrollWrapper{
    min-height: 100%;
    position: relative;
}

.wrap{
    min-height: 100%;
    position: relative;
}
#content{
    height: 1000px;
    padding-bottom: 300px;
}
#footer{
    height: 300px;
    position: absolute;  
    width: 100%;
    left: 0;
}
#up_button{
    position: fixed;
    bottom: 70px;
    right: 200px;
    z-index: 1000;
}
#delivery{
  float:left;
  width:50%;
  height:50%;
}
#room{
  clear:right;
  float:right;
  width:50%;
  height:50%;
}
#notice{
  float:left;
  width:50%;
  height:50%;
}
#event{
  clear:right;
  float:right;
  width:50%;
  height:50%;
}

</style>
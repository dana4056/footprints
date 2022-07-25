<template>
  <div id="wrap">
      <tool-bar></tool-bar>
      <div id="content">
        <div id="delivery">
          배달리스트뷰
          <delivery-area></delivery-area>
        </div>
        <div id="room">
          방쉐어
          <room-share-area></room-share-area>
        </div>
        <div id="notice">
          공지사항
          <notice-area></notice-area>
        </div>
        <div id="event">
          이벤트
          <event-area></event-area>
        </div>
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
import NoticeArea from '../components/NoticeArea.vue'
import DeliveryArea from '../components/DeliveryArea.vue'
import EventArea from '../components/EventArea.vue'
import RoomShareArea from '../components/RoomShareArea.vue'

export default {
    components:{
        ToolBar,
        FooterArea,
        UpButton, 
        NoticeArea,
        DeliveryArea,
        EventArea,
        RoomShareArea,
    }
}
</script>

<style scoped>
#wrap{
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
    height: 100x;
    position: fixed;
    bottom: 0;
    right: 200px;
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
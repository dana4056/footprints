<template>
  <div class="wrap">
      <tool-bar></tool-bar>
      <div id="content">
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
import { store } from "../store/store.js";

export default {
    components:{
        ToolBar,
        FooterArea,
        UpButton,
    },
    created(){
      let kakao = window.kakao;
      let geocoder = new kakao.maps.services.Geocoder();

      let callback = function(result, status) {
          if (status === kakao.maps.services.Status.OK) {
              let si = result[0].address.region_1depth_name;
              let gu = result[0].address.region_2depth_name;
              let dong = result[0].address.region_3depth_name;
              console.log(si+" "+gu+" "+dong);

              store.commit('SET_DELIVERY_AREA', (si+" "+gu+" "+dong));
          }
      };

      const COOR = "coords";

      function handleGeoSucces(position) {
        // console.log(position);
        let lat = position.coords.latitude;
        let lon = position.coords.longitude;
        let coord = new kakao.maps.LatLng(lat, lon);
        console.log(lat, lon);
        console.log(coord);
        geocoder.coord2Address(coord.getLng(), coord.getLat(), callback)
      }

      function handleGeoError() {
        console.log("Can't access geo location");
        return;
      }

      function askForCoords() {
        navigator.geolocation.getCurrentPosition(handleGeoSucces, handleGeoError);
        return;
      }

      function loadCoords() {
        const loadedCoords = localStorage.getItem(COOR);
        if (loadedCoords === null) {
          askForCoords();
        } else {
          //
        }
      }

      function init() {
        loadCoords();
      }
      console.log("user:",store.state.member.nick);
      if(store.state.member.nick == ""){
        init(); 
      }
    },
    // methods: {
    //   setPresentArea(area) {
    //   this.$store.commit('SET_DELIVERY_AREA', area);
    //   }
    // }
}
</script>

<style scoped>
.wrap{
  min-height: 100%;
  position: relative;
}
#content{
  height: 1000px;
  padding-bottom: 300px;
}
#footer{
  width: 100%;
  height: 300px;
  position: absolute;  
  left: 0;
}
#up_button{
  position: fixed;
  bottom: 70px;
  right: 200px;
  z-index: 1000;
}
#delivery{
  width:50%;
  height:50%;
  float:left;
}
#room{
  width:50%;
  height:50%;
  clear:right;
  float:right;
}
#notice{
  float:left;
  width:50%;
  height:50%;
}
#event{
  width:50%;
  height:50%;
  clear:right;
  float:right;
}
</style>
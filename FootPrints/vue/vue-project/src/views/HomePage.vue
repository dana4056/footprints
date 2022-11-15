<template>
  <div class="wrap">
      <tool-bar></tool-bar>
      <div id="content">
        <section id="sec1" class="sec">
          <div>
            <h2>혼자 시키기 힘든 배달을 함께</h2>
            <p>1인분 배달 안되는 곳의 음식을 함께 주문해요</p>
          </div>
          <img src="../assets/home1.png" alt="">
        </section>

        <section id="sec2" class="sec">
          <img src="../assets/home2.png" alt="">
          <div class="textBox">
            <h2>부담을 나눠요</h2>
            <p>나날이 비싸지는 최소주문 금액과 배달팁을 함께 나눠요</p>
          </div>
        </section>

        <section id="sec3" class="sec">
          <img src="../assets/home3.png" alt="">
          <div class="textBox"> 
            <h2>개인정보 공유 노!</h2>
            <p>연락처를 따로 공유하지 않고 사이트 내의 채팅으로 연락해요!</p>
          </div>
        </section>

        <section id="sec4" class="sec">
          <div>
            <h2>쉽게 만나도록 도와드려요</h2>
            <p>동네 사람들끼리 부르는 장소 별명을 지정할 수 있어요!<br>또한 실제 주소와 지도로 장소에 찾아가실 수 있도록 도와드립니다.</p>
          </div>
          <img src="../assets/home4.png" alt="">
        </section>

      </div>
      <up-button id="up_button"></up-button>
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

              store.commit('SET_DELIVERY_AREA', (si+" "+gu+" "+dong));
          }
      };

      const COOR = "coords";

      function handleGeoSucces(position) {
        let lat = position.coords.latitude;
        let lon = position.coords.longitude;
        let coord = new kakao.maps.LatLng(lat, lon);
        geocoder.coord2Address(coord.getLng(), coord.getLat(), callback)
      }

      function handleGeoError() {
        // console.log("Can't access geo location");
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

      if(store.state.member.nick == ""){
        init(); 
      }
    },
}
</script>

<style scoped>
.wrap{
  height: 1000px;
}
img{
  width: 900px;
}
#content{
  height: auto;
  min-height: 100%;
    /* footer높이 */
  padding-bottom: 300px; 
}
.textBox{
  display:inline-block;
  padding:0 20px; 
  text-align: left;
}
.sec{
  background-color:#dee1e9;
  padding: 6rem 0;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
#sec1{
  display: block;
  /* background-color: #E6F3E6; */
}
#sec2 img{
  width:25%;
}
#sec4{
  display: block;
}
#footer{

  height: 300px;
  position : relative;
  transform : translateY(-100%);

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
<!-- 해당 컴포넌트 사용시 부모 컴포넌트에서 해야할 것 
    1. 최상위 태그에 class="{.fixedWrapper:isShowmap, .scrollWrapper:!isShowmap}"
    2. 컴포넌트 태그에 ref="showMap" v-on:change="change()" 속성 추가
    3. 해당 컴포넌트 실행시키는 버튼 추가
        <button v-on:click="this.$refs.showMap.showMap(), change()">지도보기</button>
    4. isShowmap : false변수와 change() 메서드 생성
    5. .fixedWrapper .scrollWrapper 클래스 스타일 지정-->
<template>
    <div  :class="{visibleBox:openMap, invisibleBox:!openMap}">
      <div class="white-bg">
        <h4>나눔 장소 확인</h4>
        <div id="map"></div>
        <p>{{this.take_lok}}</p>
        <button v-on:click="closeMap" class="close">닫기</button>
      </div>
    </div>
</template>

<script>
export default {
    data(){
      return{
        openMap: false,
        take_loc: this.$store.getters.GET_DELIVERY_POST.take_loc,      // 음식 나눌 장소
        latitude: this.$store.getters.GET_DELIVERY_POST.y,
        longtitude: this.$store.getters.GET_DELIVERY_POST.x
      }
    },
    mounted(){
        let $vm = this;
        let kakao = window.kakao;
        let mapContainer = document.getElementById('map'),
        mapOption = { 
            center: new kakao.maps.LatLng($vm.latitude, $vm.longtitude), 
            level: 3 // 지도의 확대 레벨
        };
        console.log(mapOption);
        console.log($vm.latitude, $vm.longtitude);

        let map = new kakao.maps.Map(mapContainer, mapOption);

        let markerPosition  = new kakao.maps.LatLng($vm.latitude, $vm.longtitude); 

        let marker = new kakao.maps.Marker({
            position: markerPosition
        });

        marker.setMap(map); 
    },
    methods:{
      showMap(){
        this.openMap = true;
      },
      closeMap(){
        this.openMap = false;
      },
    }
}
</script>

<style>
.wrapper{
  width: 100%;
  height: 100%;
  position: fixed;
  overflow: hidden;
}
#map{
  width: 95%;
  height: 400px;
  margin: 0 auto;
  border-radius: 20px;
}
.visibleBox{
  width:100%;
  height:100%;
  background: rgba(0,0,0,0.6);
  visibility: visible;
  position: fixed;
  z-index: 1050;
}
.invisibleBox{
  width:100%;
  height:100%;
  visibility: hidden;
  position: fixed;
}
.invisibleBox h4{
  margin: 12px auto;
}
.visibleBox h4{
  margin: 12px auto;
}
.white-bg{
  width: 50%;
  margin: 170px auto;
  padding: 15px 0;
  background: white;
  border-radius: 62px;
}
.close{
  padding: 5px 15px;
  background:#ccc;
  border:none;
  border-radius: 10px;
  color:white;
  font-weight: bold;
  cursor:pointer;
}
.close:hover{
  transform: scale(1.1);
  transition: all 0.5s;
  color:white;
  font-weight: bold;
}
</style>
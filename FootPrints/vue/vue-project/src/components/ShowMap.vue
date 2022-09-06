<!-- 해당 컴포넌트 사용시 부모 컴포넌트에서 해야할 것 
    1. :class="{fixedWrapper:isShowmap, scrollWrapper:!isShowmap}"
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
        latitude: this.$store.getters.GET_DELIVERY_POST.x,
        longtitude: this.$store.getters.GET_DELIVERY_POST.y
      }
    },
    mounted(){
        let $vm = this;
        let kakao = window.kakao;
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = { 
            center: new kakao.maps.LatLng($vm.latitude, $vm.longtitude), // 지도의 중심좌표
            level: 3, // 지도의 확대 레벨
            mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
        };
        console.log(mapOption);
        console.log($vm.latitude, $vm.longtitude);

        var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
        // 마커가 표시될 위치입니다 
        var markerPosition  = new kakao.maps.LatLng($vm.latitude, $vm.longtitude); 

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            position: markerPosition
        });
        // 마커가 지도 위에 표시되도록 설정합니다
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
  position: fixed;
  width: 100%;
  height: 100%;
  overflow: hidden;
}
#map{
    width: 95%;
    height: 400px;
    margin: 0 auto;
    border-radius: 20px;
}

.visibleBox{
  visibility: visible;
  z-index: 1050;
  width:100%;
  height:100%;
  background: rgba(0,0,0,0.6);
  position: fixed;
}
.invisibleBox{
  visibility: hidden;
  width:100%;
  height:100%;
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
    background: white;
    border-radius: 62px;
    padding: 15px 0;
}

.close{
  cursor:pointer;
  border:none;
  background:#ccc;
  color:white;
  font-weight: bold;
  border-radius: 10px;
  padding: 5px 15px;
}

.close:hover{
  color:white;
  font-weight: bold;
  transform: scale(1.1);
  transition: all 0.5s;
}
</style>
<template>
    <div v-show="Loading" class="loadingBox">
        <img src="../assets/loading.gif" alt="">
    </div>

    <div v-show="!Loading" :class="{visibleBox:openMap, invisibleBox:!openMap}">
      <div class="white-bg">
        <h4>나눔 장소 확인</h4>
        <div id="map" ref="map"></div>
        <p>{{this.detail_loc}}</p>
        <button v-on:click="closeMap" class="close">닫기</button>
      </div>
    </div>
</template>

<script>

export default {
    data(){
      return{
        isLoading: this.$store.getters.GET_ISLOADING,
        openMap: false,
        take_loc: this.$store.getters.GET_DELIVERY_POST.take_loc,      // 음식 나눌 장소
        detail_loc: "",
        latitude: this.$store.getters.GET_DELIVERY_POST.lat,
        longtitude: this.$store.getters.GET_DELIVERY_POST.lon
      }
    },
    computed:{
      Loading(){
        return this.updateLoading();
      }
    },
    watch:{
      //isLoading이 변경될 때마다 이 함수 실행
      isLoading(flag){
        if(!flag){
          this.showMap();
        }
      }
    },
    mounted(){
        // console.log("show map mounted");
    },
    methods:{
      updateLoading(){
        this.isLoading = this.$store.getters.GET_ISLOADING;
        return this.isLoading;
      },
      showMap(){
        // console.log("쇼맵 시작");
        this.take_loc = this.$store.getters.GET_DELIVERY_POST.take_loc;     // 음식 나눌 장소
        this.latitude = this.$store.getters.GET_DELIVERY_POST.lat;
        this.longtitude = this.$store.getters.GET_DELIVERY_POST.lon;

        let $vm = this;
        let kakao = window.kakao;
        let mapContainer = this.$refs.map;
        
        console.log($vm.latitude, $vm.longtitude);
        let coord = new kakao.maps.LatLng($vm.latitude, $vm.longtitude);  // 위도, 경도 정보

        let mapOption = { 
            center: coord,
            level: 3, // 지도의 확대 레벨
            mapTypeId : kakao.maps.MapTypeId.ROADMAP
        };

        let map = new kakao.maps.Map(mapContainer, mapOption);

        let marker = new kakao.maps.Marker({
            position: coord
        });
        
        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map); 

        // 좌표 -> 상세 주소로 변경 위함
        let geocoder = new kakao.maps.services.Geocoder();

        let callback = function(result, status) {
          if (status === kakao.maps.services.Status.OK) {
            console.log(result[0]);
            $vm.detail_loc = result[0].address.address_name;
          }
        }
        geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);


        // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        var iwContent = `<div id="infoWindow" style="padding:5px;">${this.take_loc}<br><a href="https://map.kakao.com/link/map/${this.take_loc},${$vm.latitude},${$vm.longtitude}" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/${this.take_loc},${$vm.latitude},${$vm.longtitude}" target="_blank">길찾기</a></div>`; 
        var iwPosition = new kakao.maps.LatLng(33.450701, 126.570667); //인포윈도우 표시 위치입니다

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            position: iwPosition,
            content: iwContent
        });

        // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
        infowindow.open(map, marker);


        this.openMap = true;
        // console.log("쇼맵 끝");
        return;
      },
      closeMap(){
        this.openMap = false;
      },
    }
}
</script>

<style>
#infoWindow a{
  color: white;
  padding: 0 3px;
  text-decoration: none;
  background-color: #669270;
  border-radius: 5px;
}
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
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  
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
.loadingBox img{
  padding: 25% 50%;
  background-color: #ffffff;
  z-index: 10;
  width: 50px;
  position: absolute;
  left: 0px;
}
</style>
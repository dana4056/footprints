<template>
  <div>
    <tool-bar></tool-bar>
    <div id="wrap">
      <div id="wrap2">
        <div style="float:left; width:300px;">
          <div id="category">
            <p>음식 카테고리를 설정해주세요.</p>
            <select v-model="category">
              <option value="카테고리" selected="selected" disabled hidden>카테고리</option>
              <option value="KOR">한식</option>
              <option value="CHI">중식</option>
              <option value="JAP">일식</option>
              <option value="PIZ">피자</option>
              <option value="FRI">치킨</option>
              <option value="BUN">분식</option>
              <option value="FAS">패스트푸드</option>
              <option value="JOK">족발/보쌈</option>
              <option value="DES">디저트</option>
              <option value="ETC">기타</option>
            </select>
          </div>
          
          <div id="postTTL">
            <p>게시물이 유효한 시간을 정해주세요.</p>
            <input v-model="valid_time" type="datetime-local" 
            v-bind:min=minDate v-on:focusout="setMinValue">
          </div>

          <div id="peopleNum">
            <p>모집할 인원을 정해주세요.</p>
            <select v-model.number="max_person_num">
              <option value="0" selected="selected">상관없음</option>
              <option value="2">2명</option>
              <option value="3">3명</option>
              <option value="4">4명</option>
              <option value="5">5명</option>
              <option value="6">6명</option>
              <option value="7">7명</option>
              <!-- <option value="etc">그 외</option> -->
            </select>
          </div>

          <div id="place">
            <label>음식을 나눌 장소를 지정해주세요.</label>
            <div class="kmap" ref="map"></div>
            <input v-model="take_loc" v-if="inputVisible" 
            placeholder="장소 별명: ex) 세븐일레븐 앞">
          </div>
        </div>

        <div style="float:right; width:350px;">
          <input v-model="post_name" id="post_name" placeholder="제목을 입력하세요.">
          <hr>
          <textarea v-model="post_content" id="post_content" type="text" placeholder="배달팁, 최소주문 금액을 적어주세요!"></textarea>
          <button type="submit" v-on:click.prevent="register">등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import Swal from 'sweetalert2';
import dayjs from 'dayjs'

export default {
  components:{
        ToolBar,
  },
  mounted() {
    let $vm = this;
    // 날짜 입력 최소값 지정(현시간)
    $vm.minDate = dayjs().format("YYYY-MM-DDTHH:mm");

    // 지도 창 생성
    let kakao = window.kakao;
    var container = this.$refs.map;
    var options = { 
      center: new kakao.maps.LatLng(37.56676113296615, 126.97865227682179),
      level: 10
    };
    const mapInstance = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

    var geocoder = new kakao.maps.services.Geocoder();

    // 마커 생성
    var marker = new kakao.maps.Marker({ 
      position: mapInstance.getCenter(),
    }); 
    marker.setMap(mapInstance);

    // 줌인 줌아웃
    var zoomControl = new kakao.maps.ZoomControl();
    mapInstance.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

    // 클릭 이벤트 등록
    kakao.maps.event.addListener(mapInstance, 'click', function(mouseEvent) {        
      var latlng = mouseEvent.latLng; 

      marker.setPosition(latlng);

      $vm.latitude = latlng.getLat();   // 클릭 장소 위도
      $vm.longtitude = latlng.getLng(); // 클릭 장소 경도
      // console.log( $vm.latitude+" "+$vm.longtitude );

      // 지원 - 79번째 줄에서 geocoder 선언해줬고, 97번째줄 부터 103번째 줄까지가 주소 가져오는 코드입니다!
      let callback = function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            console.log(result);
            $vm.area_name = result[0].address_name;
        }
      }
      geocoder.coord2RegionCode(latlng.getLng(), latlng.getLat(), callback);
      $vm.inputVisible = true;
    });
  },
  data() {
    return {
      post_name: "",        // 글 제목
      post_content: "",     // 글 내용
      category: "카테고리",  // 음식 카테고리
      take_loc: "",         // 음식 나눌 장소 이름(ex. 북악관)
      participant_num: 0,   // 현재 참가 인원
      max_person_num: 0,    // 모집 인원
      valid_time: "",       // 게시물 유효 시간
      view_num:0,           // 조회수
      area_name: "",        // 행정지역명(선택 장소)
      latitude: 0,          // 선택지역 정보 y(위도)
      longtitude: 0,        // 선택지역 정보 x(경도)
      // ------- member entity 참조할건데 임시로 --------------
      user_name: "",     // 작성자 이름
      
      minDate: "",
      inputVisible: false,
    }
  },
  methods: {
    register() {
      if (this.submitData()){
        this.$store.dispatch('FETCH_USER') //의도가 뭐지 -> 작성자 정보 포함해야하니까
          const post = {
            post_name: this.post_name,           // 글 제목
            post_content: this.post_content,     // 글 내용
            category: this.category,             // 음식 카테고리
            take_loc: this.take_loc,             // 음식 나눌 장소
            // post_area : this.post_area,
            post_area : this.$store.state.member.area,
            participant_num: 1,                  // 현재 참가 인원
            max_person_num: this.max_person_num, // 모집 인원
            valid_time: this.valid_time,         // 게시물 유효 시간
            view_num: this.view_num ,            // 조회수
            nick:this.$store.state.member.nick,
            lat: this.latitude,
            lon: this.longtitude,
        }
        console.log("POST\n",post);
        this.$store.dispatch('POST_DELIVERY_POST', post)

				Swal.fire({
          icon: 'success',
          title: '글이 등록되었습니다.',
          confirmButtonText: '배달 모집 목록 보러가기',
        }).then(() => {
          this.$router.replace("/delivery/post");
        })
			}
      else {
        alert("입력이 올바른지 확인해주세요.")
      }
    },
		submitData() {
      if (this.post_name != "" && this.post_content != "" && 
          this.category != "카테고리" && this.take_loc != "" &&
          this.valid_time != "" ){
        return true;
      }
      else {
        return false;
      }
    },
    setMinValue() {
      const valid = dayjs(this.valid_time);
      if(valid.isBefore(dayjs(),"minute")) {
          alert('현재 시간보다 이전의 날짜는 설정할 수 없습니다.');
          this.valid_time = dayjs();
      }
    }
  }
}
</script>

<style scoped>
#wrap {
  background-color: #f2f2f2;
  height: 800px;
  padding: 80px 0;
}
#wrap2 {
    width: 800px;
    height: 570px;
    padding: 40px 50px 30px;
    margin: 0px auto;
    border-radius: 30px;
    background-color: white;
    text-align: left;
    font-size: 15px;
}
p{
  margin-top: 0px;
  margin-left: 3px;
}
select, #postTTL > input, #place > input{
    outline: none;
    width: 80%;
    height: 35px;
    background: #F8F8F8;
    border: 1px solid #BDBDBD;
    box-sizing: border-box;
    border-radius: 30px;
    padding: 8px 15px 9px;
    margin-bottom: 15px;
}
#postTTL > input {
  padding-left: 20px;
}
#post_name{
  font-weight: bold;
  font-size: 25px;
  color: #555;
  border: none;
  outline: none;
  margin-top: 5px;
  padding-left: 5px;
}
hr{
  margin: 25px 0;
}
#post_content{
  font-size: 15px;
  height: 400px;
  width: 300px;
  padding: 5px;
  border: none;
  outline: none;
}
button {
  float: right;
  width: 80px;
  height: 35px;
  border: none;
  border-radius: 10px;
  background-color: #ddd;
  margin-top: 20px;
  cursor: pointer;
}
.kmap{
  margin-top: 20px;
  width: 400px;
  height: 220px;
}
#place > input {
  margin: 15px 60px 0;
  text-align: center;
}
</style>
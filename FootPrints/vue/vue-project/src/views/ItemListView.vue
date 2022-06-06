
<template>
  <div>
    <tool-bar></tool-bar>
    <div id="content">
      배달 리스트 뷰 ~.~
      <router-link to="/writing" class="link">글 작성</router-link> 
      <hr>
      <router-link to="/viewDetails" class="link">상세보기 페이지 예시</router-link> 
    </div>
    <footer-area id="footer"></footer-area>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import FooterArea from '../components/FooterArea.vue'

export default {
  components:{
    ToolBar,
    FooterArea,
  }
}
</script>

<style scoped>
hr{
  width: 50%;
}
#wrap{
    /* height : 100%; */
    /* position: relative; */
}
#content{
    /* height: 2000px; */
    height : 100%;
}
#footer{
    height: 300px;
    /* position: absolute;  
    width: 100%;
    bottom: 0;
    left: 0; */
    position : relative;
    transform : translateY(-100%);
}
</style>
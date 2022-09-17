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

export default {
    components:{
        ToolBar,
        FooterArea,
        UpButton,
    },
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
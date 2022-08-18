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

// import NoticeArea from '../components/NoticeArea.vue'

export default {
    components:{
        ToolBar,
        FooterArea,
        UpButton,
        // NoticeArea,
    }
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
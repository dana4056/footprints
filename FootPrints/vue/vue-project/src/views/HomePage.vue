<template>
  <div id="wrap">
      <tool-bar></tool-bar>
      
      <div id="content">홈페이지 ~.~</div>
      
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

</style>
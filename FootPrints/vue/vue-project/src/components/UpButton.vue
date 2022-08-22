<!-- 부모 컴포넌트에 다음 css 추가
#up_button{
    position: fixed;
    bottom: 70px;
    right: 200px;
    z-index: 1000;
} -->

<template>
  <span class="upButton" :class="{upButtonHidden:!showBnt}">
    <i v-on:click="upClick" id="up_button" class="fa-solid fa-circle-chevron-up fa-3x"></i>
    <!-- <i v-on:click="upClick" id="up_button" class="fa-solid fa-caret-up fa-3x"></i> -->
  </span>
</template>

<script>
export default {
  data () {
    return {
      showBnt: false,
    }
  },
  mounted () {
    window.addEventListener('scroll', this.onScroll);
  },
  beforeUnmount () {
    window.removeEventListener('scroll', this.onScroll);
  },
  methods:{
    upClick(){
      window.scrollTo({top:0,  behavior:'smooth'});
    },
    onScroll () {
      if (document.documentElement.scrollTop <10){
        this.showBnt = false;
      }else{
        this.showBnt = true;
      }
    }
  },
}
</script>
<style scoped>
#up_button{
  color: #cadece;
}
.upButtonHidden{
  display: none;
}
</style>


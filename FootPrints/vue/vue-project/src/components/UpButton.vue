<template>
  <span class="upButton">
    <i v-on:click="upClick" id="up_button" class="fa-solid fa-caret-up fa-3x"></i>
  </span>
</template>

<script>
export default {
  methods:{
    upClick(){
      window.scrollTo(0, 0);
    } 
  },
}
</script>
<style scoped>
.fa-solid{
  color: #7AAB85;
}
</style>


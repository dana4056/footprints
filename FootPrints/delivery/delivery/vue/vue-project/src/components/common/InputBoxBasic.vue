<template>
  <div class="inputDiv">
      <label for="name">텍스트</label>
      <input id='name' type="text" placeholder="내용을 입력하세요">
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
label{
    text-align: left;
    display: block;
    font-size: 15px;
    font-weight: bold;
    color: #292929;
    margin: 0 0 12px;
}
input{
    width: 100%;
    height: 40px;
    background: #F8F8F8;
    border: 1px solid #BDBDBD;
    box-sizing: border-box;
    border-radius: 8px;
    padding: 8px 15px 9px;
}
</style>
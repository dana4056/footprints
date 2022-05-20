<template>
  <div id="nav">
    <a href="#" class="nav_el">발자취</a>
    <div>
      <button class="nav_el">같이 배달시키기</button>  
      <i class="fa-solid fa-comments nav_el"></i>
      <a href="#" class="nav_el">로그인</a>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scope>
  #nav {
    height: 50px;
    display: flex;
    align-items: center;
    align-items: center;
    padding: 6px;
    justify-content: space-around;
  }
  .nav_el{
    margin:3px 10px;
    text-decoration: none;
    color: black;
  }

  button{
    height: 30px;
    background: #c4c4c4;
    border: 1px solid #cdcdcd;
    padding: 2px 8px;
    border-radius: 15px;
  }

</style>
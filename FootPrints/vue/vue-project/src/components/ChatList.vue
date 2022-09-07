<template>
  <div class="chat__body" id="chat__body">
    <div v-for="(chat) in this.$store.state.chatLogs" :key="chat">
      <!-- 나의 메세지 -->
      <div v-if="chat.from_name === this.my_nick" class="chat__mymessage" :class="[isSame ? '' : 'chat__first']">
        <p class="chat__yourmessage__time">{{ chat.time.slice(10, 16) }}</p>
        <p class="chat__mymessage__paragraph">{{ chat.message }}</p>
      </div>

      <div v-else class="chat__yourmessage" :class="[isSame ? '' : 'chat__first']">
        <!-- <div class="chat__yourmessage__avartar">
          프로필 사진 - 추후 수정 필요
          <img :src="avatar" alt="" v-if="!isSame" class="chat__yourmessage__img"/>
        </div> -->

        <div>
          <p class="chat__yourmessage__user" v-if="!isSame">  {{ chat.from_name }} </p>
          <div class="chat__yourmessage__p">
            <p class="chat__yourmessage__paragraph">  {{ chat.message }} </p>
            <p class="chat__yourmessage__time"> {{  chat.time.slice(10, 16) }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isSame: false,
      my_nick: "",
    }
  },
  created() {
    this.my_nick = this.$store.state.member.nick;
  }
}
</script>

<style>
.chat__body {
  height:650px;
  padding: 2rem;
  border-right: 1px solid rgb(235, 235, 235);
  overflow: scroll;
  scroll-behavior: smooth;
}
.chat__body::-webkit-scrollbar {
  display: none;
}
.chat__first {
  margin-top: 2rem;
}
.chat__mymessage {
  min-height: 40px;
  margin: 0;
  line-break: anywhere;
  display: flex;
  justify-content: right;
  align-items: flex-end;
}
.chat__mymessage__paragraph {
  max-width: 180px;
  margin: 0.4rem 0 0 1rem;
  padding: 0.8rem;
  background-color: #7aab85;
  border-radius: 20px 20px 0px 20px;
  color: #ffffff;
  font-size: 14px;
  text-align: right;
}
.chat__yourmessage {
  display: flex;
}
.chat__yourmessage {
  display: flex;
}
.chat__yourmessage__user {
  margin-top: 0;
  margin-block-end: 0rem;
  color: #292929;
  font-size: 14px;
  font-weight: 700;
  text-align: left;
}
.chat__yourmessage__p {
  display: flex;
  align-items: flex-end;
  line-break: anywhere;
}
.chat__yourmessage__paragraph {
  max-width: 180px;
  margin: 0.4rem 1rem 0 0;
  padding: 0.8rem;
  background-color: #f3f3f3;
  border-radius: 0px 20px 20px 20px;
  color: #414141;
  font-size: 14px;
  text-align: left;
}
.chat__yourmessage__time {
  margin: 0;
  color: #9c9c9c;
  font-size: 12px;
}
</style>
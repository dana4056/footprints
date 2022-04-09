<template>
  <div id="app">
    <TodoHeader></TodoHeader>

    <TodoInput v-on:addTodoItem="addOneItem"></TodoInput>
    <!-- v-on: 하위 컴포넌트에서 발생시킨 이벤트 이름="현재 컴포넌트의 메서드 명" -->
    <!-- 하위 컴포넌트에서 this.$emit('addTodoItem', this.newTodoItem); 로 올려줌 -->

    <TodoList v-bind:propsdata="todoItems" v-on:removeItem="removeOneItem" v-on:toggleItem="toggleOneItem"></TodoList>
    <!-- v-bind:내려보낼 프롭스 속성 이름 = "현재 위치의 컴포넌트 데이터 속성" -->
    <!-- 하위 컴포넌트에서 props: ['propsdata']로 받아줌 -->

    <TodoFooter v-on:clearAll="clearAllItem"></TodoFooter>
  </div>
</template>

<script>
import TodoHeader from './components/TodoHeader.vue'
import TodoInput from './components/TodoInput.vue'
import TodoList from './components/TodoList.vue'
import TodoFooter from './components/TodoFooter.vue'


export default {
  data: function() {
    return {
      todoItems: []
    }
  },
  methods: {
    addOneItem: function(todoItem) {
      var obj = {completed: false, item: todoItem};
      localStorage.setItem(this.newTodoItem, JSON.stringify(obj));
      this.todoItems.push(obj);
    },
    removeOneItem: function(todoItem, index) {
      // todoItem.item을 쓰는 이유: key값이랑 같도록 설정했기 때문
      localStorage.removeItem(todoItem.item);
      this.todoItems.splice(index, 1);
    },
    toggleOneItem: function(todoItem, index){
      this.todoItems[index].completed = !this.todoItems[index].completed;
      localStorage.removeItem(todoItem.item);
      localStorage.setItem(todoItem.item, JSON.stringify(todoItem));
    },
    clearAllItem: function() {
      localStorage.clear();
      this.todoItems = [];
    }
  },
  // created: 인스턴스 생성되자마자 실행
  created: function(){
    if(localStorage.length > 0) {
      for (var i =0; i < localStorage.length; i++) {
        if (localStorage.key(i) !== 'loglevel:webpack-dev-server'){
          this.todoItems.push(JSON.parse(localStorage.getItem(localStorage.key(i))));
        }
      }
    }
  },
  components: {
    'TodoHeader': TodoHeader,
    'TodoInput': TodoInput,
    'TodoList': TodoList,
    'TodoFooter': TodoFooter
  }
}
</script>

<style>
  body {
    text-align: center;
    background-color: #F6F6F6;
  }
  input {
    border-style: groove;
    width: 200px;
  }
  button {
    border-style: groove;
  }
  .shadow {
    box-shadow: 5px 10px 10px rgba(0, 0, 0, 0.3);
  }
</style>
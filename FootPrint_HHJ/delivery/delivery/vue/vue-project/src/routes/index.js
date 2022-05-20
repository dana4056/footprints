import { createRouter, createWebHistory } from 'vue-router'

import HomePage from '../views/HomePage.vue'
import ItemListView from '../views/ItemListView.vue'
import LogInView from '../views/LogInView.vue'
import SignUpView from '../views/SignUpView.vue'
import SignUpCompleted from '../views/SignUpCompleted'

const routes = [
    //path: url에 대한 정보
    //component: 해당 url 주소로 갔을 때 표시될 컴포넌트(페이지라고 생각)
    {   path: '/', redirect:'/home'},
    {   path: '/home', component: HomePage  },
    {   path: '/itemlist', component: ItemListView    },
    {   path: '/login', component: LogInView  },
    {   path: '/signup', component: SignUpView  },
    {   path: '/signup-completed', component: SignUpCompleted  }
]

export const router = new createRouter({
    history: createWebHistory(),
    routes
});

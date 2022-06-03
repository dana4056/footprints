import { createRouter, createWebHistory } from 'vue-router'

import HomePage from '../views/HomePage.vue'
import ItemListView from '../views/ItemListView.vue'
import LogInView from '../views/LogInView.vue'
import SignUpView from '../views/SignUpView.vue'
import SignUpCompleted from '../views/SignUpCompleted'
import FindID from '../views/FindID'
import FindPW from '../views/FindPW'
import GetID from '../views/GetID'
import ChangePW from '../views/ChangePW'


const routes = [
    //path: url에 대한 정보
    //component: 해당 url 주소로 갔을 때 표시될 컴포넌트(페이지라고 생각)
    {   path: '/', redirect:'/home'},
    {   path: '/home', component: HomePage  },
    {   path: '/itemlist', component: ItemListView    },
    {   path: '/login', component: LogInView  },
    {   path: '/signup', component: SignUpView  },
    {   path: '/signup-completed', name:'signupCompleted', component: SignUpCompleted  },
    {   path: '/findID', component: FindID  },
    {   path: '/findPW', component: FindPW  },
    {   path: '/getID', component: GetID  },
    {   path: '/changePW', component: ChangePW  },
]

export const router = new createRouter({
    history: createWebHistory(),
    routes
});

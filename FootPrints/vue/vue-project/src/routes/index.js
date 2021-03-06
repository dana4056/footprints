import { createRouter, createWebHistory } from 'vue-router'

import HomePage from '../views/HomePage.vue'
import LogInView from '../views/LogInView.vue'
import SignUpView from '../views/SignUpView.vue'
import SignUpCompleted from '../views/SignUpCompleted'

import FindID from '../views/FindID'
import FindPW from '../views/FindPW' 
import ChangePW from '../views/ChangePW'

import DeliveryList  from '../views/DeliveryListView.vue'
import DeliveryDetail from '../views/DeliveryDetail'
import DeliveryCreate from '../views/DeliveryCreate'
import UserView from '../views/UserView'


const routes = [
    //path: url에 대한 정보
    //component: 해당 url 주소로 갔을 때 표시될 컴포넌트(페이지라고 생각)
    {   path: '/', redirect:'/home'},
    {   path: '/home', component: HomePage  },
    {   path: '/login', component: LogInView  },
    {   path: '/signup', component: SignUpView  },
    {   path: '/signup-completed', name:'signupCompleted', component: SignUpCompleted  },
    {   path: '/findID', component: FindID  },
    {   path: '/findPW', component: FindPW  },
    {   path: '/changePW', component: ChangePW  },
    {   path: '/delivery/post/create', component: DeliveryCreate } ,
    {   path: '/delivery/post', component: DeliveryList    },
    {   path: '/delivery/post/:id', component: DeliveryDetail  },
    {   path: '/user/1', component: UserView  },
]

export const router = new createRouter({
    history: createWebHistory(),
    routes
});

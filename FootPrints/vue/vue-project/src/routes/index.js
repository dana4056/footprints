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
import DeliveryAmend from '../views/DeliveryAmend'

import MyPage from '../views/MyPage'

import NoticeDetail from '../views/NoticeDetail.vue'
import NoticeList from '../views/NoticeView.vue'
import NoticeCreate from '../views/NoticeCreate.vue'

import ChatView from '../views/ChatView'

// import createPersistedState from "vuex-persistedstate";

const routes = [
    //path: url에 대한 정보
    //component: 해당 url 주소로 갔을 때 표시될 컴포넌트(페이지라고 생각)
    {   path: '/', redirect:'/home'},
    {   path: '/home', component: HomePage  },
    {   path: '/login', component: LogInView  },
    {   path: '/signup', component: SignUpView  },
    {   path: '/signup-complete', name:'signupCompleted', component: SignUpCompleted  },
    {   path: '/find-id', component: FindID  },
    {   path: '/find-password', component: FindPW  },
    {   path: '/change-password', component: ChangePW  },
    {   path: '/delivery/post/new-post', component: DeliveryCreate } ,
    {   path: '/delivery/post/:post_id/amend', component: DeliveryAmend } ,
    {   path: '/delivery/post', component: DeliveryList    },
    {   path: '/delivery/post/:id', component: DeliveryDetail  },
    {   path: '/users/:nick', component: MyPage  },
    {   path: '/notice/:id', component: NoticeDetail },
    {   path: '/notice/post', component: NoticeList },
    {   path: '/notice/new-notice', component: NoticeCreate },
    {   path: '/chat/:nick', component: ChatView  },
]

export const router = new createRouter({
    // plugins: [createPersistedState()],
    history: createWebHistory(),
    routes
});
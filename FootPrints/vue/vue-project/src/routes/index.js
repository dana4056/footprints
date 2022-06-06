import { createRouter, createWebHistory } from 'vue-router'

import HomePage from '../views/HomePage.vue'
import LogInView from '../views/LogInView.vue'
import SignUpView from '../views/SignUpView.vue'
import SignUpCompleted from '../views/SignUpCompleted'
import FindID from '../views/FindID'
import FindPW from '../views/FindPW'
import ChangePW from '../views/ChangePW'
// import DeliveryView from '../views/DeliveryListView.vue'
import ItemListView  from '../views/ItemListView.vue'
import WritingPage from '../views/WritingPage'
import ViewDetails from '../views/ViewDetails'


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
    {   path: '/writing', component: WritingPage  },
    {   path: '/viewDetails', component: ViewDetails  },
    {   path: '/itemlist', component: ItemListView    },
        // {   
    //     // 동적 라우팅? 어떻게 적용시킬 것인가 
    //     // 지금은 페이지 이동 전에 데이터를 받아서 페이지에 띄우는 개념
    //     path: '/delivery/',
    //     component: DeliveryView,
    //     beforeEnter: (to, from, next) => {        
    //         console.log(this.$store.state.member.area)
    //         store.dispatch('FETCH_DELIVERY_LIST', this.$store.state.member.area)
    //             .then(() => {
    //                 console.log('fetched');
    //                 next();
    //             })
    //             .catch((error) => {
    //                 console.log(error);
    //             });
    //     }
    // },
    // {
    //     //동적 라우팅을 안 해도 될듯
    //     //정적 페이지를 띄우는게 아니라 어차피 데이터만 바뀌는 개념이라
    //     //그냥 delivery페이지 하나로 
    //     path: '/delivery',
    //     component: DeliveryView,
    // },
    // {
    //     // 여기는 동적 라우팅이 들어가야지 근데 콘솔에 에러 뜸
    //     path: '/delivery/item/:post_name',
    //     component: ViewDetails,
    // }
]

export const router = new createRouter({
    history: createWebHistory(),
    routes
});

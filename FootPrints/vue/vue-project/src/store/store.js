import Vuex from 'vuex'
import actions from './actions'

export const store = new Vuex.Store({
    state:{
        isDuplicateNick:false,
        isDuplicateEmail:false,
        find_ID: "blabla",
        member:{
            nick: "",
            email: "",
            pw: "",
            area: "Seoul"
        },
        sidoList:{},
        sigoonguList:{},
        eupmyeondongList:{},
        sessionId:"",
        // list: [ // 이런 형식으로 들어있음
        // { "id": 31602417, "title": "Ford Is Going to 100% Online, Fixed-Price Sales for EVs", "points": 201, "user": "ddtaylor", "time": 1654215612, "time_ago": "2 hours ago", "comments_count": 223, "type": "link", "url": "https://www.roadandtrack.com/news/a40175990/ford-online-sales-no-negotiation/", "domain": "roadandtrack.com" }
        // ],
        deliveries:[],

    // deliveryList: [ //예시
    //     {
    //         "post_name": "title1",
    //         "post_content": "내용1",
    //         "areaName": "지역명1",
    //         "category": "음식카테고리1",
    //         "take_loc": "수령지역1",
    //         "person_num": "인원수1",
    //         "valid_time": "유효시간1",
    //         "time": "유석이한테물어봐야함 시간1?",
    //         "participate_num": "참여자수1",
    //     },
    //     {
    //         "post_name": "title2",
    //         "post_content": "내용2",
    //         "areaName": "지역명2",
    //         "category": "음식카테고리2",
    //         "take_loc": "수령지역2",
    //         "person_num": "인원수2",
    //         "valid_time": "유효시간2",
    //         "time": "유석이한테물어봐야함 시간?2",
    //         "participate_num": "참여자수2",
    //     },
    // ],
    },
    getters:{
        GET_MEMBER(state){
            return state.member;
        },
        // GET_SESSIONID(state){
        //     return state.sessionId;
        // }
        GET_FINDID(state){
            return state.find_ID;
        },
        GET_FINDPWD(state){
            return state.find_ID;
        },
        GET_DELIVERIES(state){
            return state.deliveries;
        },
    },
    actions,
    mutations:{
        SET_DUPLI_NICK(state, bool){
            state.isDuplicateNick = bool;
        },
        SET_DUPLI_EMAIL(state, bool){
            state.isDuplicateEmail = bool;
        },
        SET_MEMBER(state, member){
            state.member = member;
        },
        SET_SIDO_LIST(state, List){
            state.sidoList = List;
        },
        SET_SIGOONGU_LIST(state, List){
            state.sigoonguList = List;
        },
        SET_EUPMYEONDONG_LIST(state, List){
            state.eupmyeondongList = List;
        },
        // SET_SESSIONID(state, id){
        //     state.sessionId = id;
        // }
        FIND_ID(state, email){
            state.find_ID = email;
            console.log("2. " + state.find_ID);
        },
        SET_DELIVERIES(state, list){
            state.deliveries = list;
        },
    }
});

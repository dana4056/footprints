import Vuex from 'vuex'
import actions from './actions'
import dayjs from 'dayjs'


export const store = new Vuex.Store({
    state:{
        noticeList:[],
        notice:{},
        isDuplicateNick:false,
        isDuplicateEmail:false,
        find_nick: 'CANNOT_FIND_ID',
        find_email: 'CANNOT_FIND_ID',
        pwchange_done: 'FAILED',
        member:{
            nick: "춘식", 
            email: "",
            pw: "",
            area: "서울시 송파구 잠실동"
        },
        sidoList:{},
        sigoonguList:{},
        eupmyeondongList:{},
        sessionId:"",
        deliveryPostList:[],
        deliveryPost:{},
        postIDList: [],
        roomList: [],
        roomIndex: 0,
        userList: ["", ""],
        chatLogs: [
            {from_name:"", time:"", message:""},
        ],
    },
    getters:{
        GET_MEMBER(state){
            return state.member;
        },
        GET_FIND_MEMBER_NICK(state){
            return state.find_nick;
        },
        GET_FIND_MEMBER_EMAIL(state){
            return state.find_email;
        },
        GET_DELIVERIES(state){
            return state.deliveryPostList;
        },
        GET_DELIVERY_POST(state){
            console.log("디테일\n",state.deliveryPost)
            return state.deliveryPost;
        },
        GET_PWCHANGE_DONE(state){
            return state.pwchange_done;
        },
        GET_ROOMINDEX(state) {
            return state.roomIndex;
        }
    },
    actions,
    mutations:{
        SET_NOTICELIST(state, List){
            state.noticeList = List;
        },
        SET_NOTICE(state, notice){
            state.notice = notice;
        },
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
        SET_FIND_MEMBER_NICK(state, nick){
            state.find_nick = nick;
        },
        SET_FIND_MEMBER_EMAIL(state, email){
            state.find_email = email;
        },
        SET_PWCHANGE_DONE(state, string){
            state.pwchange_done = string;
        },
        SET_DELIVERIES(state, list){
            state.deliveryPostList = list;
            let idx = 0;
            for(let value of list){
                const Cdate = dayjs(value.createdDate);
                const Vdate = dayjs(value.valid_time+":00");

                state.deliveryPostList[idx].createdDate = Cdate;
                state.deliveryPostList[idx++].valid_time = Vdate;
            }
        },
        SET_DELIVERY_POST(state, post){
            const Cdate = dayjs(post.createdDate);
            const Vdate = dayjs(post.valid_time);

            state.deliveryPost = post;
            state.deliveryPost.createdDate = Cdate;
            state.deliveryPost.valid_time = Vdate;
        },
        SET_FIND_POSTID(state, list) {
            state.postIdList = list;
        },
        SET_FIND_ROOM(state, list) {
            state.roomList = list;
            console.log(state.roomList);
        },
        SET_FIND_USER(state, list) {
            state.userList = list;
        },
        SET_FIND_CHAT_LOGS(state, list) {
            state.chatLogs = list;
        }
    },
    
});

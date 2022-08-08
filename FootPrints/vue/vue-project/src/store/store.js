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
        memberChange_done: 'FAILED',
        member:{
            // nick: "", email: "", pw: "", area: ""
            nick: "지원",  email: "ccap122@skuniv.ac.kr", pw: "jw123123!", area: "서울시 송파구 잠실동"
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
        userList: [],
        chatLogs: [],
        myDPostList:[
            { post_id: 1, category: 'KOR', created_date: '2022-07-27 02:30:46', max_person_num: 5, participant_num: 1, post_content: '안농',
            post_name: '비빔밥 같이 먹을 사람~!', take_loc: '서울시 송파구 잠실동', valid_time: '2022-08-06T11:35', view_num: 0, member_nick: '지원'},
            { post_id: 2, category: 'JAP', created_date: '2022-07-27 02:30:46', max_person_num: 5, participant_num: 1, post_content: '안농',
            post_name: '초밥 같이 ㄱㄱ', take_loc: '서울시 송파구 잠실동', valid_time: '2022-08-06T11:35', view_num: 0, member_nick: '지원'},  
        ],
        myParticiList: [
            { post_id: 1, category: 'KOR', created_date: '2022-07-27 02:30:46', max_person_num: 5,  participant_num: 1, post_content: '안농',
            post_name: '비빔밥 같이 먹을 사람~!', take_loc: '서울시 송파구 잠실동', valid_time: '2022-08-21T11:35', view_num: 0, member_nick: '지원'},
            { post_id: 2, category: 'JAP', created_date: '2022-07-27 02:30:46', max_person_num: 5, participant_num: 2, post_content: '바이바이',
            post_name: '초밥 같이 ㄱㄱ', take_loc: '서울시 송파구 잠실동', valid_time: '2022-08-22T11:35', view_num: 0, member_nick: '지원'},
            { post_id: 3, category: 'CHI', created_date: '2022-07-27 02:30:46', max_person_num: 4, participant_num: 2, post_content: '뿡',
            post_name: '짜장면엔 탕수육', take_loc: '서울시 송파구 잠실동', valid_time: '2022-08-23T11:35', view_num: 0, member_nick: '춘식'},
            { post_id: 4, category: 'ETC', created_date: '2022-07-27 02:30:46', max_person_num: 3, participant_num: 3, post_content: '기타~~',
            post_name: '돼라..', take_loc: '서울시 송파구 잠실동', valid_time: '2022-08-14T11:35', view_num: 0, member_nick: '춘식'},       
            { post_id: 5, category: 'ETC', created_date: '2022-07-27 02:30:46', max_person_num: 2, participant_num: 1, post_content: '안농',
            post_name: 'ㅇㅇㅇㅇㅇ', take_loc: '서울시 송파구 잠실동', valid_time: '2022-08-06T11:35', view_num: 0, member_nick: '춘식'},    
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
        },
        GET_MEMBER_CHANGE_DONE(state){
            return state.memberChange_done;
        },
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
            console.log(state.member);
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
        },
        SET_MEMBER_CHANGE_DONE(state, string) {
            state.memberChange_done = string;
        }
    },
    
});

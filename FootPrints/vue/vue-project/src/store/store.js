import Vuex from 'vuex'
import actions from './actions'
import dayjs from 'dayjs'
import createPersistedState from "vuex-persistedstate";

export const store = new Vuex.Store({
    plugins: [createPersistedState()],
    state:{
        noticeList:[
            // {id: "1", author: '운영자', content: "내용1", post_time: "2022-07-25 13:05:32", title: "제목1", view_num: 3},
            // {id: "2", author: '운영자', content: "내용2", post_time: "2022-08-02 17:04:13", title: "제목2", view_num: 0},
            // {id: "3", author: '운영자', content: "내용3", post_time: "2022-08-15", title: "제목3", view_num: 2},
            // {id: "4", author: '운영자', content: "내용4", post_time: "2022-08-30", title: "제목4", view_num: 3},
            // {id: "5", author: '운영자', content: "내용1", post_time: "2022-07-25", title: "제목1", view_num: 3},
            // {id: "6", author: '운영자', content: "내용2", post_time: "2022-08-02", title: "제목2", view_num: 0},
            // {id: "7", author: '운영자', content: "내용3", post_time: "2022-08-15", title: "제목3", view_num: 2},
            // {id: "8", author: '운영자', content: "내용4", post_time: "2022-08-30", title: "제목4", view_num: 3},
            // {id: "9", author: '운영자', content: "내용1", post_time: "2022-07-25", title: "제목1", view_num: 3},
            // {id: "10", author: '운영자', content: "내용2", post_time: "2022-08-02", title: "제목2", view_num: 0},
            // {id: "11", author: '운영자', content: "내용3", post_time: "2022-08-15", title: "제목3", view_num: 2},
            // {id: "12", author: '운영자', content: "내용4", post_time: "2022-08-30", title: "제목4", view_num: 3},
        ],
        notice:{id: "", author: '', post_time: "", title: "", view_num: "", content: ""},
        isDuplicateNick:false,
        isDuplicateEmail:false,
        find_nick: 'CANNOT_FIND_ID',
        find_email: 'CANNOT_FIND_ID',
        pwchange_done: 'FAILED',
        memberChange_done: 'FAILED',
        member:{
            nick: "", 
            email: "",
            pw: "",
            area: ""
        },
        sidoList:{},
        sigoonguList:{},
        eupmyeondongList:{},
        sessionId:"",
        deliveryPostList:[],
        deliveryPost_presentArea: "로그인 시 지역 설정 됨",
        deliveryPost:{},
        postIdList: [0],
        roomList: [{post_id: 0, post_name: " "}],
        delivery_category: "",
        delivery_category_sort: "",
        delivery_category_area: "",
        roomIndex: 0,
        userList: [" "],
        chatLogs: [],
        myDPostList:[],
        myParticiList: [],
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
        // GET_DELIVERY_PRESENT_AREA(state){
        //     return state.deliveryPost_presentArea;
        // },
        GET_DELIVERY_POST(state){
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
        SET_MEMBER(state, loginMember){
            state.member.nick = loginMember.nick;
            state.member.area = loginMember.area;
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
        SET_DELIVERY_AREA(state, area){
            state.deliveryPost_presentArea = area;
        },
        SET_DELIVERIES(state, list){
            state.deliveryPostList = list;
            // console.log(list[0].area_Name);
            // state.deliveryPost_presentArea = list[0].area_Name;
            let idx = 0;
            for(let value of list){
                const Cdate = dayjs(value.createdDate);
                const Vdate = dayjs(value.valid_time);

                state.deliveryPostList[idx].createdDate = Cdate;
                state.deliveryPostList[idx].valid_time = Vdate;
                idx += 1;
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
        SET_MY_DPOST(state, list) {
            state.myDPostList = list;
        },
        SET_MY_PARTICI(state, list) {
            state.myParticiList = list;
        },
        SET_MEMBER_CHANGE_DONE(state, string) {
            state.memberChange_done = string;
        }
    },
    
});

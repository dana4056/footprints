import Vuex from 'vuex'
import actions from './actions'
import dayjs from 'dayjs'
import { findID } from "../api/index.js"


export const store = new Vuex.Store({
    state:{
        isDuplicateNick:false,
        isDuplicateEmail:false,
        // find_ID: "CANNOT_FIND_ID",
        find_member:{
            nick:"CANNOT_FIND_ID",
            email:""
        },
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
        deliveryPost:{}
    },
    getters:{
        GET_MEMBER(state){
            return state.member;
        },
        GET_FIND_MEMBER(state){
            return state.find_member;
        },
        GET_DELIVERIES(state){
            return state.deliveryPostList;
        },
        GET_DELIVERY_POST(state){
            return state.deliveryPost;
        }
    },
    actions,
    mutations:{
        //아이디 찾기
        FIND_ID(state, email) {
            findID(email)
                .then(response => {
                    console.log(response);
                    const findMember = {
                        nick: response.data,
                        email: email
                    }
                    state.find_member = findMember;
                    // commit('SET_FIND_MEMBER', findMember);
                })
                .catch(error => {
                    console.log(error);
                })
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
        SET_FIND_MEMBER(state, findMember){
            state.find_member = findMember;
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
        }
    }
});

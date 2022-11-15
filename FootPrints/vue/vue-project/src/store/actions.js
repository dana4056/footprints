//Token
import { fetchToken , fetchAutority} from "../api/index.js";
//Member
import { postSignup, patchChangePwd, getUserArea, postLogin, getFindId, getFindPwd, getCheckNick, getCheckEmail, } from "../api/index.js";
//Notice
import { getNoticeList, postNotice, patchNotice, deleteNotice, getNoticeDetail } from "../api/index.js";
//Users
import { getMyPost, getAttendPost, patchUserInfo } from "../api/index.js";
//Delivery
import { getDeliveryList, getSortDeliveryList, postDeliveryPost, patchDeliveryPost, deleteDeliveryPost, getDeliveryPostDetail } from "../api/index.js";
//Chat
import { getPostIdList, getPostInfoList, getNickList, getChatList, postChatData, deleteChatData  } from "../api/index.js";
//RoomInfo
import { postRoomInfo, patchRoomInfo, deleteRoomInfo } from "../api/index.js";

import { router } from '../routes/index.js';
import { store } from "./store.js";
import Swal from 'sweetalert2';

export default{ 

  ////////////////////////// TOKEN //////////////////////////

  FETCH_USER({commit}){
    if(localStorage.getItem('jwt') == null){
      alert("로그인 후 이용하세요");
      router.replace("/home");
      return;
    }else{
      return fetchToken()
      .then(response =>{
        const member = {
          nick: response.data.nick,
          email: response.data.email,
          pw: "",
          area: response.data.area
        }
        commit('SET_MEMBER',member)
      })
      .catch(error => {
        const code = error.response.status;
        if(code == 403){
          alert("권한없음: 다시 로그인 해주세요.");
          router.replace("/home");
        }
      })
    }
  },


  FETCH_AUTHORITY({commit}){
    fetchAutority()
      .then(response =>{
        commit('SET_AUTHORITY', response.data);
      })
      .catch(function() {

      });
  },

  ////////////////////////// MEMBER //////////////////////////

  // 회원가입
  POST_MEMBER(context, member) {
    return postSignup(member)
      .then(response => {
        if (response.data == "SUCCESS") {
          router.replace({
            name: "signupCompleted",
            query: { nickName: member.nick, }
          });
        }
        else {
          alert("회원가입 실패");
        }
      })
      .catch(function() {

      })
  },

  // 비밀번호 변경
  CHANGE_PWD({ commit }, memberDTO) {
    return patchChangePwd(memberDTO)
      .then(response => {
        commit('SET_PWCHANGE_DONE', response.data);
      })
      .catch(error => {
        const code = error.response.status;

        if (code == 409) {
          alert("이전 비밀번호와 다른 비밀번호로 설정해주세요.");
        }else{
          alert("비밀번호 변경에 실패하였습니다.\n다시 시도해주세요.");
        }
        router.replace("/change-password");
      })
  },

  // 로그인
  POST_LOGIN({ commit }, loginMember) {
    return postLogin(loginMember)
      .then(response => {

        if(response.status == 200){
          console.log("로그인 성공");

          localStorage.setItem('jwt', response.data); // 로컬 스토리지에 저장

          return getUserArea(loginMember.nick)
          .then(response => {
            const member = {
              nick: loginMember.nick,
              email: response.data.email,
              pw: "",
              area: response.data.area
            }

            commit('SET_MEMBER', member);
            commit('SET_DELIVERY_AREA', member.area);
            router.replace("/home");
            store.dispatch("FETCH_AUTHORITY");
          })
        }else if(response.status == 204){
          console.log("로그인 실패");
          alert("아이디나 비밀번호가 틀립니다");
        }
      })
  },
        

  // 아이디 찾기
  FIND_NICK({ commit }, email) {
    return getFindId(email)
      .then(response => {
        if(response.status == 200){
          commit('SET_FIND_MEMBER_NICK', response.data)  
        }
      })
      .catch(function() {

      })
  },

  // 비밀번호 찾기
  FIND_PWD({ commit }, email) {
    return getFindPwd(email)
      .then(response => {
        if(response.status == 200){
          commit('SET_FIND_MEMBER_EMAIL', response.data);
        }
      })
      .catch(function() {

      })
  },

  // 닉네임 중복체크
  POST_NICK({ commit }, nick) {
    return getCheckNick(nick)
      .then(function() {
        commit('SET_DUPLI_NICK', false);
      })
      .catch(function() {
        commit('SET_DUPLI_NICK', true);
      })
  },

  // 이메일 중복체크
  POST_EMAIL({ commit }, email) {
    return getCheckEmail(email)
      .then(function() {
        commit('SET_DUPLI_EMAIL', false)
      })
      .catch(function() {
        commit('SET_DUPLI_EMAIL', true);
      })
  },

  ////////////////////////// NOTICE //////////////////////////

  // 공지사항 리스트뷰 페이지 데이터 로드
  // axios.get 내부에 new Promise가 들어있으므로 then, catch가 사용 가능한 것.
  // 프로미스이므로 async/await 방식으로 변경 가능.
  FETCH_NOTICE_LIST({ commit }) {
    return getNoticeList()
      .then(response => {
        commit('SET_NOTICELIST', response.data);
      })
      .catch(function() {

      })
  },

  // 공지사항 작성
  POST_NOTICE(content, noticeDTO) {
    return postNotice(noticeDTO)
      .then(function() {
        Swal.fire({
          icon: 'success',
          title: '공지사항이 등록되었습니다.',
          confirmButtonText: '공지사항으로 가기',
        }).then(() => {
          router.replace("/notice/post");
        })
      })
      .catch(function() {

      })
  },

  // 공지사항 수정
  AMEND_NOTICE(content, noticeDTO){
    return patchNotice(noticeDTO)
  },

  DELETE_NOTICE(content, id) {
    return deleteNotice(id)
  },

  // 공지사항 상세
  FETCH_NOTICE_DETAIL({ commit }, notice_id) {
    return getNoticeDetail(notice_id)
      .then(response => {
        commit('SET_NOTICE', response.data);
      })
      .catch(function() {

      })
  },

  ////////////////////////// USERS //////////////////////////

  // 내가 쓴 글
  FETCH_MY_DPOST({ commit }, nick) {
    return getMyPost(nick)
      .then(response => {
        commit('SET_MY_DPOST', response.data);
      })
      .catch(function() {
        commit('SET_MY_DPOST', []);
      })
  },

  // 내가 참여한 글
  FETCH_MY_PARTICI({ commit }, nick) {
    return getAttendPost(nick)
      .then(response => {
        commit('SET_MY_PARTICI', response.data);
      })
      .catch(function() {
        commit('SET_MY_PARTICI', []);
      })
  },

  // 회원 정보 수정
  CHANGE_MEMBER({ commit }, memberDTO) {
    return patchUserInfo(memberDTO)
      .then(response => {
        commit('SET_MEMBER_CHANGE_DONE', response.data);
      })
      .catch(function() {

      })
  },

  ////////////////////////// DELIVERY //////////////////////////

  // 리스트뷰 페이지 데이터 로드
  FETCH_DELIVERY_LIST({ commit }, area) {
    return getDeliveryList(area)
      .then(response => {
        commit('SET_DELIVERIES', response.data);
        if (response == null) {
          alert("null권한 없음 로그인 후 이용하세요");
          router.replace("/home");
        }
      })
      .catch(error => {
        const code = error.response.status;
        // 권한 없이 들어올 수 있도록 코드 변경 되어 코드 수정 필요
        if (code == 405) {
          alert("권한 없음 로그인 후 이용하세요");
          router.replace("/home");
        }
      })
  },
  // 카테고리별 리스트뷰 페이지 데이터 로드
  FETCH_DELIVERY_LIST_SORT({ commit }, sortDTO) {
    return getSortDeliveryList(sortDTO)
      .then(response => {
        commit('SET_DELIVERIES', response.data);
      })
      .catch(error => {
        const code = error.response.status;
        if (code == 400){
          commit('SET_DELIVERIES', []);
        }
        if (code == 403) {
          alert("FETCH_DELIVERY_LIST_CATEGORY 로그인 후 이용하세요");
          router.replace("/home");
        }
      })
  },
  // 게시물 작성
  POST_DELIVERY_POST(content, post) {
    return postDeliveryPost(post)
      .then(function() {
        store.dispatch("FIND_POST_ID", store.state.member.nick);

        Swal.fire({
          icon: 'success',
          title: '글이 등록되었습니다.',
          confirmButtonText: '배달 모집 목록 보러가기',
        }).then(() => {
          router.replace("/delivery/post");
        });
      })
      .catch(error => {
        const code = error.response.status;
        if (code == 403) {
          alert("POST_DELIVERY_POST 로그인 후 이용하세요");
          router.replace("/home");
        }
      })
  },

  // 글 수정
  AMEND_DELIVERY_POST(content, post) {
    return patchDeliveryPost(post)
      .catch(error => {
        const code = error.response.status;
        if (code == 403) {
          alert("POST_DELIVERY_POST 로그인 후 이용하세요");
          router.replace("/home");
        }
      })
  },

  // 글 삭제: 채팅 로그 삭제 -> 방 정보 삭제 -> 글 삭제 순으로 이루어져야할 것 같아서 아래와 같이 작성
  DELETE_DELIVERY_POST(content, post_id) {
    return deleteChatData(post_id)
      .then(function() {
        return deleteRoomInfo(post_id)
          .then(function() {
            return deleteDeliveryPost(post_id)
              .then(function() {
                store.dispatch('FIND_POST_ID', store.state.member.nick);
              })
              .catch(function() {

              })
          })
          .catch(function() {

          })
      })
      .catch(function() {

      })
  },

  // 상세 페이지 데이터 로드
  FETCH_DELIVERY_DETAIL({ commit }, post_id) {
    return getDeliveryPostDetail(post_id)
      .then(response => {
        commit('SET_DELIVERY_POST', response.data);
        console.log("상세페이지 데이터 로드 끝..?");
        commit('SET_ISLOADING', false);
      })
      .catch(function() {

      })
  },

  ////////////////////////// CHAT //////////////////////////

  FIND_POST_ID({ commit }, nick) {
    return getPostIdList(nick)
      .then(response => {
        commit('SET_FIND_POSTID', response.data);

        if (Object.keys(response.data).length != 0) {
          store.dispatch('FIND_ROOM', response.data);
        }
        else {
          store.state.postIdList = [0];
          store.state.roomList = [{ post_id: 0, post_name: " " }];
        }
      })
  },

  FIND_ROOM({ commit }, list) {
    return getPostInfoList(list)
      .then(response => {
        commit('SET_FIND_ROOM', response.data);
      })
      .catch(function() {

      })
  },

  FIND_USER({ commit }, post_id) {
    return getNickList(post_id)
      .then(response => {
        commit('SET_FIND_USER', response.data);
      })
      .catch(function() {

      })
  },

  FIND_CHAT_LOGS({ commit }, post_id) {
    return getChatList(post_id)
      .then(response => {
        commit('SET_FIND_CHAT_LOGS', response.data);
      })
      .catch(function() {

      })
  },

  POST_CHAT_DATA(c, chatData) {
    return postChatData(chatData)
      .then(function() {
        store.dispatch('FIND_CHAT_LOGS', chatData.post_id)
      })
      .catch(function() {

      })
  },

  ////////////////////////// ROOMINFO //////////////////////////

  // 참여하기
  JOIN_DELIVERY_POST(content, roomInfo) {
    return postRoomInfo(roomInfo)
      .then(function() {
        store.dispatch("FIND_POST_ID", store.state.member.nick);

        Swal.fire({
          icon: 'success',
          title: '참여 완료!',
          confirmButtonText: '배달 모집 목록 보러가기',
        }).then(() => {
          router.replace("/delivery/post");
        })
      })
      .catch(function() {

      })
  },

  // 참여 취소하기
  EXIT_DELIVERY_POST(content, roomInfo) {
    return patchRoomInfo(roomInfo)
      .then(function() {
        store.dispatch("FIND_POST_ID", store.state.member.nick);
      })
      .catch(function() {

      })
  },
}
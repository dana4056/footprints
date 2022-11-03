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
    console.log("토큰");
    console.log(localStorage.getItem('jwt'));
    if(localStorage.getItem('jwt') == null){
      console.log("API:FETCH_USER\n멤버 가져오기 실패(토큰 없음, 로그인 필요)");
      alert("로그인 후 이용하세요");
      router.replace("/home");
      return;
    }else{
      return fetchToken()
      .then(response =>{
        console.log("API:FETCH_USER\n멤버 가져오기 성공",response.data);
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
          console.log("API:FETCH_USER\n멤버 가져오기 실패(로그인 필요)",error);
          alert("권한없음: 다시 로그인 해주세요.");
          router.replace("/home");
        }
        else{
          console.log("API:FETCH_USER\n멤버 가져오기 실패(??)",error);
        }
      })
    }
  },


  FETCH_AUTHORITY({commit}){
    fetchAutority()
      .then(response =>{
        console.log("API:FETCH_AUTHORITY\n권한 가져오기 성공",response.data);
        commit('SET_AUTHORITY', response.data);
      })
      .catch(error => {
        const code = error.response.status;
        console.log("API:FETCH_AUTHORITY\n권한 가져오기 실패",error);
        if(code == 403){
          console.log("API:FETCH_AUTHORITY\n권한 가져오기 실패(로그인 필요)",error);
          // alert("FETCH_USER 로그인 후 이용하세요");
          // router.replace("/home");
        }
        else{
          console.log("API:FETCH_AUTHORITY\n권한 가져오기 실패(??)",error);
          // console.log("페이지 최초 방문할 때 가끔 500오류 발생?");
        }
      });
  },

  ////////////////////////// MEMBER //////////////////////////

  // 회원가입
  POST_MEMBER(context, member) {
    return postSignup(member)
      .then(response => {
        if (response.data == "SUCCESS") {
          console.log('API:POST_MEMBER\n회원가입 성공', response.data);
          router.replace({
            name: "signupCompleted", 
            query: { nickName: member.nick, }
          });
        }
        else {
          console.log('API:POST_MEMBER\n회원가입 실패', response.data);
          alert("회원가입 실패");
        }
      })
      .catch(error => { console.log('API:POST_MEMBER\n회원가입 실패', error); })
  },

  // 비밀번호 변경
  CHANGE_PWD({ commit }, memberDTO) {
    return patchChangePwd(memberDTO)
      .then(response => {
        console.log('API:CHANGE_PWD\n비밀번호 변경 성공', response);
        commit('SET_PWCHANGE_DONE', response.data);
      })
      .catch(error => {
        console.log('API:CHANGE_PWD\n비밀번호 변경 실패', error);
        alert("비밀번호 변경에 실패하였습니다.\n다시 시도해주세요.");
        router.replace("/change-password");
      })
  },

  // 로그인
  POST_LOGIN({ commit }, loginMember) {
    return postLogin(loginMember)
      .then(response => {
        console.log('API:POST_LOGIN\n로그인 성공', response);

        localStorage.setItem('jwt', response.data); // 로컬 스토리지에 저장

        return getUserArea(loginMember.nick)
          .then(response => {
            console.log("지역 얻기 성공 !", response.data);
            const member = {
              nick: loginMember.nick,
              email: response.data.email,
              pw: "",
              area: response.data.area
            }
            commit('SET_MEMBER', member);
            commit('SET_DELIVERY_AREA', member.area);
            store.dispatch("FETCH_AUTHORITY");
            store.dispatch('FIND_POST_ID', loginMember.nick);
            router.replace("/home");
          })
          .catch(error => {
            console.log("지역 읽기 실패", error);
            router.replace("/home");
          })
        })
        .catch(error => {
          const code = error.response.status;
          if (code == 400) {
            console.log("API:POST_LOGIN\n로그인 실패 - 존재하지 않는 닉네임", error);
            alert("API:POST_LOGIN\n로그인 실패 - 존재하지 않는 닉네임입니다.");
          }
          else if (code == 404) {
            console.log("API:POST_LOGIN\n로그인 실패 - 비밀번호 불일치", error);
            alert("비밀번호가 일치하지 않습니다.");
          }
          else { console.log("API:POST_LOGIN\n로그인 실패", error) }
        })
  },

  // FETCH_AREA({ commit }, nick) {
  //   return getUserArea(nick)
  //   .then(response => {
  //     console.log("지역 얻기 성공 !", response.data);
  //     const member = {
  //       nick: nick,
  //       email: response.data.email,
  //       pw: "",
  //       area: response.data.area
  //     }
  //     commit('SET_MEMBER', member);
  //     commit('SET_DELIVERY_AREA', member.area);
  //     router.replace("/home");
  //     store.dispatch("FETCH_AUTHORITY");
  //     store.dispatch('FIND_POST_ID', nick);
  //   })
  //   .catch(error => {
  //     router.replace("/home");
  //     console.log("지역 읽기 실패", error);
  //   })
  // },

  // 아이디 찾기
  FIND_NICK({ commit }, email) {
    return getFindId(email)
      .then(response => {
        console.log('API:FIND_NICK\n닉네임찾기 성공', response);
        commit('SET_FIND_MEMBER_NICK', response.data)
      })
      .catch(error => { console.log('API:FIND_NICK\n닉네임찾기 실패', error); })
  },

  // 비밀번호 찾기
  FIND_PWD({ commit }, email) {
    return getFindPwd(email)
      .then(response => {
        console.log('API:FIND_PWD\n비밀번호 찾기 성공', response);
        commit('SET_FIND_MEMBER_EMAIL', response.data)
      })
      .catch(error => {
        console.log('API:FIND_PWD\n비밀번호 찾기 실패', error);
      })
  },

  // 닉네임 중복체크
  POST_NICK({ commit }, nick) {
    return getCheckNick(nick)
      .then(response => {
        console.log('API:POST_NICK\n사용가능 닉네임', response);
        commit('SET_DUPLI_NICK', false);
      })
      .catch(error => {
        console.log('API:POST_NICK\n사용불가 닉네임(중복)', error);
        commit('SET_DUPLI_NICK', true)
      })
  },

  // 이메일 중복체크
  POST_EMAIL({ commit }, email) {
    return getCheckEmail(email)
      .then(response => {
        console.log('API:POST_EMAIL\n사용가능 이메일', response);
        commit('SET_DUPLI_EMAIL', false)
      })
      .catch(error => {
        console.log('API:POST_EMAIL\n사용불가 이메일', error);
        commit('SET_DUPLI_EMAIL', true)
      })
  },

  ////////////////////////// NOTICE //////////////////////////

  // 공지사항 리스트뷰 페이지 데이터 로드
  // axios.get 내부에 new Promise가 들어있으므로 then, catch가 사용 가능한 것.
  // 프로미스이므로 async/await 방식으로 변경 가능.
  FETCH_NOTICE_LIST({ commit }) {
    return getNoticeList()
      .then(response => {
        console.log("API:FETCH_NOTICE_LIST\n공지사항 정보 받아오기 성공", response.data);
        commit('SET_NOTICELIST', response.data);
      })
      .catch(error => {
        console.log("API:FETCH_NOTICE_LIST\n공지사항 리스트 뷰 페이지 정보 받아오기 실패", error);
      })
  },

  // 공지사항 작성
  POST_NOTICE(content, noticeDTO) {
    return postNotice(noticeDTO)
      .then(response => {
        console.log("API:POST_NOTICE\n공지사항 등록 성공", response);
        Swal.fire({
          icon: 'success',
          title: '공지사항이 등록되었습니다.',
          confirmButtonText: '공지사항으로 가기',
        }).then(() => {
          router.replace("/notice/post");
        })
      })
      .catch(error => {
        console.log("API:POST_NOTICE\n공지사항 등록 실패", error);
      })
  },

  // 공지사항 수정
  AMEND_NOTICE(content, noticeDTO){
    return patchNotice(noticeDTO)
      .then(response => {
        console.log("API:AMEND_NOTICE\n공지사항 수정 성공", response);
      })
      .catch(error => {
        console.log("API:POST_NOTICE\n공지사항 수정 실패", error);
      })
  },

  DELETE_NOTICE(content, id) {
    return deleteNotice(id)
      .then(response => {
        console.log('API:DELETE_NOTICE\n공지사항 삭제 성공', response);
      })
      .catch(error => {
        console.log('API:DELETE_NOTICE\n공지사항 삭제 실패', error);
      })
  },

  // 공지사항 상세
  FETCH_NOTICE_DETAIL({ commit }, notice_id) {
    return getNoticeDetail(notice_id)
      .then(response => {
        console.log("API:FETCH_NOTICE_DETAIL\n공지사항 상세정보 받아오기 성공", response.data);
        commit('SET_NOTICE', response.data);
      })
      .catch(error => {
        console.log("API:FETCH_NOTICE_DETAIL\n공지사항 상세정보 페이지 정보 받아오기 실패", error);
      })
  },

  ////////////////////////// USERS //////////////////////////

  // 내가 쓴 글
  FETCH_MY_DPOST({ commit }, nick) {
    return getMyPost(nick)
      .then(response => {
        console.log('API:FETCH_MY_DPOST\n작성한 글 받아오기 성공', response);
        commit('SET_MY_DPOST', response.data);
      })
      .catch(error => {
        console.log('API:FETCH_MY_DPOST\n작성한 글 받아오기 실패', error);
        commit('SET_MY_DPOST', []);
      })
  },

  // 내가 참여한 글
  FETCH_MY_PARTICI({ commit }, nick) {
    return getAttendPost(nick)
      .then(response => {
        console.log('API:FETCH_MY_PARTICI\n참여한 글 받아오기 성공', response);
        commit('SET_MY_PARTICI', response.data);
      })
      .catch(error => {
        console.log('API:FETCH_MY_PARTICI\n참여한 글 받아오기 실패', error);
        commit('SET_MY_PARTICI', []);
      })
  },

  // 회원 정보 수정
  CHANGE_MEMBER({ commit }, memberDTO) {
    return patchUserInfo(memberDTO)
      .then(response => {
        console.log('API:CHANGE_MEMBER\n회원정보 변경 성공', response);
        commit('SET_MEMBER_CHANGE_DONE', response.data);
      })
      .catch(error => {
        console.log('API:CHANGE_MEMBER\n회원정보 변경 실패', error);
      })
  },

  ////////////////////////// DELIVERY //////////////////////////

  // 리스트뷰 페이지 데이터 로드
  FETCH_DELIVERY_LIST({ commit }, area) {
    return getDeliveryList(area)
      .then(response => {
        console.log("API:FETCH_DELIVERY_LIST\n배달 리스트 뷰 페이지 정보 받아오기 성공", response.data);
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
        else {
          console.log("API:FETCH_DELIVERY_LIST\n배달 리스트 뷰 페이지 정보 받아오기 실패", error);
        }
      })
  },
  // 카테고리별 리스트뷰 페이지 데이터 로드
  FETCH_DELIVERY_LIST_SORT({ commit }, sortDTO) {
    return getSortDeliveryList(sortDTO)
      .then(response => {
        console.log("API:FETCH_DELIVERY_LIST_CATEGORY\n정렬(카테고리) 배달 리스트 뷰 페이지 정보 받아오기 성공\n", response.data);
        commit('SET_DELIVERIES', response.data);
      })
      .catch(error => {
        const code = error.response.status;
        if (code == 403) {
          alert("FETCH_DELIVERY_LIST_CATEGORY 로그인 후 이용하세요");
          router.replace("/home");
        }
        else {
          console.log("API:FETCH_DELIVERY_LIST_CATEGORY\n카테고리별 배달 리스트 뷰 페이지 정보 받아오기 실패\n", error);
        }
      })
  },
  // 게시물 작성
  POST_DELIVERY_POST(content, post) {
    return postDeliveryPost(post)
      .then(response => {
        console.log("API:POST_DELIVERY_POST\n게시물 등록 성공", response);
        console.log(response.data);
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
        else {
          console.log("API:POST_DELIVERY_POST\n게시물 등록 실패", error);
        }
      })
  },

  // 글 수정
  AMEND_DELIVERY_POST(content, post) {
    return patchDeliveryPost(post)
      .then(response => {
        console.log("API:AMEND_DELIVERY_POST\n게시물 수정 성공", response);
      })
      .catch(error => {
        const code = error.response.status;
        if (code == 403) {
          alert("POST_DELIVERY_POST 로그인 후 이용하세요");
          router.replace("/home");
        }
        else {
          console.log("API:POST_DELIVERY_POST\n게시물 수정 실패", error);
        }
      })
  },

  // 글 삭제: 채팅 로그 삭제 -> 방 정보 삭제 -> 글 삭제 순으로 이루어져야할 것 같아서 아래와 같이 작성
  DELETE_DELIVERY_POST(content, post_id) {
    return deleteChatData(post_id)
      .then(response => {
        console.log('API:DELETE_DELIVERY_POST\n채팅 로그 삭제 성공', response);

        return deleteRoomInfo(post_id)
          .then(response => {
            console.log('API:DELETE_DELIVERY_POST\n방 정보 삭제 성공', response);

            return deleteDeliveryPost(post_id)
              .then(response => {
                console.log('API:DELETE_DELIVERY_POST\n글 삭제 성공', response);
                store.dispatch('FIND_POST_ID', store.state.member.nick);
              })
              .catch(error => {
                console.log('API:DELETE_DELIVERY_POST\n글 삭제 실패', error);
              })
          })
          .catch(error => {
            console.log('API:DELETE_DELIVERY_POST\n방 정보 삭제 실패', error);
          })
      })
      .catch(error => {
        console.log('API:DELETE_DELIVERY_POST\n채팅 로그 삭제 실패', error);
      })
  },

  // 상세 페이지 데이터 로드
  FETCH_DELIVERY_DETAIL({ commit }, post_id) {
    return getDeliveryPostDetail(post_id)
      .then(response => {
        console.log("API:FETCH_DELIVERY_DETAIL\n상세페이지 정보 받아오기 성공", response.data);
        commit('SET_DELIVERY_POST', response.data);
        commit('SET_ISLOADING', false);
      })
      .catch(error => {
        const code = error.response.status;
        if (code == 403) {
          console.log("API:FETCH_DELIVERY_DETAIL\n상세페이지 정보 받아오기 실패 - 로그인 필요", error);
          alert("FETCH_DELIVERY_DETAIL 로그인 후 이용하세요");
          router.replace("/home");
        }
        else {
          router.replace("/forbidden");
          console.log("API:FETCH_DELIVERY_DETAIL\n상세페이지 정보 받아오기 실패", error);
        }
      })
  },

  ////////////////////////// CHAT //////////////////////////

  FIND_POST_ID({ commit }, nick) {
    return getPostIdList(nick)
      .then(response => {
        console.log("API:SET_FIND_POSTID 사용자의 POST_ID 리스트 받아오기 성공", response.data);
        commit('SET_FIND_POSTID', response.data);

        if (Object.keys(response.data).length != 0) {
          store.dispatch('FIND_ROOM', response.data);
        }
        else {
          store.state.postIdList = [0];
          store.state.roomList = [{ post_id: 0, post_name: " " }];
        }
      })
      .catch(error => {
        console.log("사용자의 POST_ID 리스트 받아오기 실패", error);
      })
  },

  FIND_ROOM({ commit }, list) {
    return getPostInfoList(list)
      .then(response => {
        console.log("API:SET_FIND_ROOM 사용자의 Room 리스트 받아오기 성공", response.data);
        commit('SET_FIND_ROOM', response.data);
      })
      .catch(error => {
        console.log("사용자의 Room 리스트 받아오기 실패", error);
      })
  },

  FIND_USER({ commit }, post_id) {
    return getNickList(post_id)
      .then(response => {
        console.log("API:SET_FIND_USER 채팅방에 속한 사용자 nick 리스트 받아오기 성공", response.data);
        commit('SET_FIND_USER', response.data);
      })
      .catch(error => {
        console.log("채팅방에 속한 사용자 nick 리스트 받아오기 실패", error);
      })
  },

  FIND_CHAT_LOGS({ commit }, post_id) {
    return getChatList(post_id)
      .then(response => {
        console.log("API:SET_FIND_CHAT_LOGS 채팅방 chatlogs 리스트 받아오기 성공", response.data);
        commit('SET_FIND_CHAT_LOGS', response.data);
      })
      .catch(error => {
        console.log("채팅방 chatlogs 리스트 받아오기 실패", error);
      })
  },

  POST_CHAT_DATA(c, chatData) {
    return postChatData(chatData)
      .then(response => {
        console.log("채팅 보내기 성공", response.data);
        store.dispatch('FIND_CHAT_LOGS', chatData.post_id)
      })
      .catch(error => {
        console.log("채팅 보내기 실패", error);
      })
  },

  ////////////////////////// ROOMINFO //////////////////////////

  // 참여하기
  JOIN_DELIVERY_POST(content, roomInfo) {
    return postRoomInfo(roomInfo)
      .then(response => {
        console.log('API:JOIN_DELIVERY_POST\n배달 참여 성공', response);
        store.dispatch("FIND_POST_ID", store.state.member.nick);
      })
      .catch(error => {
        console.log('API:JOIN_DELIVERY_POST\n배달 참여 실패', error);
      })
  },

  // 참여 취소하기
  EXIT_DELIVERY_POST(content, roomInfo) {
    return patchRoomInfo(roomInfo)
      .then(response => {
        console.log('API:EXIT_DELIVERY_POST\n참여 취소 성공', response);
        store.dispatch("FIND_POST_ID", store.state.member.nick);
      })
      .catch(error => {
        console.log('API:EXIT_DELIVERY_POST\n참여 취소 실패', error);
      })
  },
}
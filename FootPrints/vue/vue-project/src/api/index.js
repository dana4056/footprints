import axios from 'axios';

// 1. HTTP Request & Response와 관련된 기본 설정
const config = {
    baseUrl:"http://localhost:8080"
};

////////////////////////// TOKEN //////////////////////////

function fetchToken() {
    return axios.get(`${config.baseUrl}/token`, {
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

function fetchAutority(){
    return axios.get(`${config.baseUrl}/authority`, {
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

////////////////////////// MEMBER //////////////////////////

function postSignup(member) {
    return axios.post(`${config.baseUrl}/member`, member);
}

function patchChangePwd(changePwMemberDTO) {
    return axios.patch(`${config.baseUrl}/member`, changePwMemberDTO);
}

function getUserArea(nick) {
    console.log("function findUserArea 실행")
    return axios.get(`${config.baseUrl}/member/login`, {
        params: {
            nick: nick,
        },
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}

function postLogin(loginMember) {
    return axios.post(`${config.baseUrl}/member/login`, loginMember);
}

function getFindId(email) {
    return axios.get(`${config.baseUrl}/member/find-id`, {
        params: {
            email: email,
        },
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}

function getFindPwd(email) {
    return axios.get(`${config.baseUrl}/member/find-password`, {
        params : {
            email: email,
        },
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}

function getCheckNick(nick) {
    return axios.get(`${config.baseUrl}/member/check-nick`, {
        params: {
            nick: nick,
        },
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}

function getCheckEmail(email) {
    return axios.get(`${config.baseUrl}/member/check-email`, {
        params: {
            email: email,
        },
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}

////////////////////////// NOTICE //////////////////////////

function getNoticeList() {
    return axios.get(`${config.baseUrl}/notice`);
}

function postNotice(noticeDTO) {
    return axios.post(`${config.baseUrl}/notice`, noticeDTO,{
        headers: {
          'Content-Type': 'application/json',
          'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
      });
}

function patchNotice(noticeDTO){
    return axios.patch(`${config.baseUrl}/notice`, noticeDTO, {
        headers: {
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    })
}

function deleteNotice(id) {
    return axios.delete(`${config.baseUrl}/notice`, {
        params: {
            id: id,
        },
        headers: {
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

function getNoticeDetail(notice_id) {
    return axios.get(`${config.baseUrl}/notice/${notice_id}`,{
        params: {
            isFrontReq: 1
        },
        headers: {
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
      });
}

////////////////////////// USERS //////////////////////////

function getMyPost(nick) {
    return axios.get(`${config.baseUrl}/users/${nick}/my-post`, {
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}

function getAttendPost(nick) {
    return axios.get(`${config.baseUrl}/users/${nick}/attend-post`, {
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}
  
function patchUserInfo(memberDTO) {
    return axios.patch(`${config.baseUrl}/users`, memberDTO);
}

////////////////////////// DELIVERY //////////////////////////

function getDeliveryList(area) {
    return axios.get(`${config.baseUrl}/delivery/post`, {
        params: {
            area: area,
            isFrontReq:1
        },
        headers: {
            'Content-Type': 'text/plain',
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

//카테고리 별로 불러오기 위함
function getSortDeliveryList(sortDTO) {
    return axios.get(`${config.baseUrl}/delivery/post/sort`, {
        params: {
            category: sortDTO.category,
            sort_criteria: sortDTO.sort_criteria,
            area: sortDTO.area
        },
        headers: {
            'Content-Type': 'text/plain',
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

function postDeliveryPost(post) {
    return axios.post(`${config.baseUrl}/delivery/post`, post, {
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

function patchDeliveryPost(post) {
    return axios.patch(`${config.baseUrl}/delivery/post`, post, {
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

function deleteDeliveryPost(post_id) {
    return axios.delete(`${config.baseUrl}/delivery/post`, {
        params: {
            post_id: post_id,
        },
        headers: {
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

function getDeliveryPostDetail(post_id) {
    return axios.get(`${config.baseUrl}/delivery/post/${post_id}`, {
        params: {
            isFrontReq : 1
        },
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

// function getDeliveryPostEdit(post_id) {
//     return axios.get(`${config.baseUrl}/delivery/post/${post_id}/edit`, {
//         headers: {
//             'X-AUTH-TOKEN': localStorage.getItem('jwt')
//         },
//     });
// }

////////////////////////// CHAT //////////////////////////

function getPostIdList(nick) {
    return axios.get(`${config.baseUrl}/chat/post-id-list`, {
        params: {
            nick: nick,
        },
        headers: {
            'Content-Type': 'text/plain',
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

function getPostInfoList(list) {
    console.log("postIDList: " + list);
    return axios.post(`${config.baseUrl}/chat/get-PostInfoList`, list, {
        headers: {
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

function getNickList(post_id) {
    return axios.get(`${config.baseUrl}/chat/get-nick-list`, {
        params: {
            post_id: post_id,
        },
        headers: {
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
  }

function getChatList(post_id) {
    return axios.get(`${config.baseUrl}/chat`, {
        params: {
            post_id: post_id,
        },
        headers: {
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
  }
  
  function postChatData(chatData) {
    console.log(chatData);
    return axios.post(`${config.baseUrl}/chat`, chatData, {
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
  }

function deleteChatData(post_id) {
    return axios.delete(`${config.baseUrl}/chat`, {
        params: {
            post_id: post_id,
        },
        headers: {
            'Content-Type': 'application/json'
        }
    });
  }

////////////////////////// ROOMINFO //////////////////////////


function postRoomInfo(roomInfo) {
    return axios.post(`${config.baseUrl}/room-info`, roomInfo);
}

function patchRoomInfo(roomInfo) {
    return axios.patch(`${config.baseUrl}/room-info`, {
        params: {
            nick : roomInfo.nick,
            post_id: roomInfo.post_id,
        },
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt'),
            'Content-Type': 'application/json'
        }
    });
  }

function deleteRoomInfo(post_id) {
    return axios.delete(`${config.baseUrl}/room-info`, {
        params: {
            post_id: post_id,
        },
        headers: {
            'Content-Type': 'application/json'
        }
    });
}
export{
    //Token
    fetchToken,
    fetchAutority,

    //Member
    postSignup,
    patchChangePwd,
    getUserArea,
    postLogin,
    getFindId,
    getFindPwd,
    getCheckNick,
    getCheckEmail,
    
    //Notice
    getNoticeList,
    postNotice,
    patchNotice,
    deleteNotice,
    getNoticeDetail,

    //Users
    getMyPost,
    getAttendPost,
    patchUserInfo,

    //Delivery
    getDeliveryList,
    getSortDeliveryList,
    postDeliveryPost,
    // postMakeRoomInfo,
    patchDeliveryPost,
    deleteDeliveryPost,
    getDeliveryPostDetail,
    // getDeliveryPostEdit,

    //Chat
    getPostIdList,
    getPostInfoList,
    getNickList,
    getChatList,
    postChatData,
    deleteChatData,

    //RoomInfo
    postRoomInfo,
    patchRoomInfo,
    deleteRoomInfo,
}
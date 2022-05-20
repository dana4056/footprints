import axios from 'axios';


// 1. HTTP Request & Response와 관련된 기본 설정
const config = {
    baseUrl:"http://localhost:8080"
};


// 2. API 함수들을 정리
function postEmail(email){
    return axios.post(`${config.baseUrl}/signup/check-email`, email, {
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}

function postNick(nick){
    return axios.post(`${config.baseUrl}/signup/check-nick`, nick, {
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}

function postPhone(phone){
    return axios.post(`${config.baseUrl}/signup/authentic-code`, phone, {
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}

function postMemberInfo(member){
    return axios.post(`${config.baseUrl}/signup`, member);
}

function postLogin(member){
    return axios.post(`${config.baseUrl}/login`, member);
}

export{
    postEmail,
    postNick,
    postPhone,
    postMemberInfo,
    postLogin,
} 
<template>
    <div id="wrap">
        <div id='completed'>
            <img src="../assets/congratulation.png">
            <h3>회원가입 완료</h3>
            <p>{{nick}}님, 환영합니다.</p>
        </div>
    </div>
</template>

<script>
import axios from 'axios';


export default {
    data(){
        return{
            nick:""
        }
    },
    created(){
        axios.get("http://localhost:8080/signup-completed", {
            params: {
                nick: 12345
                }
            })
        .then(response=>{
            console.log(response)
        });
    }
}
</script>

<style scoped>

#wrap{
    display: flex;
    height: 100vh;
    flex-direction: column;
    justify-content: center;
}

#completed{
    width: 400px;
    margin: 0 auto;
    padding: 15px;
}
img{
    width: 100px;
}

</style>
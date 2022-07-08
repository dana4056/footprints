import { createApp } from 'vue'
import App from './App.vue'
import { store } from './store/store'
import { router } from './routes/index.js'
import VueCookies from 'vue-cookies';

const app = createApp(App)
app.use(store)
app.use(router)
app.use(VueCookies)
app.mount('#app')

//쿠키의 만료일은 1일이다. (글로벌 세팅)
app.$cookies.config("1d");





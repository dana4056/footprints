import { createApp } from 'vue'
import App from './App.vue'
import { store } from './store/store'
import { router } from './routes/index.js'

const app = createApp(App)
app.use(store)
app.use(router)
app.mount('#app')



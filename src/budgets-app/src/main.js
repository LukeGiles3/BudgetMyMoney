import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ToastPlugin from 'vue-toast-notification'
import 'vue-toast-notification/dist/theme-bootstrap.css'

import '@fortawesome/fontawesome-free/js/all'

createApp(App).use(router).use(ToastPlugin).mount('#app')

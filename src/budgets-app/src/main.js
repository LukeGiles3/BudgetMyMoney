import { createApp, provide } from 'vue';
import App from './App.vue';
import router from './router';
import ToastPlugin from 'vue-toast-notification';
import 'vue-toast-notification/dist/theme-bootstrap.css';
import { initializeApp } from "firebase/app";

const firebaseConfig = {
    apiKey: "AIzaSyDeLtcJzumCie_o3vto1zS5sLUfFw2nPAg",
    authDomain: "budgetmymoney-9e8f1.firebaseapp.com",
    projectId: "budgetmymoney-9e8f1",
    storageBucket: "budgetmymoney-9e8f1.appspot.com",
    messagingSenderId: "1054426200113",
    appId: "1:1054426200113:web:d3b27ebb401de31f877d10"
};

const firebaseApp = initializeApp(firebaseConfig);

provide('firebase', firebaseApp);

import '@fortawesome/fontawesome-free/js/all';

createApp(App)
    .use(router)
    .use(ToastPlugin)
    .mount('#app');

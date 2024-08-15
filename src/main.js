import { createApp } from 'vue'
import App from './App.vue'
import { createRouter, createWebHistory } from 'vue-router'
import ToastPlugin from 'vue-toast-notification';
//import 'vue-toast-notification/dist/theme-default.css';
import 'vue-toast-notification/dist/theme-bootstrap.css';
import LoginPage from './components/LoginPage.vue';
import HomePage from './components/HomePage.vue';
import SignupPage from './components/SignupPage.vue';
import PaymentPage from "./components/PaymentPage.vue"

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', component: HomePage },
        { path: '/signup', component: SignupPage },
        { path: '/login', component: LoginPage },
        { path: '/payment', component: PaymentPage },
    ]
});

const app = createApp(App)
app.use(router);
app.use(ToastPlugin);
app.mount('#app')

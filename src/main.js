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

import HouseDetail from './components/HouseDetail.vue';
import UserProfile from './components/UserProfile.vue'
import AdminDashBoard from './components/AdminDashBoard.vue'
import ManageProperties from '@/components/ManageProperties.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', component: HomePage },
        { path: '/signup', component: SignupPage },
        { path: '/login', component: LoginPage },

        { path: '/payment', component: PaymentPage },

        {
            path: '/house/:id',
            
            component: HouseDetail, // The detail page component
            props: true, // Pass route params as props to the component
          },
          {
            path: '/profile',
            name: 'UserProfile',
            component: UserProfile,
          },
          {
            path: '/admin',
            name: 'AdminDashBoard',
            component: AdminDashBoard,
          },
          {
            path: '/manage-properties',
            name: 'ManageProperties',
            component: ManageProperties,
          }

    ]
});

const app = createApp(App)
app.use(router);
app.use(ToastPlugin);
app.mount('#app')

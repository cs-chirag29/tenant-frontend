import { createApp } from 'vue'
import App from './App.vue'
import { createRouter, createWebHistory } from 'vue-router'
import ToastPlugin from 'vue-toast-notification';
import 'vue-toast-notification/dist/theme-bootstrap.css';
import LoginPage from './components/LoginPage.vue';
import HomePage from './components/HomePage.vue';
import SignupPage from './components/SignupPage.vue';

import PaymentPage from "./components/PaymentPage.vue"

import HouseDetail from './components/HouseDetail.vue';
import UserProfile from './components/UserProfile.vue'
import AdminPropertyAdd from './components/Admin/AdminPropertyAdd.vue';
import OrderPage from './components/OrderPage.vue';
import BookingPage from './components/BookingPage.vue';
import EditProfile from './components/EditProfile.vue';
import AdminDashboard from './components/Admin/AdminDashboard.vue';
import ManageProperties from "./components/Admin/ManageProperties.vue"


const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', component: HomePage },
        { path: '/signup', component: SignupPage },
        { path: '/login', component: LoginPage },

        { path: '/payment', component: PaymentPage },

        {
            path: '/house/:id',
            component: HouseDetail, 
            props: true, 
          },
          {
            path: '/profile',
            name: 'UserProfile',
            component: UserProfile,
          },{
            path: '/admin/addproperty',
            name: 'AdminPropertyAdd',
            component: AdminPropertyAdd,
          },{
            path: "/orderdetails",
            name: 'OrderPage',
            component: OrderPage, 
          },{
            path: "/book/:id",
            name: 'BookingPage',
            component: BookingPage, 
          },{
            path: "/edit",
            name: 'EditProfile',
            component: EditProfile, 
          },{
            path: "/admin",
            name: 'AdminDashBoard',
            component: AdminDashboard, 
          },
          {
            path: "/admin/manage-properties",
            name: 'ManageProperties',
            component: ManageProperties, 
          }

    ]
});

const app = createApp(App)
app.use(router);
app.use(ToastPlugin);
app.mount('#app')

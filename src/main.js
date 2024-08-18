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
import SuccessPage from './components/SuccessPage.vue';
import AllHousesPage from './components/AllHousesPage.vue';
import ManageUser from "./components/Admin/ManageUser.vue";

import { isAuthenticated, isAdmin } from '../src/components/Utils/ProtectedFile';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', component: HomePage },
        { path: '/signup', component: SignupPage },
        { path: '/login', component: LoginPage },

        { path: '/payment', component: PaymentPage,meta: { requiresAuth: true } },

        {
            path: '/house/:id',
            component: HouseDetail, 
            props: true, 
          },
          {
            path: '/profile',
            name: 'UserProfile',
            component: UserProfile,
            meta: { requiresAuth: true }
          },{
            path: "/order/:id",
            name: 'OrderPage',
            component: OrderPage, 
            meta: { requiresAuth: true }
          },{
            path: "/book/:id",
            name: 'BookingPage',
            component: BookingPage,
            meta: { requiresAuth: true } 
          },{
            path: "/edit",
            name: 'EditProfile',
            component: EditProfile, 
            meta: { requiresAuth: true }
          },{
            path: "/success",
            name: 'SuccessPage',
            component: SuccessPage, 
            meta: { requiresAuth: true }
          },{
            path: "/allHouses",
            name: 'AllHousesPage',
            component: AllHousesPage, 
          },{
            path: '/admin/addproperty',
            name: 'AdminPropertyAdd',
            component: AdminPropertyAdd,
            meta: { requiresAuth: true, requiresAdmin: true }
          },
          {
            path: "/admin",
            name: 'AdminDashBoard',
            component: AdminDashboard, 
            meta: { requiresAuth: true, requiresAdmin: true }
          },
          {
            path: "/admin/manage-properties",
            name: 'ManageProperties',
            component: ManageProperties,
            meta: { requiresAuth: true, requiresAdmin: true } 
          },
          {
            path: "/admin/manage-users",
            name: 'ManageUser',
            component: ManageUser, 
            meta: { requiresAuth: true, requiresAdmin: true }
          },


    ]
});


router.beforeEach((to, from, next) => {
  const requiresAuth = to.meta.requiresAuth;
  const requiresAdmin = to.meta.requiresAdmin;
  
  const authenticated = isAuthenticated();
  const admin = isAdmin();

  if (requiresAuth && !authenticated) {
    next('/login');
  } else if (requiresAdmin && !admin) {
    next('/');
  } else {
    next(); 
  }
});

const app = createApp(App)
app.use(router);
app.use(ToastPlugin);
app.mount('#app')

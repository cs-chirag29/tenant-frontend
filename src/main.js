import { createApp } from 'vue'
import App from './App.vue'
import { createRouter, createWebHistory } from 'vue-router'
import LoginPage from './components/LoginPage.vue';
import HomePage from './components/HomePage.vue';
import SignupPage from './components/SignupPage.vue';
import HouseDetail from './components/HouseDetail.vue';
import UserProfile from './components/UserProfile.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', component: HomePage },
        { path: '/signup', component: SignupPage },
        { path: '/login', component: LoginPage },
        {
            path: '/house/:id',
            
            component: HouseDetail, // The detail page component
            props: true, // Pass route params as props to the component
          },
          {
            path: '/profile',
            name: 'UserProfile',
            component: UserProfile,
          }
    ]
});

const app = createApp(App)
app.use(router);

app.mount('#app')

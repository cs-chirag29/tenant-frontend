<script setup>
import { ref } from 'vue';
import axios from 'axios';
import HeaderPage from './HeaderPage.vue';
import { useRouter } from 'vue-router';
import {useToast} from 'vue-toast-notification';


const user = ref({
  userName: '',
  password: '',
});

const router = useRouter();
const toast = useToast(); 

const onSubmit = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/user-logins', {
      username: user.value.userName,
      password: user.value.password,
    });

    
    if (response.data) {
      
      const tenant = response.data;
      // console.log(tenant);
      
      sessionStorage.setItem('tenant', JSON.stringify(tenant));
      router.push('/profile');
      
      toast.success('Login successful!');
    }
  } catch (error) {
    toast.error('Login failed. Please check your credentials.'); 
    console.error('Login error:', error);
  }
};
</script>

<template>
  <HeaderPage></HeaderPage>
    <div class="container">
      <form @submit.prevent="onSubmit" novalidate>
        <h2 class="text-center">Login</h2>
  

      <div class="form-group">
        <label for="userName">Username</label>
        <input
          type="userName"
          id="userName"
          v-model="user.userName"
          :class="{'form-control': true}"
          required
        />
        
      </div>

     
      <div class="form-group">
        <label for="password">Password</label>
        <input
          type="password"
          id="password"
          v-model="user.password"
          :class="{'form-control': true}"
          required
        />
      </div>
  
        <button type="submit"  class="btn btn-primary">Login</button>
      </form>
      
    </div>

   
  </template>
  
<style scoped src="../css/LoginPage.css"></style>
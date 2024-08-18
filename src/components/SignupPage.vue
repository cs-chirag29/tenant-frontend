<script setup>
  import { ref} from "vue"
  import axios from "axios";
  import HeaderPage from "./HeaderPage.vue";
  import { useRouter } from 'vue-router'; 
  import LoaderPage from "./Utils/LoaderPage.vue";
  import {useToast} from 'vue-toast-notification';

  const user = ref(  {
        firstName: '',
        lastName: '',
        email: '',
        phoneNumber: '',
        address: '',
        city: '',
        state: '',
        zipCode: '',
        userName: '',
        password: ''
      })

      const loading= ref(false);
      const router = useRouter(); 
      const toast = useToast();
    // const  errors= ref({});
    
    // const  validateForm=() =>{
    //     this.errors = {};
    //     if (!this.loginData.name) this.errors.name = true;
    //     if (!this.loginData.email || !this.validEmail(this.loginData.email)) this.errors.email = true;
    //     if (!this.loginData.username) this.errors.username = true;
    //     if (!this.loginData.password) this.errors.password = true;
    //   }

 

  const onSubmit = async () => {
    loading.value = true; 
  try {
    const response = await axios.post('http://localhost:8080/tenants', user.value); 
    
    sessionStorage.setItem('authToken', response.data); 
    toast.success('User registered successfully!');
    
   
      router.push('/login');
   
  } catch (error) {
    toast.error('There was an error creating the user.');
    console.error('There was an error creating the user:', error);
  } finally {
   
      loading.value = false;
   
  }
}

    
  </script>
<template>  
  <HeaderPage></HeaderPage>
  <LoaderPage v-if="loading.value" />
  <div v-else class="container">
      <form @submit.prevent="onSubmit" novalidate>
        <h2 class="text-center">Sign Up</h2>
  
        <div class="form-group">
        <label for="firstName">First Name</label>
        <input
          type="text"
          id="firstName"
          v-model="user.firstName"
          :class="{'form-control': true}"
          required
        />
        
      </div>

      <div class="form-group">
        <label for="lastName">Last Name</label>
        <input
          type="text"
          id="lastName"
          v-model="user.lastName"
          :class="{'form-control': true}"
          required
        />
       
      </div>

      <div class="form-group">
        <label for="email">Email</label>
        <input
          type="email"
          id="email"
          v-model="user.email"
          :class="{'form-control': true}"
          required
        />
        
      </div>

      <div class="form-group">
        <label for="contact">Contact</label>
        <input
          type="text"
          id="contact"
          v-model="user.phoneNumber"
          :class="{'form-control': true}"
          required
        />
        
      </div>

      <div class="form-group">
        <label for="address">Address</label>
        <input
          type="text"
          id="address"
          v-model="user.address"
          :class="{'form-control': true}"
          required
        />
        
      </div>

      <div class="form-group">
        <label for="city">City</label>
        <input
          type="text"
          id="city"
          v-model="user.city"
          :class="{'form-control': true}"
          required
        />
       
      </div>

      <div class="form-group">
        <label for="state">State</label>
        <input
          type="text"
          id="state"
          v-model="user.state"
          :class="{'form-control': true}"
          required
        />
        
      </div>

      <div class="form-group">
        <label for="zipCode">ZIP Code</label>
        <input
          type="text"
          id="zipCode"
          v-model="user.zipCode"
          :class="{'form-control': true}"
          required
        />
        
      </div>

      <!-- <div class="form-group">
        <label for="dateOfBirth">Date of Birth</label>
        <input
          type="date"
          id="dateOfBirth"
          v-model="user.dateOfBirth"
          :class="{'form-control': true}"
          required
        />
      </div> -->
      <div class="form-group">
        <label for="username">Username</label>
        <input
          type="text"
          id="username"
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

        <button type="submit" :disabled="loading.value" class="btn btn-primary">{{ loading.value ? 'Signing Up...' : 'Sign Up' }}</button>
      </form>
      
    </div>
   
  </template>
  
<style scoped src="../css/SignupPage.css"></style>
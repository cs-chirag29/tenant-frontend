<script setup>
  import { ref} from "vue"
//   import axios from "axios";
  import HeaderPage from "./HeaderPage.vue";
  
  const user = ref(  {
        email: '',
        password: '',
    })


  const onSubmit = async () => {
  try {
    // const response = await axios.post('http://localhost:8080/users', user.value);
    // console.log('User created:', response.data);
  } catch (error) {
    console.error('There was an error creating the user:', error);
  }
}

    
  </script>
<template>
  <HeaderPage></HeaderPage>
    <div class="container">
      <form @submit.prevent="onSubmit" novalidate>
        <h2 class="text-center">Login</h2>
  
        

      

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
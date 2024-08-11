<script setup>
  import { ref} from "vue"
  import axios from "axios";
  import HeaderPage from "./HeaderPage.vue";
  
  const user = ref(  {
        firstName: '',
        lastName: '',
        email: '',
        contact: '',
        address: '',
        city: '',
        state: '',
        zipCode: '',
        dateOfBirth: ''
      })

    // const  errors= ref({});
    
    // const  validateForm=() =>{
    //     this.errors = {};
    //     if (!this.loginData.name) this.errors.name = true;
    //     if (!this.loginData.email || !this.validEmail(this.loginData.email)) this.errors.email = true;
    //     if (!this.loginData.username) this.errors.username = true;
    //     if (!this.loginData.password) this.errors.password = true;
    //   }

    //   const validEmail=(email) =>{
    //     const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    //     return re.test(email);
    //   }

  const onSubmit = async () => {
  try {
    const response = await axios.post('http://localhost:8080/users', user.value);
    console.log('User created:', response.data);
  } catch (error) {
    console.error('There was an error creating the user:', error);
  }
}

    
  </script>
<template>
  <HeaderPage></HeaderPage>
    <div class="container">
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
          v-model="user.contact"
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

      <div class="form-group">
        <label for="dateOfBirth">Date of Birth</label>
        <input
          type="date"
          id="dateOfBirth"
          v-model="user.dateOfBirth"
          :class="{'form-control': true}"
          required
        />
      </div>
  
        <button type="submit"  class="btn btn-primary">Sign Up</button>
      </form>
      
    </div>

   
  </template>
  
<style scoped src="../css/SignupPage.css"></style>
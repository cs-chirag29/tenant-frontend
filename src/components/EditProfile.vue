<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useToast } from 'vue-toast-notification';

const user = ref({});

const router = useRouter();
const toast = useToast();


const saveProfile = async () => {
  try {
    const tenantId = user.value.tenantId;
    const response = await axios.put(`http://localhost:8080/tenants/${tenantId}`, user.value);
    
    sessionStorage.setItem('tenant', JSON.stringify(response.data));
    toast.success('User updated successfully!');
    router.push('/profile');
  } catch (error) {
    toast.error('There was an error creating the user.');
  }
};

const initializeUserData = () => {
  const storedUser = sessionStorage.getItem('tenant');
  if (storedUser) {
    user.value = JSON.parse(storedUser);
  }
};


onMounted(() => {
  initializeUserData();
});
</script>
<template>

    <div class="edit-profile-container">
      <div class="header">
    <div style="position: absolute; top: 2%; left: 2%; display: flex; justify-content: center; align-items: center;" class="left">
      <img width="50" height="50" src="https://img.icons8.com/ios-filled/50/228BE6/long-arrow-left.png" alt="long-arrow-left"/>
      <router-link to="/" class="home-link" style="text-decoration: none; font-size: 1.7rem; color:white; margin-left: 1rem;">Back</router-link>
    </div>
  </div>
      <form @submit.prevent="saveProfile">
        <h2>Edit Profile</h2>
        <div class="form-group">
          <label for="firstName">First Name</label>
          <input type="text" id="name" class="form-control" v-model="user.firstName" required />
        </div>

        <div class="form-group">
          <label for="name">Last Name</label>
          <input type="text" id="name" class="form-control" v-model="user.lastName" required />
        </div>
  
        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" id="email" class="form-control" v-model="user.email" required />
        </div>
  
        <div class="form-group">
          <label for="phoneNumber">Phone Number</label>
          <input type="tel" id="phone" class="form-control" v-model="user.phoneNumber" required />
        </div>
  
        <div class="form-group">
          <label for="address">Address</label>
          <input type="text" id="address" class="form-control" v-model="user.address" required />
        </div>
  
        <div class="form-group">
          <label for="state">State</label>
          <input type="text" id="state" class="form-control" v-model="user.state" required />
        </div>
  
        <div class="form-group">
          <label for="city">City</label>
          <input type="text" id="city" class="form-control" v-model="user.city" required />
        </div>
  
        <div class="form-group">
          <label for="zip">Zip Code</label>
          <input type="text" id="zip" class="form-control" v-model="user.zipCode" required />
        </div>
  
        <button type="submit" class="btn-primary">Save Changes</button>
      </form>
    </div>
  </template>

  
  <style scoped src="../css/EditProfile.css">
  
  </style>
  
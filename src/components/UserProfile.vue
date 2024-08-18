<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import {  useRouter } from 'vue-router';
import {useToast} from 'vue-toast-notification';

const user = ref({});
const router = useRouter();
const toast = useToast();
const orders= ref([]);
const tenantId = ref(null); 

onMounted(async () => {
  const tenantData = sessionStorage.getItem('tenant');
  if (tenantData) {
    try {
      user.value = JSON.parse(tenantData);
      tenantId.value = user.value.tenantId;
    } catch (error) {
      console.error('Failed to parse tenant data:', error);
      router.push('/login');
    }
  } else {
    router.push('/login');
  }

  try {
    const response = await axios.get("http://localhost:8080/leases");
    orders.value = response.data.filter(order => order.tenant.tenantId === tenantId.value);
  } catch (error) {
    console.log(error);
  }
});

const goToEditProfile = () => {
  router.push('/edit');
};

const handleorder = (order) => {
  router.push(`/order/${order.leaseId}`);
};


const calculateTotalDays = (startDate, endDate) => {
  const start = new Date(startDate);
  const end = new Date(endDate);
  if (isNaN(start) || isNaN(end)) return 0;
  
  const timeDiff = end - start;
  const dayDiff = Math.ceil(timeDiff / (1000 * 60 * 60 * 24)); 
  return dayDiff + 1;
};
const handlelogout=()=>{
  sessionStorage.setItem("tenant", "");
  toast.success("Logout Successful")
  router.push("/login")
}
const handleadmin=()=>{
  router.push("/admin")
}
</script>

<template>
  <div class="header">
    <div class="left" style="position: absolute; left: 2%; right: 2%;">
      <img width="40" height="40" src="https://img.icons8.com/ios-filled/50/228BE6/long-arrow-left.png" alt="long-arrow-left"/>
      <router-link to="/" class="home-link" style="font-size: 1.5rem;">Back</router-link>
    </div>
  </div>
  
  <div class="profile-page" v-if="user && Object.keys(user).length">
   
    <div class="profile-info">
      <img src="https://cdn1.iconfinder.com/data/icons/mix-color-3/502/Untitled-7-1024.png" alt="Profile Image" class="profile-image"/>
      <div style="display:flex;justify-content:space-between; align-items: center;"><h2 style="margin-right: 1rem;">{{ user.firstName }}</h2>
      <h2>{{ user.lastName }}</h2></div>

      <div style="display:flex;justify-content:space-between; align-items: center;">
        <h3 style="margin-right: 1rem;">Email :</h3>
        <p>{{ user.email }}</p>
      </div>
        <div style="display:flex;justify-content:space-between; align-items: center;"><h3 style="margin-right: 1rem;">Phone No. :</h3>
          <p>{{ user.phoneNumber }}</p></div>

          <div style="display:flex;justify-content:space-between; align-items: center;"><h3 style="margin-right: 1rem;">State :</h3>
            <p>{{ user.state }}</p></div>
            
            <div style="display:flex;justify-content:space-between; align-items: center;"><h3 style="margin-right: 1rem;">City :</h3>
              <p>{{ user.city }}</p></div>
              <div style="display:flex;justify-content:space-between; align-items: center;"><h3 style="margin-right: 1rem;">Zip-Code :</h3>
                <p>{{ user.zipCode }}</p></div>
      
                <div style="display: flex; justify-content: space-evenly;">
                <button @click="goToEditProfile">Edit Profile</button>
                <button style="margin-left: 2rem;" @click="handlelogout">Logout</button>
              </div>
              <div style="margin-top: 2rem;"><button v-if="user.role === 'admin'" @click="handleadmin">Admin Dashboard</button></div>
              
    </div>

    
    <div v-if="orders.length>0" class="user-details">
      <h2>Order History</h2>

      <div class="user-details-card" v-for="order in orders" :key="order.leaseId" @click="handleorder(order)">
        <div>
          <img :src="order?.unit?.unitImage"/>
        </div>
        <div style="width: 80%; display: flex; justify-content: space-between;">
          <div style="display: flex; flex-direction: column; justify-content: space-between; margin-left: 1rem;">
          <span  class="user-details-card-header">{{ order?.unit?.property?.propertyName }}</span>
          <div style="display: flex; color: black; margin-top: -1rem;">
          <h3 style="margin-right: 1rem;">{{ order?.unit?.property?.address }}</h3>
          <h3>{{ order?.unit?.property?.city }}</h3>
        </div>
        <div style="margin-top: -1.2rem;">
          <h3> Booking From : {{ order?.startDate }}</h3>
          <h3 style="margin-top: -0.9rem;"> Booking To : {{ order?.endDate }}</h3>
        </div>
        </div>

        <div >
          <h3>Security Deposit : Rs {{ order?.securityDeposit }}</h3>
          <h3>Rent Amount : Rs {{ order?.unit?.rentAmount }}</h3>
          <h3>Total : Rs {{ calculateTotalDays(order.startDate, order.endDate) * parseFloat(order?.unit?.rentAmount) || 0 }}</h3>
        </div>
      </div>
      </div>
      
    </div>
    <div v-else class="user-details">
      <div><h2>Order History</h2></div>
      <div style="width: 100%; height: 100%; display: flex; justify-content: center; align-items: center; "><p style="font-size: 3rem; margin-bottom: 5rem;">No orders found.</p></div>
    </div>
  </div>
  
  

</template>

<style scoped src="../css/UserProfile.css"></style>

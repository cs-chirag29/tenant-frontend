<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const user = ref({});
const router = useRouter();

onMounted(() => {
  const tenantData = sessionStorage.getItem('tenant');
  if (tenantData) {
    try {
      user.value = JSON.parse(tenantData);
    } catch (error) {
      console.error('Failed to parse tenant data:', error);
      router.push('/login');
    }
  } else {
    router.push('/login');
  }
});

const goToEditProfile = () => {
  router.push('/edit');
};

const hasOrders = () => user.value.payments && user.value.payments.length > 0;
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
      
                <button @click="goToEditProfile">Edit Profile</button>
    </div>

    
    <div v-if="hasOrders()" class="user-details">
      <h2>Order History</h2>
      <ul>
        <li v-for="payment in user.payments" :key="payment.id">{{ payment.details }}</li>
      </ul>
    </div>
    <div v-else class="user-details">
      <div><h2>Order History</h2></div>
      <div style="width: 100%; height: 100%; display: flex; justify-content: center; align-items: center; "><p style="font-size: 3rem; margin-bottom: 5rem;">No orders found.</p></div>
      
    </div>
  </div>
  
  
    <!-- <div class="user-details">
      <h2>Rented Properties</h2>
      <div class="property-list">
        <div class="property-card" v-for="property in user.rentedProperties" :key="property.id">
          <img :src="property.image" alt="Property Image" />
          <div>
            <h3>{{ property.name }}</h3>
            <p>Location: {{ property.location }}</p>
            <p>Price: ₹{{ property.price }} per day</p>
          </div>
        </div>
      </div>
       -->
      <!-- <h2>Order History</h2>
      <ul>
        <li v-for="order in user.orderHistory" :key="order.id">{{ order.details }}</li>
      </ul>

      <div class="rental-period-card">
        <h3>Rental Period</h3>
        <p>Start Date: {{ user.rentalPeriod.startDate }}</p>
        <p>End Date: {{ user.rentalPeriod.endDate }}</p>
      </div>
    </div> -->
  
</template>

<style scoped src="../css/UserProfile.css"></style>

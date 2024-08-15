<template>
    <div class="header">
        <div class="left">
          <img
            width="64"
            height="64"
            src="https://img.icons8.com/arcade/64/cottage.png"
            alt="cottage"
          />
          <router-link to="/" class="home-link">Back</router-link>
            
        </div >
        
      </div>
      
      <div class="profile-page">
    <!-- Toggle Button -->
    
    
    <!-- Left Side: Profile Info -->
    <div class="profile-info" :class="{ collapsed: isCollapsed }">
      <img src="https://cdn1.iconfinder.com/data/icons/mix-color-3/502/Untitled-7-1024.png" alt="Profile Image" class="profile-image"/>
      <h2>{{ user.name }}</h2>
      <p>{{ user.email }}</p>
      <button>Edit Profile</button>
      <button class="toggle-btn" @click="toggleSidebar">
      {{ isCollapsed ? '>>' : '<<' }}
    </button>
    </div>

    <!-- Right Side: User Details -->
    <div class="user-details">
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
      
      <h2>Order History</h2>
      <ul>
        <li v-for="order in user.orderHistory" :key="order.id">{{ order.details }}</li>
      </ul>

      <div class="rental-period-card">
        <h3>Rental Period</h3>
        <p>Start Date: {{ user.rentalPeriod.startDate }}</p>
        <p>End Date: {{ user.rentalPeriod.endDate }}</p>
      </div>
    </div>
  </div>  
  </template>
  
  <script setup>
  import { ref } from 'vue';
  
  const user = ref({
    name: "Rahul",
    email: "Rahul@example.com",
    rentedProperties: [
      {
        id: 1,
        name: "Green Shores",
        location: "Nagaon, Alibaug",
        price: 2500,
        image: "https://www.villabalisale.com/uploads/images/property/2023-04-26-property-6448915ee5dca.jpg"
      },
      {
        id: 2,
        name: "LakeView Inn",
        location: "Palghar",
        price: 1500,
        image: "https://i.pinimg.com/originals/11/4c/e9/114ce94d782662858157cbe701ed5ab3.jpg"
      }
    ],
    orderHistory: [
      { id: 1, details: "Order #1234 - Green Shores" },
      { id: 2, details: "Order #5678 - LakeView Inn" }
    ],
    rentalPeriod: {
      startDate: "2024-08-01",
      endDate: "2024-08-15"
    }
  });
  const isCollapsed = ref(false);

const toggleSidebar = () => {
  isCollapsed.value = !isCollapsed.value;
};
  </script>
  <style scoped src="../css/UserProfile.css"></style>
  
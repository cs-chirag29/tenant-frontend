<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const houseId = route.params.id;
const house = ref({});

onMounted( async()=>{

  try {
    
    const response = await axios.get(`http://localhost:8080/api/units/getUnitById/${houseId}`);
    house.value = response.data;
    console.log(house.value);
  } catch (error) {
    console.log(error);
  }
})

const bookHouse = () => {
  router.push(`/book/${houseId}`)
};


</script>

<template>
    <div>
      <div class="header">
       <div class="left" style="position: absolute; left: 2%; right: 2%;">
      <img width="40" height="40" src="https://img.icons8.com/ios-filled/50/228BE6/long-arrow-left.png" alt="long-arrow-left"/>
      <router-link to="/" class="home-link">Back</router-link>
    </div>
      </div>
  

      <div class="house-detail">
        <div class="house-header">
          <img :src="house?.unitImage" alt="House Image" class="house-image"/>
          <div class="house-title">
            <h1 v-if="house.property?.propertyName">{{ house.property.propertyName }}</h1>
          </div>
          <div class="house-address" style="margin: 1rem 0rem;">
            <div>
            <h2>{{ house.property?.address }}</h2>
            <h2>{{ house.property?.city }}</h2>
          </div>
          <div>
            <h2>{{ house.property?.state }}</h2>
            <h2>{{ house.property?.zipCode }}</h2>
          </div>
          </div>
        </div>
        <div class="house-info">
          <h2 style="font-size: 1.2rem;">Description :</h2>
          <p style="font-size: 1.2rem;">{{ house.property?.description }}</p>
          <h3>Features:</h3>
          <div style="display: flex; justify-content: space-between; align-items: center; margin-top: -1rem; font-size: 1.2rem;">
          <div>
            <li>Unit Number : {{ house.unitNumber }}</li>
            <li>Unit Type : {{ house.unitType }}</li>
          </div>
          <div>
            <li>Status : <span style="color: green;">{{ house.status }} </span> </li>
          </div>
          </div>
          
        </div>
        <div class="house-actions" style="display: flex; justify-content: flex-end;">
          <h3><span>Price: ₹{{ house.rentAmount }} per day </span></h3>
          <button @click="bookHouse">Book Now</button>
        </div>
      </div>
    </div>
  </template>
  

  
  <style scoped src="../css/Header.css"></style>
  
  <style scoped>
  .house-detail {
    max-width: 800px;
    margin: 0 auto;
    padding: 2rem;
    background-color: #f9f9f9;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  .house-header {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  
  .house-image {
    width: 100%;
    height: auto;
    border-radius: 8px;
    margin-bottom: 1rem;
  }
  
  .house-title h1 {
    background: linear-gradient(to top left, #000080, #00bfff);
    -webkit-background-clip: text;
    color: transparent;
  }
  
  .house-title h2 {
    margin: 0;
    font-size: 1.2rem;
    /* color: #777; */
  }
  .house-address{
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;

  }
  
  .house-address h2{

    margin: 0;
    font-size: 1.2rem;
    /* color: #777; */
  }
  .house-info {
    margin-top: 2rem;
   
  }
  .house-info p{
    
    margin: 0;
    font-size: 1.2rem;
    color: #777;
  }
  
  .house-info h3 {
    font-size: 1.5rem;
    color: #333;
  }
  
  .house-info p {
    font-size: 1rem;
    color: #555;
    margin-bottom: 1rem;
  }
  
  .house-info ul {
    list-style-type: disc;
    margin-left: 2rem;
  }
  
  .house-info li {
    margin-bottom: 0.5rem;
    color: #555;
  }

  .house-actions span {
    background: linear-gradient(to top left, #000080, #00bfff);
    -webkit-background-clip: text;
    color: transparent;
    margin-right: 1rem;
    font-size: 1.5rem;
  }

  
  
  .house-actions {
    display: flex;
    justify-content: space-between;
    margin-top: 2rem;
  }
  
  .house-actions button {
    padding: 0.8rem 1.5rem;
    border: none;
    border-radius: 5px;
    background-color: #007bff;
    color: white;
    font-size: 1rem;
    cursor: pointer;
  }
  
  .house-actions button:hover {
    background-color: #0056b3;
  }
  
  /* Additional styles for Navbar */
  .header {
    width: 100%;
    height: 12vh;
    display: flex;
    align-items: center;
  }
  
  .left {
    display: flex;
    align-items: center;
  }
  
  .left img {
    margin-right: 1rem;
  }
  
  .home-link {
    color: rgb(102, 101, 101);
    text-decoration: none;
    font-size: 1.2rem;
    cursor: pointer;
  }
  
  .home-link:hover {
    background: linear-gradient(to top left, #000080, #00bfff);
    -webkit-background-clip: text;
    color: transparent;
    transform: scale(1.1);
    cursor: pointer;
  }
  </style>
  
<template>
  <div>
    <!-- Content above the homeoptions section -->
    <div id="houses" class="homeoptions">
      <h1>Trending Rental Deals</h1>
      <span><h3>Most Popular House Rental Deals</h3></span>
      <div class="homeoptions-container">
        <HouseBox v-for="house in houses" :key="house.id" :house="house"/>
        <router-link 
          v-for="house in houses" 
          :key="house.id" 
          :to="`/house/${house.id}`" 
          class="house-link"
        >
          
        </router-link>
      </div>
      <button class="homeoptions-button">View More →</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import HouseBox from './HouseBox.vue';

const houses = ref([{
  id:1,
    image: "https://www.villabalisale.com/uploads/images/property/2023-04-26-property-6448915ee5dca.jpg",
    name: "Green Shores",
    price: 2500,
    description: "Charming, move-in-ready home with contemporary features and a prime location."
}, {
  id:2,
    image: "https://i.pinimg.com/originals/11/4c/e9/114ce94d782662858157cbe701ed5ab3.jpg",
    name: "LakeView Inn",
    price: 1500,
    description: "Charming, move-in-ready home with contemporary features and a prime location."
}, {
  id:3,
    image: "https://i.pinimg.com/originals/19/1f/c1/191fc146dfe4737399c70386faacdaf1.jpg",
    name: "Cozy Inn",
    price: 1200,
    description: "Charming, move-in-ready home with contemporary features and a prime location."
}]);


</script>

<style scoped src="../css/HomeOptions.css"></style>

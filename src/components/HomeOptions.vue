<template>
  <div>
    <!-- Content above the homeoptions section -->
    <div id="houses" class="homeoptions">
      <h1>Trending Rental Deals</h1>
      <span><h3>Most Popular House Rental Deals</h3></span>
      <div class="homeoptions-container">
        <HouseBox v-for="house in houses" :key="house.id" :house="house"/>
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
    image: "https://tse4.mm.bing.net/th/id/OIP._yC174sEBWELImhmt0sS8gHaE2?rs=1&pid=ImgDetMain",
    name: "House-1",
    price: 1000,
    description: "Charming, move-in-ready home with contemporary features and a prime location."
}, {
  id:2,
    image: "https://tse4.mm.bing.net/th/id/OIP._yC174sEBWELImhmt0sS8gHaE2?rs=1&pid=ImgDetMain",
    name: "House-2",
    price: 1000,
    description: "Charming, move-in-ready home with contemporary features and a prime location."
}, {
  id:3,
    image: "https://tse4.mm.bing.net/th/id/OIP._yC174sEBWELImhmt0sS8gHaE2?rs=1&pid=ImgDetMain",
    name: "House-3",
    price: 1000,
    description: "Charming, move-in-ready home with contemporary features and a prime location."
}]);


</script>

<style scoped src="../css/HomeOptions.css"></style>

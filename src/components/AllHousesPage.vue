<script setup>
import axios from 'axios';
import { ref, computed, onMounted } from 'vue';
import HouseBox from './HouseBox.vue';

const houses = ref([]);
const currentPage = ref(1);
const housesPerPage = 3; 
const error = ref(null);

const fetchData = async () => {
  try {
    const response = await axios.get('http://localhost:8080/units'); 
    houses.value = response.data;
    console.log(houses.value);
  } catch (err) {
    error.value = err.message;
    console.error('Error fetching data:', err);
  }
};

const paginatedHouses = computed(() => {
  const start = (currentPage.value - 1) * housesPerPage;
  const end = start + housesPerPage;
  return houses.value.slice(start, end);
});

const totalPages = computed(() => {
  return Math.ceil(houses.value.length / housesPerPage);
});

const goToPage = (page) => {
  if (page >= 1 && page <= totalPages.value) {
    currentPage.value = page;
  }
};

onMounted(() => {
  fetchData();
});
</script>


<template>
    <div>
      <div class="header">
        <div class="left" style="position: absolute; left: 2%; top: 2%; display: flex; justify-content: center; align-items: center;">
          <img width="40" height="40" src="https://img.icons8.com/ios-filled/50/228BE6/long-arrow-left.png" alt="long-arrow-left"/>
          <router-link to="/" class="home-link" style="font-size: 1.5rem; margin-left: 0.5rem; text-decoration: none; color: #333;">Back</router-link>
        </div>
      </div>
      
      <div v-if="houses.length > 0" id="houses" class="homeoptions">
        <h1>Featured Rental Properties</h1>
        <span><h3>Popular Rental Choices</h3></span>
        <div class="homeoptions-container">
          <HouseBox v-for="house in paginatedHouses" :key="house.id" :house="house"/>
        </div>
  
        <!-- Pagination Controls -->
        <div class="pagination-controls">
          <button @click="goToPage(currentPage - 1)" :disabled="currentPage === 1">Previous</button>
          <span>Page {{ currentPage }} of {{ totalPages }}</span>
          <button @click="goToPage(currentPage + 1)" :disabled="currentPage == totalPages">Next</button>
        </div>
      </div>
    </div>
  </template>
  


<style scoped src="../css/AllHouses.css"></style>

<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import HouseBox from './HouseBox.vue';

const houses = ref([]);

const error = ref(null);

const fetchData = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/units/getAllUnit'); 
    houses.value = response.data;
    console.log(houses.value);
  } catch (err) {
    error.value = err.message;
    console.error('Error fetching data:', err);
  }
};


onMounted(() => {
  fetchData();
});

</script>
<template>
  <div>
   
    <div id="houses" class="homeoptions">
      <h1>Trending Rental Deals</h1>
      <span><h3>Most Popular House Rental Deals</h3></span>
      <div class="homeoptions-container">
        <HouseBox v-for="house in houses" :key="house?.id" :house="house"/>
        <router-link 
          v-for="house in houses" 
          :key="house?.id" 
          :to="`/house/${house.id}`" 
          class="house-link"
        >
          
        </router-link>
      </div>
      <button class="homeoptions-button">View More →</button>
    </div>
  </div>
</template>



<style scoped src="../css/HomeOptions.css"></style>

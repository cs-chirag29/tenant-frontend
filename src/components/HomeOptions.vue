<template>
  <div id="houses" class="homeoptions">
    <h1>Trending Rental Deals</h1>
    <span><h3>Most Popular House Rental Deals</h3></span>
    <div class="homeoptions-container">
      <HouseBox v-for="property in displayedProperties" :key="property.id" :property="property" />
    </div>
    <button class="homeoptions-button">View More →</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import HouseBox from './HouseBox.vue';

const properties = ref([]);
const displayedProperties = ref([]);

const fetchProperties = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/properties/getAllProperties');
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    const data = await response.json();
    properties.value = data;
    displayedProperties.value = properties.value.slice(0, 3);

    // Debugging output
    console.log('Fetched properties:', properties.value);
    console.log('Displayed properties:', displayedProperties.value);
  } catch (error) {
    console.error('Failed to fetch properties:', error);
  }
};

onMounted(() => {
  fetchProperties();
});
</script>

<style scoped src="../css/HomeOptions.css"></style>

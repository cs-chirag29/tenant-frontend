<template>
  <div>
    <!-- Navbar -->
    <div class="header">
      <div class="left">
        <img
          width="64"
          height="64"
          src="https://img.icons8.com/arcade/64/cottage.png"
          alt="cottage"
        />
        <router-link to="/" class="home-link">Home</router-link>
      </div>
    </div>

    <!-- House Detail -->
    <div class="house-detail">
      <div class="house-header">
        <!-- Static image -->
        <img src="https://www.villabalisale.com/uploads/images/property/2023-04-26-property-6448915ee5dca.jpg"
               alt="House Image" class="house-image"/>
        <div class="house-title">
          <h1>{{ house.propertyName }}</h1>
          <h2>{{ house.address }}, {{ house.city }}, {{ house.state }} - {{ house.zipCode }}</h2>
        </div>
      </div>
      <div class="house-info">
        <h3>Price: ₹{{ house.price }} per day</h3>
        <h3>Description</h3>
        <p>{{ house.description }}</p>
        <h3>Features:</h3>
        <!-- Static features -->
        <ul>
          <li v-for="feature in features" :key="feature">{{ feature }}</li>
        </ul>
      </div>
      <div class="house-actions">
        <button @click="bookHouse">Book Now</button>
        <button @click="contactOwner">Contact Owner</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const houseId = route.params.id;

const house = ref({
  propertyName: '',
  address: '',
  city: '',
  state: '',
  zipCode: '',
  description: '',
  price: 'N/A', // Placeholder value
});

const features = [
  "3 Bedrooms",
  "2 Bathrooms",
  "Garage",
  "Backyard",
  "Modern Kitchen",
  "Close to public transport"
];

// Fetch data from Spring Boot API
const fetchHouseData = async () => {
  try {
    const response = await fetch(`http://localhost:8080/api/properties/getPropertyById/${houseId}`);
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    const data = await response.json();
    house.value = {
      ...data,
      price: 2500 // Assuming price is not part of the API response
    };
  } catch (err) {
    console.error('Error fetching house data:', err);
    house.value = {
      propertyName: 'Unknown',
      address: 'N/A',
      city: 'N/A',
      state: 'N/A',
      zipCode: 'N/A',
      description: 'No description available',
      price: 'N/A',
    };
  }
};

onMounted(() => {
  fetchHouseData();
});

const bookHouse = () => {
  alert('Booking functionality to be implemented.');
};

const contactOwner = () => {
  alert('Contact functionality to be implemented.');
};
</script>

<style scoped src="../css/Header.css"></style>

<style scoped src="../css/HouseDetail.css"></style>

<template>
    <div class="manage-properties">
      <h1>Manage Properties</h1>
      <div class="property-list">
        <div class="property-card" v-for="property in properties" :key="property.id">
          <img :src="property.image" alt="Property Image" />
          <div>
            <h3>{{ property.name }}</h3>
            <p>Location: {{ property.location }}</p>
            <p>Price: ₹{{ property.price }} per day</p>
            <button @click="editProperty(property.id)">Edit</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  
  // Mock data for now
  const properties = ref([
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
    },
  ]);
  
  const editProperty = (id) => {
    // Implement edit functionality
    console.log(`Edit property with ID: ${id}`);
  };
  </script>
  
  <style scoped>
  .manage-properties {
    padding: 2rem;
  }
  
  .property-list {
    display: flex;
    flex-wrap: wrap;
    gap: 1.5rem;
  }
  
  .property-card {
    width: 250px;
    padding: 1rem;
    background-color: #ffffff;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  
  .property-card img {
    width: 100%;
    height: 150px;
    object-fit: cover;
    border-radius: 8px;
    margin-bottom: 1rem;
  }
  
  .property-card h3 {
    font-size: 1.2rem;
    color: #333;
  }
  
  .property-card p {
    font-size: 1rem;
    color: #555;
  }
  
  .property-card button {
    margin-top: 1rem;
    padding: 0.5rem 1rem;
    border: none;
    background-color: #007bff;
    color: white;
    border-radius: 5px;
    cursor: pointer;
  }
  
  .property-card button:hover {
    background-color: #0056b3;
  }
  </style>
  
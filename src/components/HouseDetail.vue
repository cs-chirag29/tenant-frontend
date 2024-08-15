<template>
    <div>
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
  

      <div class="house-detail">
        <div class="house-header">
          <img :src="house.image" alt="House Image" class="house-image"/>
          <div class="house-title">
            <h1>{{ house.name }}</h1>
            <h2>{{ house.location }}</h2>
          </div>
        </div>
        <div class="house-info">
          <h3>Price: ₹{{ house.price }} per day</h3>
          <p>{{ house.description }}</p>
          <h3>Features:</h3>
          <ul>
            <li v-for="feature in house.features" :key="feature">{{ feature }}</li>
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
  import { ref } from 'vue';
  import { useRoute } from 'vue-router';
  
  const route = useRoute();
  const houseId = route.params.id;
  
  const houses = {
    1: {
      id: 1,
      image: "https://www.villabalisale.com/uploads/images/property/2023-04-26-property-6448915ee5dca.jpg",
      name: "Green Shores",
      location: "Nagaon, Alibaug",
      price: 2500,
      description: "This charming, move-in-ready home features contemporary design, a spacious layout, and is situated in a prime location. Perfect for families or individuals looking for comfort and convenience.",
      features: [
        "3 Bedrooms",
        "2 Bathrooms",
        "Garage",
        "Backyard",
        "Modern Kitchen",
        "Close to public transport"
      ]
    },
    2: {
      id: 2,
      image: "https://i.pinimg.com/originals/11/4c/e9/114ce94d782662858157cbe701ed5ab3.jpg",
      name: "LakeView Inn",
      location: "Palghar",
      price: 1500,
      description: "A beautiful suburban house with spacious interiors, a large garden, and close to top schools. Ideal for families.",
      features: [
        "4 Bedrooms",
        "3 Bathrooms",
        "Large Garden",
        "Modern Kitchen",
        "Close to Schools",
        "Quiet Neighborhood"
      ]
    },
    3: {
      id: 3,
      image: "https://i.pinimg.com/originals/19/1f/c1/191fc146dfe4737399c70386faacdaf1.jpg",
      name: "Cozy Inn",
      location: "Coorg",
      price: 1200,
      description: "A peaceful countryside retreat with a rustic design, surrounded by nature. Perfect for those seeking tranquility.",
      features: [
        "2 Bedrooms",
        "1 Bathroom",
        "Large Land Area",
        "Rustic Design",
        "Nature Surroundings",
        "Great for Weekend Getaways"
      ]
    }
  };
  
  const house = ref(houses[houseId]);
  
  const bookHouse = () => {
    alert('Booking functionality to be implemented.');
  };
  
  const contactOwner = () => {
    alert('Contact functionality to be implemented.');
  };
  </script>
  
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
    margin: 0;
    font-size: 2rem;
    color: #333;
  }
  
  .house-title h2 {
    margin: 0;
    font-size: 1.2rem;
    color: #777;
  }
  
  .house-info {
    margin-top: 2rem;
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
    width: auto;
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
  
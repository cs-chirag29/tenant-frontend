<template>
  

    <div class="header">
        <div class="header">
    <div class="left" style="position: absolute; left: 5%; right: 2%;">
      <img width="40" height="40" src="https://img.icons8.com/ios-filled/50/228BE6/long-arrow-left.png" alt="long-arrow-left"/>
      <router-link to="/" class="home-link">Back</router-link>
    </div>
  </div>
        
      </div>
    <div class="admin-dashboard-2" >
      <div class="sidebar"  >
        <h2>Admin Menu</h2>
        <ul>
          <li><router-link to="/admin/manage-properties">Manage Properties</router-link></li>
          <li><router-link to="/admin/manage-users">Manage Users</router-link></li>
          <li><router-link to="/admin/addProperty">Add New Properties</router-link></li>
        </ul>
        
      </div>
      
      <div class="main-content">
        <h1>Welcome, Admin</h1>
        <div class="stats">
          <div class="card">
            <h3>Total Listed Properties</h3>
            <p>{{ totalListed }}</p>
          </div>
          <div class="card">
            <h3>Total Booked Properties</h3>
            <p>{{ totalBooked }}</p>
          </div>
          <div class="card">
            <h3>Total Users</h3>
            <p>{{ totalUsers }}</p>
          </div>
        </div>
        <!-- <div class="listed-properties">
          <h2>Listed Properties</h2>
          <div class="property-list">
            <div class="property-card" v-for="property in listedProperties" :key="property.id">
              <img :src="property.image" alt="Property Image" />
              <div>
                <h3>{{ property.name }}</h3>
                <p>Location: {{ property.location }}</p>
                <p>Price: ₹{{ property.price }} per day</p>
              </div>
            </div>
          </div>
        </div> -->
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  
  // Mock data for now
  const totalListed = ref(10);
  const totalBooked = ref(5);
  const totalUsers = ref(25);
  
  // const listedProperties = ref([
  // {
  //       id: 1,
  //       name: "Green Shores",
  //       location: "Nagaon, Alibaug",
  //       price: 2500,
  //       image: "https://www.villabalisale.com/uploads/images/property/2023-04-26-property-6448915ee5dca.jpg"
  //     },
  //     {
  //       id: 2,
  //       name: "LakeView Inn",
  //       location: "Palghar",
  //       price: 1500,
  //       image: "https://i.pinimg.com/originals/11/4c/e9/114ce94d782662858157cbe701ed5ab3.jpg"
  //     },
  // ]);
  


  </script>
  
  <style scoped>
  .admin-dashboard {
    display: flex;
    position: relative;
  }
  
  .admin-dashboard-2{
    display: flex;
  }
  
  .sidebar {
  width: 20%;
  background-color: #f8f9fa;
  padding: 2rem;
  position: relative;
  box-shadow: rgba(3, 102, 214, 0.3) 0px 0px 0px 3px;
  transition: width 0.3s;
  border-radius: 15px;
  display: flex;
  align-items: center;
  flex-direction: column;
}
  
  .sidebar h2 {
    font-size: 2rem;
    color: #333;
    margin-bottom: 2rem;
    background: linear-gradient(to top left, #000080, #00bfff);
    -webkit-background-clip: text;
    color: transparent;
  }
  
  .sidebar ul {
    list-style-type: none;
    padding: 0;
  
  }
  .sidebar ul li:hover {
  transform: scale(1.1);
  }
  
  .sidebar ul li {
    margin-bottom: 1.5rem;
    font-size: 2.5rem;
  }
  
  .sidebar ul li a {
    text-decoration: none;
    color: #333;
    font-size: 1.5rem;
  }
  .sidebar ul li a:hover {
  transform: scale(1.1);
}
  
  .main-content {
    width: 80%;
    padding: 2rem;
    height: 70vh;
    overflow-y: scroll;
  }
  
  .main-content h1 {
    font-size: 2rem;
    margin-bottom: 2rem;
    background: linear-gradient(to top left, #000080, #00bfff);
    -webkit-background-clip: text;
    color: transparent;
  }
  .main-content {
    width: 80%;
    padding: 2rem;
    background-color: #fff;
  }
  
  .main-content h1 {
    font-size: 2rem;
    margin-bottom: 2rem;
  }
  
  .stats {
    display: flex;
    gap: 2rem;
  }
  
  .card {
    flex: 1;
    padding: 1rem;
    background-color: #e6f7ff;
    border-radius: 8px;
    text-align: center;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  .card h3 {
    font-size: 1.2rem;
    color: #007bff;
  }
  
  .card p {
    font-size: 1.5rem;
    color: #333;
  }
  
  .listed-properties {
    margin-top: 3rem;
  }
  
  .property-list {
    display: flex;
    gap: 1.5rem;
    flex-wrap: wrap;
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
  /* Header */
  .header {
    
    width: 100%;
    height: 10vh;
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
}

.left {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
}

.left img {
    size: 80%;
    margin-right: 10px;
}

.home-link {
    text-decoration: none;
    font-size: 1.2rem;
    color: rgb(102, 101, 101);
}

.home-link:hover {
    color: rgb(102, 101, 101);
    background: linear-gradient(to top left, #000080, #00bfff);
    -webkit-background-clip: text;
    color: transparent;
    transform: scale(1.1);
    cursor: pointer;
}

  </style>
  
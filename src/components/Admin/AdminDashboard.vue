<script setup>
  import axios from 'axios';
import { onMounted, ref } from 'vue';
  
const totalListed = ref([]);
  const totalBooked = ref([]);
  const totalUsers = ref([]);
  const loggeduser = ref({});
  
   onMounted(async()=>{
    const userData = await axios.get("http://localhost:8080/tenants/getAllTenants")
    totalUsers.value=userData.data;

    const orderData = await axios.get("http://localhost:8080/leases/getAllLeases")
    totalBooked.value=orderData.data;

    const unitData = await axios.get("http://localhost:8080/units/getAllUnits")
    totalListed.value=unitData.data;

     loggeduser.value = JSON.parse(sessionStorage.getItem("tenant"));

   })
  
  
  </script>
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
        <h1>Welcome, {{ loggeduser.firstName }}{{' '}}{{ loggeduser.lastName }}</h1>
        <div class="stats">
          <div class="card">
            <h3 style="font-size: 1.5rem;">Total Listed Properties</h3>
            <p style="font-size: 2rem;">{{ totalListed.length }}</p>
          </div>
          <div class="card">
            <h3 style="font-size: 1.5rem;">Total Booked Properties</h3>
            <p style="font-size: 2rem;">{{ totalBooked.length }}</p>
          </div>
          <div class="card">
            <h3 style="font-size: 1.5rem;">Total Users</h3>
            <p style="font-size: 2rem;">{{ totalUsers.length }}</p>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  
  
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
    justify-content: center;
    align-items: center;
    height: 60vh;
  }
  
  .card {
    flex: 1;
    padding: 1rem;
    background-color: #e6f7ff;
    border-radius: 8px;
    width: 20vw;
    height: 20vh;
    text-align: center;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
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
  
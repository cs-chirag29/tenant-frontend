<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import {useToast} from 'vue-toast-notification';

const toast = useToast();
const units = ref([]);

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8080/units/getAllUnits');
    units.value = response.data;
  } catch (error) {
    console.error('Error fetching units:', error);
  }
});


const deleteUnit = async (unitId) => {
  try {
    await axios.delete(`http://localhost:8080/units/${unitId}`);
    units.value = units.value.filter(unit => unit.unitId !== unitId);
    toast.success("Unit Removed  Successfully")
  } catch (error) {
    toast.success("Error while removing unit")
  }
};
</script>

  
<template>
  <div class="admin-dashboard">
    <div class="header">
      <div class="left" style="position: absolute; left: 5%; right: 2%;">
        <img width="40" height="40" src="https://img.icons8.com/ios-filled/50/228BE6/long-arrow-left.png" alt="long-arrow-left"/>
        <router-link to="/admin" class="home-link">Back</router-link>
      </div>
    </div>

    <div class="admin-dashboard-2">
      <div class="sidebar">
        <h2>Admin Menu</h2>
        <ul>
          <li><router-link to="/admin/manage-properties">Manage Properties</router-link></li>
          <li><router-link to="/admin/manage-users">Manage Users</router-link></li>
          <li><router-link to="/admin/addProperty">Add New Properties</router-link></li>
        </ul>
      </div>

      <div class="main-content">
        <h1>Properties Details</h1>
    
        <div>
          <div class="listed-properties" v-for="unit in units" :key="unit.unitId">
            <div>
              <img :src="unit?.unitImage" style="width: 5rem; border-radius: 5px; margin-top: 0.15rem;"/>
            </div>
            <h3>{{ unit?.property?.propertyName }} </h3>
            <h3>{{ unit?.property?.address }}</h3>
            <h3>{{ unit?.property?.city }}</h3>
            <h3>{{ unit?.rentAmount }}</h3>
            <h3>{{ unit?.status }}</h3>
            <button 
              :id="`delete-button-${unit.unitId}`"
              @click="deleteUnit(unit.unitId)"
            >
              <img width="30" height="30" src="https://img.icons8.com/ios-glyphs/30/FA5252/filled-trash.png" alt="filled-trash"/>
            </button>
            
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

  
  <style scoped>
  .admin-dashboard {
    display: flex;
    position: relative;
    width: 100%;
    flex-direction: column;
  }

  button {
    cursor: pointer;
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
    display: flex;
    align-items: center;
    justify-content: space-around;
    box-shadow: rgba(3, 102, 214, 0.3) 0px 0px 0px 3px;
    border-radius: 10px;
  }
  
  .property-list {
    display: flex;
    gap: 1.5rem;
    flex-wrap: wrap;
  }
  button {
    background: transparent;
    border: none;
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
.update-button {
  width: 5rem;
  height: 2.2rem;
  background-color: green;
  color: white;
  cursor: pointer;
  border-radius: 8px;
}

select{
  font-size: 1rem;
  padding: 0.3rem;
  border-radius: 5px;
  
}

  </style>
  
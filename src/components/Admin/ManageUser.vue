<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import { useToast } from 'vue-toast-notification';

const toast = useToast();
const users = ref([]);
const superAdminId = ref(null);
const currentUser = ref(null);

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8080/tenants/getAllTenants');
    users.value = response.data;
  
    const adminUser = users.value.find(user => user.role === 'super-admin');
    if (adminUser) {
      superAdminId.value = adminUser.tenantId;
    }

    const currentUserData = sessionStorage.getItem("tenant");
    if (currentUserData) {
      currentUser.value = JSON.parse(currentUserData);
    }
  } catch (error) {
    console.error('Error fetching users:', error);
    toast.error('Error fetching users: ' + error.message);
  }
});

// const handleRoleChange = (user) => {
//   if (user.tenantId === superAdminId.value) {
//     toast.error("You cannot change the role of the super admin.");
//     return;
//   }

//   if (user.role === 'super-admin') {
//     toast.error("You cannot assign the super-admin role.");
//     return;
//   }

//   if (user.role === 'admin' && currentUser.value.role === 'admin' && currentUser.value.tenantId === user.tenantId) {
//     toast.error("You cannot change your own role.");
//     return;
//   }

//   if (user.role === 'admin' && currentUser.value.role === 'admin') {
//     toast.error("You cannot change the role of another admin.");
//     return;
//   }
// };

const updateRole = async (user) => {
  const newuser = users.value.find(u=>u.tenantId===user.tenantId);
  if (newuser.tenantId === superAdminId.value) {
    toast.error("You cannot change the role of the super admin.");
    return;
  }

  if (newuser.role === 'super-admin') {
    toast.error("You cannot assign the super-admin role.");
    return;
  }

  if (newuser.role === 'admin' && currentUser.value.role === 'admin' && currentUser.value.tenantId === newuser.tenantId) {
    toast.error("You cannot change your own role.");
    return;
  }

  if (newuser.role === 'user' && currentUser.value.role === 'admin') {
    toast.error("You cannot change the role of another admin.");
    return;
  }
  

  try {
    await axios.put(`http://localhost:8080/tenants/${user.tenantId}`, user);
    toast.success("User role changed successfully");
  } catch (error) {
    toast.error('Error updating role: ' + error.message);
  }
};

const deleteUser = async (userId) => {
  if (userId === superAdminId.value) {
    toast.error("You cannot delete the super admin.");
    return;
  }

  const userToDelete = users.value.find(u => u.tenantId === userId);
  if (userToDelete) {
    if (currentUser.value.role === 'admin' && userToDelete.role === 'admin') {
      toast.error("Admins cannot delete each other.");
      return;
    }
    if (currentUser.value.role === 'admin' && currentUser.value.tenantId === userId) {
      toast.error("Admins cannot delete themselves.");
      return;
    }
    if (currentUser.value.role === 'user') {
      toast.error("Users cannot delete other users or admins.");
      return;
    }
  }

  try {
    await axios.delete(`http://localhost:8080/api/user-logins/${userId}`);
    await axios.delete(`http://localhost:8080/tenants/${userId}`);
    users.value = users.value.filter(user => user.tenantId !== userId);
    toast.success("User deleted successfully");
  } catch (error) {
    toast.error('Error deleting user: ' + error.message);
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
        <h1>User Details</h1>
    
        <div>
          <div class="listed-properties" v-for="user in users" :key="user.tenantId">
            <div>
              <h3>{{ user.firstName }} {{ user.lastName }}</h3>
            </div>
            <h3>{{ user.email }}</h3>
            <h3>{{ user.phoneNumber }}</h3>
            <select 
              v-model="user.role" 
           
              :disabled="user.tenantId === superAdminId.value"
            >
              <option value="super-admin">Super Admin</option>
              <option value="user">User</option>
              <option value="admin">Admin</option>
            </select>
            <button 
              :id="`delete-button-${user.tenantId}`"
              @click="deleteUser(user.tenantId)"
              :disabled="user.tenantId === superAdminId.value"
              style="cursor: pointer;"
            >
              <img width="30" height="30" src="https://img.icons8.com/ios-glyphs/30/FA5252/filled-trash.png" alt="filled-trash"/>
            </button>
            <button 
              class="update-button"
              :id="`update-button-${user.tenantId}`" 
              @click="updateRole(user)"
            >
              Update
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
  
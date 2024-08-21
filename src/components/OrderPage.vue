<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useRoute, } from 'vue-router';

const route = useRoute();
// const router = useRouter();

const order = ref({});

onMounted(async() => {
  const id = route.params.id;
  try {
    const response = await axios.get(`http://localhost:8080/leases/getLeaseById/${id}`);
    order.value = response.data;
  } catch (error) {
    console.error('Error fetching lease details:', error);
  }
});
const calculateTotalDays = (startDate, endDate) => {
  const start = new Date(startDate);
  const end = new Date(endDate);
  const timeDiff = end - start;
  const dayDiff = Math.ceil(timeDiff / (1000 * 60 * 60 * 24)); 
  return dayDiff+1;
};

</script>
<template>
    <!-- <div v-if="loading">
      <Loader />
    </div> -->
    <div class="header">
    <div class="left" style="position: absolute; left: 2%; top: 2%; display: flex; align-items: center; justify-content: center;">
      <img width="40" height="40" src="https://img.icons8.com/ios-filled/50/228BE6/long-arrow-left.png" alt="long-arrow-left"/>
      <router-link to="/profile" class="home-link" style="font-size: 1.3rem; text-decoration: none; margin-left: 0.5rem; color: black;">Back</router-link>
    </div>
  </div>
    <div>
      <div class="orderdetails">
        <div class="orderdetails-heading">
          <h1>BOOKING DETAILS</h1>
        </div>
        <div class="orderdetails-container-1">
          <h1>BOOKING INFO</h1>
          <div class="orderdetails-detail-page">
            <form class="orderdetails-detail-form">
              <div class="orderdetails-group">
                <label for="securityDeposit">Security Deposit</label>
                <h2>Rs {{order.securityDeposit  }}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="start-date">Contract Start Date</label>
                <h2>{{ order.startDate}}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="end-date">Contract End Date</label>
                <h2>{{ order.endDate }}</h2>
              </div>
            </form>
          </div>
  
          <h1>YOUR DETAILS</h1>
          <div class="orderdetails-page">
            <form class="orderdetails-form">
              <div class="orderdetails-group">
                <label for="first-name">First Name</label>
                <h2>{{order?.tenant?.firstName }}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="last-name">Last Name</label>
                <h2>{{ order?.tenant?.lastName }}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="address">Address</label>
                <h2>{{ order?.tenant?.address }}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="city">City</label>
                <h2>{{order?.tenant?.city}}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="zipCode">Zipcode</label>
                <h2>{{ order?.tenant?.zipCode }}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="phoneNumber">Phone Number</label>
                <h2>{{ order?.tenant?.phoneNumber }}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="email">Email</label>
                <h2>{{order?.tenant?.email}}</h2>
              </div>
            </form>
          </div>

        </div>
  
        <div class="orderdetails-container-2">
          <h1>HOUSE DETAILS</h1>
          <img :src="order?.unit?.unitImage" alt="house" />
          <div class="orderdetails-container-2-box">
            <div class="orderdetails-container-2-box-2">
              <div class="orderdetails-calculator">
                <h3>House Model</h3>
                <h3>{{ order?.unit?.property?.propertyName }}</h3>
              </div>
              <div class="orderdetails-calculator">
                <h3>Security Deposit</h3>
                <h3>Rs {{ order?.securityDeposit }}</h3>
              </div>
              <div class="orderdetails-calculator">
                <h3>Rent Amount</h3>
                <h3>Rs {{ order?.unit?.rentAmount }} /day</h3>
              </div>
              <div class="orderdetails-calculator">
                <h3>Booking From :</h3>
                <h3>{{order.startDate}}</h3>
              </div>
              <div class="orderdetails-calculator">
                <h3>Booking To :</h3>
                <h3>{{ order.endDate }}</h3>
              </div>
              <div class="orderdetails-calculator">
                <h3>Total Days</h3>
                <h3>{{ calculateTotalDays(order.startDate, order.endDate)}} </h3>
              </div>
              <div class="orderdetails-calculator-total">
                <h3>Total Amount</h3>
                <h3>Rs {{ calculateTotalDays(order.startDate, order.endDate) * order.unit?.rentAmount }}</h3>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>

  
  <style scoped src="../css/OrderPage.css">

  </style>
  
<script setup>
import axios from 'axios';
// import { computed } from 'vue';
import { ref, onMounted, computed,  } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import {useToast} from 'vue-toast-notification';


const leaseInfo = ref({
securityDeposit: '',
startDate: '',
endDate: '',
});

const userDetails = ref({
tenantId:'',
firstName: '',
lastName: '',
address: '',
city: '',
zipCode: '',
phoneNumber: '',
email: '',
});

const unit = ref({});

const route = useRoute();
const router = useRouter();
const id = route.params.id;

const toast = useToast();
onMounted(async() => {

  if (!route.query?.fromHouseDetail) {
    console.log("Received route state:", route.query);
    router.push('/');
  }
const userData = JSON.parse(sessionStorage.getItem('tenant') || '{}');


userDetails.value = { ...userDetails.value, ...userData };
const tenantId=userDetails.value.tenantId;
console.log(tenantId);
console.log(userDetails.value)

try {
  const response = await axios.get(`http://localhost:8080/units/${id}`)
  unit.value=response.data;
  console.log(unit.value);
} catch (error) {
  console.log(error);
}

});

// const ratePerDay = computed(() => unit.value.rentAmount / 30);

const calculateTotalDays = (startDate, endDate) => {
  const start = new Date(startDate);
  const end = new Date(endDate);
  const timeDiff = end - start;
  const dayDiff = Math.ceil(timeDiff / (1000 * 60 * 60 * 24)); 
  return dayDiff+1;
};

const handleBooking= async()=>{
  if (!leaseInfo.value.securityDeposit || !leaseInfo.value.startDate || !leaseInfo.value.endDate) {
    toast.error("Please fill in all lease details");
    return;
  }
  try {  

   console.log({
      tenantId: userDetails.value.tenantId,
      securityDeposit: leaseInfo.value.securityDeposit,
      startDate: leaseInfo.value.startDate,
      endDate: leaseInfo.value.endDate,
      unitId: unit.value.unitId,
    });
    const response = await axios.post("http://localhost:8080/leases",{
      tenantId: userDetails.value.tenantId,
      securityDeposit: leaseInfo.value.securityDeposit,
      startDate: leaseInfo.value.startDate,
      endDate: leaseInfo.value.endDate,
      unitId: unit.value.unitId,
    });
    console.log(response.data);
    toast.success("Booking Successful");
    router.push({ name: 'SuccessPage', query: { fromBookingPage: true } });
  } catch (error) {
    toast.error("Unable to accept the booking, Try Again!!")
  }
  
}
const totalAmount = computed(() => {
  if (unit.value.rentAmount && leaseInfo.value.startDate && leaseInfo.value.endDate) {
    const days = calculateTotalDays(leaseInfo.value.startDate, leaseInfo.value.endDate);
    return days * unit.value.rentAmount;
  }
  return 0;
});
</script>
<template>
    <div>
      <!-- <div v-if="loading" class="loader-container">
        <Loader />
      </div> -->
      <div >
        <div class="booking">
          <div class="booking-container-1">
            <h1>LEASE INFO</h1>
            <div class="booking-detail-page">
              <form class="booking-detail-form">
                <div class="booking-detail-group">
                  <label for="Security">Security Deposit</label>
                  <input
                    type="text"
                    id="security"
                    placeholder="Enter Security Deposit Amount"
                     v-model="leaseInfo.securityDeposit"
                  />
                </div>
  
                <div class="book-group">
                  <label for="start-date">Contract Start Date</label>
                  <input
                    type="date"
                    id="start-date"
                     v-model="leaseInfo.startDate"
                  />
                </div>
  
                <div class="book-group">
                  <label for="end-date">Contract End Date</label>
                  <input
                    type="date"
                    id="end-date"
                     v-model="leaseInfo.endDate"
                  />
                </div>
              </form>
            </div>
  
            <h1>YOUR DETAILS</h1>
            <div class="booking-page">
              <form class="booking-form">
                <div class="book-group">
                  <label for="first-name">First Name</label>
                  <input
                    type="text"
                    id="first-name"
                    v-model="userDetails.firstName"
                    placeholder="Enter Your First Name"
                  />
                </div>
                <div class="book-group">
                  <label for="last-name">Last Name</label>
                  <input
                    type="text"
                    id="last-name"
                   v-model="userDetails.lastName"
                    placeholder="Enter Your Last Name"
                  />
                </div>
                <div class="book-group">
                  <label for="address">Address</label>
                  <input
                    type="text"
                    id="address"
                    v-model="userDetails.address"
                    placeholder="Enter Address"
                  />
                </div>
                
                <div class="book-group">
                  <label for="city">City</label>
                  <input
                    type="text"
                    id="city"
                   v-model="userDetails.city"
                    placeholder="Enter City"
                  />
                </div>
                <div class="book-group">
                  <label for="country">ZipCode</label>
                  <input
                    type="text"
                    id="zipcode"
                    v-model="userDetails.zipCode"
                    placeholder="Enter Zipcode"
                  />
                </div>
                <div class="book-group">
                  <label for="phone">Phone</label>
                  <input
                    type="number"
                    id="phone"
                   v-model="userDetails.phoneNumber"
                    placeholder="Enter Phone Number"
                  />
                </div>
                <div class="book-group">
                  <label for="email">Email</label>
                  <input
                    type="email"
                    id="email"
                    v-model="userDetails.email"
                    placeholder="Enter Email"
                  />
                </div>
              </form>
            </div>
  
            <div class="add-extra-page">
              <div class="booking-last">
                <div class="extra-group">
                  <div class="extra-group-1">
                    <input type="checkbox" id="agreementCheckbox" required />
                    <label for="agreementCheckbox">I agree with the Terms & Conditions and Privacy Policy</label>
                  </div>
                </div>
                <button @click="handleBooking" >Book Now</button>
              </div>
            </div>
          </div>
  
          <div class="booking-container-2">
            <h1>BOOKING DETAILS</h1>
            <img :src="unit.unitImage" alt="house" />
            <div class="booking-container-2-box">
              <h2 v-if="unit.property" style="margin-top:-2rem; margin-left: 10rem;">{{ unit?.property?.propertyName }}</h2>
              <h3 style="margin-top: -2rem;">Contract Start Date: {{ leaseInfo.startDate  }}</h3>
              <h3 style="margin-top: -1rem; margin-bottom: 2rem;">Contract End Date: {{ leaseInfo.endDate }}</h3>
              <h2 style="margin-top: -2rem;">Rent: Rs {{ unit.rentAmount  }}/month </h2>
              <h2 style="margin-top: -2rem;">Total: Rs {{totalAmount }}/month </h2>
              <span style="margin-top: -2rem;">(Excluding Security Deposit)</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
 
  
  <style scoped src="../css/BookingPage.css">
 
  </style>
  
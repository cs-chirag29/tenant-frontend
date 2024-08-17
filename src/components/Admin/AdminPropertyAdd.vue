<script setup>
import axios from 'axios';
import { ref } from 'vue';
import {useToast} from 'vue-toast-notification';


const property = ref({
    propertyName: '',
    address: '',
    city: '',
    zipCode: '',
    state: '',
    description:''
});

const unit = ref({
  unitNumber: '',
  unitType: '', 
  unitImage: '', 
  rentAmount: '',
  status: ''
});


const toast = useToast();
// const lease = ref({
//   leaseStartDate: '',
//   leaseEndDate: '',
//   securityDeposit: '' 
// });


const statuses = ['Available', 'Not Available'];

const onSubmit = async () => {
  console.log('Submitting data:', property.value);
  
  try {
    const propertyResponse = await axios.post('http://localhost:8080/api/properties/', property.value);
    const propertyId = propertyResponse.data.propertyId;

    console.log(unit.value);
    const unitData = {
    ...unit.value,
    propertyId: propertyId,
    rentAmount: parseFloat(unit.value.rentAmount)
  };
  console.log(unitData)
  
    const unitResponse = await axios.post('http://localhost:8080/api/units', unitData);

    console.log(unitResponse.unitId);
    toast.success("Data Added successfully");
  
    property.value = {
            propertyName: '',
            address: '',
            city: '',
            zipCode: '',
            state: '',
            description:'',
        };
        unit.value = {
            unitNumber: '',
            unitType: '', 
            unitImage: '', 
            rentAmount: '',
            status: ''
        };

  } catch (error) {
    console.error('Error submitting form:', error);
  }
};
</script>

<template>
  <div class="admin-add">
    <form @submit.prevent="onSubmit" novalidate>
     
      <div>
        <h1>Add Property Details</h1>
        <div class="container">
          <div class="content">
            <label for="propertyName">Property Name:</label>
            <input v-model="property.propertyName" id="propertyName" type="text" required />
          </div>
          <div class="content">
            <label for="propertyAddress">Address:</label>
            <input v-model="property.address" id="propertyAddress" type="text" required />
          </div>
          <div class="content">
            <label for="propertyCity">City:</label>
            <input v-model="property.city" id="propertyCity" type="text" required />
          </div>
          <div class="content">
            <label for="propertyZipcode">Zip Code:</label>
            <input v-model="property.zipCode" id="propertyZipcode" type="text" required />
          </div>
          <div class="content">
            <label for="propertyState">State:</label>
            <input v-model="property.state" id="propertyState" type="text" required />
          </div>

          <div class="content">
            <label for="propertyDescription">Desciption:</label>
            <input v-model="property.description" id="propertyDescription" type="text" required />
          </div>


        </div>
      </div>

       <!-- Unit Details Section -->
       <div>
        <h1>Add Unit Details</h1>
        <div class="container">
          <div class="content">
            <label for="unitNumber">Unit Number:</label>
            <input v-model="unit.unitNumber" id="unitNumber" type="text" required />
          </div>
          <div class="content">
            <label for="unitType">Unit Type:</label>
            <input v-model="unit.unitType" id="unitType" type="text" required />
          </div>
          <div class="content">
            <label for="unitImage">Image Url:</label>
            <input v-model="unit.unitImage" id="unitImage" type="text" required />
          </div>
          <div class="content">
            <label for="rentAmount">Rent Amount:</label>
            <input v-model="unit.rentAmount" id="rentAmount" type="number" step="0.01" required />
          </div>
          <div class="content">
            <label for="status">Status:</label>
            <select v-model="unit.status" id="status" required>
              <option disabled value="">Select status</option>
              <option v-for="status in statuses" :key="status" :value="status">{{ status }}</option>
            </select>
          </div>
        </div>
      </div>

      <!-- Lease Details Section -->
      <!-- <div>
        <h1>Add Lease Details</h1>
        <div class="container">
          <div class="content">
            <label for="leaseStartDate">Lease Start Date:</label>
            <input v-model="lease.leaseStartDate" id="leaseStartDate" type="date" required />
          </div>
          <div class="content">
            <label for="leaseEndDate">Lease End Date:</label>
            <input v-model="lease.leaseEndDate" id="leaseEndDate" type="date" required />
          </div>
          <div class="content">
            <label for="rentAmount">Rent Amount:</label>
            <input v-model="lease.rentAmount" id="rentAmount" type="number" step="0.01" required />
          </div>
        </div>
      </div> -->


      <button type="submit">Add Lease</button>
    </form>
  </div>
</template>

<style scoped src="../Admin/AdminPropertyAdd.css"></style>

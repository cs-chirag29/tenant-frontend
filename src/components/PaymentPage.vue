<template>
  <div class="paymentContainer">
    <form class="paymentForm" @submit.prevent="submitHandler">
      <h1>Payment Details</h1>
      <div class="payment-box">
        <img width="30" height="30" src="https://img.icons8.com/ios/50/939393/bank-card-back-side.png" alt="bank-card-back-side" />
        <input
          v-model="cardNumber"
          type="text"
          class="paymentInput"
          placeholder="4242 4242 4242 4242"
        />
      </div>
      <div class="payment-box">
        <img width="30" height="30" src="https://img.icons8.com/material-outlined/24/939393/calendar.png" alt="calendar" />
        <input
          v-model="expiryDate"
          type="text"
          class="paymentInput"
          placeholder="MM/YY"
        />
      </div>
      <div class="payment-box">
        <img width="30" height="30" src="https://img.icons8.com/material-outlined/24/939393/key--v1.png" alt="key--v1" />
        <input
          v-model="cvc"
          type="text"
          class="paymentInput"
          placeholder="CVC"
        />
      </div>

      <input
        type="submit"
        :value="`Pay - ₹${totalPrice}`"
        class="paymentFormBtn"
        :disabled="isProcessing"
      />
    </form>
  </div>
</template>


<!-- <script setup>
import { ref } from 'vue';
import axios from 'axios';

const cardNumber = ref('');
const expiryDate = ref('');
const cvc = ref('');
const totalPrice = ref(1000); 
const isProcessing = ref(false);

const submitHandler = async () => {
  isProcessing.value = true;

  try {
    const paymentData = {
      amount: totalPrice.value,
    };

    const config = {
      headers: {
        'Content-Type': 'application/json',
      },
    };

    const { data } = await axios.post('/payment/process', paymentData, config);

    alert('Payment successful!');
  } catch (error) {
    alert('Error processing payment: ' + error.message);
  } finally {
    isProcessing.value = false;
  }

};
</script> -->

<style scoped src="../css/Payment.css"></style>

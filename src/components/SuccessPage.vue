<template>
    <div class="successful">
      <div class="booking-success">
        <img width="70" height="70" src="https://img.icons8.com/fluency/48/ok--v1.png" alt="ok--v1" />
        <h1>Booking Confirmed!</h1>
        <p>Payment successful and booking confirmed!</p>
        <p>Thank you for choosing our services!</p>
        <router-link to="/">Go back to Home</router-link>
      </div>
    </div>
  </template>
  
  <script setup>
import { onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';

const route = useRoute();
const router = useRouter();

onMounted(() => {
  
  if (!route.query?.fromBookingPage) {
    router.push('/');
  }
});
</script>
  
  <style scoped src="../css/SuccessPage.css"></style>
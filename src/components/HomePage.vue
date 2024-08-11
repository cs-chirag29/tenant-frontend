<script setup>
import HeaderPage from './HeaderPage.vue';
import HomeOptions from './HomeOptions.vue';
import ReviewsPage from './ReviewsPage.vue';
import HelpPage from "./HelpPage.vue";

</script>
<template>
  <HeaderPage></HeaderPage>
 <div class='home-page'>
    <div class='home-left'>
      <span><h1>Looking to</h1></span>
      <h1>Rent a House</h1>
      <p>
        Explore Our Selection Of Beautiful Homes And Enjoy A Hassle-free Rental Experience. Whether It's A Business Relocation Or A New Home For Your Family, We've Got The Perfect Place For You.
      </p>
      <div class='home-page-booking'>
        <form class="home-reservation-form" >
          <div class="home-form-group">
            <label for="location">Location:</label>
            <input type="text" id="location" name="location" placeholder="Enter location" required />
          </div>
  
          <div class="home-form-group">
            <label for="start-date">Start Date:</label>
            <input type="date" id="start-date" name="start-date" required />
          </div>
  
          <div class="home-form-group">
            <label for="end-date">End Date:</label>
            <input type="date" id="end-date" name="end-date" required />
          </div>
          <button type="submit">Book Now</button>
        </form>
      </div>
    </div>
    <div class="home-right">
      <img src="https://www.thespruce.com/thmb/mEibsli-wVSbIBN_lo1maoyaRS8=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Litchfield_BeresfordHill_025-5b89787fc9e77c00258aa53c.jpg" alt='House' />
    </div>
  </div>

  <HomeOptions></HomeOptions>
  <ReviewsPage></ReviewsPage>
  <HelpPage></HelpPage>
</template>

<style scoped src="../css/HomePage.css"></style>
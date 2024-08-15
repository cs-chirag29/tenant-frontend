<template>
    <div class="header">
      <div class="left">
        <img
          width="64"
          height="64"
          src="https://img.icons8.com/arcade/64/cottage.png"
          alt="cottage"
        />
      </div>
      <div class="mid">
        <router-link to="/">Home</router-link>
        <router-link to="#houses" @click.prevent="scrollToSection('#houses')">Houses</router-link>
        <router-link to="#reviews" @click.prevent="scrollToSection('#reviews')">Reviews</router-link>
        <router-link to="#help" @click.prevent="scrollToSection('#help')">Help</router-link>
      </div>
      <div class="right">
        <router-link to="/signup">
          <button>Sign Up</button>
        </router-link>
        <router-link to="/login">
          <button>Sign In</button>
        </router-link>
        <router-link to="/profile">
          <button>Profile</button>
        </router-link>
      </div>
    </div>
  </template>
  
  <script setup>
  import { nextTick } from 'vue';
  
  const scrollToSection = (sectionId) => {
    nextTick(() => {
      const element = document.querySelector(sectionId);
      if (element) {
        element.scrollIntoView({ behavior: 'smooth' });
      }
    });
  };
  </script>

<style scoped src="../css/Header.css"></style>
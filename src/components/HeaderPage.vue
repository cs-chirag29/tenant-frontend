<script setup>
import { nextTick, onMounted, ref } from 'vue';

const isLoggedIn = ref(false);

onMounted(() => {
  const tenantData = sessionStorage.getItem('tenant');
  isLoggedIn.value = !!tenantData;
});

const scrollToSection = (sectionId) => {
  nextTick(() => {
    const element = document.querySelector(sectionId);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  });
};
</script>

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
      <template v-if="isLoggedIn">
        <router-link to="/profile">
          <button><img style="width: 100%; margin-top: 1rem;" src="https://cdn1.iconfinder.com/data/icons/mix-color-3/502/Untitled-7-1024.png" alt="Profile Image" class="profile-image"/></button>
        </router-link>
      </template>
      <template v-else>
        <router-link to="/signup">
          <button>Sign Up</button>
        </router-link>
        <router-link to="/login">
          <button>Sign In</button>
        </router-link>
      </template>
    </div>
  </div>
</template>

<style scoped src="../css/Header.css"></style>

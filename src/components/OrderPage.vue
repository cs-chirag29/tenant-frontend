<template>
    <!-- <div v-if="loading">
      <Loader />
    </div> -->
    <div>
      <div class="orderdetails">
        <div class="orderdetails-heading">
          <h1>ORDER DETAILS</h1>
        </div>
        <div class="orderdetails-container-1">
          <h1>ORDER INFO</h1>
          <div class="orderdetails-detail-page">
            <form class="orderdetails-detail-form">
              <div class="orderdetails-group">
                <label for="location">Location</label>
                <h2>{{  }}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="pickup-date">Pickup Date</label>
                <h2>{{ }}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="return-date">Return Date</label>
                <h2>{{  }}</h2>
              </div>
            </form>
          </div>
  
          <h1>YOUR DETAILS</h1>
          <div class="orderdetails-page">
            <form class="orderdetails-form">
              <div class="orderdetails-group">
                <label for="first-name">First Name</label>
                <h2>{{ }}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="last-name">Last Name</label>
                <h2>{{  }}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="address-1">Address 1</label>
                <h2>{{  }}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="address-2">Address 2</label>
                <h2>{{  }}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="city">City</label>
                <h2>{{}}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="country">Country</label>
                <h2>{{ }}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="phone">Phone</label>
                <h2>{{  }}</h2>
              </div>
              <div class="orderdetails-group">
                <label for="email">Email</label>
                <h2>{{}}</h2>
              </div>
            </form>
          </div>
  
          <h1>ADDED EXTRAS</h1>
          <div class="orderdetails-extra-page">
            <form class="orderdetails-extra-form">
              <div  class="orderdetails-extra-group">
                <div class="orderdetails-extra-group-1">
                  <label><img width="30" height="30" src="https://img.icons8.com/color/48/checked-2.png" alt="checked-2" /> Child Seat, Rs250/day</label>
                </div>
                <p>Add a child seat for your little one. We offer comfortable and safe child seats for a pleasant journey.</p>
              </div>
              <div  class="orderdetails-extra-group">
                <div class="orderdetails-extra-group-1">
                  <label><img width="30" height="30" src="https://img.icons8.com/color/48/checked-2.png" alt="checked-2" /> GPS Navigation, Rs550/day</label>
                </div>
                <p>When renting a car, you are often driving in new and unfamiliar areas. Choose a guaranteed GPS to never get lost, and to be directed through the quickest or most scenic routes.</p>
              </div>
              <div  class="orderdetails-extra-group">
                <div class="orderdetails-extra-group-1">
                  <label><img width="30" height="30" src="https://img.icons8.com/color/48/checked-2.png" alt="checked-2" /> Extended Insurance, Rs2050/day</label>
                </div>
                <p>We offer an extended insurance plan that includes additional services at no cost in the event of breakdowns, loss of keys, and similar incidents.</p>
              </div>
              <div  class="orderdetails-extra-group">
                <div class="orderdetails-extra-group-1">
                  <label><img width="30" height="30" src="https://img.icons8.com/color/48/checked-2.png" alt="checked-2" /> Wifi Hotspot, Rs100/day</label>
                </div>
                <p>The Wifi Hotspot allows up to 5 devices to connect to the Hotspot within your rental car. Our devices also come with a language translator, maps, local guides, and more.</p>
              </div>
              <div class="NoExtra-orderdetails">
                <p>No extra added</p>
              </div>
            </form>
          </div>
        </div>
  
        <div class="orderdetails-container-2">
          <h1>CAR DETAILS</h1>
          <img src="" alt="car" />
          <div class="orderdetails-container-2-box">
            <div class="orderdetails-container-2-box-2">
              <div class="orderdetails-calculator">
                <h3>Car Model</h3>
                <h3></h3>
              </div>
              <div class="orderdetails-calculator">
                <h3>Subtotal</h3>
                <h3>Rs /day</h3>
              </div>
              <div class="orderdetails-calculator">
                <h3>Pickup</h3>
                <h3></h3>
              </div>
              <div class="orderdetails-calculator">
                <h3>Return</h3>
                <h3></h3>
              </div>
              <div class="orderdetails-calculator">
                <h3>Total Days</h3>
                <h3></h3>
              </div>
              <div class="orderdetails-calculator-total">
                <h3>Total Amount</h3>
                <h3>Rs </h3>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  
  </script>
  
  <style scoped src="../css/OrderPage.css">

  </style>
  
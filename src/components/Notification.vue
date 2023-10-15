<template>
    <div class="notification">
      <i :class="iconClass"></i>
      <span>{{ message }}</span>
      <span class="timestamp">{{ timestamp }}</span>
    </div>
  </template>
  
  <script>
  export default {
    name:"NouvellePatient",
    data() {
      return {
        notifs:[],
        loading: false ,
       
      };
    },
    methods: {
      

      
    }
    
  };
 

  </script>
  
  <style scoped>
  .notification {
    display: flex;
    align-items: center;
    padding: 10px;
    border-bottom: 1px solid #ccc;
  }
  
  .timestamp {
    margin-left: auto;
  }
  </style>
  
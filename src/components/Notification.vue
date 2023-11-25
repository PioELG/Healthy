<template>
  <!DOCTYPE html>
  <html>
    <head>
      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
      />
    </head>
    <body class="w3-light-grey">
    <div class="notification">
      <i :class="iconClass"></i>
      <span>{{ message }}</span>
      <span class="timestamp">{{ timestamp }}</span>
    </div>
    </body>
    </html>
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
  
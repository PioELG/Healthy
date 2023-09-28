<template>
    <!DOCTYPE html>
    <html>
    <head>
    <title>W3.CSS Template</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   
    </head>
    <body class="w3-light-grey">
    
    <!-- Top container -->
    <div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
      <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
      <span class="w3-bar-item w3-right" style="font-family: 'Pacifico', cursive;">Healthy</span>
    
    </div>
    </body>
    </html>
</template>
  
  <script>
  export default {
    name: 'Header', // Remplacez par le nom de votre composant
    data() {
      return {
        // Les données de votre composant vont ici
        mySidebar: null,
      overlayBg: null,
      };
    },
    methods: {
      // Les méthodes de votre composant vont ici
      
    },
    computed: {
      // Les propriétés calculées de votre composant vont ici
    },
    // Autres options de composant (comme "props", "watch", etc.) vont ici
  };
  </script>
  
  <style scoped>
  html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
    .w3-container-rounded {
      border-radius: 10px; /* Vous pouvez ajuster le rayon selon vos préférences */
    }
    .w3-container-rounded:hover {
      transform: translate(5px, -5px); /* Déplacez la div vers le bas et vers la droite */
      transition: transform 0.2s ease; /* Ajoutez une transition fluide */
    }
    .w3-bar w3-top w3-black w3-large
    {
        z-index:4;
    }
  </style>
  
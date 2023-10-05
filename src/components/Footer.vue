<template>
   <div class="foot">
    <p>Salut</p>
   </div>
     
   </template>
     
     <script>
   
    import keycloak from '../main.js';
   import jwtDecode from 'jwt-decode';
   
     export default {
       name: 'Footer', // Remplacez par le nom de votre composant
       
       data() {
         return {
           // Les données de votre composant vont ici
           doc:'',
           decodedToken:{},
           roles:{}
 
         };
       },
       methods: {
         // Les méthodes de votre composant vont ici
 
         
       },
       mounted(){
         // Les propriétés calculées de votre composant vont ici
         
       
       },
       // Autres options de composant (comme "props", "watch", etc.) vont ici
     };
     </script>
     
     <style scoped>
     .foot{
        background-color:black;
     }
   
     </style>
     
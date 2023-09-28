<template>
  <NouvelleMedecin v-if="doc"></NouvelleMedecin>  
  <NouvellePatient v-else></NouvellePatient>
  
</template>
  
  <script>
import NouvelleMedecin from './NouvelleMedecin.vue';
import NouvellePatient from './NouvellePatient.vue';
import keycloak from '../main.js';
  import jwtDecode from 'jwt-decode';

  export default {
    name: 'Nouvelle', // Remplacez par le nom de votre composant
    components:
    {
        NouvelleMedecin,
        NouvellePatient
    },
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
        
        if (keycloak.token) {
     this.decodedToken = jwtDecode(keycloak.token);
     this.roles = this.decodedToken.realm_access.roles;
     if((this.roles.includes('patient')))
     {
        console.log("hello patient")  ; 
        this.doc=false;
     }
     if((this.roles.includes('medecin')))
     {
          console.log("hello doc")  ;
        this.doc=true;
        console.log(this.doc)  ;
     }
   
     
       
   }
      },
    // Autres options de composant (comme "props", "watch", etc.) vont ici
  };
  </script>
  
  <style scoped>

  </style>
  
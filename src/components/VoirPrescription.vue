<template>
    <div class="w3-main" style="margin-left:300px;margin-top:43px;">

        <!-- Header -->
        <header class="w3-container" style="padding-top:22px">
          <h5><b><i class="fa fa-stethoscope"></i> Mes Prescriptions</b></h5>
        </header>
       
        <main>
          <ul class="chat-list" v-for="medicament in medicaments" :key="medicament.id">
              <li class="chat-item">
                  <div class="chat-preview" >
                      <h2>{{ medicament.nom }}</h2>
                      <p v-for="posologie in getPosologies(medicament.id)" :key="posologie.id"> 
                        {{ posologie.quantite }} {{ posologie.unite }} {{ posologie.heurePrise }}
                          <input type="checkbox" class="medication-checkbox" id="paracetamol-evening">
                      </p>
                      
                  </div>
              </li>
              
      
              <!-- Ajoutez d'autres chats ici -->
          </ul>
      </main>
       
      
        <!-- End page content -->
      </div>
   </template>
     
     <script>
    
    import keycloak from '../main.js';
    import axios from 'axios';
   
   
     export default {
       name: 'VoirPrescription', // Remplacez par le nom de votre composant
       
       data() {
         return {
           // Les données de votre composant vont ici
          medicaments:[],
          posologies:[]
 
         };
       },
       methods: {
         // Les méthodes de votre composant vont ici
         fetchMedicaments() {
  const accessToken = keycloak.token; // Remplacez par votre jeton d'accès
        
  // Définissez l'en-tête d'autorisation avec le jeton d'accès
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
    }
  };
  const id = this.$route.params.id;
  
 
 

  axios.get(`http://192.168.224.1:8080/api/medicament`, config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      
       
        this.medicaments=response.data;
      
      
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des médicaments:', error);
    }).finally(()=> this.loading = false);
    
   
},
fetchPosologie() {
  const accessToken = keycloak.token; // Remplacez par votre jeton d'accès
        
  // Définissez l'en-tête d'autorisation avec le jeton d'accès
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
    }
  };
  const id = this.$route.params.id;
  
 
 

  axios.get(`http://192.168.224.1:8080/api/posologie`, config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      
       
        this.posologies=response.data;
      
      
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des posologies:', error);
    }).finally(()=> this.loading = false);
    
   
},
getPosologies(medId) {
    return this.posologies.filter(posologie => posologie.medicament_id === medId);
  },
 
         
       },
       mounted(){
         // Les propriétés calculées de votre composant vont ici
         this.medicaments=[],
          this.posologies= [],
          this.fetchMedicaments();
           this.fetchPosologie();
       
       },
       // Autres options de composant (comme "props", "watch", etc.) vont ici
     };
     </script>
     
     <style scoped>
     html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
/* Styles de la page de chat */
.chat-list {
    list-style: none;
    padding: 0;
}

.chat-item {
    display: flex;
    align-items: center;
    border: 1px solid #ccc;
    padding: 10px;
    margin-bottom: 10px;
    border-radius: 5px;
    transition: transform 0.3s, background-color 0.3s;
    background-color: white; /* Fond blanc */
}

.chat-item:hover {
    transform: scale(1.02);
    cursor: pointer;
    background-color: rgb(88, 103, 97);
    color: rgb(0, 0, 0);
}


.chat-preview {
    flex-grow: 1;
}
     </style>
     
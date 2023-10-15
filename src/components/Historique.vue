<template>
<div class="w3-main" style="margin-left:300px;margin-top:20px;">
        <br><br>
    <div class="historique">
        
      <h4>Voici l'historique de tous les médicaments déja prescrits au patient</h4>


        <ul class="chat-list" >
             <li class="chat-item" v-for="medicament in medicaments" :key="medicament.id">
                    <div class="chat-preview" >
                        <p>
                            <h4>{{ medicament.nom }}</h4>
                

                            <i class="fa fa-plus" style="color: green;" v-if="medicament.prescription!=='Oui'" @click="AjouterMedicament(medicament.id)"></i> 

                        </p>
               
                     </div>
             </li>
        
         </ul>
    </div>
       
</div>
   </template>
     
     <script>
    
    import keycloak from '../main.js';
    import axios from 'axios';
   
   
     export default {
       name: 'VoirPrescription', 
       
       data() {
         return {
          
          medicaments:[],
         
          statut:""
 
         };
       },
       methods: {
         
         fetchMedicaments() {
  const accessToken = keycloak.token; 
        

  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` 
    }
  };
  const id = this.$route.params.id;
  
 
 

  axios.get(`http://192.168.224.1:8080/api/medicament/all/${id}`, config) 
    .then(response => {
      
       
        this.medicaments=response.data;
      
      
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des médicaments:', error);
    }).finally(()=> this.loading = false);
    
   
},
async AjouterMedicament(MedicamentId) {
    const accessToken = keycloak.token;

const config = {
  headers: {
    'Authorization': `Bearer ${accessToken}` 
  }
};
    if (confirm("Êtes-vous sûr de vouloir ajouter ce médicament à la prescription ?")) {
      try {
        
        

        await axios.put(`http://192.168.224.1:8080/api/medicament/statut/${MedicamentId}`,{prescription:"Oui"},config);
        this.fetchMedicaments();



      } catch (error) {
        console.error('Erreur lors de la suppression de la prescription :', error);
      }
    }
  },

       
 
         
       },
       mounted(){
         
         this.medicaments=[],
          this.posologies= [],
          this.fetchMedicaments();
           
       
       },
       
     };
     </script>
     
     <style scoped>
     html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}

     .chat-list {
        list-style: none;
        padding: 0;
      }
      
      .chat-item {
        
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
        background-color: rgb(38, 212, 143);
        color: rgb(0, 0, 0);
      }
      
      
      .chat-preview {
        flex-grow: 1;
      }
      .w3-main
  {
    height: 100%;
    min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
  }
  .historique
  {
    margin:20px;
  }
      


     </style>
     
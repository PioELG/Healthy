<template>
    <div class="w3-main" style="margin-left:300px;margin-top:20px;">
        <br>
        <div class="container">
      
              <div class="constants">
                  <table>
                      <tr>
                          <td><h2>Mes Constantes  </h2></td>
                          <td></td><td></td>
                       <td> <router-link to="/AjouterConstantes"><i class="fa fa-pencil" style="color: blue;"></i></router-link></td> 
                      </tr>
                  </table>
      <p><strong><i class="fa fa-balance-scale" style="color: rgb(0, 255, 140);"></i> Poids :</strong> {{ constantes.poids }} kg</p>
      <p><strong><i class="fa fa-stethoscope" style="color: green;"></i> Pression Artérielle :</strong>{{constantes.pressionArterielle}}mmHg</p>
      <p><strong><i class="fa fa-heartbeat" style="color: red;"></i> Fréquence Cardiaque :</strong> {{ constantes.freqCar }} bpm</p>
      <p><strong><i class="fa fa-cloud" style="color: rgb(0, 136, 255) ;"></i>  Fréquence Respiratoire :</strong> {{ constantes.freqRes }} cycles/min</p>
      <p><strong><i class="fa fa-thermometer" style="color: rgb(236, 141, 6);"></i> Température :</strong>{{ constantes.temperature }}°C</p>
      
                
                  
              </div>
              <div class="symptoms">
                  <table>
                      <tr>
                          <td><h2> Mes Symptômes </h2></td>
                          <td></td><td></td>
                          <td><router-link to="/AjouterSymptomes"><i class="fa fa-plus" style="color: green;"></i></router-link></td>
                      </tr>
                  </table>
                  
                  <ul v-for="symptome in symptomes" :key="symptome.id">
                      <li>{{symptome.nom}} &nbsp;&nbsp;&nbsp; <i class="fa fa-trash" style="color: red;" @click="supprimerSymptome(symptome.id)"></i></li>     <!-- Ajoutez d'autres symptômes ici -->
                  </ul>
              </div>
          
          </div>
          

      </div>
  
</template>
  
  <script>
    import keycloak from '@/main';
  import axios from 'axios';

  export default {
    name: 'MonEtat', 
    data() {
      return {
        
        constantes:[],
        symptomes:[],
        
      };
    },
    methods: {
      
      fetchConstantes() {
  const accessToken = keycloak.token; 

  
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` 
    }
  };

  axios.get('http://192.168.224.1:8080/api/constantes', config) 
    .then(response => {
      this.constantes = response.data;
      console.log(response.data);
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des constantes :', error);
    });
},
fetchSymptomes() {
  const accessToken = keycloak.token;

 
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` 
    }
  };

  axios.get('http://192.168.224.1:8080/api/symptomes', config) 
    .then(response => {
      this.symptomes = response.data;
      console.log(response.data);
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des symptomes :', error);
    });
},
async supprimerSymptome(symptomeId) {
    const accessToken = keycloak.token; 


const config = {
  headers: {
    'Authorization': `Bearer ${accessToken}` 
  }
};
    if (confirm("Votre symptôme a t'il disparu ?")) {
      try {
        
         await axios.delete(`http://192.168.224.1:8080/api/symptomes/${symptomeId}`,config);

        
        console.log('Symptôme supprimé avec succès !');

        
        this.symptomes = this.symptomes.filter(s=> s.id !== symptomeId);
      } catch (error) {
        console.error('Erreur lors de la suppression du symptome :', error);
       
      }
    }
  }
      
    },
    mounted(){
        
        this.fetchConstantes();
        this.fetchSymptomes();
        
      
      },
   
  };
  </script>
  
  <style scoped>
  html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
  #customers {
         font-family: Arial, Helvetica, sans-serif;
         border-collapse: collapse;
         width: 100%;
       
       }
       
       #customers td, #customers th {
         border: 1px solid #ddd;
         padding: 8px;
       }
       
       #customers tr:nth-child(even){background-color: #f2f2f2;}
       
       #customers tr:hover {background-color: #ddd;}
       
       #customers th {
         padding-top: 12px;
         padding-bottom: 12px;
         text-align: left;
         background-color: #04AA6D;
         color: white;
       }
       .table
       {
         margin-bottom: 80px;
       }
 
       body {
     font-family: Arial, sans-serif;
     margin: 0;
     padding: 0;
     background-color: #f0f0f0;
 }
 
 
 
 .container {
     max-width: 600px;
     margin: 20px auto;
     padding: 20px;
     background-color: #fff;
     border-radius: 5px;
     box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
 }
 
 h1 {
     text-align: center;
     color: #333;
 }
 
 .patient-info {
     margin-bottom: 20px;
 }
 
 
 
 .constants, .symptoms, .appointment, .prescription,.rdv {
     margin-top: 20px;
     padding: 15px;
     background-color: #f5f5f5;
     border-radius: 5px;
     border: 1px solid #ddd;
 }
 
 
 .prescription {
     position: relative; 
 }
 

 .etat-traitement {
     background-color: #047408; 
     color: #fff;
     padding: 10px;
     border-radius: 5px;
     transition: background-color 0.3s, color 0.3s;
 }
 

 
 
 
 .symptoms ul {
     list-style: disc;
     padding-left: 20px;
 }
 
 
 .symptoms ul li {
     margin-bottom: 5px;
 }
 .medicament {
     padding: 10px;
     background-color: #f5f5f5;
     border-radius: 5px;
     margin-bottom: 10px;
     cursor: pointer;
     transition: background-color 0.3s, color 0.3s;
     display: flex;
 }
 

 .medicament:hover {
     background-color: #04AA6D;
     color: white;
 }
 .content {
     flex-grow: 1;
 }
 
 .icones {
     margin-left: auto; 
 }
 .w3-main
  {
    height: 100%;
    min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
  }
  
  </style>
  
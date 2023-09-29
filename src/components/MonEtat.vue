<template>
    <div class="w3-main" style="margin-left:300px;margin-top:43px;">

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
      
                
                  <!-- Ajoutez d'autres constantes ici -->
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
          
        <!-- End page content -->
      </div>
  
</template>
  
  <script>
    import keycloak from '@/main';
  import axios from 'axios';

  export default {
    name: 'MonEtat', // Remplacez par le nom de votre composant
    data() {
      return {
        // Les données de votre composant vont ici
        constantes:[],
        symptomes:[],
        
      };
    },
    methods: {
      // Les méthodes de votre composant vont ici
      fetchConstantes() {
  const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

  // Définissez l'en-tête d'autorisation avec le jeton d'accès
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
    }
  };

  axios.get('http://192.168.224.1:8080/api/constantes', config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      this.constantes = response.data;
      console.log(response.data);
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des constantes :', error);
    });
},
fetchSymptomes() {
  const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

  // Définissez l'en-tête d'autorisation avec le jeton d'accès
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
    }
  };

  axios.get('http://192.168.224.1:8080/api/symptomes', config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      this.symptomes = response.data;
      console.log(response.data);
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des symptomes :', error);
    });
},
async supprimerSymptome(symptomeId) {
    const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

// Définissez l'en-tête d'autorisation avec le jeton d'accès
const config = {
  headers: {
    'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
  }
};
    if (confirm("Votre symptôme a t'il disparu ?")) {
      try {
        // Envoyez une requête de suppression à votre API Backend en utilisant l'ID du conseil
         await axios.delete(`http://192.168.224.1:8080/api/symptomes/${symptomeId}`,config);

        // Gérez la réponse de l'API (par exemple, affichez un message de succès)
        console.log('Symptôme supprimé avec succès !');

        // Mettez à jour la liste des conseils en supprimant le conseil supprimé
        this.symptomes = this.symptomes.filter(s=> s.id !== symptomeId);
      } catch (error) {
        console.error('Erreur lors de la suppression du symptome :', error);
        // Gérez les erreurs de l'API (par exemple, affichez un message d'erreur)
      }
    }
  }
      
    },
    mounted(){
        // Les propriétés calculées de votre composant vont ici
        this.fetchConstantes();
        this.fetchSymptomes();
        
      
      },
    // Autres options de composant (comme "props", "watch", etc.) vont ici
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
     position: relative; /* Permet de positionner le texte de l'état du traitement */
 }
 
 /* Style pour le texte avec fond vert */
 .etat-traitement {
     background-color: #047408; /* Vert */
     color: #fff; /* Texte blanc */
     padding: 10px;
     border-radius: 5px;
     transition: background-color 0.3s, color 0.3s; /* Transition fluide */
 }
 
 /* Style pour le texte avec fond rouge */
 
 
 /* Style pour la liste des symptômes */
 .symptoms ul {
     list-style: disc;
     padding-left: 20px;
 }
 
 /* Style pour les éléments de la liste des symptômes */
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
 
 /* Animation au survol */
 .medicament:hover {
     background-color: #04AA6D;
     color: white;
 }
 .content {
     flex-grow: 1; /* Permet au contenu de s'étendre pour occuper l'espace disponible */
 }
 
 .icones {
     margin-left: auto; /* Pousse les icônes vers la droite (fin) */
 }
  </style>
  
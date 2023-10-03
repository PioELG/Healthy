<template>
    <div class="w3-main" style="margin-left:300px;margin-top:43px;">
        <!-- Header -->
        <div class="container">
          <h2>Symptômes</h2>
          <form @submit.prevent="submitForm">
              <div class="form-group">
                  <label for="titre">Que ressentez-vous?:</label>
                  <input type="text" id="symp" name="symp" v-model="titre" required>
              </div>
              
              <div class="form-group">
                  <input type="submit" value="Ajouter">
              </div>
          </form> 
          </div>
          <!-- End page content -->
      </div>
  
</template>
  
  <script>
   import keycloak from '@/main';
  import axios from 'axios';

  export default {
    name: 'AjouterSymptomes', // Remplacez par le nom de votre composant
    data() {
      return {
        // Les données de votre composant vont ici
        titre:''
        
      };
    },
    methods: {
      // Les méthodes de votre composant vont ici
      async submitForm() {
            const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

// Définissez l'en-tête d'autorisation avec le jeton d'accès
const config = {
  headers: {
    'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
  }
};


    // Envoie les données à votre API Backend en utilisant Axios
    try {
       await axios.post('http://192.168.224.1:8080/api/symptomes', {nom:this.titre},config);
       await axios.post('http://192.168.224.1:8080/api/notification/patient', { contexte:"ses symptômes"},config);


      // Gérez la réponse de l'API (par exemple, affichez un message de succès)
      console.log('Symptôme ajouté avec succès !');
      // Réinitialisez le champ de texte
      this.titre = '';
      window.history.back();
      
    } catch (error) {
      console.error('Erreur lors de l\'ajout du symptome :', error);
      // Gérez les erreurs de l'API (par exemple, affichez un message d'erreur)
    }
}
      
    },
    mounted(){
        // Les propriétés calculées de votre composant vont ici
        
      
      },
    // Autres options de composant (comme "props", "watch", etc.) vont ici
  };
  </script>
  
  <style scoped>
  html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
 


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
  
  .form-group {
              margin-bottom: 20px;
          }
  
          label {
              font-weight: bold;
          }
  
          input[type="text"],
          input[type="number"],
          input[type="email"] {
              width: 100%;
              padding: 10px;
              margin-top: 5px;
              border: none;
              border-radius: 5px;
              background-color: #f4f4f4;
          }
  
          input[type="submit"] {
              background-color: #00b300;
              color: #fff;
              padding: 10px 20px;
              border: none;
              border-radius: 5px;
              cursor: pointer;
          }
  
          input[type="submit"]:hover {
              background-color: #00b300;
          }
  </style>
  
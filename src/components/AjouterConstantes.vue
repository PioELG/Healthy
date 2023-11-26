<template>
  <!DOCTYPE html>
  <html>
    <head>
      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
      />
    </head>
    <body>
    <div class="w3-main" style="margin-left:300px;margin-top:20px;">
      <br>
    <div class="container">
      
        <h2>Mettez à jour vos constantes</h2>
        <form @submit.prevent="submitForm">
            <div class="form-group">
                <label for="poids">Poids (Kg):</label>
                <input type="number" id="poids" name="poids" v-model="poids" required>
            </div>
            <div class="form-group">
                <label for="Tension Artérielle">Pression Artérielle(mmHg) :</label>
                <input type="number" id="TA" name="TA" v-model="pa" required>
            </div>
            <div class="form-group">
                <label for="Fréquence Respiratoire">Fréquence Respiratoire (cycles/min):</label>
                <input type="number" id="FR" name="FR" v-model="fr" required>
            </div>
            <div class="form-group">
                <label for="Fréquence Cardiaque">Fréquence Cardiaque(bpm) :</label>
                <input type="number" id="FC" name="FC" required v-model="fc">
            </div>
            <div class="form-group">
                <label for="Température">Température(°C) :</label>
                <input type="number" id="Température" name="Température" v-model="temperature" required>
            </div>
            <div class="form-group">
                <input type="submit" value="Ajouter" >
            </div>
        </form>
          
            
        </div> 
    
      
 </div>
 </body>
 </html>
  
</template>
  
  <script>
  import keycloak from '@/main';
  import axios from 'axios';


  export default {
    
    name: 'AjouterConstantes', 
    data() {
      return {
      
        poids:'',
        pa:'',
        fr:'',
        fc:'',
        temperature:'',
      };
    },
    methods: {
        async submitForm() {
            const accessToken = keycloak.token; 


const config = {
  headers: {
    'Authorization': `Bearer ${accessToken}` 
  }
};


    
    try {
       await axios.post('http://localhost:8080/api/constantes', { poids: this.poids,freqCar:this.fc,freqRes:this.fr,pressionArterielle:this.pa, temperature:this.temperature},config);
       await axios.post('http://localhost:8080/api/notification/patient', { contexte:"ses constantes"},config);


      
      console.log('Constantes ajoutées avec succès !');
     
      this.poids = '';
      this.pa = '';
      this.fr = '';
      this.fc = '';
      this.temperature='';

      window.history.back();
    } catch (error) {
      console.error('Erreur lors de l\'ajout du conseil :', error);
     
    }
  }
     
      
    },
    mounted(){
    
        
      
      },
    
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
  
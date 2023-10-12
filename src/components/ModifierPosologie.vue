<template>
<div class="w3-main" style="margin-left:300px;margin-top:20px;">
    <br> <br>
    <div class="posologie" style="margin-left:50px;margin-right:50px;padding:70px;">
        
      
    <div class="form-group">
        <label for="quantite">Quantité :</label>
        <input type="number" id="quantite" name="quantite" required v-model="quantite"> 
    </div>

    <div class="form-group">
        <label for="unite">Unité :</label>
        <select id="unite" name="unite" v-model="unite">
            <option value="comprimés">Comprimés</option>
            <option value="ml">ml</option>
            <option value="dose">dose</option>
        </select>
    </div>

   
        <div class="form-group">
          <label for="temps">Temps de Prise :</label>
          <select id="temps" name="temps" required v-model="heurePrise">
              <option value="le matin">Matin</option>
              <option value="à midi">Midi</option>
              <option value="le soir">Soir</option>
              <option value="Toutes les 2h">Toutes les 2 heures</option>
          </select>
        </div>
        <button type="button" id="ajouterPosologie" class="btn" @click="submitForm">Modifier Posologie</button>

        
    </div>
</div>
</template>
  
  <script>
  import keycloak from '@/main';
  import axios from 'axios';
  export default {
    name: 'ModifierPosologie', // Remplacez par le nom de votre composant
    data() {
      return {
        quantite:"",
        unite:"",
        heurePrise:""
       
      };
    },
    methods: {
        async submitForm() {
            const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

// Définissez l'en-tête d'autorisation avec le jeton d'accès
const config = {
  headers: {
    'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
  }
};
const id = this.$route.params.id;



    // Envoie les données à votre API Backend en utilisant Axios
    try {
        
       await axios.put(`http://192.168.224.1:8080/api/posologie/${id}`, { quantite:this.quantite,unite:this.unite,heurePrise:this.heurePrise},config);

      // Gérez la réponse de l'API (par exemple, affichez un message de succès)
      console.log('Posologie modifiée avec succès !');
      // Réinitialisez le champ de texte
      this.quantite = '';
      this.unite = '';
      this.heurePrise='';

     
      window.history.back();
      
    } catch (error) {
      console.error('Erreur lors de la modif de la posologie:', error);
      // Gérez les erreurs de l'API (par exemple, affichez un message d'erreur)
    }
  }
    
        

      
  },
    mounted() {
    
    },
    // Autres options de composant (comme "props", "watch", etc.) vont ici
  };
  </script>
  
  <style scoped>
  html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}

h1 {
            text-align: center;
            color: #333;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="number"],
        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        select {
            appearance: none;
        }

        .posologie {
    display: flex;
    justify-content: space-between; 
    align-items: center;
    background-color:white;
    border-radius:5px;
    
}

.posologie label {
    width: 100%; /* Largeur du label réduite pour laisser plus d'espace au sélecteur */
    margin-right: 70 px;
}

.posologie select {
    width: 100%; /* Largeur du sélecteur ajustée */
}

.btn {
    display: inline-block;
    padding: 10px 20px;
    background-color: #007BFF;
    color: #fff;
    border: none;
    border-radius: 10px; /* Augmentation du rayon pour un aspect plus arrondi */
    cursor: pointer;
}

.btn:hover {
    background-color: #0056b3;
}

#ajouterPrescription {
    margin-top: 20px; /* Espacement entre les boutons */
    background-color: #009900; /* Couleur pour le bouton "Ajouter Prescription" */
}
   
        @media (max-width: 768px) {
            .container {
                padding: 10px;
            }

            .posologie label {
                width: 45%;
            }

            .posologie input {
                width: 45%;
            }
        }


        .w3-main
        {
          height: 100%;
          min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
        }
        

  </style>
  
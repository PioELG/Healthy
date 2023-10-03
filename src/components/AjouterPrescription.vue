<template>
  <div class="w3-main" style="margin-left:300px;margin-top:43px;">

      <!-- Header -->
     
      <h1>Ajouter une Prescription</h1>
      <div class="container">
          <div class="form-group" v-if="!showPosologie">
              <label for="medicament">Médicament :</label>
              <input type="text" id="medicament" name="medicament"  v-model="nom"  pattern=".*\S+.*" title="Ce champ ne peut pas être vide.">
              <button type="button" id="ajouterPrescription" class="btn"  @click="submitMedicament">Ajouter Médicament</button>
          </div>
          <div class="posologie" v-if="showPosologie">
              <div>{{ this.nom }}</div>
            
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
                    <option value="le soir">Soir</option>
                    <option value="Toutes les 2h">Toutes les 2 heures</option>
                </select>
              </div>
              <button type="button" id="ajouterPosologie" class="btn" @click="submitPoso">Ajouter Posologie</button>

              
          </div>
          <button v-if="showPosologie" type="button" id="ajouterPrescription" class="btn"  @click="goback">Ajouter Prescription</button>
          
          
      </div>
    
     
      
     
    
      <!-- End page content -->
    </div>
    
</template>

<script>
import keycloak from '@/main';
import axios from 'axios';
export default {
  name: 'AjoutRdv', // Remplacez par le nom de votre composant
  data() {
    return {
      // Les données de votre composant vont ici
     prescription:[],
     medicament:[],
     posologie:[],
     nom:'',
     quantite:'',
     heurePrise:'',
     unite:'',
     showPosologie:false,
     medicamentId:''
    };
  },
  methods: {
    forMPoso()
    {
      this.showPosologie=!this.showPosologie;
    },
    goback()
    {
      window.history.back();

    },
      async submitMedicament() {
          const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

// Définissez l'en-tête d'autorisation avec le jeton d'accès
const config = {
headers: {
  'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
}
};
const id = this.$route.params.id;

this.forMPoso();





  // Envoie les données à votre API Backend en utilisant Axios
  try {
      
     const response=await axios.post('http://192.168.224.1:8080/api/medicament', {nom:this.nom,patient_id:id},config);

    // Gérez la réponse de l'API (par exemple, affichez un message de succès)
    console.log('medicament ajoutée avec succès !');
    this.medicamentId = response.data.id
    
    // Réinitialisez le champ de texte
    console.log("salut");
    console.log(this.medicamentId);
   
    
  } catch (error) {
    console.error('Erreur lors de l\'ajout de la presc:', error);
    // Gérez les erreurs de l'API (par exemple, affichez un message d'erreur)
  };


 
},
async submitPoso() {
          const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

// Définissez l'en-tête d'autorisation avec le jeton d'accès
const config = {
headers: {
  'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
}
};
const id = this.$route.params.id;


try {
      
      await axios.post('http://192.168.224.1:8080/api/posologie', { quantite:this.quantite,unite:this.unite,heurePrise:this.heurePrise,medicament_id:this.medicamentId},config);

      await axios.post('http://192.168.224.1:8080/api/notification/doc', { contexte:"une prescription",cible:id},config);

     // Gérez la réponse de l'API (par exemple, affichez un message de succès)
     console.log('posologie ajouté avec succès !');
     // Réinitialisez le champ de texte
     this.quantite = '';
     this.unite = '';
     this.heurePrise='';
     
   } catch (error) {
     console.error('Erreur lors de l\'ajout de la posologie:', error);
     // Gérez les erreurs de l'API (par exemple, affichez un message d'erreur)
   };

  
 
  
 
}

  

    
},
  mounted() {
   console.log(this.$route.params.id);
   this.showPosologie=false;
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
    justify-content: space-between; /* Alignement horizontal avec espaces entre les éléments */
    align-items: center;
    margin-top: 10px; /* Espace supplémentaire au-dessus de la div posologie */
}

.posologie label {
    width: 100%; /* Largeur du label réduite pour laisser plus d'espace au sélecteur */
    margin-right: 10px;
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

</style>

<template>
  <div class="w3-main" style="margin-left:300px;margin-top:20px;">

    
     <br>
      <h3 style="margin-left:350px" >Ajouter un nouveau médicament</h3>
      <div class="container" style="margin:50px;">
          <div class="form-group" v-if="!showPosologie">
              <label for="medicament">Médicament :</label>

              

             <!-- <input type="text" id="medicament" name="medicament"  v-model="nom"  pattern=".*\S+.*" title="Ce champ ne peut pas être vide."> -->

              <div class="posologie">
              <div class="form-group">
                <label for="jours"> A prendre pendant</label>
                <input type="number" id="quantite" name="quantite" required v-model="nb1">  
                {{ nb1 }} {{ nb2 }}
             </div>
        
            <div class="form-group" style="margin-right:500px; margin-top:27px ; min-width:50px;">
               
                <select id="duree" name="duree" v-model="nb2">
                    <option value="jours">jours</option>
                    <option value="semaine(s)">semaine(s)</option>
                    <option value="mois">mois</option>
                </select>
            </div>
          </div>



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
          <button v-if="showPosologie" type="button" id="ajouterPrescription" class="btn"  @click="goback" style="margin-left:50px;">Valider</button>
          
          
      </div>
    
     
      
     
    
      
    </div>
    
</template>

<script>
import keycloak from '@/main';
import axios from 'axios';
export default {
  name: 'AjoutRdv', 
  data() {
    return {
      
     prescription:[],
     medicament:[],
     posologie:[],
     nom:'',
     quantite:'',
     heurePrise:'',
     unite:'',
     showPosologie:false,
     medicamentId:'',
     nb1:'',
     nb2:'',
     nb:''
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
          const accessToken = keycloak.token; 


const config = {
headers: {
  'Authorization': `Bearer ${accessToken}` 
}
};
const id = this.$route.params.id;

this.forMPoso();






  try {
    this.nb= this.nb1 + this.nb2;
      
     const response=await axios.post('http://192.168.224.1:8080/api/medicament', {nom:this.nom,patient_id:id, duree: this.nb},config);

    
    console.log('medicament ajoutée avec succès !');
    this.medicamentId = response.data.id
    
  
    console.log("salut");
    console.log(this.medicamentId);
   
    
  } catch (error) {
    console.error('Erreur lors de l\'ajout de la presc:', error);
   
  };


 
},
async submitPoso() {
          const accessToken = keycloak.token; 


const config = {
headers: {
  'Authorization': `Bearer ${accessToken}` 
}
};
const id = this.$route.params.id;


try {
      
      await axios.post('http://192.168.224.1:8080/api/posologie', { quantite:this.quantite,unite:this.unite,heurePrise:this.heurePrise,medicament_id:this.medicamentId},config);

      await axios.post('http://192.168.224.1:8080/api/notification/doc', { contexte:"une prescription",cible:id},config);

      

     
     console.log('posologie ajouté avec succès !');
     
     this.quantite = '';
     this.unite = '';
     this.heurePrise='';
     
   } catch (error) {
     console.error('Erreur lors de l\'ajout de la posologie:', error);
     
   };

  
 
  
 
}

  

    
},
  mounted() {
   
   this.showPosologie=false;
   console.log('this.nb1'+'this.nb2')

  },
  computed: {
                filteredOptions() {
                    const filter = this.filterLetter.toLowerCase();
                    return this.options.filter(option => option.toLowerCase().startsWith(filter));
                }
            }
 
};
</script>

<style scoped>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}

h1 {
            text-align: center;
            color: #333;
        }

        .container {
            min-width: 500px;
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
    margin-top: 10px; 
}

.posologie label {
    width: 100%; 
    margin-right: 10px;
}

.posologie select {
    width: 100%; 
}

.btn {
    display: inline-block;
    padding: 10px 20px;
    background-color: #007BFF;
    color: #fff;
    border: none;
    border-radius: 10px; 
    cursor: pointer;
}

.btn:hover {
    background-color: #0056b3;
}

#ajouterPrescription {
    margin-top: 20px; 
    background-color: #009900;
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

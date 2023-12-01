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
      <div class="w3-main" style="margin-left: 300px; margin-top: 20px">
        <header class="w3-container" style="padding-top: 22px">
          <h5>
            <b><i class="fa fa-address-book fa-fw"></i> Mes Rendez-Vous</b>
          </h5>
        </header>

        <main style="margin-left: 30px; margin-right: 30px">
          <ul class="chat-list" v-for="rdv in rdvs" :key="rdv.id">
            <li class="chat-item">
              <div class="chat-preview">
                <h4>
                  Rendez-Vous avec le patient: &nbsp;&nbsp;
                  <strong>{{ getNomPrenom(rdv.malade_id).nom  }} {{ getNomPrenom(rdv.malade_id).prenom }}</strong>  &nbsp;&nbsp;&nbsp;
                  <router-link :to="'/ModifierRdv/' + rdv.id">
                     <i class="fa fa-pen" style="color: rgb(127, 127, 197)"></i>
                  </router-link>
                  &nbsp;&nbsp;&nbsp;

                  <i
              class="fa fa-trash"
              style="color: rgb(208, 85, 85)"
              @click="supprimerRdv(rdv.id)"
            ></i>
                </h4>

              <p>
                <h5>
                  <span class="icon"
                    ><i class="fas fa-calendar" style="color: #e1dd72"></i
                  ></span>
                  
                  &nbsp; &nbsp; Date : &nbsp;
                  <strong>{{ formatDate(rdv.date) }}</strong>
                 </h5>
              </p>
              <p>
                <h5>
                  <span class="icon"
                    ><i class="fas fa-clock" style="color: #a8c66c"></i
                  ></span>
                  
                  &nbsp;&nbsp; Heure :&nbsp;<strong>{{ rdv.heure }}</strong>
                </h5>
               </p>
               <p>
                <h5>
                  <span class="icon"
                    > <i class="fa fa-bug fa" style="color: #1b6535"></i></span>
                  
                  &nbsp;&nbsp; Pathologie du patient :&nbsp;<strong>{{ getNomPrenom(rdv.malade_id).pathologie }}</strong>
                </h5>
               </p>
              </div>
            </li>
          </ul>
        </main>
      </div>
    </body>
  </html>
  </template>
  
  <script>
  import keycloak from '@/main';
  import axios from 'axios';
  
  export default {
    name: 'rdvMedecin',
    data() {
      return {
        rdvs: [],
        malades: [],
      };
    },
    methods: {
      async fetchRdv() {
        const accessToken = keycloak.token;
        const config = {
          headers: {
            'Authorization': `Bearer ${accessToken}`
          }
        };
        const id = this.$route.params.id;
  
        try {
          const rdvResponse = await axios.get(`http://localhost:8080/api/rdv/${id}`, config);
          this.rdvs = rdvResponse.data;
  
          const maladeResponse = await axios.get('http://localhost:8080/api/malade/St', config);
          this.malades = maladeResponse.data;
        } catch (error) {
          console.error('Erreur lors de la récupération des rendez-vous ou des malades:', error);
        }
      },
      async supprimerRdv(rdvId) {
        if (confirm("Êtes-vous sûr de vouloir supprimer ce rendez-vous ?")) {
          const accessToken = keycloak.token;
          const config = {
            headers: {
              'Authorization': `Bearer ${accessToken}`
            }
          };
          try {
            await axios.delete(`http://localhost:8080/api/rdv/${rdvId}`, config);
            console.log('Rendez-vous supprimé avec succès !');
            this.rdvs = this.rdvs.filter(r => r.id !== rdvId);
          } catch (error) {
            console.error('Erreur lors de la suppression du rendez-vous:', error);
          }
        }
      },
      getNomPrenom(maladeId) {
        const malade = this.malades.find(m => m.id === maladeId);
        if (malade) {
          return {
            nom: malade.nom,
            prenom: malade.prenom,
            pathologie:malade.pathologie
          };
        }
        return { nom: '', prenom: '',pathologie:'' };
      },

      formatDate(dateString) {
      const currentDate = new Date();
      const targetDate = new Date(dateString);

      if (
        targetDate.getDate() === currentDate.getDate() + 1 &&
        targetDate.getMonth() === currentDate.getMonth() &&
        targetDate.getFullYear() === currentDate.getFullYear()
      ) {
        return "demain";
      } 
      if (
        targetDate.getDate() === currentDate.getDate() 
       
      ) {
        return "Aujourd'hui";
      }else {
        const options = {
          weekday: "long",
          year: "numeric",
          month: "long",
          day: "numeric",
        };
        return targetDate.toLocaleDateString("fr-FR", options);
      }
    },
    },
    mounted() {
      this.fetchRdv();
    },
  };
  </script>
  
  <style scoped>
  .link {
    text-decoration: none;
  }
  
  html,
  body,
  h1,
  h2,
  h3,
  h4,
  h5 {
    font-family: "Raleway", sans-serif;
  }
  
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
    background-color: white;
  }
  
  .chat-preview {
    flex-grow: 1;
  }
  .w3-main {
    height: 100%;
    min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
  }
  </style>
  
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
          <p v-if="rdvs.length === 0">Aucun rendez-vous prévu avec votre médecin pour l'instant</p>
          <ul class="chat-list" v-for="rdv in rdvs" :key="rdv.id">
            <li class="chat-item">
              <div class="chat-preview">

                
                
                <h4>
                  Rendez-Vous avec votre médecin: &nbsp;&nbsp;
                  <strong>{{ doctor.nom }} {{ doctor.prenom }}</strong>
                </h4>

                <p>
                <h5>
                  <span class="icon"
                    ><i class="fas fa-calendar" style="color: #49f094"></i
                  ></span>
                  
                  &nbsp; &nbsp; Date : &nbsp;
                  <strong>{{ formatDate(rdv.date) }}</strong>
                 </h5>
                </p>
                <p>
                <h5>
                  <span class="icon"
                    ><i class="fas fa-clock" style="color: #ff0000"></i
                  ></span>
                  
                  &nbsp;&nbsp; Heure :&nbsp;<strong>{{ rdv.heure }}</strong>
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
import keycloak from "@/main";
import axios from "axios";

export default {
  name: "rdvMedecin",
  data() {
    return {
      rdvs: [],
      doctor: [],
    };
  },
  methods: {
    async fetchRdv() {
      const accessToken = keycloak.token;
      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };

      try {
        const rdvResponse = await axios.get(
          "http://192.168.224.1:8080/api/rdv/patient",
          config
        );
        this.rdvs = rdvResponse.data;

        const doctorResponse = await axios.get(
          "http://192.168.224.1:8080/api/doctor",
          config
        );
        this.doctor = doctorResponse.data;
      } catch (error) {
        console.error("Erreur lors de la récupération du doctor", error);
      }
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
      }  if (
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

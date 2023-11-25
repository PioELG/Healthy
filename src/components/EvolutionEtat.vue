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
        <br /><br />
        <div class="historique">
          <h4>Evolution de l'Etat de santé du patient</h4>

          <ul class="chat-list">
            <li
              class="chat-item"
              v-for="constante in constantes"
              :key="constante.id"
            >
              <p>Date modification:{{ formatDate(constante.dateModif) }}</p>
              <div class="chat-preview">
                <h5>
                  <strong
                    ><i
                      class="fa fa-balance-scale"
                      style="color: rgb(0, 0, 0)"
                    ></i>
                    Poids :</strong
                  >
                  {{ constante.poids }} kg
                </h5>
                <h5>
                  <strong
                    ><i class="fa fa-stethoscope" style="color: green"></i>
                    Tension Artérielle :</strong
                  >
                  {{ constante.pressionArterielle }} mmHg
                </h5>
                <h5>
                  <strong
                    ><i class="fa fa-heartbeat" style="color: red"></i>
                    Fréquence Cardiaque :</strong
                  >
                  {{ constante.freqCar }} bpm
                </h5>
                <h5>
                  <strong
                    ><i class="fa fa-cloud" style="color: rgb(0, 136, 255)"></i>
                    Fréquence Respiratoire :</strong
                  >
                  {{ constante.freqRes }} cycles/min
                </h5>
                <h5>
                  <strong
                    ><i
                      class="fa fa-thermometer"
                      style="color: rgb(236, 141, 6)"
                    ></i>
                    Température :</strong
                  >
                  {{ constante.temperature }}°C
                </h5  >
              </div>
            </li>
          </ul>
        </div>
      </div>
    </body>
  </html>
</template>

<script>
import keycloak from "../main.js";
import axios from "axios";

export default {
  name: "EvolutionEtat",

  data() {
    return {
      constantes: [],
    };
  },
  methods: {
    fetchConstantes() {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };
      const id = this.$route.params.id;

      axios
        .get(`http://192.168.224.1:8080/api/constantes/${id}`, config)
        .then((response) => {
          this.constantes = response.data;
        })
        .catch((error) => {
          console.error(
            "Erreur lors de la récupération des constantes :",
            error
          );
        });
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
      } else {
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
    this.fetchConstantes();
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
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 5px;
  transition: transform 0.3s, background-color 0.3s;
  background-color: white; /* Fond blanc */
}

.chat-preview {
  flex-grow: 1;
}
.w3-main {
  height: 100%;
  min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
}
.historique {
  margin: 20px;
}
</style>

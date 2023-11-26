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
            <b><i class="fa fa-stethoscope"></i> Mes Prescriptions</b>
          </h5>
        </header>

        <main style="margin-left: 30px; margin-right: 30px">
          <ul
            class="chat-list"
            v-for="medicament in medicaments"
            :key="medicament.id"
          >
            <li class="chat-item">
              <div class="chat-preview">
                <h4>
                  <strong>Nom du médicament</strong>: {{ medicament.nom }}
                </h4>
                <h4>
                  <strong>Durée</strong>: A prendre pendant
                  {{ medicament.duree }}
                </h4>

                <h4>
                  <strong
                    >Posologie
                    <i
                      class="fas fa-arrow-alt-circle-right"
                      style="color: rgb(51, 255, 0)"
                    ></i></strong
                  >:
                </h4>

                <h4
                  v-for="posologie in getPosologies(medicament.id)"
                  :key="posologie.id"
                >
                  {{ posologie.quantite }} {{ posologie.unite }}
                  {{ posologie.heurePrise }}
                  <!--  <input
                    type="checkbox"
                    :id="'myCheckbox_' + posologie.id"
                    :v-model="checkboxStates[medicament.id]"
                    @change="handleCheckboxChange(medicament.id)"
                  /> -->
                </h4>
              </div>
            </li>
          </ul>
        </main>
      </div>
    </body>
  </html>
</template>

<script>
import keycloak from "../main.js";
import axios from "axios";

export default {
  name: "VoirPrescription",

  data() {
    return {
      medicaments: [],
      posologies: [],
      checkboxStates: {},
    };
  },

  methods: {
    fetchMedicaments() {
      const accessToken = keycloak.token;
      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };
      axios
        .get(`http://localhost:8080/api/medicament`, config)
        .then((response) => {
          this.medicaments = response.data;
        })
        .catch((error) => {
          console.error(
            "Erreur lors de la récupération des médicaments:",
            error
          );
        })
        .finally(() => (this.loading = false));
    },

    fetchPosologie() {
      const accessToken = keycloak.token;
      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };
      axios
        .get(`http://localhost:8080/api/posologie`, config)
        .then((response) => {
          this.posologies = response.data;
        })
        .catch((error) => {
          console.error(
            "Erreur lors de la récupération des posologies:",
            error
          );
        })
        .finally(() => (this.loading = false));
    },

    getPosologies(medId) {
      return this.posologies.filter(
        (posologie) => posologie.medicament_id === medId
      );
    },

    handleCheckboxChange(medId) {
      const state = this.checkboxStates[medId];

      if (state) {
        this.saveCheckboxState(medId, state);
        this.saveCheckboxTimestamp(medId);

        setTimeout(() => {
          this.checkboxStates[medId] = false;
          this.saveCheckboxState(medId, false);
          localStorage.removeItem(`checkboxTimestamp_${medId}`);
        }, 10 * 60 * 1000); // 10 minutes
      } else {
        this.saveCheckboxState(medId, false);
        localStorage.removeItem(`checkboxTimestamp_${medId}`);
      }
    },

    saveCheckboxState(medId, state) {
      localStorage.setItem(`checkboxState_${medId}`, JSON.stringify(state));
    },

    saveCheckboxTimestamp(medId) {
      const timestamp = new Date().getTime();
      localStorage.setItem(`checkboxTimestamp_${medId}`, timestamp);
    },
  },

  mounted() {
    this.medicaments = [];
    this.posologies = [];
    this.fetchMedicaments();
    this.fetchPosologie();

    this.medicaments.forEach((medicament) => {
      const storedState = JSON.parse(
        localStorage.getItem(`checkboxState_${medicament.id}`)
      );
      const timestamp = localStorage.getItem(
        `checkboxTimestamp_${medicament.id}`
      );

      const duration = 10 * 60 * 1000; // 10 minutes
      const currentTime = new Date().getTime();

      if (
        storedState !== null &&
        timestamp !== null &&
        currentTime - timestamp < duration
      ) {
        this.$set(this.checkboxStates, medicament.id, storedState);
      } else {
        this.$set(this.checkboxStates, medicament.id, false);
      }
    });
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
  min-height: 100vh;
}
</style>

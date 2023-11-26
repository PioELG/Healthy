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
        <!-- Header -->
        <header class="w3-container" style="padding-top: 22px">
          <h5>
            <b
              ><router-link to="/MesPatients" class="link"
                ><i class="fa fa-users fa-fw"></i> Mes Patients</router-link
              ></b
            >
          </h5>
        </header>
        <label for="statutFiltre" style="margin-left: 30px"
          >Filtrer par statut:</label
        >
        <select
          id="statutFiltre"
          name="statutFiltre"
          v-model="statutFiltre"
          style="margin-left: 30px; max-width: 300px"
        >
          <option value="Tous">Tous</option>
          <option value="Sous traitement">Sous traitement</option>
          <option value="FinTraitement">Fin de traitement</option>
          <option value="Non traité">Non traité</option>
        </select>

        <div class="w3-row-padding w3-margin-bottom">
          <table id="customers">
            <tr>
              <th>Nom</th>
              <th>Prenom</th>
              <th>Email</th>
              <th>Pathologie</th>
              <th>Statut</th>

              <th>Actions</th>
            </tr>
            <tr
              v-for="malade in malades.filter((m) =>
                filtrerParStatut(m.statut)
              )"
              :key="malade.id"
            >
              <td>{{ malade.nom }}</td>
              <td>{{ malade.prenom }}</td>
              <td>{{ malade.email }}</td>
              <td>{{ malade.pathologie }}</td>
              <td>{{ malade.statut }}</td>
              <td>
                <i
                  class="fa fa-plus"
                  style="color: green"
                  @click="ouvrirFormulaire(malade, malade.id)"
                  v-if="malade.statut === 'Non traité'"
                ></i>
                &nbsp;&nbsp;&nbsp;
              </td>
            </tr>
            <tr></tr>
            <tr v-if="maladeEnCoursDeMiseAJour !== null">
              <td colspan="6">
                <form
                  @submit.prevent="soumettreFormulaire"
                  style="padding: 20px"
                >
                  <label for="medicament" style="margin-bottom: 200px"
                    >Nouveau Diagnostic
                    <strong
                      >{{ maladeSingle.nom }} {{ maladeSingle.prenom }} </strong
                    >:</label
                  >
                  <br />

                  <input
                    type="text"
                    v-model="searchInput"
                    @input="filterOptions"
                    placeholder="Filtrer Pathologie"
                  />

                  <select id="pathologie" name="pathologie" v-model="nom">
                    <option value="" disabled selected>
                      Sélectionnez une pathologie
                    </option>
                    <option
                      v-for="pathologie in searchInput
                        ? filteredPathologies
                        : pathologies"
                      :value="pathologie.nom"
                      :key="pathologie.id"
                    >
                      {{ pathologie.nom }}
                    </option>
                  </select>

                  <button type="submit">Enregistrer</button> &nbsp;&nbsp;&nbsp;
                  <button @click="fermer">Annuler</button>
                </form>
              </td>
            </tr>
          </table>
        </div>
        <br />
      </div>
    </body>
  </html>
</template>

<script>
import keycloak from "@/main";
import jwtDecode from "jwt-decode";
import axios from "axios";

export default {
  name: "ListePatient",

  data() {
    return {
      malades: [],
      maladeSingle: [],

      maladeEnCoursDeMiseAJour: null,
      nouvellePathologie: "",
      idDoc: "",
      pathologies: [],
      nom: "",
      statutFiltre: "",
      showError: "",
      searchInput: "",
      filteredPathologies: [],
    };
  },
  methods: {
    filterOptions() {
      this.filteredPathologies = this.pathologies.filter((pathologie) =>
        pathologie.nom.toLowerCase().includes(this.searchInput.toLowerCase())
      );
    },
    fetchMalade() {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };

      axios
        .get("http://localhost:8080/api/malade", config)
        .then((response) => {
          this.malades = response.data;
        })
        .catch((error) => {
          console.error(
            "Erreur lors de la récupération des malades du jour :",
            error
          );
        });
    },
    ouvrirFormulaire(malade, IdSingle) {
      this.maladeEnCoursDeMiseAJour = malade;
      this.nouvellePathologie = malade.pathologie;
      this.idDoc = jwtDecode(keycloak.token).sub;

      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };

      axios
        .get(`http://localhost:8080/api/malade/single/${IdSingle}`, config)
        .then((response) => {
          this.maladeSingle = response.data;
        })
        .catch((error) => {
          console.error(
            "Erreur lors de la récupération des malades du jour :",
            error
          );
        });
    },
    fermer() {
      this.maladeEnCoursDeMiseAJour = null;
    },
    soumettreFormulaire() {
      if (this.maladeEnCoursDeMiseAJour) {
        const accessToken = keycloak.token;

        const config = {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        };
        this.maladeEnCoursDeMiseAJour.pathologie = this.nouvellePathologie;
        this.maladeEnCoursDeMiseAJour.statut = "Sous traitement";
        this.maladeEnCoursDeMiseAJour.traitant = jwtDecode(keycloak.token).sub;

        const newData = {
          pathologie: this.nom,
          statut: this.maladeEnCoursDeMiseAJour.statut,
          traitant: this.maladeEnCoursDeMiseAJour.traitant,
        };

        if (this.nom.trim() === "") {
          this.showError = false;
          alert(
            "Veuillez remplir le champ correspondant à la pathologie du patient!"
          );
        } else {
          axios
            .put(
              `http://localhost:8080/api/malade/${this.maladeEnCoursDeMiseAJour.id}`,
              newData,
              config
            )
            .then((response) => {
              console.log("Réponse du serveur :", response.data);
              this.$router.push("/MesPatients");
            })
            .catch((error) => {
              console.error("Erreur lors de la requête PUT :", error);
            });
        }
      }
    },
    fetchPathologie() {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };

      axios
        .get("http://localhost:8080/api/pathologie/all", config)
        .then((response) => {
          this.pathologies = response.data;
        })
        .catch((error) => {
          console.error(
            "Erreur lors de la récupération des pathologies :",
            error
          );
        });
    },
    filtrerParStatut(statut) {
      if (this.statutFiltre === "Tous") {
        return true;
      } else {
        return statut === this.statutFiltre;
      }
    },
  },
  mounted() {
    this.fetchMalade();
    this.fetchPathologie();
    this.statutFiltre = "Tous";
  },
};
</script>

<style scoped>
#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td,
#customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even) {
  background-color: #f2f2f2;
}

#customers tr:hover {
  background-color: #ddd;
}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04aa6d;
  color: white;
}
.table {
  margin-bottom: 80px;
}
.link {
  text-decoration: none;
}

.w3-main {
  height: 100%;
  min-height: 100vh;
}
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
button {
  display: inline-block;
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 10px;
  cursor: pointer;
}
input[type="text"] {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>

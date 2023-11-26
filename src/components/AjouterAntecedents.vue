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
        <h2 style="text-align: center">
          Le patient a t-il des antécedents médicaux ?
        </h2>
        <br />

        <p style="text-align: center">{{ this.nom }}</p>
        <div class="container" style="margin: 50px">
          <div class="form-group">
            <label for="medicament">Antecedents :</label>

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

            <button
              type="button"
              id="ajouterAntecedent"
              class="btn"
              @click="submit"
            >
              Ajouter Antecedent
            </button>
          </div>
        </div>
      </div>
    </body>
  </html>
</template>

<script>
import keycloak from "@/main";
import axios from "axios";

export default {
  name: "AjoutAntecedents",

  data() {
    return {
      nom: "",
      pathologies: [],
      searchInput: "",
      filteredPathologies: [],
      showErrorPoso: false,
    };
  },
  methods: {
    filterOptions() {
      this.filteredPathologies = this.pathologies.filter((pathologie) =>
        pathologie.nom.toLowerCase().includes(this.searchInput.toLowerCase())
      );
    },
    async submit() {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };
      const id = this.$route.params.id;
      if (this.nom.trim() === "") {
        this.showError = false;
        alert("Veuillez remplir tous les champs !");
      } else {
        try {
          await axios.post(
            "http://localhost:8080/api/antecedent",
            { nom: this.nom, patient_id: id },
            config
          );

          console.log("Antecedent ajouté avec succès !");

          this.nom = "";
          window.history.back();
        } catch (error) {
          console.error("Erreur lors de l'ajout de la posologie:", error);
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
  },
  mounted() {
    this.fetchPathologie();
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
  background-color: #007bff;
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
.w3-main {
  height: 100%;
  min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
}
</style>

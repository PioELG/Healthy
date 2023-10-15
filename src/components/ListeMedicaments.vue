<template>
  <div class="container" style="margin-left: 300px; margin-top: 20px">
    <header class="w3-container" style="padding-top: 25px">
      <table>
        <tr>
          <td>
            <h5>
              <b><i class="fa fa-medkit fa-fw"></i>Médicaments</b>
              &nbsp;&nbsp;&nbsp;
              <i
                class="fa fa-plus fa-fw"
                style="color: rgb(1, 128, 5)"
                @click="ouvrirPopup"
              ></i>
            </h5>
          </td>
          <div
            class="header-search"
            style="margin-top: 0px; margin-left: 500px"
          >
            <input
              type="text"
              placeholder="Rechercher "
              v-model="recherche"
              @input="filtrerMedicaments"
              style="min-width: 80px"
            />
            &nbsp;&nbsp;
            <i class="fa fa-search"></i>
          </div>
          <td></td>
        </tr>
      </table>
    </header>
    <div class="popup" v-if="showPopup">
      <div class="popup-content">
        <input
          placeholder="Nom du médicament"
          v-model="nom"
          style="min-width: 400px; max-width: 600px"
        />
        &nbsp;&nbsp;&nbsp;

        <button @click="submitForm" class="BtnMed">Enregistrer</button>
        &nbsp;&nbsp;&nbsp;
        <button @click="fermerPopup" class="BtnMed">Annuler</button>
      </div>
    </div>
    <div class="w3-main">
      <div class="w3-row-padding w3-margin-bottom">
        <table id="customers">
          <tr>
            <th>NomMédicament</th>

            <th>Actions</th>
          </tr>
          <tr
            v-for="modeleMedicament in paginatedMed"
            :key="modeleMedicament.id"
          >
            <td>{{ modeleMedicament.nom }}</td>
            <td>
              <i
                class="fa fa-trash"
                style="color: red"
                @click="supprimer(modeleMedicament.id)"
              ></i>
            </td>
          </tr>
        </table>
      </div>
      <br />
    </div>
    <div class="pagination">
      <button
        @click="previousPage"
        :disabled="currentPage === 0"
        class="page"
        style="margin-left: 10px"
      >
        Précédent
      </button>
      <span style="margin-left: 100px">Page {{ currentPage + 1 }}</span>
      <button
        @click="nextPage"
        :disabled="currentPage === totalPages - 1"
        class="page"
        style="margin-left: 200px"
      >
        Suivant
      </button>
    </div>
  </div>
</template>

<script>
import keycloak from "@/main";
import axios from "axios";

export default {
  name: "rdvMedecin",
  data() {
    return {
      modeleMedicaments: [],
      allModel: [],
      paginatedMed: [],
      showPopup: false,
      showError: false,
      nom: "",
      recherche: "",
      currentPage: 0,
      totalPages: 1,
    };
  },
  methods: {
    nextPage() {
      if (this.currentPage < this.totalPages - 1) {
        this.currentPage++;
        this.fetchMedocs();
      }
    },
    previousPage() {
      if (this.currentPage > 0) {
        this.currentPage--;
        this.fetchMedocs();
      }
    },
    filtrerMedicaments() {
      const recherche = this.recherche.toLowerCase();
      if (recherche === "") {
        this.paginatedMed = this.modeleMedicaments;
      } else {
        this.paginatedMed = this.allModel.filter((modeleMedicament) => {
          return modeleMedicament.nom.toLowerCase().includes(recherche);
        });
      }
    },
    ouvrirPopup() {
      this.showPopup = true;
    },
    fermerPopup() {
      this.showPopup = false;
    },
    fetchMedocs() {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };

      axios
        .get(
          `http://192.168.224.1:8080/api/modele?page=${this.currentPage}`,
          config
        )
        .then((response) => {
          this.modeleMedicaments = response.data.content;
          this.totalPages = response.data.totalPages;
          this.paginatedMed = response.data.content;
        })
        .catch((error) => {
          console.error(
            "Erreur lors de la récupération des modeles de médicament:",
            error
          );
        });
    },
    fetchAll() {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };

      axios
        .get(`http://192.168.224.1:8080/api/modele/all`, config)
        .then((response) => {
          this.allModel = response.data;
        })
        .catch((error) => {
          console.error(
            "Erreur lors de la récupération des modeles de médicament:",
            error
          );
        });
    },
    async supprimer(Id) {
      if (confirm("Êtes-vous sûr de vouloir supprimer ce médicament ?")) {
        const accessToken = keycloak.token;
        const config = {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        };
        try {
          await axios.delete(
            `http://192.168.224.1:8080/api/modele/${Id}`,
            config
          );
          console.log("Medicament supprimé avec succès !");
          this.modeleMedicaments = this.modeleMedicaments.filter(
            (m) => m.id !== Id
          );
        } catch (error) {
          console.error("Erreur lors de la suppression du medicament:", error);
        }
      }
    },

    async submitForm() {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };
      if (this.nom.trim() === "") {
        this.showError = false;
        alert("Veuillez correctement rensigner le champ !");
      } else {
        try {
          await axios.post(
            "http://192.168.224.1:8080/api/modele",
            { nom: this.nom },
            config
          );

          console.log("Modele ajouté avec succès !");
          this.nom = "";
          this.fetchMedocs();
        } catch (error) {
          console.error(
            "Erreur lors de l'ajout du Model de medicament :",
            error
          );
        }
      }
    },
  },
  mounted() {
    this.fetchMedocs();
    this.fetchAll();
  },
};
</script>

<style scoped>
.container {
  height: 100%;
  min-height: 100vh;
}
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

.popup {
  padding: 20px;
  border-radius: 5px;

  z-index: 1000;
}
.BtnMed {
  display: inline-block;
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 10px;
  cursor: pointer;
}
.page {
  display: inline-block;
  padding: 10px 20px;
  background-color: #0d8215;
  color: #fff;
  border: none;
  border-radius: 10px;
  cursor: pointer;
}
</style>

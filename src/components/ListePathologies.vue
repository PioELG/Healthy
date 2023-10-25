<template>
  <div class="w3-main" style="margin-left: 300px; margin-top: 20px">
    <header class="w3-container" style="padding-top: 25px">
      <table>
        <tr>
          <td>
            <h5>
              <b><i class="fa fa-bug fa-fw"></i> Pathologies</b>
              &nbsp;&nbsp;&nbsp;
              <i
                class="fa fa-plus fa-fw"
                style="color: rgb(1, 128, 5)"
                @click="ouvrirPopup"
              ></i>
            </h5>
          </td>
          <td>
            <div
              class="header-search"
              style="margin-top: 30px; margin-left: 500px"
            >
              <input
                type="text"
                placeholder="Rechercher "
                v-model="recherche"
                @input="filtrerPathologies"
                style="min-width: 80px; margin-top: 5px"
              />
              &nbsp;&nbsp;
            </div>
          </td>
          <td></td>
          <td>
            <i
              class="fa fa-search"
              style="margin-top: 0px; margin-right=100px"
            ></i>
          </td>
        </tr>
      </table>
    </header>
    <div class="popup" v-if="showPopup">
      <div class="popup-content">
        <input
          placeholder="Nom de la pathologie"
          v-model="nom"
          style="min-width: 300px; max-width: 600px"
        />
        &nbsp;&nbsp;&nbsp;
        <!-- Autres champs... -->
        <button @click="submitForm" class="path">Enregistrer</button>
        &nbsp;&nbsp;&nbsp;
        <button @click="fermerPopup" class="path">Annuler</button>
      </div>
    </div>
    <div class="w3-main">
      <div class="w3-row-padding w3-margin-bottom">
        <table id="customers">
          <tr>
            <th>Nom Pathologie</th>

            <th>Actions</th>
          </tr>
          <tr v-for="pathologie in paginatedPathologies" :key="pathologie.id">
            <td>{{ pathologie.nom }}</td>
            <td>
              <i
                class="fa fa-trash"
                style="color: red"
                @click="supprimer(pathologie.id)"
              ></i>
            </td>
          </tr>
        </table>
      </div>

      <div class="pagination" style="margin-top: 35px">
        <button
          @click="previousPage"
          :disabled="currentPage === 0"
          class="page"
          style="margin-left: 10px"
        >
          Précédent</button
        >&nbsp;&nbsp;&nbsp;
        <span style="margin-right: 10px">Page {{ currentPage + 1 }}</span>
        <button
          @click="nextPage"
          :disabled="currentPage === totalPages - 1"
          style="margin-left: 40px"
          class="page"
        >
          Suivant
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import keycloak from "@/main";
import axios from "axios";

export default {
  name: "ListePathologies",
  data() {
    return {
      pathologies: [],
      pathologiesAll: [],
      paginatedPathologies: [],
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
        this.fetchPathologie();
      }
    },
    previousPage() {
      if (this.currentPage > 0) {
        this.currentPage--;
        this.fetchPathologie();
      }
    },
    filtrerPathologies() {
      const recherche = this.recherche.toLowerCase();
      if (recherche === "") {
        this.paginatedPathologies = this.pathologies;
      } else {
        this.paginatedPathologies = this.pathologiesAll.filter((pathologie) => {
          return pathologie.nom.toLowerCase().includes(recherche);
        });
      }
    },

    ouvrirPopup() {
      this.showPopup = true;
    },
    fermerPopup() {
      this.showPopup = false;
    },
    fetchPathologie() {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };

      axios
        .get(
          `http://192.168.224.1:8080/api/pathologie?page=${this.currentPage}`,
          config
        )
        .then((response) => {
          this.pathologies = response.data.content;
          this.totalPages = response.data.totalPages;
          this.paginatedPathologies = response.data.content;
        })
        .catch((error) => {
          console.error(
            "Erreur lors de la récupération des pathologies:",
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
        .get(`http://192.168.224.1:8080/api/pathologie/all`, config)
        .then((response) => {
          this.pathologiesAll = response.data;
        })
        .catch((error) => {
          console.error(
            "Erreur lors de la récupération des pathologies all:",
            error
          );
        });
    },
    async supprimer(Id) {
      if (confirm("Êtes-vous sûr de vouloir supprimer cette pathologie ?")) {
        const accessToken = keycloak.token;
        const config = {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        };
        try {
          await axios.delete(
            `http://192.168.224.1:8080/api/pathologie/${Id}`,
            config
          );
          console.log("Pathologie supprimé avec succès !");
          this.pathologies = this.pathologies.filter((p) => p.id !== Id);
        } catch (error) {
          console.error(
            "Erreur lors de la suppression de la pathologie:",
            error
          );
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
        alert("Veuillez renseigner correctement le champ !");
      } else {
        try {
          await axios.post(
            "http://192.168.224.1:8080/api/pathologie",
            { nom: this.nom },
            config
          );

          console.log("pathologie ajoutée avec succès !");

          this.nom = "";
          this.fetchPathologie();
        } catch (error) {
          console.error("Erreur lors de l'ajout de la pathologie :", error);
        }
      }
    },
  },
  mounted() {
    this.fetchPathologie();
    this.fetchAll();
    console.log(this.pathologiesAll);
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
.popup {
  padding: 20px;

  z-index: 1000;
}
.header-search {
  border-radius: 5px;
}
.path {
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

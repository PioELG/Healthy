<template>
  <div class="container" style="margin-left: 300px; margin-top: 20px">
    <header class="w3-container" style="padding-top: 25px">
      <h5>
        <b><i class="fa fa-address-book fa-fw"></i> Mes Rendez-vous</b>
      </h5>
    </header>
    <div class="w3-main">
      <div class="w3-row-padding w3-margin-bottom">
        <table id="customers">
          <tr>
            <th>NomPatient</th>
            <th>PrenomPatient</th>
            <th>Pathologie</th>
            <th>Date Rdv</th>
            <th>Heure Rdv</th>

            <th>Actions</th>
          </tr>
          <tr v-for="rdv in rdvs" :key="rdv.id">
            <td>{{ getNomPrenom(rdv.malade_id).nom }}</td>
            <td>{{ getNomPrenom(rdv.malade_id).prenom }}</td>
            <td>{{ getNomPrenom(rdv.malade_id).pathologie }}</td>
            <td>{{ rdv.date }}</td>
            <td>{{ rdv.heure }}</td>
            <td>
              <router-link :to="'/ModifierRdv/' + rdv.id"
                ><i class="fa fa-pencil" style="color: blue"></i
              ></router-link>
              &nbsp;&nbsp;&nbsp;
              <i
                class="fa fa-trash"
                style="color: red"
                @click="supprimerRdv(rdv.id)"
              ></i
              >&nbsp;&nbsp;&nbsp;
            </td>
          </tr>
        </table>
      </div>
      <br />
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
      rdvs: [],
      malades: [],
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
          "http://192.168.224.1:8080/api/rdv",
          config
        );
        this.rdvs = rdvResponse.data;

        const maladeResponse = await axios.get(
          "http://192.168.224.1:8080/api/malade/St",
          config
        );
        this.malades = maladeResponse.data;
      } catch (error) {
        console.error(
          "Erreur lors de la récupération des rendez-vous ou des malades:",
          error
        );
      }
    },
    async supprimerRdv(rdvId) {
      if (confirm("Êtes-vous sûr de vouloir supprimer ce rendez-vous ?")) {
        const accessToken = keycloak.token;
        const config = {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        };
        try {
          await axios.delete(
            `http://192.168.224.1:8080/api/rdv/${rdvId}`,
            config
          );
          console.log("Rendez-vous supprimé avec succès !");
          this.rdvs = this.rdvs.filter((r) => r.id !== rdvId);
        } catch (error) {
          console.error("Erreur lors de la suppression du rendez-vous:", error);
        }
      }
    },
    getNomPrenom(maladeId) {
      const malade = this.malades.find((m) => m.id === maladeId);
      if (malade) {
        return {
          nom: malade.nom,
          prenom: malade.prenom,
          pathologie: malade.pathologie,
        };
      }
      return { nom: "", prenom: "", pathologie: "" };
    },
  },
  mounted() {
    this.fetchRdv();
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
</style>

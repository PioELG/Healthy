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
        <b> <i class="fa fa-users fa-fw"></i> Mes Patients </b>
      </h5>
    </header>

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
        <tr v-for="malade in malades" :key="malade.id">
          <td>{{ malade.nom }}</td>
          <td>{{ malade.prenom }}</td>
          <td>{{ malade.email }}</td>
          <td>{{ malade.pathologie }}</td>
          <td>{{ malade.statut }}</td>
          <td>
            <router-link :to="'/InfosPatient/' + malade.id">
              <i class="fa fa-eye" style="color: blue"></i> </router-link
            >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

            <router-link :to="'/Message/' + malade.id"
              ><i class="fa fa-envelope"></i
            ></router-link>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

            <i
              class="fa fa-check fa-lg"
              style="color: green"
              @click="FinTraitement(malade.id)"
            ></i>
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
import axios from "axios";

import jwtDecode from "jwt-decode";

export default {
  name: "MesPatients",

  data() {
    return {
      malades: [],
      doc: "",
      decodedToken: {},
      roles: {},
    };
  },
  methods: {
    fetchMalade() {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };

      axios
        .get("http://localhost:8080/api/malade/St", config)
        .then((response) => {
          this.malades = response.data;
        })
        .catch((error) => {
          console.error(
            "Erreur lors de la récupération des conseils du jour :",
            error
          );
        });
      console.log(this.malades);
    },

    FinTraitement(idM) {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };

      axios
        .put(
          `http://localhost:8080/api/malade/Ft/${idM}`,
          { statut: "FinTraitement" },
          config
        )
        .then((response) => {
          console.log("Réponse du serveur :", response.data);
        })
        .catch((error) => {
          console.error("Erreur lors de la requête PUT :", error);
        });

      axios.delete(`http://localhost:8080/api/rdv/malade/${idM}`, config);

      console.log("Rendez-vous supprimé avec succès !");

      axios.delete(
        `http://localhost:8080/api/notification/malade/${idM}`,
        config
      );

      axios.delete(
        `http://localhost:8080/api/symptomes/malade/${idM}`,
        config
      );

      axios.delete(
        `http://localhost:8080/api/message/malade/${idM}`,
        config
      );

      this.fetchMalade();
      console.log(" supprimé avec succès !");
    },
  },
  mounted() {
    if (keycloak.token) {
      this.decodedToken = jwtDecode(keycloak.token);
      this.roles = this.decodedToken.realm_access.roles;
      if (this.roles.includes("patient")) {
        console.log("hello patient");
        this.doc = false;
      }
      if (this.roles.includes("medecin")) {
        console.log("hello doc");
        this.doc = true;
        console.log(this.doc);
      }
    }
    if (this.doc) {
      this.fetchMalade();
    } else {
      this.$router.push("/");
    }
  },
};
</script>

<style scoped>
#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
  margin-top: 20px;
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

.infos {
  display: none;
}

.fa-pencil:hover .infos {
  display: block;
}
.w3-main {
  height: 100%;
  min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
}
</style>

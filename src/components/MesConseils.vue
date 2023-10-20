<template>
  <!DOCTYPE html>
  <html>
    <head>
      <title>W3.CSS Template</title>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1" />
      <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css" />
      <link
        rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Raleway"
      />
      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
      />
    </head>
    <body>
      <div class="w3-main" style="margin-left: 300px; margin-top: 20px">
        <header style="margin-left: 20px">
          <h5>
            <b><i class="fa fa-heart fa-fw"></i> Mes Conseils</b>
          </h5>
        </header>

        <div>
          <main>
            <section class="conseils">
              <article
                class="conseil"
                v-for="conseil in conseils"
                :key="conseil.id"
              >
                <table>
                  <tr>
                    <td><h2>Conseil du jour</h2></td>
                    <td>
                      <i
                        class="fa fa-exclamation"
                        style="font-size: 45px; color: red"
                      ></i>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <i
                        class="fa fa-pencil"
                        style="color: blue"
                        @click="activerEdition(conseil)"
                      ></i>
                      &nbsp;&nbsp;&nbsp;
                      <!-- Icône de modification -->

                      <i
                        class="fa fa-trash"
                        style="color: red"
                        @click="supprimerConseil(conseil.id)"
                      ></i>
                    </td>
                  </tr>
                </table>

                <div class="modif">
                  <textarea
                    v-if="conseil.editMode"
                    v-model="conseil.contenu"
                    required
                  ></textarea>
                  &nbsp;&nbsp;&nbsp;
                  <button
                    v-if="conseil.editMode"
                    @click="enregistrerConseil(conseil)"
                  >
                    Modifier
                  </button>
                </div>
                <p>{{ conseil.contenu }}</p>
              </article>
            </section>
          </main>
        </div>
      </div>
    </body>
  </html>
</template>

<script>
import keycloak from "@/main";
import axios from "axios";
export default {
  name: "MesConseils",
  data() {
    return {
      conseils: [],
    };
  },
  methods: {
    fetchConseilsDuJour() {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };

      axios
        .get("http://192.168.224.1:8080/api/conseils", config)
        .then((response) => {
          this.conseils = response.data;
        })
        .catch((error) => {
          console.error(
            "Erreur lors de la récupération des conseils du jour :",
            error
          );
        });
    },
    activerEdition(conseil) {
      conseil.editMode = true;
      conseil.conseilEdition = conseil.contenu;
      console.log(conseil.conseilEdition);
    },
    enregistrerConseil(conseil) {
      const accessToken = keycloak.token;
      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };
      conseil.conseilEdition = conseil.contenu;
      axios
        .put(
          `http://192.168.224.1:8080/api/conseils/${conseil.id}`,
          {
            contenu: conseil.conseilEdition,
          },
          config
        )
        .then((response) => {
          conseil.editMode = false;
          conseil.contenu = conseil.conseilEdition;
          console.log("Conseil modifié avec succès !");
          console.log(conseil.contenu);
        })
        .catch((error) => {
          console.error("Erreur lors de la modification du conseil :", error);
        });
    },
    async supprimerConseil(conseilId) {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };
      if (confirm("Êtes-vous sûr de vouloir supprimer ce conseil ?")) {
        try {
          await axios.delete(
            `http://192.168.224.1:8080/api/conseils/${conseilId}`,
            config
          );

          console.log("Conseil supprimé avec succès !");

          this.conseils = this.conseils.filter((c) => c.id !== conseilId);
        } catch (error) {
          console.error("Erreur lors de la suppression du conseil :", error);
        }
      }
    },
  },
  mounted() {
    this.fetchConseilsDuJour();
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
main {
  max-width: 800px;
  margin: 2rem auto;
  padding: 2rem;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.conseils {
  display: flex;
  flex-direction: column;
}

.conseil {
  border: none;
  padding: 1rem;
  margin-bottom: 1rem;
  background-color: #f5f5f5e2;
}

.conseil h2 {
  font-size: 1.5rem;
  margin-bottom: 0.5rem;
}
button {
  background-color: #007bff;
  color: #fff;
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
.modif {
  display: flex;
}
.container {
  height: 100%;
  min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
}
</style>

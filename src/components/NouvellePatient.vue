<template>
  <!DOCTYPE html>
  <html>
    <head>
      <title>W3.CSS Template</title>
      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
      />
    </head>
    <body>
      <div class="w3-main" style="margin-left: 300px; margin-top: 20px">
        <!-- Header -->
        <header class="w3-container" style="padding-top: 27px">
          <h4>
            <b style="margin-left: 30px">Mon Tableau de bord</b>
          </h4>
        </header>

        <div class="w3-row-padding w3-margin-bottom">
          <div class="w3-quarter">
            <router-link to="/MessageP" class="link">
              <div
                class="w3-container w3-black w3-padding-16 w3-container-rounded"
              >
                <div class="w3-left">
                  <i class="fa fa-comment w3-xxxlarge"></i>
                </div>
                <div class="w3-right"></div>
                <div class="w3-clear"></div>
                <h4>Mes Messages</h4>
              </div>
            </router-link>
          </div>

          <div class="w3-quarter">
            <router-link to="/rdvPatient" class="link">
              <div
                class="w3-container w3-blue w3-padding-16 w3-container-rounded"
              >
                <div class="w3-left"><i class="fa fa-eye w3-xxxlarge"></i></div>
                <div class="w3-right"></div>
                <div class="w3-clear"></div>
                <h4>Mes Rendez-Vous</h4>
              </div>
            </router-link>
          </div>

          <div class="w3-quarter" style="max-height: 50px">
            <router-link to="/MesconseilsP" class="link">
              <div
                class="w3-container w3-teal w3-padding-16 w3-container-rounded"
              >
                <div class="w3-left">
                  <i class="fa fa-heart w3-xxxlarge"></i>
                </div>
                <div class="w3-right"></div>
                <div class="w3-clear"></div>
                <h4>Mes Conseils</h4>
              </div>
            </router-link>
          </div>

          <div class="w3-quarter">
            <router-link to="/MonEtat" class="link">
              <div
                class="w3-container w3-orange w3-text-white w3-padding-16 w3-container-rounded"
              >
                <div class="w3-left">
                  <i class="fa fa-stethoscope w3-xxxlarge"></i>
                </div>
                <div class="w3-right"></div>
                <div class="w3-clear"></div>
                <h4>Mon Etat</h4>
              </div>
            </router-link>
          </div>
        </div>

        <div class="w3-panel">
          <div class="w3-row-padding" style="">
            <div class="w3-twothird">
              <table>
                <tr>
                  <td>
                    <h5>Nouvelles</h5>
                  </td>
                  &nbsp;&nbsp;
                  <td>
                    <h5><i class="fa fa-bell"></i></h5>
                  </td>
                </tr>
              </table>

              <table class="w3-table w3-striped w3-white">
                <tr v-for="notif in notifs" :key="notif.id">
                  <td>
                    <i
                      class="fas fa-exclamation-circle"
                      style="font-size: 24px; color: rgb(243, 11, 11)"
                    ></i>
                  </td>
                  <td>
                    <div class="contexte">
                      Votre médecin vient d'ajouter {{ notif.contexte }}
                    </div>
                  </td>
                  <td>
                    <i
                      class="fas fa-check-circle"
                      style="font-size: 24px; color: green"
                      @click="supprimerNotification(notif.id)"
                    ></i>
                  </td>
                </tr>
                <tr v-for="rdv in rdvDemain" :key="rdv.id">
                  <td>
                    <i
                      class="fas fa-exclamation-circle"
                      style="font-size: 24px; color: rgb(243, 11, 11)"
                    ></i>
                  </td>
                  <td>
                    <div class="contexte">
                      Votre rendez-vous avec le médecin est pour demain à
                      <strong>{{ rdv.heure }}</strong>
                    </div>
                  </td>
                  <td>
                    <i
                      class="fas fa-check-circle"
                      style="font-size: 24px; color: green"
                    ></i>
                  </td>
                </tr>
              </table>
            </div>
          </div>
        </div>
      </div>
    </body>
  </html>
</template>

<script>
import keycloak from "@/main";
import axios from "axios";
import { getMessaging, getToken, onMessage } from "firebase/messaging";
import { app } from "/public/firebase";
export default {
  name: "NouvellePatient",
  data() {
    return {
      notifs: [],
      rdvs: [],
      rdvDemain: [],
    };
  },
  methods: {
    fetchNotif() {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };

      axios
        .get("http://192.168.224.1:8080/api/notification/patient", config)
        .then((response) => {
          this.notifs = response.data;
        })
        .catch((error) => {
          console.error("Erreur lors de la récupération des notifs :", error);
        });
    },
    async supprimerNotification(NotifId) {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };
      if (confirm("Êtes-vous sûr de vouloir supprimer cette notification ?")) {
        try {
          await axios.delete(
            `http://192.168.224.1:8080/api/notification/${NotifId}`,
            config
          );

          console.log("prescription supprimée avec succès !");

          this.notifs = this.notifs.filter((n) => n.id !== NotifId);
        } catch (error) {
          console.error(
            "Erreur lors de la suppression de la notification :",
            error
          );
        }
      }
    },
    async fetchRdv() {
      const accessToken = keycloak.token;
      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };

      try {
        const response = await axios.get(
          "http://192.168.224.1:8080/api/rdv/patient",
          config
        );
        this.rdvs = response.data;

        const demain = new Date();
        demain.setDate(demain.getDate() + 1);

        this.rdvDemain = this.rdvs.filter((rdv) => {
          const rdvDate = new Date(rdv.date);
          return rdvDate.toDateString() === demain.toDateString();
        });
      } catch (error) {
        console.error(
          "Erreur lors de la récupération des rendez-vous :",
          error
        );
      }
    },
    requestNotificationPermission() {
      Notification.requestPermission().then((permission) => {
        if (permission === "granted") {
          console.log("Notification permission granted");
          this.subscribeToNotifications();
        } else {
          console.log("Notification permission denied");
        }
      });
    },

    subscribeToNotifications() {
      try {
        const messaging = getMessaging(app); // Get the messaging instance

        onMessage(messaging, (payload) => {
          console.log("Message received. ", payload);
          // ...
        });

        console.log("yo voici les message", messaging);

        const currentToken = getToken(messaging, {
          vapidKey:
            "BG2oABfYY_5Ud9CI92wD7SV7ndU5aXZJRHdZL6tcmmwFR9pqVsDM17Zu-hH1INfQ2shj73F9LtbtTeHSlHCfrHo",
        });

        if (currentToken) {
          // You can now send the 'currentToken' to your server to associate it with the user.
          console.log("FCM Token:", currentToken);
        } else {
          console.log("No registration token available.");
        }
      } catch (error) {
        console.error("Error getting FCM token:", error);
      }
    },

    async sendNotification() {
      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };

      try {
        await axios.post(
          "http://192.168.224.1:8080/api/notification/doc",
          "Connectez vous ",
          config
        );
        console.log("Notification sent successfully!");
      } catch (error) {
        console.error("Error sending notification:", error);
      }
    },

    showDailyNotification() {
      const now = new Date();
      const targetTime = new Date(
        now.getFullYear(),
        now.getMonth(),
        now.getDate(),
        19, // Heure à laquelle vous voulez afficher la notification (7h)
        50, // Minutes
        0 // Secondes
      );

      if (now > targetTime) {
        targetTime.setDate(targetTime.getDate() + 1);
      }

      const timeUntilNotification = targetTime - now;

      setTimeout(() => {
        const notificationMessage =
          "Connectez-vous à votre application Healthy.com aujourd'hui pour vérifier votre prise de médicament.";

        const notification = new Notification("Rappel quotidien", {
          body: notificationMessage,
        });

        notification.onclick = () => {};
      }, timeUntilNotification);
    },
  },
  mounted() {
    this.fetchNotif();
    console.log(this.notifs);
    this.fetchRdv();
    this.showDailyNotification();
    this.requestNotificationPermission();
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
.w3-container-rounded {
  border-radius: 10px;
}
.w3-container-rounded:hover {
  transform: translate(5px, -5px);
  transition: transform 0.2s ease;
}
.w3-container {
  padding-top: 22px;
}
.w3-main {
  height: 100%;
  min-height: 100vh;
}
.w3-row-padding {
  margin-left: 20px;
}

.w3-container {
  padding: 16 px;
}
.link {
  text-decoration: none;
}
.w3-table {
  border-radius: 10px;
  width: 100%;
  min-width: 100vh;
}
.w3-table td i {
  margin-right: 10px;
}

.w3-table td {
  transition: transform 0.2s ease;
}

.w3-table tr:hover td {
  transform: translateX(10px);
}

h5 {
  font-size: 18px;
  margin-bottom: 10px;
}
.contexte {
  border-radius: 10px;
  padding: 10px;
}
</style>

import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import Keycloak from "keycloak-js";
import "vue-search-select/dist/VueSearchSelect.css";
import "./registerServiceWorker";
import firebaseMessaging from "../public/firebase";
//import OneSignalVuePlugin from "@onesignal/onesignal-vue3"; // Import OneSignalVuePlugin here

let initOptions = {
  url: "http://localhost:8180",
  realm: "realm",
  clientId: "health-frontend",
};

let keycloak = new Keycloak(initOptions);

keycloak
  .init({ checkLoginIframe: false })
  .then((auth) => {
    if (!auth) {
      // window.location.reload();
      keycloak.login();
      console.log(auth);
    } else {
      const app = createApp(App, { props: { keycloak: keycloak } });
      app.use(router);

      // Add OneSignalVuePlugin to your Vue app with the appropriate configuration
      /*app.use(OneSignalVuePlugin, {
        appId: "7c64ae25-3c67-4c80-865b-3157777905a1",
      });*/
      app.config.globalProperties.$messaging = firebaseMessaging;
      app.mount("#app");
    }

    localStorage.setItem("vue-token", keycloak?.token);
    localStorage.setItem("vue-refresh-token", keycloak?.refreshToken);

    console.log(keycloak.token);

    setInterval(() => {
      keycloak
        .updateToken(70)
        .then((refreshed) => {
          if (refreshed) {
            console.log("Token refreshed", refreshed);
          } else {
            console.warn(
              "Token not refreshed, valid for " +
                Math.round(
                  keycloak.tokenParsed.exp +
                    keycloak.timeSkew -
                    new Date().getTime() / 1000
                ) +
                " seconds"
            );
          }
        })
        .catch(() => {
          console.error("Failed to refresh token");
        });
    }, 60000);
  })
  .catch((e) => {
    console.log(e);
    console.error("Authentication Failed");
  });

export default keycloak;

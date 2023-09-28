import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import Keycloak from 'keycloak-js'

let initOptions = {
  url: 'http://localhost:8180',
  realm: 'realm',
  clientId: 'health-frontend',

}

let keycloak = new Keycloak(initOptions);



 keycloak.init({checkLoginIframe: false}).then(auth => {
  if (!auth) {
    // window.location.reload();
    keycloak.login()
    console.log(auth)
  } else {
    const app = createApp(App, { props: { keycloak: keycloak } });
    app.use(router);
    app.mount('#app');
  }

 
 localStorage.setItem("vue-token", keycloak?.token);
  localStorage.setItem("vue-refresh-token", keycloak?.refreshToken);

  console.log(keycloak.token);

  setInterval(() => {
    keycloak.updateToken(70).then((refreshed) => {
      if (refreshed) {
        console.log('Token refreshed', refreshed);
      } else {
        console.warn('Token not refreshed, valid for ' +
          Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
      }
    }).catch(() => {
      console.error('Failed to refresh token');
    });
  }, 60000);
  

}).catch((e) => {
    console.log(e)
  console.error("Authentication Failed");
});
export default keycloak;
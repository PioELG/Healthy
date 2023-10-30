if ("serviceWorker" in navigator) {
  navigator.serviceWorker
    .register("/service-worker.js") // Assurez-vous que le chemin relatif est correct par rapport à l'emplacement de votre fichier HTML Vue.js
    .then((registration) => {
      console.log("Service Worker registered with scope:", registration.scope);
    })
    .catch((error) => {
      console.error("Service Worker registration failed:", error);
    });
}

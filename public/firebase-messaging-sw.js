if ("serviceWorker" in navigator) {
  navigator.serviceWorker
    .register("firebase.js")
    .then((reg) => {
      console.log(`Service Worker Registration (Scope: ${reg.scope})`);
    })
    .catch((error) => {
      const msg = `Service Worker Error (${error})`;
      console.error(msg);
    });
} else {
  console.warn("Service Worker not available");
}
/*
import { initializeApp } from "firebase/app";
import { getMessaging } from "firebase/messaging/sw";

// Initialize the Firebase app in the service worker by passing in
// your app's Firebase config object.
// https://firebase.google.com/docs/web/setup#config-object
const firebaseApp = initializeApp({
  apiKey: "AIzaSyDMmjrbEqtmv5XqgYZ76g1uqif-z_roP88",
  authDomain: "springbootnotifs.firebaseapp.com",
  projectId: "springbootnotifs",
  storageBucket: "springbootnotifs.appspot.com",
  messagingSenderId: "248798658047",
  appId: "1:248798658047:web:4ecbb8320d9027ff393bca",
  measurementId: "G-VK8Q46DZLP",
});

// Retrieve an instance of Firebase Messaging so that it can handle background
// messages.
const messaging = getMessaging(firebaseApp);

messaging.onBackgroundMessage(messaging, (payload) => {
  console.log(
    "[firebase-messaging-sw.js] Received background message ",
    payload
  );
  // Customize notification here
  const notificationTitle = payload.notification.title;
  const notificationOptions = {
    body: payload.notification.body,
    icon: "/firebase-logo.png",
  };

  self.registration.showNotification(notificationTitle, notificationOptions);
});
*/

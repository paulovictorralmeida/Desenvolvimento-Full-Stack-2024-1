import { createPinia } from "pinia";
import { createApp } from "vue";
import { createVuestic } from "vuestic-ui";
import "vuestic-ui/css";
import App from "./App.vue";
import "./assets/css/main.css";

createApp(App).use(createPinia()).use(createVuestic()).mount("#app");

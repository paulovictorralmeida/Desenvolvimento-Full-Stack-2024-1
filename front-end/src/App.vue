<template>
  <div>{{ counterStore.name }}</div>
  <button @click="counterStore.increment()">Incrementa</button>
</template>

<script lang="ts">
import { useCounterStore } from '../stores/index'
import { defineComponent } from 'vue';


export default defineComponent({
  name: 'App',
  components: {
  },
  data() {
    return {
      counterStore: useCounterStore()
    }
  },
});
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

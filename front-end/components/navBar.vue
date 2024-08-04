<script setup lang="ts">
const route = useRoute()

const links = [{
  label: 'Home',
  icon: 'i-heroicons-home',
  to: '/'
}, {
  label: 'Juízes',
  icon: 'i-ion-clipboard',
  to: '/juizes/'
}, {
  label: 'Atletas',
  icon: 'i-ion-body-sharp',
  to: '/atletas/'
}, {
  label: 'Faixas',
  icon: 'i-ion-barbell',
  to: '/faixas/'
}]
</script>

<template>
  <UHorizontalNavigation :links="links" class="border-b border-gray-200 dark:border-gray-800" />
</template>


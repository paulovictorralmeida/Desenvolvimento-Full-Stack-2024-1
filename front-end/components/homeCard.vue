<template>
  <UCard>
    <Placeholder class="h-32" />
    {{ number }}
    {{ type }}
  </UCard>
</template>

<script>
  export default {
    name: 'homeCard',
    props: {
      type: String,
    },
    data() {
      return {
        number: 0
      }
    },
    methods: {
      getData() {

      },
    }
  }
</script>
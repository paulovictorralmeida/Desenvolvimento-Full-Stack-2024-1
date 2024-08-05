<template>
  <div class="table-container">
    <div class="button-container">
      <UButton @click="handleClick">Cadastrar</UButton>
    </div>
    <JudgesTable />
  </div>
</template>

<script>
export default {
  name: "CadastroJuizes",
  methods: {
    handleClick() {
      this.$router.push({ path: '/juizes/edit/', query: { id: '0' } })
    }
  },
};
</script>
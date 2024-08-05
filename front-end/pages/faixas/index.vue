<template>
  <div class="table-container">
    <div class="button-container">
      <UButton @click="handleClick">Cadastrar</UButton>
    </div>
    <WeightRangesTable />
  </div>
</template>

<script>
export default {
  name: "CadastroFaixas",
  methods: {
    handleClick() {
      this.$router.push('/faixas/edit/')
    }
  },
};
</script>

<style>
.table-container {
  padding: 40px;
}
</style>
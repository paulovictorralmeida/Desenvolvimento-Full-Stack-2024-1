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
      this.$router.push({ path: '/faixas/edit/', query: { id: '0' } })
    }
  },
};
</script>

<style>
.table-container {
  padding: 40px;
}
</style>
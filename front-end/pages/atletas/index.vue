<template>
  <div class="table-container">
    <div class="button-container">
      <UButton @click="handleClick">Cadastrar</UButton>
    </div>
    <AthletesTable />
  </div>
</template>

<script>
export default {
  name: "CadastroAtletas",
  methods: {
    handleClick() {
      this.$router.push({ path: '/atletas/edit/', query: { id: '0' } })
    }
  },
};
</script>

<style>
.button-container {
  padding-bottom: 20px;
  display: flex;
  justify-content: flex-end;
}
</style>
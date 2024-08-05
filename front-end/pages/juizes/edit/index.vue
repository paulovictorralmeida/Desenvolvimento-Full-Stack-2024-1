<template>
  <div class="card-form-container">
    <UCard>
      <template #header>
        <h3 v-if="isEditPage">Editar Juíz</h3>
        <h3 v-else>Cadastrar Juíz</h3>
        <Placeholder class="h-8" />
      </template>

      <div class="card-form-input">
        <UInput v-model="nome" placeholder="Nome" />
      </div>
      <div class="card-form-input">
        <UInput v-model="idade" placeholder="Idade" />
      </div>
      <div class="card-form-input">
        <UInput v-model="cpf" placeholder="CPF" />
      </div>
      <div class="card-form-input">
        <UInput v-model="rg" placeholder="RG" />
      </div>

      <div v-if="hasError" class="card-form-error-text">
        <span>Você deve preencher todos os campos!</span>
      </div>

      <template #footer>
        <div class="card-form-footer">
          <UButton @click="handleBack">Voltar</UButton>
          <UButton @click="handleClick">Salvar</UButton>
        </div>
      </template>
    </UCard>
  </div>
  <UModal v-model="isOpen">
    <div class="p-4">
      <span>Não foi possível realizar a solicitação!</span>
    </div>
  </UModal>
</template>

<script>
export default {
  data() {
    return {
      nome: "",
      idade: "",
      cpf: "",
      rg: "",
      hasError: false,
      isOpen: false,
    };
  },
  computed: {
    id() {
      return this.$route.query.id;
    },
    isEditPage() {
      return this.id !== "0";
    },
  },
  methods: {
    handleBack() {
      this.$router.push({ path: "/juizes/" });
    },
    async handleClick() {
      if (
        this.nome.length === 0 ||
        this.idade.length === 0 ||
        this.cpf.length === 0 ||
        this.rg.length === 0
      ) {
        this.hasError = true;
      } else {
        this.hasError = false;
        const data = {
          nome: this.nome,
          idade: this.idade,
          cpf: this.cpf,
          rg: this.rg,
        };
        try {
          const response = await $fetch("http://localhost:8081/ufc-back/juizes", {
          method: "POST",
          body: {
            data,
          },
        });
        if (response.data === 200) {
          this.handleBack();
        }
        } catch(error) {
          this.isOpen = true
          console.log(error)
        }
      }
    },
  },
};
</script>

<style>
.card-form-container {
  padding: 40px;
}
.card-form-input {
  padding: 10px 0;
}
.card-form-footer {
  display: flex;
  justify-content: space-between;
}
.card-form-error-text {
  display: flex;
  justify-content: flex-end;
  font-size: small;
  margin-top: 20px;
  color: #f07178;
}
</style>

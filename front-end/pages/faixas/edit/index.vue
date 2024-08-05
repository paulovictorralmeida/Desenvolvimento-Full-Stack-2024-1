<template>
  <div class="card-form-container">
    <UCard>
      <template #header>
        <h3 v-if="isEditPage">Editar Faixas</h3>
        <h3 v-else>Cadastrar Faixas</h3>
      </template>

      <div class="card-form-input">
        <UInput v-model="pesoMinimo" placeholder="Minimo" @input="handleWeightValidation" />
      </div>
      <div class="card-form-input">
        <UInput v-model="pesoMaximo" placeholder="Máximo" @input="handleWeightValidation" />
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
      pesoMinimo: "",
      pesoMaximo: "",
      hasError: false,
      isOpen: false,
      method: "POST",
      toast: useToast()
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
  mounted() {
    if (this.isEditPage) {
      this.getData();
      this.method = "PUT";
    }
  },
  methods: {
    async getData() {
      try {
        const data = await $fetch(
          `http://localhost:8081/ufc-back/faixas/${this.id}`,
          {
            method: "GET",
          }
        );
        this.pesoMinimo = data.pesoMinimo;
        this.pesoMaximo = data.pesoMaximo;
      } catch (error) {
        this.handleBack();
      }
    },
    handleBack() {
      this.$router.push({ path: "/faixas/" });
    },
    async handleClick() {
      if (
        this.pesoMinimo.length === 0 ||
        this.pesoMaximo.length === 0 
      ) {
        this.hasError = true;
      } else {
        this.hasError = false;
        try {
          const response = await $fetch(
            !this.isEditPage
              ? `http://localhost:8081/ufc-back/faixas`
              : `http://localhost:8081/ufc-back/faixas/${this.id}`,
            {
              method: this.method,
              body: {
                pesoMinimo: this.pesoMinimo,
                pesoMaximo: this.pesoMaximo,
              },
            }
          );
          this.toast.add({ title: 'As informações foram salvas!' })
          this.handleBack();
        } catch (error) {
          this.isOpen = true;
          console.log(error);
        }
      }
    },
    handleWeightValidation(e) {
      let value = event.target.value;
      value = value.replace(/\D/g, '');

      if (value.length > 3) {
        value = value.slice(0, 3);
      }

      event.target.value = value;
    }
  },
};
</script>

<style>
.card-form-container {
  padding: 40px;
  max-width: 600px;
  margin: auto;
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

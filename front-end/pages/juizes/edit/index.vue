<template>
  <div class="card-form-container">
    <UCard>
      <template #header>
        <h3 v-if="isEditPage">Editar Juíz</h3>
        <h3 v-else>Cadastrar Juíz</h3>
      </template>

      <div class="card-form-input">
        <UInput v-model="nome" placeholder="Nome" />
      </div>
      <div class="card-form-input">
        <UInput
          v-model="idade"
          placeholder="Idade"
          @input="handleBirthValidation"
        />
      </div>
      <div class="card-form-input">
        <UInput v-model="cpf" placeholder="CPF" @input="handleCpfValidation" />
      </div>
      <div class="card-form-input">
        <UInput v-model="rg" placeholder="RG" @input="handleRgValidation" />
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
          `http://localhost:8081/ufc-back/juizes/${this.id}`,
          {
            method: "GET",
          }
        );
        this.nome = data.nome;
        this.idade = data.idade;
        this.cpf = data.cpf;
        this.rg = data.rg;
      } catch (error) {
        this.handleBack();
      }
    },
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
        try {
          const response = await $fetch(
            !this.isEditPage
              ? `http://localhost:8081/ufc-back/juizes`
              : `http://localhost:8081/ufc-back/juizes/${this.id}`,
            {
              method: this.method,
              body: {
                nome: this.nome,
                idade: this.idade,
                cpf: this.cpf,
                rg: this.rg,
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
    handleCpfValidation(e) {
      let value = event.target.value;
      value = value.replace(/\D/g, "");

      if (value.length > 11) {
        value = value.slice(0, 11);
      }

      event.target.value = value;
    },
    handleRgValidation(e) {
      let value = event.target.value;
      value = value.replace(/\D/g, "");

      if (value.length > 7) {
        value = value.slice(0, 7);
      }

      event.target.value = value;
    },
    handleBirthValidation(e) {
      let value = event.target.value;
      value = value.replace(/\D/g, "");

      if (value.length > 2) {
        value = value.slice(0, 2);
      }

      event.target.value = value;
    },
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

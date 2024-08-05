<template>
  <CardSection />
  <div class="home-section-container">
    <UAlert
      icon="i-ion-clipboard"
      color="rose"
      variant="subtle"
      title="Último juiz cadastrado:"
      :description="juiz.nome"
    />
    <UAlert
      icon="i-ion-body-sharp"
      color="primary"
      variant="subtle"
      title="Último atleta cadastrado:"
      :description="atleta.nome"
    />
    <UAlert
      icon="i-ion-barbell"
      color="blue"
      variant="subtle"
      title="Última faixa de peso cadastrada:"
      :description="`${faixa.pesoMinimo} - ${faixa.pesoMaximo}`"
    />
  </div>
</template>

<script>
export default {
  name: "homeIndex",
  data() {
    return {
      juiz: '',
      atleta: '',
      faixa: ''
    };
  },
  created() {
    this.getJuiz();
    this.getAtleta();
    this.getFaixa();
  },
  methods: {
    async getJuiz() {
      const data = await $fetch(`http://localhost:8081/ufc-back/juizes`, {
        method: "GET",
      });
      const maiorId = Math.max(...data.result.map(juiz => juiz.id));
      const juiz = data.result.find(juiz => juiz.id === maiorId);
      this.juiz = juiz
    },
    async getAtleta() {
      const data = await $fetch(`http://localhost:8081/ufc-back/atletas`, {
        method: "GET",
      });
      const maiorId = Math.max(...data.result.map(atleta => atleta.id));
      const atleta = data.result.find(atleta => atleta.id === maiorId);
      this.atleta = atleta
    },
    async getFaixa() {
      const data = await $fetch(`http://localhost:8081/ufc-back/faixas`, {
        method: "GET",
      });
      const maiorId = Math.max(...data.result.map(faixa => faixa.id));
      const faixa = data.result.find(faixa => faixa.id === maiorId);
      this.faixa = faixa
    },
  }, 
};
</script>

<style>
.home-section-container {
  max-width: 600px;
  margin: auto;
  padding: 0 20px;
  display: flex;
  gap: 20px;
  flex-direction: column;
}
</style>

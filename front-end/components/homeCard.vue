<template>
  <UCard>
    <span class="card-number" :class="type">{{ number }}</span>
    {{ type }}
  </UCard>
</template>

<script>
export default {
  name: "homeCard",
  props: {
    type: String,
  },
  data() {
    return {
      number: 0,
    };
  },
  created() {
    this.getData();
  },
  methods: {
    async getData() {
      const data = await $fetch(`http://localhost:8081/ufc-back/${this.type}`, {
        method: "GET",
      });
      this.number = data.totalResults;
    },
  }, 
};
</script>

<style>
.card-number {
  font-size: 32px;
}

.juizes {
  color: #F97583;
}

.atletas {
  color: #42B883;
}

.faixas {
  color: #79B8FF;
}
</style>

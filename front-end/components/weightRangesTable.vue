<script>
export default {
  name: "weightRangesTable",
  data() {
    return {
      columns: [],
      faixas: [],
      items: [],
    };
  },
  created() {
    this.getData();
    
    this.columns = [
      {
        key: "pesoMinimo",
        label: "Peso Mínimo",
      },
      {
        key: "pesoMaximo",
        label: "Peso Máximo",
      },
      {
        key: "actions",
      },
    ];
    
    this.items = (row) => [
      [
        {
          label: "Editar",
          icon: "i-heroicons-pencil-square-20-solid",
          click: () => this.handleEditClick(row.id),
        },
        {
          label: "Deletar",
          icon: "i-heroicons-trash-20-solid",
          click: () => this.deleteFaixa(row.id),
        },
      ],
    ];
  },
  methods: {
    async getData() {
      const data = await $fetch(`http://localhost:8081/ufc-back/faixas`, {
        method: "GET",
      });
      this.faixas = data.result;
    },
    async deleteFaixa(id) {
      try {
        const data = await $fetch(`http://localhost:8081/ufc-back/faixas/${id}`, {
          method: "DELETE",
        });
        this.faixas = this.faixas.filter(faixa => faixa.id !== id);
      } catch (error) {
        console.log(error)
      }
    },
    handleEditClick(id) {
      this.$router.push({ path: '/faixas/edit/', query: { id: id } })
    }
  },
};
</script>

<template>
  <UCard>
    <UTable :rows="faixas" :columns="columns">
      <template #actions-data="{ row }">
        <UDropdown :items="items(row)">
          <UButton
            color="gray"
            variant="ghost"
            icon="i-heroicons-ellipsis-horizontal-20-solid"
          />
        </UDropdown>
      </template>
    </UTable>
  </UCard>
</template>

<script>
export default {
  name: "weightRangesTable",
  data() {
    return {
      columns: [],
      atletas: [],
      items: [],
    };
  },
  created() {
    this.getData();
    
    this.columns = [{
  key: 'nome',
  label: 'Nome'
}, {
  key: 'idade',
  label: 'Idade'
}, {
  key: 'cpf',
  label: 'CPF'
}, {
  key: 'rg',
  label: 'RG'
}, 
{
  key: 'cidade',
  label: 'Cidade'
}, {
  key: 'actions'
}];
    
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
          click: () => this.deleteAtleta(row.id),
        },
      ],
    ];
  },
  methods: {
    async getData() {
      const data = await $fetch(`http://localhost:8081/ufc-back/atletas`, {
        method: "GET",
      });
      this.atletas = data.result;
    },
    async deleteAtleta(id) {
      try {
        const data = await $fetch(`http://localhost:8081/ufc-back/atletas/${id}`, {
          method: "DELETE",
        });
        this.atletas = this.atletas.filter(atleta => atleta.id !== id);
      } catch (error) {
        console.log(error)
      }
    },
    handleEditClick(id) {
      this.$router.push({ path: '/atletas/edit/', query: { id: id } })
    }
  },
};
</script>

<template>
  <UCard>    
    <UTable  :rows="atletas" :columns="columns">
      <template #actions-data="{ row }">
        <UDropdown :items="items(row)">
          <UButton color="gray" variant="ghost" icon="i-heroicons-ellipsis-horizontal-20-solid" />
        </UDropdown>
      </template>
    </UTable>
  </UCard>
</template>
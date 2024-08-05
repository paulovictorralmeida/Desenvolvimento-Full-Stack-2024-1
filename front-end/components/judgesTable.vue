<script>
export default {
  name: "judgesTable",
  data() {
    return {
      columns: [],
      juizes: [],
      items: [],
    };
  },
  created() {
    this.getData();

    this.columns = [
      {
        key: "nome",
        label: "Nome",
      },
      {
        key: "idade",
        label: "Idade",
      },
      {
        key: "cpf",
        label: "CPF",
      },
      {
        key: "rg",
        label: "RG",
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
          click: () => this.deleteJuiz(row.id),
        },
      ],
    ];
  },
  methods: {
    async getData() {
      const data = await $fetch(`http://localhost:8081/ufc-back/juizes`, {
        method: "GET",
      });
      this.juizes = data.result;
    },
    async deleteJuiz(id) {
      try {
        const data = await $fetch(
          `http://localhost:8081/ufc-back/juizes/${id}`,
          {
            method: "DELETE",
          }
        );
        this.juizes = this.juizes.filter((juiz) => juiz.id !== id);
      } catch (error) {
        console.log(error);
      }
    },
    handleEditClick(id) {
      this.$router.push({ path: "/juizes/edit/", query: { id: id } });
    },
  },
};
</script>

<template>
  <UCard>
    <UTable :rows="juizes" :columns="columns">
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

<script setup lang="ts">
const columns = [{
  key: 'pesoMinimo',
  label: 'Peso Mínimo'
}, {
  key: 'pesoMaximo',
  label: 'Peso Máximo'
}, {
  key: 'actions'
}]

const people = [{
  id: 1,
  pesoMinimo: '30',
  pesoMaximo: '60',
}, {
  id: 2,
  pesoMinimo: '85',
  pesoMaximo: '105',
}]

const items = (row: any) => [
  [{
    label: 'Editar',
    icon: 'i-heroicons-pencil-square-20-solid',
    click: () => console.log('Edit', row.id)
  }, {
    label: 'Deletar',
    icon: 'i-heroicons-trash-20-solid',
    click: () => console.log('Delete', row.id)
  }]
]
</script>

<template>
  <UTable  :rows="people" :columns="columns">
    <template #actions-data="{ row }">
      <UDropdown :items="items(row)">
        <UButton color="gray" variant="ghost" icon="i-heroicons-ellipsis-horizontal-20-solid" />
      </UDropdown>
    </template>
  </UTable>
</template>
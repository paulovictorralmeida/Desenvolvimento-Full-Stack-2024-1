<script setup lang="ts">
const columns = [{
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
}, {
  key: 'actions'
}]

const people = [{
  id: 1,
  nome: 'Lindsay Walton',
  idade: 'Front-end Developer',
  cpf: 'lindsay.walton@example.com',
  rg: 'Member'
}, {
  id: 2,
  nome: 'Courtney Henry',
  idade: 'Designer',
  cpf: 'courtney.henry@example.com',
  rg: 'Admin'
}, {
  id: 3,
  nome: 'Tom Cook',
  idade: 'Director of Product',
  cpf: 'tom.cook@example.com',
  rg: 'Member'
}, {
  id: 4,
  nome: 'Whitney Francis',
  idade: 'Copywriter',
  cpf: 'whitney.francis@example.com',
  rg: 'Admin'
}, {
  id: 5,
  nome: 'Leonard Krasner',
  idade: 'Senior Designer',
  cpf: 'leonard.krasner@example.com',
  rg: 'Owner'
}, {
  id: 6,
  nome: 'Floyd Miles',
  idade: 'Principal Designer',
  cpf: 'floyd.miles@example.com',
  rg: 'Member'
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
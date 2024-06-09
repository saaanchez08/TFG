<template>
    <div class="categorias">
      <h1>Categorías de Maquinarias</h1>
      <FormularioCategorias />
    </div>
  </template>
  
  <script>
  import FormularioCategorias from '@/components/Formularios/FormularioCategorias.vue';
  
  export default {
    name: 'Categorias',
    components: {
      FormularioCategorias
    }
  }
  </script>
  
  
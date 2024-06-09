<template>
    <div class="alquiler">
      <h1>Alquiler de Maquinarias</h1>
      <FormularioAlquiler />
    </div>
  </template>
  
  <script>
  import FormularioAlquiler from '@/components/Formularios/FormularioAlquiler.vue';
  
  export default {
    name: 'Alquiler',
    components: {
      FormularioAlquiler
    }
  }
  </script>

  
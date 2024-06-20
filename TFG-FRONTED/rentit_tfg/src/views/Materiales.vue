<template>
    <div class="Materiales">
      <h1>Materiales</h1>
      <FormularioMateriales />
    </div>
</template>
  
<script>
  import FormularioMateriales from '@/components/Formularios/FormularioMateriales.vue';
    
    export default {
      name: 'Materiales',
      components: {
        FormularioMateriales,             
      },
      data() {
        return {
          form: {
            materialID: "",
            nombre: "",
            descripcion: "",
            precio: "",
            estado: "Disponible",
            categoriaID: "",
          },
        };
      },
      methods: {
        submitForm() {
          console.log(this.form);
        },
      },
    };
</script>
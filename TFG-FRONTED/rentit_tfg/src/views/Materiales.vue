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
        // Aquí puedes agregar la lógica para enviar los datos a tu servidor
        // por ejemplo, usando Axios para hacer una petición HTTP POST
        // axios.post('/api/materiales', this.form)
        //   .then(response => {
        //     console.log('Datos enviados:', response.data);
        //   })
        //   .catch(error => {
        //     console.error('Error al enviar los datos:', error);
        //   });
      },
    },
  };
  </script>
  
  
  
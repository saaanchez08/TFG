<template>
    <div id="app">
      <h1>Formulario de Materiales</h1>
      <form @submit.prevent="submitForm">
        <div>
          <label for="materialID">Material ID:</label>
          <input type="number" v-model="form.materialID" required />
        </div>
        <div>
          <label for="nombre">Nombre:</label>
          <input type="text" v-model="form.nombre" required />
        </div>
        <div>
          <label for="descripcion">Descripción:</label>
          <input type="text" v-model="form.descripcion" required />
        </div>
        <div>
          <label for="precio">Precio:</label>
          <input type="number" v-model="form.precio" required />
        </div>
        <div>
          <label for="estado">Estado:</label>
          <select v-model="form.estado" required>
           <option value="Disponible">Disponible</option>
            <option value="No Disponible">No Disponible</option>
          </select>
        </div>
        <div>
          <label for="categoriaID">Categoría ID:</label>
          <input type="number" v-model="form.categoriaID" required />
        </div>
        <button type="submit">Enviar</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        form: {
          materialID: '',
          nombre: '',
          descripcion: '',
          precio: '',
          estado: 'Disponible',
          categoriaID: ''
        }
      }
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
      }
    }
  }
  </script>
  

  <style scoped>
  form {
    max-width: 400px;
    margin: auto;
    padding: 20px;
    background-color: #f4f4f4;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  div {
    margin-bottom: 15px;
  }
  label {
    display: block;
    margin-bottom: 5px;
  }
  input {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
  }
  button {
    padding: 10px 20px;
    background-color: #007bff;
    color: white;
    border: none;
    cursor: pointer;
  }
  button:hover {
    background-color: #0056b3;
  }
  ul {
    list-style-type: none;
    padding: 0;
    max-width: 400px;
    margin: auto;
  }
  li {
    background-color: #007bff;
    color: white;
    margin: 10px 0;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  h3 {
    margin: 0 0 10px 0;
  }
  p {
    margin: 5px 0;
  }
  </style>
  
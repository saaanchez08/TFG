<template>
  <div id="app">
    <h1>Formulario de Materiales</h1>
    <form @submit.prevent="buscarMaterial">
      <div>
        <label for="materialID">Material ID:</label>
        <input type="number" v-model="form.materialID" />
      </div>
      <div>
        <label for="nombre">Nombre:</label>
        <input type="text" v-model="form.nombre" />
      </div>
      <div>
        <label for="descripcion">Descripción:</label>
        <input type="text" v-model="form.descripcion" />
      </div>
      <div>
        <label for="precio">Precio:</label>
        <input type="number" v-model="form.precio" />
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
        <input type="number" v-model="form.categoriaID" />
      </div>
      <button type="submit">Buscar</button>
    </form>
    <ul v-if="resultados.length">
      <li v-for="material in resultados" :key="material.materialID">
        <h3>{{ material.nombre }}</h3>
        <p>ID: {{ material.materialID }}</p>
        <p>Descripción: {{ material.descripcion }}</p>
        <p>Precio: {{ material.precio }}</p>
        <p>Estado: {{ material.estado }}</p>
        <p>Categoría ID: {{ material.categoriaID }}</p>
      </li>
    </ul>
    <p v-else-if="busquedaRealizada" class="no-results">No se encontraron resultados con los filtros proporcionados.</p>
  </div>
</template>

<script>
import axios from 'axios';

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
      },
      resultados: [],
      busquedaRealizada: false
    };
  },
  methods: {
    async buscarMaterial() {
      try {
        const params = {};
        if (this.form.materialID) params.materialID = this.form.materialID;
        if (this.form.nombre) params.nombre = this.form.nombre;
        if (this.form.descripcion) params.descripcion = this.form.descripcion;
        if (this.form.precio) params.precio = this.form.precio;
        if (this.form.estado) params.estado = this.form.estado;
        if (this.form.categoriaID) params.categoriaID = this.form.categoriaID;

        const response = await axios.get('http://localhost:8081/tienda/material', { params });
        this.resultados = response.data;
        this.busquedaRealizada = true; // Marca que la búsqueda ha sido realizada
      } catch (error) {
        console.error('Error al obtener datos del backend:', error);
      }
    }
  }
};
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
  
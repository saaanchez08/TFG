<template>
    <div>
      <h2>Búsqueda de Categorías</h2>
      <form @submit.prevent="buscarCategoria">
        <div>
          <label for="id">ID Producto:</label>
          <input type="number" id="id" v-model="id" placeholder="Buscar por ID">
        </div>
        <div>
          <label for="nombre">Nombre:</label>
          <input type="text" id="nombre" v-model="nombre" placeholder="Buscar por nombre">
        </div>
        <div>
          <label for="descripcion">Descripción:</label>
          <input type="text" id="descripcion" v-model="descripcion" placeholder="Buscar por descripción">
        </div>
        <button type="submit">Buscar</button>
      </form>
      <ul v-if="resultados.length">
        <li v-for="categoria in resultados" :key="categoria.id">
          <h3>{{ categoria.nombre }}</h3>
          <p>ID Producto: {{ categoria.id }}</p>
          <p>Descripción: {{ categoria.descripcion }}</p>
        </li>
      </ul>
      <p v-else>No se encontraron resultados.</p>
    </div>
  </template>
  
  <script>
  export default {
    name: 'FormularioCategorias',
    data() {
      return {
        id: '',
        nombre: '',
        descripcion: '',
        categorias: [
          { id: 1, nombre: 'Excavadoras', descripcion: 'Maquinaria utilizada para excavar.' },
          { id: 2, nombre: 'Bulldozers', descripcion: 'Maquinaria utilizada para empujar grandes cantidades de tierra.' },
          { id: 3, nombre: 'Retroexcavadoras', descripcion: 'Maquinaria combinada para excavar y cargar.' },
          { id: 4, nombre: 'Gruas', descripcion: 'Maquinaria utilizada para levantar y mover objetos pesados.' },
          { id: 5, nombre: 'Cargadoras', descripcion: 'Maquinaria utilizada para cargar materiales en camiones.' }
        ],
        resultados: []
      };
    },
    methods: {
      buscarCategoria() {
        this.resultados = this.categorias.filter(categoria => {
          return (
            (this.id === '' || categoria.id === Number(this.id)) &&
            (this.nombre === '' || categoria.nombre.toLowerCase().includes(this.nombre.toLowerCase())) &&
            (this.descripcion === '' || categoria.descripcion.toLowerCase().includes(this.descripcion.toLowerCase()))
          );
        });
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
  
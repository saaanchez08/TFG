<template>
  <div>
    <h2>Búsqueda de Categorías</h2>
    <form @submit.prevent="buscarCategoria">
      <div>
        <label for="categoriaID">ID Categoría:</label>
        <input type="number" id="categoriaID" v-model="categoriaID" placeholder="Buscar por ID">
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
      <li v-for="categoria in resultados" :key="categoria.categoriaID">
        <h3>{{ categoria.nombre }}</h3>
        <p>ID Categoría: {{ categoria.categoriaID }}</p>
        <p>Descripción: {{ categoria.descripcion }}</p>
      </li>
    </ul>
    <p v-else-if="hasSearched" class="no-results">No se encontraron resultados.</p>
  </div>
</template>

<script>
export default {
  name: 'FormularioCategorias',
  data() {
    return {
      categoriaID: '',
      nombre: '',
      descripcion: '',
      resultados: [],
      hasSearched: false 
    };
  },
  methods: {
    buscarCategoria() {
      const url = new URL('http://localhost:8081/tienda/categoria');
      const params = new URLSearchParams();

      if (this.categoriaID) params.append('categoriaID', this.categoriaID);
      if (this.nombre) params.append('nombre', this.nombre);
      if (this.descripcion) params.append('descripcion', this.descripcion);

      fetch(`${url}?${params.toString()}`)
        .then(response => response.json())
        .then(data => {
          this.resultados = data;
          this.hasSearched = true; 
        })
        .catch(error => {
          console.error('Error al obtener los datos:', error);
          this.hasSearched = true; 
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
  
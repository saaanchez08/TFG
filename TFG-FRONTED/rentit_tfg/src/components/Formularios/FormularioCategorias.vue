<template>
  <div id="app">
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
      <button type="submit"><span></span>Buscar</button>
    </form>
    <div v-if="resultados.length" class="resultados-grid">
      <div v-for="categoria in resultados" :key="categoria.categoriaID" class="resultado-item">
        <h3>{{ categoria.nombre }}</h3>
        <p>ID Categoría: {{ categoria.categoriaID }}</p>
        <p>Descripción: {{ categoria.descripcion }}</p>
      </div>
    </div>
    <p v-else-if="hasSearched" class="no-results">No se encontraron resultados.</p>
    <footer class="footer">
      <div class="footer-content">
        <h3>RENT.<span style="color: yellow">IT</span></h3>
      </div>
    </footer>
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
    font-size: 20px;
    font-family: sans-serif;
    font-weight: bolder;
  }
  input {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
  }
  button {
    padding: 0.9em 1.8em;
    text-transform: uppercase;
    text-decoration: none;
    letter-spacing: 4px;
    color: transparent;
    border: 3px solid #007bff;
    font-size: 14px;
    position: relative;
    font-family: inherit;
    background: transparent;
    cursor: pointer;
  }

  button::before {
    content: "Buscar";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: #363636;
    color: white;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: all 0.5s;
  }

  button:hover::before {
    left: 100%;
    transform: scale(0) rotateY(360deg);
    opacity: 0;
  }

  button::after {
    content: "Buscar";
    position: absolute;
    top: 0;
    left: -100%;
    width: 100%;
    height: 100%;
    background-color: #363636;
    color: white;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: all 0.5s;
    transform: scale(0) rotateY(0deg);
    opacity: 0;
  }

  button:hover::after {
    left: 0;
    transform: scale(1) rotateY(360deg);
    opacity: 1;
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
  .resultados-grid {
    width: 50%;
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(20%, 1fr)); /* Ajuste de tamaño mínimo de columnas */
    gap: 10px; /* Espacio entre los elementos */
    margin-top: 20px;
    margin-left: 25%;
  }

  .resultado-item {
    background-color: #363636;
    color: #ffffff;
    padding: 10px; /* Reducir padding */
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Reducir sombra */
    font-size: 14px; /* Ajuste de tamaño de fuente */
    border: solid 3px #007bff;
  }

  .no-results {
    color: red;
    margin-top: 20px;
  }
  .footer {
    width: 100%;
    padding: 10px 20px;
    position: fixed;
    bottom: 0;
  }

  .footer-content {
    display: flex;
    justify-content: flex-start;
  }

  .footer-content h3 {
    font-weight: bold;
    font-size: 40px;
    font-family: sans-serif;
  }
</style>
  
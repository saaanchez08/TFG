<template>
  <div id="app">
    <h2>Búsqueda de Categorías</h2>
    <form @submit.prevent="buscarCategoria">
      <div>
        <label for="categoriaID">ID Categoría:</label>
        <input type="number" id="categoriaID" v-model="form.categoriaID" placeholder="Buscar por ID">
      </div>
      <div>
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" v-model="form.nombre" placeholder="Buscar por nombre">
      </div>
      <div>
        <label for="descripcion">Descripción:</label>
        <input type="text" id="descripcion" v-model="form.descripcion" placeholder="Buscar por descripción">
      </div>
      <button type="submit" class="buscar-button"><span></span>Buscar</button>
    </form>

    <div class="boton-insert">
      <button @click="mostrarFormularioInsercion" class="insertar-button">Insertar Categoria</button>
    </div>

    <div v-if="resultados.length" class="resultados-grid">
      <div v-for="categoria in resultados" :key="categoria.categoriaID" class="resultado-item">
        <div class="categoria-header">
          <h3>{{ categoria.nombre }}</h3>
          <button @click="eliminarCategoria(categoria.categoriaID)" class="icono-eliminar">
            <i class="fas fa-trash"></i>
          </button>
          <button @click="mostrarFormularioEditar(categoria)" class="icono-modificar">
            <i class="fas fa-edit"></i>
          </button>
        </div>
        <p>ID Categoría: {{ categoria.categoriaID }}</p>
        <p>Descripción: {{ categoria.descripcion }}</p>
      </div>
    </div>
    <p v-else-if="hasSearched" class="no-results">No se encontraron resultados.</p>

    <!-- Formulario para insertar -->
    <form v-if="mostrarFormularioInsertar" @submit.prevent="insertarCategoria">
      <div>
        <label for="nombreNuevo">Nombre:</label>
        <input type="text" v-model="nuevoCategoria.nombre" required />
      </div>
      <div>
        <label for="descripcionNuevo">Descripción:</label>
        <input type="text" v-model="nuevoCategoria.descripcion" />
      </div>
      <button type="submit" class="insertar-button"><span></span>Insertar</button>
    </form>

    <!-- Formulario para modificación -->
    <div v-if="categoriaAEditar">
      <h2>Editar Categoria</h2>
      <form @submit.prevent="editarCategoria">
        <div>
          <label for="nombreEditar">Nombre:</label>
          <input type="text" v-model="categoriaAEditar.nombre" required />
        </div>
        <div>
          <label for="descripcionEditar">Descripción:</label>
          <input type="text" v-model="categoriaAEditar.descripcion" required />
        </div>
        <button type="submit" class="guardar-button"><span></span>Guardar Cambios</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'FormularioCategorias',
  data() {
    return {
      form: {
        categoriaID: '',
        nombre: '',
        descripcion: ''
      },
      resultados: [],
      hasSearched: false,
      mostrarFormularioInsertar: false,
      nuevoCategoria: {
        nombre: '',
        descripcion: ''
      },
      categoriaAEditar: null
    };
  },
  methods: {
    mostrarFormularioInsercion() {
      this.mostrarFormularioInsertar = true;
    },

    async insertarCategoria() {
      try {
        await axios.post('http://localhost:8081/tienda/categoria', this.nuevoCategoria);
        alert('Categoria insertada correctamente');
        this.nuevoCategoria = {
          nombre: '',
          descripcion: ''
        };
        this.mostrarFormularioInsertar = false;
        this.buscarCategoria();
      } catch (error) {
        console.error('Error al insertar categoria:', error);
        alert('Error al insertar la categoria');
      }
    },

    async buscarCategoria() {
      try {
        const params = {};
        if (this.form.categoriaID) params.categoriaID = this.form.categoriaID;
        if (this.form.nombre) params.nombre = this.form.nombre;
        if (this.form.descripcion) params.descripcion = this.form.descripcion;

        const response = await axios.get('http://localhost:8081/tienda/categoria', { params });
        this.resultados = response.data;
        this.hasSearched = true;
      } catch (error) {
        console.error('Error al obtener los datos:', error);
        this.hasSearched = true;
      }
    },

    async eliminarCategoria(id) {
      if (confirm('¿Estás seguro de que deseas eliminar esta Categoria?')) {
        try {
          await axios.delete(`http://localhost:8081/tienda/categoria/${id}`);
          this.resultados = this.resultados.filter((categoria) => categoria.categoriaID !== id);
          alert('Categoria eliminada con éxito');
        } catch (error) {
          console.error('Error al eliminar la categoria:', error);
          alert('Error al eliminar la categoria');
        }
      }
    },

    mostrarFormularioEditar(categoria) {
      this.categoriaAEditar = { ...categoria };
    },

    async editarCategoria() {
      try {
        await axios.put(`http://localhost:8081/tienda/categoria/${this.categoriaAEditar.categoriaID}`, this.categoriaAEditar);
        alert('Categoria modificada correctamente');
        this.categoriaAEditar = null;
        this.buscarCategoria();
      } catch (error) {
        console.error('Error al modificar la categoria:', error);
        alert('Error al modificar la categoria');
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
.boton-insert{
text-align: center;
margin: 20px;
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
.buscar-button {
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

.buscar-button::before {
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

.buscar-button:hover::before {
  left: 100%;
  transform: scale(0) rotateY(360deg);
  opacity: 0;
}

.buscar-button::after {
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

.buscar-button:hover::after {
  left: 0;
  transform: scale(1) rotateY(360deg);
  opacity: 1;
}

.insertar-button {
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

.insertar-button::before {
  content: "Insertar";
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

.insertar-button:hover::before {
  left: 100%;
  transform: scale(0) rotateY(360deg);
  opacity: 0;
}

.insertar-button::after {
  content: "Insertar Categoria";
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

.insertar-button:hover::after {
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
  grid-template-columns: repeat(auto-fill, minmax(20%, 1fr)); 
  gap: 10px; 
  margin-top: 20px;
  margin-left: 25%;
}

.resultado-item {
  background-color: #363636;
  color: #ffffff;
  padding: 10px; 
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); 
  font-size: 14px; 
  border: solid 3px #007bff;
  position: relative;
}

.categoria-header {
  display: flex;
  justify-content: space-evenly;
}

.icono-eliminar {
  background: none;
  border: none;
  color: #ff4d4d;
  cursor: pointer;
  font-size: 16px;
}

.icono-eliminar:hover {
  color: #ff1a1a;
}

.no-results {
  color: red;
  margin-top: 20px;
}

</style>
  
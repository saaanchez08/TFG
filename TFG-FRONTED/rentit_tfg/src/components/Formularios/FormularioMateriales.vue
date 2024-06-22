<template>
  <div id="app">
    <h1>Formulario de Materiales</h1>
    <form @submit.prevent="buscarMaterial">
      <div>
        <label for="materialID">Material ID:</label>
        <input type="number" v-model="form.materialID" placeholder="Buscar por ID" />
      </div>
      <div>
        <label for="nombre">Nombre:</label>
        <input type="text" v-model="form.nombre" placeholder="Buscar por nombre" />
      </div>
      <div>
        <label for="descripcion">Descripción:</label>
        <input type="text" v-model="form.descripcion" placeholder="Buscar por descripción" />
      </div>
      <div>
        <label for="precio">Precio:</label>
        <input type="number" v-model="form.precio" placeholder="Buscar por precio" />
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
        <input type="number" v-model="form.categoriaID" placeholder="Buscar por CategoriaID" />
      </div>
      <button type="submit" class="buscar-button"><span></span>Buscar</button>
    </form>

    <div class="boton-insert">
      <button @click="mostrarFormularioInsercion" class="insertar-button">Insertar Material</button>
    </div>

    <div v-if="resultados.length" class="resultados-grid">
      <div v-for="material in resultados" :key="material.materialID" class="resultado-item">
        <div class="material-header">
          <h3>{{ material.nombre }}</h3>
          <button @click="eliminarMaterial(material.materialID)" class="icono-eliminar">
            <i class="fas fa-trash"></i>
          </button>
          <button @click="mostrarFormularioEditar(material)" class="icono-modificar">
            <i class="fas fa-edit"></i>
          </button>
        </div>
        <p>ID: {{ material.materialID }}</p>
        <p>Descripción: {{ material.descripcion }}</p>
        <p>Precio: {{ material.precio }} /dia</p>
        <p>Categoría ID: {{ material.categoriaID }}</p>
      </div>
    </div>
    
    <p v-else-if="busquedaRealizada" class="no-results">No se encontraron resultados con los filtros proporcionados.</p>

    <!-- Formulario para insertar -->
    <form v-if="mostrarFormularioInsertar" @submit.prevent="insertarMaterial">
      <div>
        <label for="nombreNuevo">Nombre:</label>
        <input type="text" v-model="nuevoMaterial.nombre" required />
      </div>
      <div>
        <label for="descripcionNuevo">Descripción:</label>
        <input type="text" v-model="nuevoMaterial.descripcion" />
      </div>
      <div>
        <label for="precioNuevo">Precio:</label>
        <input type="number" v-model.number="nuevoMaterial.precio" />
      </div>
      <div>
        <label for="estadoNuevo">Estado:</label>
        <select v-model="nuevoMaterial.estado" required>
          <option value="Disponible">Disponible</option>
          <option value="No Disponible">No Disponible</option>
        </select>
      </div>
      <div>
        <label for="categoriaIDNuevo">Categoría ID:</label>
        <input type="number" v-model.number="nuevoMaterial.categoriaID" />
      </div>
      <button type="submit" class="insertar-button"><span></span>Insertar</button>
    </form>

    <!-- Formulario para modificación -->
    <div v-if="materialAEditar">
      <h2>Editar Material</h2>
      <form @submit.prevent="editarMaterial">
        <div>
          <label for="nombreEditar">Nombre:</label>
          <input type="text" v-model="materialAEditar.nombre" required />
        </div>
        <div>
          <label for="descripcionEditar">Descripción:</label>
          <input type="text" v-model="materialAEditar.descripcion" required />
        </div>
        <div>
          <label for="precioEditar">Precio:</label>
          <input type="number" v-model="materialAEditar.precio" required />
        </div>
        <div>
          <label for="estadoEditar">Estado:</label>
          <select v-model="materialAEditar.estado" required>
            <option value="Disponible">Disponible</option>
            <option value="No Disponible">No Disponible</option>
          </select>
        </div>
        <div>
          <label for="categoriaIDEditar">Categoría ID:</label>
          <input type="number" v-model="materialAEditar.categoriaID" required />
        </div>
        <button type="submit" class="guardar-button"><span></span>Guardar Cambios</button>
      </form>
    </div>
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
      busquedaRealizada: false,
      mostrarFormularioInsertar: false,
      nuevoMaterial: {
        nombre: '',
        descripcion: '',
        precio: '',
        estado: 'Disponible',
        categoriaID: ''
      },
      materialAEditar: null
    };
  },
  methods: {
    mostrarFormularioInsercion() {
      this.mostrarFormularioInsertar = true;
    },

    async insertarMaterial() {
      try {
        await axios.post('http://localhost:8081/tienda/material', this.nuevoMaterial);
        alert('Material insertado correctamente');
        this.nuevoMaterial = {
          nombre: '',
          descripcion: '',
          precio: '',
          estado: 'Disponible',
          categoriaID: ''
        };
        this.mostrarFormularioInsertar = false;
        this.buscarMaterial(); // Actualizar la lista después de insertar
      } catch (error) {
        console.error('Error al insertar material:', error);
        alert('Error al insertar el material');
      }
    },

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
        this.busquedaRealizada = true;
      } catch (error) {
        console.error('Error al obtener datos del backend:', error);
      }
    },

    async eliminarMaterial(id) {
      if (confirm('¿Estás seguro de que deseas eliminar este material?')) {
        try {
          await axios.delete(`http://localhost:8081/tienda/material/${id}`);
          this.resultados = this.resultados.filter((material) => material.materialID !== id);
          alert('Material eliminado con éxito');
        } catch (error) {
          console.error('Error al eliminar el material:', error);
          alert('Error al eliminar el material');
        }
      }
    },

    mostrarFormularioEditar(material) {
      this.materialAEditar = { ...material };
    },

    async editarMaterial() {
      try {
        await axios.put(`http://localhost:8081/tienda/material/${this.materialAEditar.materialID}`, this.materialAEditar);
        alert('Material modificado correctamente');
        this.materialAEditar = null;
        this.buscarMaterial(); // Actualizar la lista después de modificar
      } catch (error) {
        console.error('Error al modificar el material:', error);
        alert('Error al modificar el material');
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
select{
  padding: 10px;  
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
  content: "Insertar Material";
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

.material-header {
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

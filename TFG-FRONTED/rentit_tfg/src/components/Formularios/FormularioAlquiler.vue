<template>
  <div id="app">
    <div class="flex">
      <form @submit.prevent="buscarAlquiler">
        <h2>Búsqueda de Alquileres</h2>
        <div>
          <label for="alquilerID">ID Alquiler:</label>
          <input type="number" id="alquilerID" v-model="alquilerID" placeholder="Buscar por ID">
        </div>
        <div>
          <label for="fechaInicio">Fecha de Inicio:</label>
          <input type="date" id="fechaInicio" v-model="fechaInicio">
        </div>
        <div>
          <label for="fechaFin">Fecha de Fin:</label>
          <input type="date" id="fechaFin" v-model="fechaFin" :min="fechaInicio">
        </div>
        <div>
          <label for="materialID">Material:</label>
          <select id="materialID" v-model="materialID">
            <option value="" disabled>Selecciona un material</option>
            <option v-for="material in materiales" :key="material.materialID" :value="material.materialID">
              {{ material.nombre }}
            </option>
          </select>
        </div>
        <div>
          <label for="precio">Precio:</label>
          <input type="number" id="precio" v-model="precio" step="0.01">
        </div>
        <button type="submit" class="buscar-button"><span></span>Buscar</button>
      </form>
        <!-- Formulario para insertar -->
      <form v-if="mostrarFormularioInsertar" @submit.prevent="insertarAlquiler">
        <h2>Insercion de Alquileres</h2>
        <div>
          <label for="materialIDNuevo">Material:</label>
          <select id="materialIDNuevo" v-model="nuevoAlquiler.materialID" required>
            <option value="" disabled>Selecciona un material</option>
            <option v-for="material in materiales" :key="material.materialID" :value="material.materialID">
              {{ material.nombre }}
            </option>
          </select>
        </div>
        <div>
          <label for="fechaInicioNuevo">Fecha de Inicio:</label>
          <input type="date" id="fechaInicioNuevo" v-model="nuevoAlquiler.fecha_inicio" required @change="calculatePrecioTotal('nuevo')">
        </div>
        <div>
          <label for="fechaFinNuevo">Fecha de Fin:</label>
          <input type="date" id="fechaFinNuevo" v-model="nuevoAlquiler.fecha_fin" required @change="calculatePrecioTotal('nuevo')" :min="nuevoAlquiler.fecha_inicio">
        </div>
        <div>
          <label for="precioTotalNuevo">Precio Total:</label>
          <input type="text" id="precioTotalNuevo" v-model="nuevoAlquiler.precio" readonly>
        </div>
        <button type="submit" class="insertar-button"><span></span>Insertar</button>
      </form>
        <!-- Formulario para modificación -->
        <div v-if="alquilerAEditar">
          <form @submit.prevent="editarAlquiler">
            <h2>Modificacion de Alquileres</h2>
            <div>
              <label for="materialIDEditar">Material:</label>
              <select id="materialIDEditar" v-model="alquilerAEditar.materialID" required>
                <option value="" disabled>Selecciona un material</option>
                <option v-for="material in materiales" :key="material.materialID" :value="material.materialID">
                  {{ material.nombre }}
                </option>
              </select>
            </div>
            <div>
              <label for="fechaInicioEditar">Fecha de Inicio:</label>
              <input type="date" id="fechaInicioEditar" v-model="alquilerAEditar.fecha_inicio" required>
            </div>
            <div>
              <label for="fechaFinEditar">Fecha de Fin:</label>
              <input type="date" id="fechaFinEditar" v-model="alquilerAEditar.fecha_fin" required @change="calculatePrecioTotal('editar')" :min="alquilerAEditar.fecha_inicio">
            </div>
            <div>
              <label for="precioTotalEditar">Precio Total:</label>
              <input type="text" id="precioTotalEditar" v-model="alquilerAEditar.precio" readonly>
            </div>
            <button type="submit" class="guardar-button"><span></span>Guardar Cambios</button>
          </form>
        </div>
      </div>
    </div>
 
    <div class="boton-insert">
      <button @click="mostrarFormularioInsercion" class="insertar-button">Insertar Alquiler</button>
    </div>

    <div v-if="resultados.length" class="resultados-grid">
      <div v-for="alquiler in resultados" :key="alquiler.alquilerID" class="resultado-item">
        <div class="alquiler-header">
          <h3>{{ getMaterialNombre(alquiler.materialID) }}</h3>
          <button @click="eliminarAlquiler(alquiler.alquilerID)" class="icono-eliminar">
            <i class="fas fa-trash"></i>
          </button>
          <button @click="mostrarFormularioEditar(alquiler)" class="icono-modificar">
            <i class="fas fa-edit"></i>
          </button>
        </div>
        <p>ID Alquiler: {{ alquiler.alquilerID }}</p>
        <p>Fecha de Inicio: {{ alquiler.fecha_inicio }}</p>
        <p>Fecha de Fin: {{ alquiler.fecha_fin }}</p>
        <p>Precio: {{ alquiler.precio }} /dia</p>
      </div>
    </div>
    <p v-else-if="hasSearched" class="no-results">No se encontraron resultados.</p>
</template>

<script>
import axios from 'axios';
import jsPDF from 'jspdf';

export default {
  name: 'FormularioAlquiler',
  data() {
    return {
      alquilerID: '',
      fechaInicio: '',
      fechaFin: '',
      materialID: '',
      precio: '',
      materiales: [],
      resultados: [],
      nuevoAlquiler: {
        fecha_inicio: '',
        fecha_fin: '',
        materialID: '',
        precio: ''
      },
      resumenAlquiler: {
        fecha_inicio: '',
        fecha_fin: '',
        materialID: '',
        precio: '',
        alquilerID: '' 
      },
      alquilerAEditar: null,
      mostrarFormularioInsertar: false,
      hasSearched: false,
      alquilerRealizado: false
    };
  },
  methods: {
    mostrarFormularioInsercion() {
      this.mostrarFormularioInsertar = true;
    },
    async insertarAlquiler() {
      try {
        const response = await axios.post('http://localhost:8081/tienda/alquiler/realizar', {
          fecha_inicio: this.nuevoAlquiler.fecha_inicio,
          fecha_fin: this.nuevoAlquiler.fecha_fin,
          material: {
            materialID: this.nuevoAlquiler.materialID
          },
          precio: this.nuevoAlquiler.precio
        });
        alert('Alquiler insertado correctamente');
        this.resumenAlquiler = { ...this.nuevoAlquiler, alquilerID: response.data.alquilerID };
        this.alquilerRealizado = true;
        this.nuevoAlquiler = {
          fecha_inicio: '',
          fecha_fin: '',
          materialID: '',
          precio: ''
        };
        this.mostrarFormularioInsertar = false;
        this.buscarAlquiler();
        this.generarPDF(this.resumenAlquiler);
      } catch (error) {
        console.error('Error al insertar alquiler:', error);
        alert('Error al insertar el alquiler');
      }
    },
    buscarAlquiler() {
      const url = new URL('http://localhost:8081/tienda/alquiler');
      const params = new URLSearchParams();

      if (this.alquilerID) params.append('alquilerID', this.alquilerID);
      if (this.fechaInicio) params.append('fecha_inicio', this.fechaInicio);
      if (this.fechaFin) params.append('fecha_fin', this.fechaFin);
      if (this.materialID) params.append('materialID', this.materialID);
      if (this.precio) params.append('precio', this.precio);

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
    },
    async eliminarAlquiler(id) {
      if (confirm('¿Estás seguro de que deseas eliminar este Alquiler?')) {
        try {
          await axios.delete(`http://localhost:8081/tienda/alquiler/${id}`);
          this.resultados = this.resultados.filter((alquiler) => alquiler.alquilerID !== id);
          alert('Alquiler eliminado correctamente');
        } catch (error) {
          console.error('Error al eliminar alquiler:', error);
          alert('Error al eliminar el alquiler');
        }
      }
    },
    mostrarFormularioEditar(alquiler) {
      alert('Si quieres modificar el material que quieres alquilar, debes borrar las fechas introduccidas y volverlas a poner');

      this.alquilerAEditar = { ...alquiler };
    },
    async editarAlquiler() {
      try {
        await axios.put(`http://localhost:8081/tienda/alquiler/${this.alquilerAEditar.alquilerID}`, {
          fecha_inicio: this.alquilerAEditar.fecha_inicio,
          fecha_fin: this.alquilerAEditar.fecha_fin,
          materialID: this.alquilerAEditar.materialID,
          precio: this.alquilerAEditar.precio
        });
        alert('Alquiler actualizado correctamente');
        this.generarPDF(this.alquilerAEditar);
        this.alquilerAEditar = null;
        this.buscarAlquiler();
      } catch (error) {
        console.error('Error al actualizar alquiler:', error);
        alert('Error al actualizar el alquiler');
      }
    },
    async fetchMateriales() {
      try {
        const response = await axios.get('http://localhost:8081/tienda/material');
        this.materiales = response.data;
      } catch (error) {
        console.error('Error al obtener la lista de materiales:', error);
        alert('Error al cargar la lista de materiales');
      }
    },
    calculatePrecioTotal(tipo) {
      let fechaInicio, fechaFin, materialID;
      if (tipo === 'nuevo') {
        fechaInicio = this.nuevoAlquiler.fecha_inicio;
        fechaFin = this.nuevoAlquiler.fecha_fin;
        materialID = this.nuevoAlquiler.materialID;
      } else if (tipo === 'editar') {
        fechaInicio = this.alquilerAEditar.fecha_inicio;
        fechaFin = this.alquilerAEditar.fecha_fin;
        materialID = this.alquilerAEditar.materialID;
      }

      if (fechaInicio && fechaFin && materialID) {
        const start = new Date(fechaInicio);
        const end = new Date(fechaFin);
        const diffTime = Math.abs(end - start);
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

        const material = this.materiales.find(m => m.materialID === parseInt(materialID));

        if (material) {
          if (tipo === 'nuevo') {
            this.nuevoAlquiler.precio = (material.precio * diffDays).toFixed(2);
          } else if (tipo === 'editar') {
            this.alquilerAEditar.precio = (material.precio * diffDays).toFixed(2);
          }
        }
      }
    },
    getMaterialNombre(materialID) {
      const material = this.materiales.find(m => m.materialID === parseInt(materialID));
      return material ? material.nombre : '';
    },
    generarPDF(alquiler) {
      const doc = new jsPDF();
      doc.text('Resumen del Alquiler', 10, 10);
      doc.text(`Fecha de Inicio: ${alquiler.fecha_inicio}`, 10, 20);
      doc.text(`Fecha de Fin: ${alquiler.fecha_fin}`, 10, 30);
      doc.text(`Material: ${this.getMaterialNombre(alquiler.materialID)}`, 10, 40);
      doc.text(`Precio Total: ${alquiler.precio}`, 10, 50);

      doc.text('Gracias por alquilar con Rent.it', 10, 70);
      doc.save(`alquiler_${alquiler.alquilerID}.pdf`);
    }
  },
  mounted() {
    this.fetchMateriales();
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
input, select {
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

.pdf-button {
  padding: 0.9em 1.8em;
  text-transform: uppercase;
  text-decoration: none;
  letter-spacing: 4px;
  color: transparent;
  border: 3px solid red;
  font-size: 14px;
  position: relative;
  font-family: inherit;
  background: transparent;
  cursor: pointer;
}

.pdf-button::before {
  content: "PDF";
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

.pdf-button:hover::before {
  left: 100%;
  transform: scale(0) rotateY(360deg);
  opacity: 0;
}

.pdf-button::after {
  content: "Generar PDF";
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

.pdf-button:hover::after {
  left: 0;
  transform: scale(1) rotateY(360deg);
  opacity: 1;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
}

.form-resumen-container {
  display: flex;
  justify-content: center; 
  align-items: flex-start;
  gap: 20px; 
  width: 100%; 
}

.form-container, .resumen-container {
  flex: 1;
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 8px;
  background-color: #f9f9f9;
  max-width: 400px; 
  width: 50%; 
}
.resumen {
  display: flex;
  flex-direction: column;
  border: 3px solid red;
  padding: 20px;
  margin-top: 20px;
  text-align: left;
  background-color: #363636;
  color: white
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

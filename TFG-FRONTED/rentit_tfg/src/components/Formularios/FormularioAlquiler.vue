<template>
  <div>
    <form @submit.prevent="submitForm">
      <div>
        <label for="fecha_inicio">Fecha de Inicio:</label>
        <input type="date" id="fecha_inicio" v-model="fecha_inicio" required>
      </div>
      <div>
        <label for="fecha_fin">Fecha de Fin:</label>
        <input type="date" id="fecha_fin" v-model="fecha_fin" required @change="calculatePrecioTotal">
      </div>
      <div>
        <label for="materialID">Material:</label>
        <select id="materialID" v-model="materialID" required>
          <option value="" disabled>Selecciona un material</option>
          <option v-for="material in materiales" :key="material.materialID" :value="material.materialID">
            {{ material.nombre }}
          </option>
        </select>
      </div>
      <div>
        <label for="precioTotal">Precio Total:</label>
        <input type="text" id="precioTotal" v-model="precioTotal" readonly>
      </div>
      <button type="submit">Realizar Alquiler</button>
    </form>

    <!-- Mensaje de éxito -->
    <div v-if="successMessage" class="success-message">
      {{ successMessage }}
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'FormularioAlquiler',
  data() {
    return {
      fecha_inicio: '',
      fecha_fin: '',
      materialID: '',
      materiales: [], // Aquí se almacenarán los materiales obtenidos del backend
      precioTotal: '',
    };
  },
  methods: {
    async submitForm() {
      if (!this.fecha_inicio || !this.fecha_fin || !this.materialID) {
        alert('Por favor completa todos los campos');
        return;
      }

      this.calculatePrecioTotal();

      const formData = {
        fecha_inicio: this.fecha_inicio,
        fecha_fin: this.fecha_fin,
        material: {
          materialID: this.materialID
        },
        precio: parseFloat(this.precioTotal)
      };

      try {
        const response = await axios.post('http://localhost:8081/tienda/alquiler/realizar', formData);
        console.log('Respuesta del servidor:', response.data);
        alert('Alquiler realizado con éxito');
        this.resetForm();
      } catch (error) {
        console.error('Error al realizar el alquiler:', error.response ? error.response.data : error.message);
        alert('Error al realizar el alquiler');
      }
    },
    async fetchMateriales() {
      try {
        // Obtener la lista de materiales desde el backend solo una vez al cargar el formulario
        const response = await axios.get('http://localhost:8081/tienda/material');
        this.materiales = response.data; // Asignar los materiales al array en data()
      } catch (error) {
        console.error('Error al obtener la lista de materiales:', error);
        alert('Error al cargar la lista de materiales');
      }
    },
    calculatePrecioTotal() {
      if (this.fecha_inicio && this.fecha_fin && this.materialID) {
        // Calcular la diferencia en días entre las fechas
        const start = new Date(this.fecha_inicio);
        const end = new Date(this.fecha_fin);
        const diffTime = Math.abs(end - start);
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

        // Obtener el material seleccionado
        const material = this.materiales.find(m => m.materialID === parseInt(this.materialID));

        // Calcular el precio total basado en el precio del material y los días de alquiler
        if (material) {
          this.precioTotal = (material.precio * diffDays).toFixed(2);
        }
      } else {
        this.precioTotal = '';
      }
    },
    resetForm() {
      this.fecha_inicio = '';
      this.fecha_fin = '';
      this.materialID = '';
      this.precioTotal = '';
    },
  },
  mounted() {
    // Cargar la lista de materiales al montar el componente
    this.fetchMateriales();
  },
};
</script>

<style scoped>
form {
  max-width: 400px;
  margin: auto;
}
div {
  margin-bottom: 15px;
}
label {
  display: block;
  margin-bottom: 5px;
}
input, select {
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
</style>

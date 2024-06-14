<template>
  <form @submit.prevent="submitForm">
    <div>
      <label for="fechaInicio">Fecha de Inicio:</label>
      <input type="date" id="fechaInicio" v-model="fechaInicio" required>
    </div>
    <div>
      <label for="fechaFin">Fecha de Fin:</label>
      <input type="date" id="fechaFin" v-model="fechaFin" required @change="calculatePrecioTotal">
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
</template>

<script>
import axios from 'axios';

export default {
  name: 'FormularioAlquiler',
  data() {
    return {
      fechaInicio: '',
      fechaFin: '',
      materialID: '',
      materiales: [], // Aquí se almacenarán los materiales obtenidos del backend
      precioTotal: '',
    };
  },
  methods: {
    async submitForm() {
      // Validar formulario antes de enviar
      if (!this.fechaInicio || !this.fechaFin || !this.materialID) {
        alert('Por favor completa todos los campos');
        return;
      }

      // Calcular precio total antes de enviar
      this.calculatePrecioTotal();

      // Preparar objeto de datos para enviar al backend
      const formData = {
        fechaInicio: this.fechaInicio,
        fechaFin: this.fechaFin,
        materialID: this.materialID,
        precioTotal: this.precioTotal,
      };

      try {
        // Realizar la solicitud POST al backend para guardar el alquiler
        const response = await axios.post('http://localhost:puerto/tienda/alquiler/realizar', formData);

        console.log('Respuesta del servidor:', response.data);
        alert('Alquiler realizado con éxito');
        this.resetForm(); // Reiniciar formulario después del éxito
      } catch (error) {
        console.error('Error al realizar el alquiler:', error.response.data);
        alert('Error al realizar el alquiler');
      }
    },
    async fetchMateriales() {
      try {
        // Obtener la lista de materiales desde el backend solo una vez al cargar el formulario
        const response = await axios.get('http://localhost:puerto/tienda/materiales');
        this.materiales = response.data; // Asignar los materiales al array en data()
      } catch (error) {
        console.error('Error al obtener la lista de materiales:', error);
        alert('Error al cargar la lista de materiales');
      }
    },
    calculatePrecioTotal() {
      if (this.fechaInicio && this.fechaFin && this.materialID) {
        // Calcular la diferencia en días entre las fechas
        const start = new Date(this.fechaInicio);
        const end = new Date(this.fechaFin);
        const diffTime = Math.abs(end - start);
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

        // Obtener el material seleccionado
        const material = this.materiales.find(m => m.materialID === this.materialID);

        // Calcular el precio total basado en el precio del material y los días de alquiler
        if (material) {
          this.precioTotal = (material.precio * diffDays).toFixed(2);
        }
      } else {
        this.precioTotal = '';
      }
    },
    resetForm() {
      this.fechaInicio = '';
      this.fechaFin = '';
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

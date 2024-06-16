<template>
  <div class="container">
    <form @submit.prevent="submitForm">
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
        <label for="fechaInicio">Fecha de Inicio:</label>
        <input type="date" id="fechaInicio" v-model="fechaInicio" required>
      </div>
      <div>
        <label for="fechaFin">Fecha de Fin:</label>
        <input type="date" id="fechaFin" v-model="fechaFin" required :min="fechaInicio" @change="calculatePrecioTotal">
      </div>
      <div>
        <label for="precioTotal">Precio Total:</label>
        <input type="text" id="precioTotal" v-model="precioTotal" readonly>
      </div>
      <button type="submit">Realizar Alquiler</button>
    </form>
    <div v-if="alquilerRealizado" class="resumen">
      <h2>Resumen del Alquiler</h2>
      <p><strong>Fecha de Inicio:</strong> {{ fechaInicio }}</p>
      <p><strong>Fecha de Fin:</strong> {{ fechaFin }}</p>
      <p><strong>Material:</strong> {{ getMaterialNombre(materialID) }}</p>
      <p><strong>Precio Total:</strong> {{ precioTotal }}</p>
      <button @click="generarPDF">Generar PDF</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import jsPDF from 'jspdf';

export default {
  name: 'FormularioAlquiler',
  data() {
    return {
      fechaInicio: '',
      fechaFin: '',
      materialID: '',
      materiales: [], // Aquí se almacenarán los materiales obtenidos del backend
      precioTotal: '',
      alquilerRealizado: false, // Nuevo estado para mostrar el resumen
    };
  },
  methods: {
        async submitForm() {
      if (!this.fechaInicio || !this.fechaFin || !this.materialID) {
        alert('Por favor completa todos los campos');
        return;
      }

      if (new Date(this.fechaFin) < new Date(this.fechaInicio)) {
        alert('La fecha de fin debe ser posterior o igual a la fecha de inicio');
        return;
      }

      this.calculatePrecioTotal();

      const formData = {
        fecha_inicio: this.fechaInicio,
        fecha_fin: this.fechaFin,
        material: {
          materialID: this.materialID
        },
        precio: parseFloat(this.precioTotal)
      };

      try {
        const response = await axios.post('http://localhost:8081/tienda/alquiler/realizar', formData);
        console.log('Respuesta del servidor:', response.data);
        alert('Alquiler realizado con éxito');
        this.alquilerRealizado = true; // Mostrar el resumen al realizar el alquiler
      } catch (error) {
        console.error('Error al realizar el alquiler:', error.response.data);
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
      if (this.fechaInicio && this.fechaFin && this.materialID) {
        // Calcular la diferencia en días entre las fechas
        const start = new Date(this.fechaInicio);
        const end = new Date(this.fechaFin);
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
      this.fechaInicio = '';
      this.fechaFin = '';
      this.materialID = '';
      this.precioTotal = '';
    },
    getMaterialNombre(materialID) {
      const material = this.materiales.find(m => m.materialID === parseInt(materialID));
      return material ? material.nombre : '';
    },
    generarPDF() {
      const doc = new jsPDF();
      doc.text('Resumen del Alquiler', 10, 10);
      doc.text(`Fecha de Inicio: ${this.fechaInicio}`, 10, 20);
      doc.text(`Fecha de Fin: ${this.fechaFin}`, 10, 30);
      doc.text(`Material: ${this.getMaterialNombre(this.materialID)}`, 10, 40);
      doc.text(`Precio Total: ${this.precioTotal}`, 10, 50);

      doc.text('Gracias por alquilar con Rent.it', 10, 70);
      doc.save('resumen_alquiler.pdf');
    }
  },
  mounted() {
    // Cargar la lista de materiales al montar el componente
    this.fetchMateriales();
  },
};
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  width: 400px;
  margin-bottom: 20px;
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



.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
}

.form-resumen-container {
  display: flex;
  justify-content: center; /* Centrar horizontalmente */
  align-items: flex-start; /* Alinear elementos al inicio verticalmente */
  gap: 20px; /* Espacio entre el formulario y el resumen */
  width: 100%; /* Para asegurar que el contenedor ocupe todo el ancho disponible */
}

.form-container, .resumen-container {
  flex: 1;
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 8px;
  background-color: #f9f9f9;
  max-width: 400px; /* Ancho máximo para los contenedores */
  width: 50%; /* Ancho del contenedor */
}

.resumen {
  display: flex;
  flex-direction: column;
  width: 400px;
  border: 1px solid #ccc;
  padding: 20px;
  margin-top: 20px;
  text-align: left;
}
</style>

<template>
  <form @submit.prevent="submitForm">

   
    <div>
      <label for="fechaInicio">Fecha de Inicio:</label>
      <input type="date" id="fechaInicio" v-model="fechaInicio" required>
    </div>
    <div>
      <label for="fechaFin">Fecha de Fin:</label>
      <input type="date" id="fechaFin" v-model="fechaFin" required @input="calculateDuracion">
    </div>

    <div>
      <label for="materialID">MaterialID(nombre del material):</label>
      <select id="materialID" v-model="materialID" required @change="updatePrecio">
        <option value="" disabled>Selecciona un material</option>
        <option v-for="item in materiales" :key="item.materialID" :value="item.materialID">
          {{ item.materialID }}
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
export default {
  name: 'FormularioAlquiler',
  data() {
    return {
      nombre: '',
      materialID: '',
      fechaInicio: '',
      fechaFin: '',
      precioTotal: '',
      materiales: [
        { materialID: 1, precio: 150.00 },
        { materialID: 2, precio: 200.00 },
      ]
    };
  },
  // methods: {
  //   submitForm() {
  //     const alquiler = {
  //       nombre: this.nombre,
  //       materialID: this.materialID,
  //       fechaInicio: this.fechaInicio,
  //       fechaFin: this.fechaFin,
  //       duracion: this.duracion,
  //       precioTotal: this.precioTotal
  //     };
  //     console.log('Alquiler realizado:', alquiler);
  //     alert('Alquiler realizado con éxito');
  //     this.resetForm();
  //   },
  //   resetForm() {
  //     this.nombre = '';
  //     this.materialID = '';
  //     this.fechaInicio = '';
  //     this.fechaFin = '';
  //     this.duracion = 0;
  //     this.precioTotal = '';
  //   },
  //   updatePrecio() {
  //     this.calculatePrecioTotal();
  //   },
  //   calculateDuracion() {
  //     if (this.fechaInicio && this.fechaFin) {
  //       const startDate = new Date(this.fechaInicio);
  //       const endDate = new Date(this.fechaFin);
  //       const diffTime = Math.abs(endDate - startDate);
  //       this.duracion = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1;
  //       this.calculatePrecioTotal();
  //     }
  //   },
  //   calculatePrecioTotal() {
  //     if (this.materialID && this.duracion > 0) {
  //       const material = this.materiales.find(m => m.materialID === this.materialID);
  //       this.precioTotal = (material.precio * this.duracion).toFixed(2);
  //     }
  //   }
  // }
}
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

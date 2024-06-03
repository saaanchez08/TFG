<template>
  <form @submit.prevent="submitForm">
    <div>
      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" v-model="nombre" required>
    </div>
    <div>
      <label for="maquinaria">Maquinaria:</label>
      <select id="maquinaria" v-model="maquinaria" required>
        <option value="" disabled>Selecciona una maquinaria</option>
        <option v-for="item in maquinarias" :key="item" :value="item">{{ item }}</option>
      </select>
    </div>
    <div>
      <label for="fecha">Fecha de Alquiler:</label>
      <input type="date" id="fecha" v-model="fecha" required>
    </div>
    <div>
      <label for="duracion">Duración (días):</label>
      <input type="number" id="duracion" v-model="duracion" required>
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
      maquinaria: '',
      fecha: '',
      duracion: '',
      maquinarias: ['Excavadora', 'Bulldozer', 'Retroexcavadora', 'Grua', 'Cargadora']
    };
  },
  methods: {
    submitForm() {
      const alquiler = {
        nombre: this.nombre,
        maquinaria: this.maquinaria,
        fecha: this.fecha,
        duracion: this.duracion
      };
      console.log('Alquiler realizado:', alquiler);
      // Aquí puedes agregar lógica para enviar los datos a un servidor o procesarlos como necesites
      alert('Alquiler realizado con éxito');
      this.resetForm();
    },
    resetForm() {
      this.nombre = '';
      this.maquinaria = '';
      this.fecha = '';
      this.duracion = '';
    }
  }
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

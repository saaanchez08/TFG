
<template>
  <div class="home">
    <h1> Bienvenido a la Pagina de Administracion de Rent.<span style="color: yellow">it</span></h1>
    <br>
    <h2>Esta es la página de inicio <br> (ES APLICACION PARTE VENDEDORA COMO SI FUERA DETRAS DE UN MOSTRADOR)</h2>
  </div>
</template>

<style scoped>
.home {
  align-items: center; 
  text-align: center; 
  /* Establecer la imagen de fondo */
  background-image: url('@/assets/img/pexels-kawserhamid-176342.jpg'); /* Ajusta la ruta según donde tengas tu imagen */
  background-size: cover; /* Ajusta el tamaño para cubrir completamente el contenedor */
  background-position: center; /* Centra la imagen de fondo */
  min-height: 98vh; /* Asegura que el fondo cubra toda la altura de la pantalla */
  display: flex;
  flex-direction: column;
  font-family: Arial, sans-serif;
  color: white;
}

h1 {
margin-top: 10%;
color: white;
}

</style>

  <script>
  
  export default {
    name: 'HomeView',
  }
</script>
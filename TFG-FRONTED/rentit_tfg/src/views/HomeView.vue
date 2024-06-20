
<template>
  <div class="home">
    <a class="btn-shine">BETA</a>
    <h1> Bienvenido a la Pagina de Administracion de Rent.<span style="color: yellow">it</span></h1>
    <br>
    <h2>Esta es la página de inicio <br> (ES APLICACION PARTE VENDEDORA COMO SI FUERA DETRAS DE UN MOSTRADOR)</h2>
  </div>
</template>

<style scoped>
.home {
  align-items: center; 
  text-align: center; 
  background-image: url('@/assets/img/pexels-kawserhamid-176342.jpg'); 
  background-size: cover; 
  background-position: center; 
  min-height: 98vh; 
  display: flex;
  flex-direction: column;
  font-family: Arial, sans-serif;
  color: white;
}

h1 {
margin-top: 10%;
color: white;
}

.btn-shine {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 12px 48px;
  color: #fff;
  background: linear-gradient(to right, #9f9f9f 0, #fff 10%, #868686 20%);
  background-position: 0;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: shine 3s infinite linear;
  animation-fill-mode: forwards;
  -webkit-text-size-adjust: none;
  font-weight: 600;
  font-size: 30px;
  text-decoration: none;
  white-space: nowrap;
  font-family: "Poppins", sans-serif;
}
@-moz-keyframes shine {
  0% {
    background-position: 0;
  }
  60% {
    background-position: 180px;
  }
  100% {
    background-position: 180px;
  }
}
@-webkit-keyframes shine {
  0% {
    background-position: 0;
  }
  60% {
    background-position: 180px;
  }
  100% {
    background-position: 180px;
  }
}
@-o-keyframes shine {
  0% {
    background-position: 0;
  }
  60% {
    background-position: 180px;
  }
  100% {
    background-position: 180px;
  }
}
@keyframes shine {
  0% {
    background-position: 0;
  }
  60% {
    background-position: 180px;
  }
  100% {
    background-position: 180px;
  }
}

</style>

  <script>
  
  export default {
    name: 'HomeView',
  }
</script>
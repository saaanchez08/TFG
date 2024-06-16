<template>
  <div>
    <head>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<nav>
      <div class="menu-header">
        <p>MENU EMPLEADO</p>
        <i class="fas fa-bars menu-icon"></i>
      </div>
      <ul class="menu-list">
        <li>
          <router-link to="/">
            <i class="fas fa-home"></i> Inicio
          </router-link>
        </li>
        <li>
          <router-link to="/alquiler">
            <i class="fas fa-briefcase"></i> Alquiler
          </router-link>
        </li>
        <li>
          <router-link to="/categorias">
            <i class="fas fa-car"></i> Categorías
          </router-link>
        </li>
        <li>
          <router-link to="/materiales">
            <i class="fa-solid fa-weight-hanging"></i> Materiales
          </router-link>
        </li>
      </ul>
    </nav>
    <router-view/>
  </div>
</template>
<style scoped>
nav {
  background-color: #393e46;
  width: 250px;
  height: 100vh;
  padding: 20px;
  position: fixed;
  top: 0;
  left: 0;
  font-family: 'Voga', serif;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

.menu-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: white;
}

.menu-header p {
  margin: 0;
  font-size: 1.5em;
}

.menu-icon {
  cursor: pointer;
}

.menu-list {
  list-style-type: none;
  padding: 0;
  margin-top: 20px;
}

.menu-list li {
  margin-bottom: 10px;
}

.menu-list a {
  color: #ecf0f1;
  text-decoration: none;
  font-size: 1.1em;
  display: flex;
  align-items: center;
}

.menu-list a i {
  margin-right: 10px;
}

.menu-list a:hover {
  color: #f96d00;
}
</style>



<script setup>

</script>
<!--
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Inicio</title>
    <link rel="stylesheet" href="main.css">
</head>
<body>
  <header>
    <div class="container">
        <h1>Rent.<span style="color: yellow">it</span></h1>
        <nav>
            <ul>
                <li><a href="#" class="titulo">INICIO</a></li>
                <li>
                    <a href="#" class="titulo">PRODUCTOS <span>&#8650;</span> </a>
                    <ul>
                        <li><a href="#">-----------</a></li>
                        <li><a href="#">-----------</a></li>
                        <li><a href="#">-----------</a></li>
                        <li><a href="#">-----------</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="titulo">CATEGORIAS <span>&#8650;</span> </a>
                    <ul>
                        <li><a href="#">-----------</a></li>
                        <li><a href="#">-----------</a></li>
                        <li><a href="#">-----------</a></li>
                        <li><a href="#">-----------</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="titulo">RESEÑAS <span>&#8650;</span> </a>
                    <ul>
                        <li><a href="#">-----------</a></li>
                        <li><a href="#">-----------</a></li>
                        <li><a href="#">-----------</a></li>
                        <li><a href="#">-----------</a></li>
                    </ul>
                </li>
                <li><a href="#" class="titulo">ACERCA DE</a></li>
                <li><a href="#" class="titulo">CONTACTO</a></li>
            </ul>
        </nav>
        <div class="user-buttons" id="userButtons">
            <button id="entrar">¿Ya tienes cuenta?</button>
            <button id="registerBtn" onclick="register()">Registrarse</button>
          
      </div>
      <div class="user-buttons" id="afterRegistration" style="display: none;">       
          <button id="publishBtn">Publicar</button>
          <button id="profileBtn">Perfil</button>
      </div>
    </div>
</header>

<!-- vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv Barra lateral derecha vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv -->
<!--
<nav class="sidebar">
    <h2>Menú de Navegación</h2>
    <hr class="rounded">
    <p>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor 
        incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
        exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure 
        dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit
        anim id est laborum."
    </p>
    <hr class="rounded">
    <p>








    </p>
</nav>

<!-- -------------------------------------------------------------------------------------- -->
<!-- vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv Pagina de bienvenida vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv -->
<!--
<div class="content">
    <h1>Bienvenido a Rent.it</h1>
    <h2>"Construye tus sueños paso a paso. ¡Tu proyecto, nuestra herramienta!"</h2>
</div>

<!-- --------------------------------------------------------------------------------------- -->
<!-- vvvvvvvvvvvvvvvvvvvvvvvvvvv Contenido central de la pagina vvvvvvvvvvvvvvvvvvvvvvvvvvvv -->


<!--
<div class="container">
    <div class="content2">
        <h2>Bienvenido a nuestra página de inicio</h2>
        <p>Esta es una página web simple utilizando HTML, CSS y JavaScript.</p>
    </div>
</div>



<!-- --------------------------------------------------------------------------------------- -->
<!-- vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv Carrusel de imagenes vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv --> 



<!-- --------------------------------------------------------------------------------------- -->
<!-- vvvvvvvvvvvvvvvvvvvvvvvvvvvv Formulario de registro y login vvvvvvvvvvvvvvvvvvvvvvvvvvv -->

    <!-- Ventana emergente de registro -->
    <!--
    <div id="registerModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>Registrarse</h2>
            <form>
              <label for="username">Nombre de usuario</label>
              <input type="text" id="username" name="username" required><br><br>
              <label for="email">Correo electrónico</label>
              <input type="email" id="email" name="email" required><br><br>
              <label for="password">Contraseña</label>
              <input type="password" id="password" name="password" required><br><br>
              <input type="submit"  value="Registrarse">
            </form>
        </div>
    </div>

    <!-- Ventana emergente de inicio de sesión -->
    <!--
    <div id="loginModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>Tu cuenta</h2>
            <form>
                <!-- Aquí puedes agregar los campos para el inicio de sesión -->
                <!--
                <label for="emailLogin">Correo electrónico</label>
                <input type="email" id="emailLogin" name="emailLogin" required><br><br>
                <label for="password">Contraseña</label>
                <input type="password" id="password" name="password" required><br><br>
                <input type="submit" id="loginBtn" value="Entrar">
            </form>
        </div>
    </div>

<!-- ------------------------------------------------------------------------------- -->
<!-- vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv Video vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv -->

  
<!-- -------------------------------------------------------------------------------- -->
<!-- vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv Footer vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv -->
<!--
    <footer>
        <div class="container">
            <p>Derechos de autor © 2024 - Todos los derechos reservados</p>
        </div>
    </footer>

<!-- --------------------------------------------------------------------------------- -->

<!--
    <script src="code.js"></script>
</body>
</html>
-->
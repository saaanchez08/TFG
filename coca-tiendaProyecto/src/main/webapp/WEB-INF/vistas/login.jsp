<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Simple Web Application</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div class="container">
        <h1>Bienvenido a Rent.<span style="color: yellow">it</span></h1>
        <h2>Registrate para Entrar</h2>
        <font color="red">${errorMessage}</font>
        <form id="loginForm" action="login" method="post">
           	Nombre: <input type="text" name="name" /><br>
            <br> Contraseña: <input type="password" name="password" /><br>
            <br> <input type="submit" value="Login" />
        </form>
    </div>

    <!-- Animación de carga -->
    <div class="loading-overlay" id="loadingOverlay">
        <div class="loading-spinner"></div>
    </div>

    <script>
        // Función para mostrar la animación de carga
        function showLoading() {
            document.getElementById('loadingOverlay').style.display = 'block';
        }

        // Escuchar el evento submit del formulario
        document.getElementById('loginForm').addEventListener('submit', function() {
            showLoading(); // Mostrar animación de carga al enviar el formulario
        });
    </script>
</body>
</html>

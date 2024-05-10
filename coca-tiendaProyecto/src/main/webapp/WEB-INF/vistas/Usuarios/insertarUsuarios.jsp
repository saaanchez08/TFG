<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/tienda/css/main.css">
	<link rel="stylesheet" href="/tienda/css/formularios.css">
    <link rel="stylesheet" href="/tienda/css/tablas.css">
</head>
<body>

    <%@include file="../menu.html"%>
    
    <div class="container">
        <div class="form">
        	<h2>Registrate</h2>
            <form class="form-categoria" action="http://localhost:8080/tienda/usuarios/insertarusuario" method="POST" id="formulario">
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre">
                
                <label for="email">Email</label>
                <input type="email" id="email" name="email">
                
                <label for="contrasena">Contraseña</label>
                <input type="password" id="contrasena" name="contrasena">
                
                <input type="submit" value="Insertar">
            </form>
            <c:if test="${resultado == 1}">
				<p class="confirmacion">Usuario insertado correctamente</p>
			</c:if>
        </div>
    </div>
</body>
</html>

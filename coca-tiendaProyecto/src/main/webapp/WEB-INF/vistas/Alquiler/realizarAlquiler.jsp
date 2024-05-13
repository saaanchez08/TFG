<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


   <%@ page isELIgnored="false"%>
<html>
<head>
	<link rel="stylesheet" href="/tienda/css/main.css">
	<link rel="stylesheet" href="/tienda/css/formularios.css">
    <link rel="stylesheet" href="/tienda/css/tablas.css">
    <script src="js/code.js" defer></script>
</head>
<body>
<header>
</header>
<%@include file="../menu.html" %>


<div class="container">
        <div class="form">
       	<h2>Realizar Alquiler </h2>
<form class="form-categoria" action="http://localhost:8080/tienda/alquiler/realizaralquiler" method="POST" id="formulario">
                
             	<label for="fechaInicio">Fecha Inicio</label>
				<input type="date" id="fechaInicio" name="fechaInicio">

             	<label for="fechaFin">Fecha Fin</label>
				<input type="date" id="fechaFin" name="fechaFin">               
                <!-- Monto -->
        		<label for="monto">Cantidad Disponible</label>
				<input type="number" id="monto" name="monto" step="0.01" min="0">
		        <!-- usuarioID -->
               	<label for="usuarioID"> Identificador Usuario </label>
				<input type ="number" id="usuarioID" name="usuarioID">
                 <!-- materialID -->
                <label for="materialID"> Codigo Material</label>
				<input type ="number" id="materialID" name="materialID">
                
                <input type="submit" value="Alquilar">
            </form>
            <c:if test="${resultado == 1}">
				<p class="confirmacion">El Alquiler se ha realizado correctamente!!!</p>
			</c:if>
        </div>
    </div>
</body>
</html>

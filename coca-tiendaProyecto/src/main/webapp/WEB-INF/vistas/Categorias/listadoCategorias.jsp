<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


   <%@ page isELIgnored="false"%>
<html>
<head>
	<link rel="stylesheet" href="/tienda/css/main.css">
	<link rel="stylesheet" href="/tienda/css/formularios.css">
    <link rel="stylesheet" href="/tienda/css/tablas.css">
</head>
<body>
<header>
</header>
<%@include file="../menu.html" %>



<div class="container">
	<div class= "form">
	<h2>Listar Categorias</h2>
		<form class="form-categoria" action="http://localhost:8080/tienda/categorias/listarcategorias" method ="POST">
			<label for="id"> ID Categoria</label>
			<input type ="text" id="id" name="id">
			
			<label for="nombre"> Nombre Categoria</label>
			<input type ="text" id="nombre" name="nombre">
			
			<label for="descripcion">Descripcion Categoria</label>
			<input type ="text" id="descripcion" name="descripcion">
			
			<input type="submit" value="Listar Todas Nuestras Categorias">
		</form>
		<c:if test="${not empty lista }">
			<table class="tabla-categoria">
				<tr>
					<th>ID</th>
					<th>NOMBRE </th>
					<th>DESCRIPCION</th>
					
				</tr>
				
				<c:forEach items="${lista}" var="categoria">
				
					<tr>
						<td> ${categoria.id} </td>
						<td> ${categoria.nombre} </td>
						<td> ${categoria.descripcion} </td>
						
					</tr>
				
				</c:forEach>
			
			</table>
		</c:if>
	</div>
</div>



</body>
</html>

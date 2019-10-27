<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vehicles</title>
</head>
<body>
<%@ include file="header.jsp" %>  
	<h2>List Of Vehicles</h2>
		<table width="800" border="1">
		<th>VehicleId</th>
		<th>Type</th>
		<th>Make</th>
		<th>Model</th>
		<th>Year</th>
		<th>Color</th>
		<th>License Plate</th>
		<th>Cost</th>
		<th>Options</th>
			<c:forEach var="vehicle" items="${vehicleDetails}" varStatus="status">
				<tr>
					<td>${vehicle.vehicleId}</td>
					<td>${vehicle.type}</td>
					<td>${vehicle.make}</td>
					<td>${vehicle.model}</td>
					<td>${vehicle.year}</td>
					<td>${vehicle.color}</td>
					<td>${vehicle.licensePlate}</td>
					<td>${vehicle.cost}</td>
					<td><a
						href="${contextPath }/vehicle/modify?licensePlate=${vehicle.licensePlate}">Modify</a>/<a
						href="${contextPath }/vehicle/delete?licensePlate=${vehicle.licensePlate}">Delete</a>
					</td>
					
				</tr>
			</c:forEach>
		</table>
	
	<a href="${contextPath}/vehicle/dashboard">Back</a>
</body>
</html>
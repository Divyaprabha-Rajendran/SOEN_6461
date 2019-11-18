<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cancel/Return Dashboard</title>
</head>
<body>
 <%@ include file="header.jsp" %>  

	<table width="800" border="1">
		<th>Vehicle Type</th>
		<th>License Plate</th>
		<th>Availability</th>
		<th>Start Date</th>
		<th>End Date</th>
		<th>License Number</th>
		<th>Cost</th>
		<th>Options</th>
		<c:forEach var="vehicle" items="${vehicleDetails}">
			<tr>
				<td>${vehicle.type}</td>
				<td>${vehicle.licensePlate}</td>
				<td>${vehicle.availability}</td>
				<td>${vehicle.startDate}</td>
				<td>${vehicle.dueDate}</td>
				<td>${vehicle.licenseNumber}</td>
				<td>${vehicle.cost}</td>
<c:if test="${vehicle.status=='reserved'}">
						<td><a href="${contextPath }/cancelReturn/transactionSearch?licensePlate=${vehicle.licensePlate}&reservationId=${vehicle.reservationId}&operation=${vehicle.status}">Cancel</a>
				</c:if>
				<c:if test="${vehicle.status=='rented'}">
						<td><a href="${contextPath }/cancelReturn/transactionSearch?licensePlate=${vehicle.licensePlate}&reservationId=${vehicle.reservationId}&operation=${vehicle.status}">Return</a>
				</c:if>			</tr>
		</c:forEach>

	</table>
	<a href="${contextPath }/clerk/login">Back</a>
</body>
</html>
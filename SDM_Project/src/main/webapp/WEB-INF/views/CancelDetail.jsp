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
<%-- 	<form action="${contextPath}/cancelReturn/transactionDetail" --%>
<!-- 		method="POST"> -->
<!-- 		<input type="text" namplaceholder="Search.." name="transactionId" -->
<!-- 			size="30" required="required"> <input type="submit" -->
<!-- 			value="search" /> -->
<!-- 	</form> -->
<%-- 	<c:if test="${transaction != null}"> --%>
<!-- 		<h3> -->
<!-- 			<a -->
<%-- 				href="<c:url value="/cancelReturn/transactionExtract?reservationId=${transaction.reservationId}"/>">${transaction.reservationId}</a> --%>
<!-- 		</h3> -->
<%-- 	</c:if> --%>
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
				<td><a href="${contextPath }/cancelReturn/transactionSearch?licensePlate=${vehicle.licensePlate}&licenseNumber=${vehicle.licenseNumber}">Cancel/Return</a>
			</tr>
		</c:forEach>

	</table>
	<a href="${contextPath }/clerk/login">Back</a>
</body>
</html>
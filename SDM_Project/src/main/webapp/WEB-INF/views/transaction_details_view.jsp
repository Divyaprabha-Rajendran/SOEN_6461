<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	page import="java.util.*, java.text.*"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction Details View Dashboard</title>
</head>
<body>
<h2>Transaction History</h2>
	<table width="800" border="1">
		<th>Reservation Id</th>
		<th>Start Date</th>
		<th>Due Date</th>
		<th>License Number</th>
		<th>License Plate</th>
		<th>Status</th>
		<th>Cost</th>
		<c:forEach var="transaction" items="${transactionDetails}">
			<tr>
				<td>${transaction.reservationId }</td>
				<td>${transaction.startdate }</td>
				<td>${transaction.duedate }</td>
				<td>${transaction.licenseNumber }</td>
				<td>${transaction.licensePlate }</td>
				<td>${transaction.status }</td>
				<td>${transaction.cost }</td>
				
			</tr>
		</c:forEach>
	</table>
	</body>
</html>
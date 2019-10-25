<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table width="800" border="1">
		<!-- 			<tr> -->
		<!-- 				<td  height="40" colspan="2"><font  size="5 "> Vehicle ID : </font></td> -->
		<%-- 				<td name="reservationId">${transaction.vehicleId}</td> --%>
		<!-- 			</tr> -->
		<tr>
			<td height="40" colspan="2"><font size="5 "> Make : </font></td>
			<td name="startdate">${hello.make}</td>
		</tr>
		<tr>
			<td height="40" colspan="2"><font size="5 "> Model : </font></td>
			<td name="duedate">${hello.model}</td>
		</tr>
		<tr>
			<td height="40" colspan="2"><font size="5 "> Year : </font></td>
			<td name="customerId">${hello.year}</td>
		</tr>
		<tr>
			<td height="40" colspan="2"><font size="5 "> Color : </font></td>
			<td name="vehicleId">${hello.color}</td>
		</tr>
		<tr>
			<td height="40" colspan="2"><font size="5 "> License
					Plate : </font></td>
			<td name="status">${hello.licensePlate}</td>
		</tr>
		<tr>
			<td height="40" colspan="2"><font size="5 "> Availability
					: </font></td>
			<td name="cost">${hello.availability}</td>
		</tr>
		<tr>
			<td height="40" colspan="2"><font size="5 "> Cost : </font></td>
			<td name="cost">${hello.cost}</td>
		</tr>

	</table>
		<a href="${contextPath }/admin/catalog">Back</a>
	<a href="${contextpath}/root/clerk/logout">Logout</a>
</body>
</html>
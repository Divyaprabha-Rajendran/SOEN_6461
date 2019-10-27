<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify Vehicle</title>
</head>
<body>
<<<<<<< HEAD
=======
<%@ include file="header.jsp" %>  
>>>>>>> branch 'master' of https://github.com/Divyaprabha-Rajendran/SOEN_6461.git
	<form action="${contextPath}/vehicle/updateVehicleDetails" method="post">
		<table width="800" border="1">
			<tr>
				<td height="40" colspan="2"><font size="5 "> Vehicle Id
						: </font></td>
				<td><input name="vehicleId" type="number" value=${vehicleDetails.vehicleId }></td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Vehicle
						Type : </font></td>
				<td><input name="type" value=${vehicleDetails.type }></td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Make : </font></td>
				<td><input name="make" value = ${vehicleDetails.make }></td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Year : </font></td>
				<td><input name="year" type="number" value = ${vehicleDetails.year } ></td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Model : </font></td>
				<td><input name="model" value = ${vehicleDetails.model } ></td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Color : </font></td>
				<td><input name="color" value = ${vehicleDetails.color } ></td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Number
						Plate : </font></td>
				<input type="text" hidden="hidden" name="licensePlate"
					value="${vehicleDetails.licensePlate }">
				<td>${vehicleDetails.licensePlate }</td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Availabillity : </font></td>
				<input type="text" hidden="hidden" name="availability"
					value="${vehicleDetails.availability }">
				<td>${vehicleDetails.availability }</td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Cost : </font></td>
				<td><input name="cost" type="number" value = ${vehicleDetails.cost }></td>
			</tr>
		</table>
		<a href="${contextPath }/vehicle/modifyDeleteVehicle">Back</a> <input
			type="submit" id="submit" value="Submit">
		<pre id="user"></pre>
	</form>
</body>
</html>
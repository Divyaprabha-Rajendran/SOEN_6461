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

<%@ include file="header.jsp" %> 
	<form action="${contextPath}/vehicle/updateVehicleDetails" method="post">
<table width="800" border="1">
<tr>
<td height="40" colspan="2"><font size="5 "> Vehicle Id
: </font></td>
<td><input name="vehicleId" type="number" value=${vehicleDetails.vehicleId } readOnly="true"></td>
</tr>
<tr>
<td height="40" colspan="2"><font size="5 "> Vehicle
Type : </font></td>
<td><input name="type" value=${vehicleDetails.type } required="required"></td>
</tr>
<tr>
<td height="40" colspan="2"><font size="5 "> Make : </font></td>
<td><input name="make" value = ${vehicleDetails.make } required="required"></td>
</tr>
<tr>
<td height="40" colspan="2"><font size="5 "> Year : </font></td>
<td><input name="year" type="number" value = ${vehicleDetails.year } required="required"></td>
</tr>
<tr>
<td height="40" colspan="2"><font size="5 "> Model : </font></td>
<td><input name="model" value = ${vehicleDetails.model } required="required" ></td>
</tr>
<tr>
<td height="40" colspan="2"><font size="5 "> Color : </font></td>
<td><input name="color" value = ${vehicleDetails.color } required="required" ></td>
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
<td><input name="cost" type="number" value = ${vehicleDetails.cost } required="required"></td>
</tr>
</table>
<a href="${contextPath }/vehicle/modifyDeleteVehicle">Back</a> <input
type="submit" id="submit" value="Submit">
<pre id="user"></pre>
</form>

</body>
</html>
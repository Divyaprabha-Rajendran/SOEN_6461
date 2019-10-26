<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vehicle details</title>
</head>
<body>
 <%@ include file="header.jsp" %>  
<table width="800" border="1">
<!-- <tr> -->
<!-- <td  height="40" colspan="2"><font  size="5 "> Vehicle ID : </font></td> -->
<%-- <td name="reservationId">${transaction.vehicleId}</td> --%>
<!-- </tr> -->
<tr>
<td height="40" colspan="2"><font size="5 "> Make : </font></td>
<td name="startdate">${transaction.make}</td>
</tr>
<tr>
<td height="40" colspan="2"><font size="5 "> Model : </font></td>
<td name="duedate">${transaction.model}</td>
</tr>
<tr>
<td height="40" colspan="2"><font size="5 "> Year : </font></td>
<td name="customerId">${transaction.year}</td>
</tr>
<tr>
<td height="40" colspan="2"><font size="5 "> Color : </font></td>
<td name="vehicleId">${transaction.color}</td>
</tr>
<tr>
<td height="40" colspan="2"><font size="5 "> License
Plate : </font></td>
<td name="status">${transaction.licensePlate}</td>
</tr>
<tr>
<td height="40" colspan="2"><font size="5 "> Availability
: </font></td>
<td name="cost">${transaction.availability}</td>
</tr>
<tr>
<td height="40" colspan="2"><font size="5 "> Cost : </font></td>
<td name="cost">${transaction.cost}</td>
</tr>

</table>
<a href ="${contextPath}/admin/catalog" name="Back"
value="Back">Back</a>
<a
href="${contextPath}/admin/catalog/viewDetail?idparam=${nextId}">Next</a>
</body>
</html>
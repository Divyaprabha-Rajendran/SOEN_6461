<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	page import="java.util.*, java.text.*"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <%@ include file="header.jsp" %>  
	<p>${message}</p>
	<form action="${contextPath}/vehicle/createNewVehicle" onsubmit="return validate()" method="get">
	
		<table width="800" border="1">
			<tr>
				<td height="40" colspan="2"><font size="5 "> Type
						: </font></td>
				<td><input type="text" name="type" required="required"></td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Make :
				</font></td>
				<td><input type="text" name="make" required="required"></td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Model :
				</font></td>
				<td><input type="text" name="model" required="required"></td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Year (>=2000):
				</font></td>
				<td><input type="text" name="year" required="required" pattern="[2][0][0-9]{2}" placeholder="2016"></td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Color :
				</font></td>
				<td><input type="text" name="color" required="required"></td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> License
						Number : </font></td>
				<td><input type="text" name="licenseNumber" required="required"
					pattern="[A-Z]{3} [0-9]{3}"
					, placeholder="ABC 123"></td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Cost :
				</font></td>
				<td><input type="text" name="cost" required="required"></td>
			</tr>
		</table>
		<a href="${contextPath}/vehicle/dashboard">Back</a>
		
		<input type="submit" value="Submit">
	</form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
 <%@ include file="header.jsp" %>  
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<h3>
		<a href="<c:url value="/client/create"/>">Create new client</a>
	</h3>
	<form:form action="${contextPath}/client/dashboard" method="post">
		<input type="text" namplaceholder="Search.." name="searchId" size="30"
			required="required">
		<input type="submit" value="search" />
	</form:form>

	<table width="800" border="1">
		<th>First Name</th>
		<th>Last Name</th>
		<th>License No</th>
		<th>Expiry Date</th>
		<th>Phone Number</th>
		<th>Options</th>
		<c:forEach var="client" items="${clientDetails }">
			<tr>
				<td>${client.firstName }</td>
				<td>${client.lastName }</td>
				<td>${client.licenseNumber }</td>
				<td>${client.licenseExpiryDate }</td>
				<td>${client.phoneNo }</td>
				<td><a href="${contextPath }/client/viewDetails?licenseNumber=${client.licenseNumber }">View</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="${contextPath}/clerk/login">Back</a>
</body>
</html>


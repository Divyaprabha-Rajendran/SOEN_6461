<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<h2>Edit Details</h2>
	
	<form:form action="${contextPath}/client/detailsSave" commandName="clientDetails"
		method="post">

		<table>
			<tr>
				<td>First name :</td>
				<td><form:input path="firstName" size="30"  required="required" />
				<font color="red"><form:errors path="firstName" /></font></td>
			</tr>
			

			<tr>
				<td>Last name :</td>
				<td><form:input path="lastName" size="30"  required="required" />
					<font color="red"><form:errors path="lastName" /></td>
			</tr>
			
			<tr>
				<td>DOB :</td>
				<td><form:input path="DOB" size="30"
						 required="required" /><font color="red"><form:errors path="DOB" /></td>
			</tr>
			
			<tr>
				<td>LicenseNumber :</td>
				<td><form:input path="licenseNumber" size="30"
						 required="required" /><font color="red"><form:errors path="licenseNumber" /> </td>
			</tr>
			
			<tr>
				<td>LicenseExpiryDate:</td>
				<td><form:input path="licenseExpiryDate" size="30"
						 required="required" /> <font color="red"><form:errors path="licenseExpiryDate" /></td>
			</tr>
			
			<tr>
				<td> PhoneNo:</td>
				<td><form:input path="phoneNo" size="30"
						 required="required" /> <font color="red"><form:errors path="phoneNo" /></td>
			</tr>
						
			<tr>
				<td colspan="2"><input type="submit" value="Save" /></td>
			</tr>
		</table>

	</form:form>
</body>
</html>
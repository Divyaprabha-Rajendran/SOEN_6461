<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client Details</title>
</head>
<body>

<center>
<table width="800" border="1" >

<tr>
<center> Client Details</center></font></tr>
<tr>
<td  height="40" colspan="2"><font  size="5 "> Client ID : </font></td>
<td>${client.clientId}</td>
</tr>
<tr><td height="40" ><font size="5"> First name :</font></td>
<td>${client.firstName}</td>
</tr>
<tr><td height="40" ><font size="5"> Last name :</font></td>
<td>${client.lastName}</td>
</tr>
<tr>
<td  height="40" colspan="2"><font size="5">DOB :</font></td>
<td>${client.DOB}</td></tr>

<tr>
<td height="40" colspan="2"><font size="5"> LicenseNumber :</font></td>
<td>${client.licenseNumber}</td></tr>
<tr>
<td height="40" colspan="2"><font size="5"> LicenseExpiryDate :</font></td>
<td>${client.licenseExpiryDate}</td>
</tr>

<tr>
<td height="40" colspan="2"><font size="5"> PhoneNo : </font></td>
<td>${client.phoneNo}</td>
</tr>

</table>


</center>
<h3><a href="<c:url value="/client/clientDetailEdit"/>"> Edit Details</a></h3>
 <h3><a href="<c:url value="/client/deleteRecord"/>"> Delete Record</a></h3>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>

    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>

<html>

<head>

  </head>

  <body>
 <%@ include file="header.jsp" %>  
    <h3><a href="<c:url value="/vehicle/createVehicle"/>">Create New Vehicle</a></h3>
    <h3>
<a href="<c:url value="/vehicle/modifyDeleteVehicle"/>">Modify
Vehicle Details</a>
</h3>
    

    </body>
    
    <a href="${contextPath}/root/clerk/login">Back</a>

     

</html>


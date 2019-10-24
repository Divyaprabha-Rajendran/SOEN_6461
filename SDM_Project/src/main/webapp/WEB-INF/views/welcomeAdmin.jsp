<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>

    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>

<html>

<head>

  </head>

  <body>

    <h2>${message}</h2>

    <h3><a href="<c:url value="/admin/catalog"/>">View Catalog</a></h3>

    <h3><a href="<c:url value="#"/>">Vehicle Transaction History</a></h3>

    <h3><a href="<c:url value="#"/>">Vehicle Record Management </a></h3>

    </body>

      <a href="${contextpath }/root/">Logout</a>

</html>


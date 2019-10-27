<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	page import="java.util.*, java.text.*"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>

<html>

<head>
<%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setDateHeader("Expires", 0);
response.setHeader("Pragma", "no-cache");
%>
  </head>

  <body>
 <%@ include file="header.jsp" %>  
    

    <h3><a href="<c:url value="/admin/catalog"/>">View Catalog</a></h3>

    <h3><a href="<c:url value="/transaction/transactionDetails"/>">Vehicle Transaction History</a></h3>

    <h3><a href="<c:url value="/vehicle/dashboard"/>">Vehicle Record Management </a></h3>

    </body>

      <a href="${contextpath}/root/clerk/logout">Logout</a>

</html>


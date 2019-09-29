<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  </head>
  <body>
    <table border="1">
        <th>serialno</th>
		<th>vehicleid</th>
		<th>type</th>
		<th>make</th>
		<th>model</th>
		<th>year</th>
		<th>color</th>
		<th>licenseplate</th>
		<th>availability</th>
		<th>cost</th>

		<c:forEach var="cl" items="${message}" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td><a href="${contextPath}/catalog/object?idparam=${cl.vehicleid}" >${cl.vehicleid}</a></td>
				<td>${cl.type}</td>
				<td>${cl.make}</td>
				<td>${cl.model}</td>
				<td>${cl.year}</td>
				<td>${cl.color}</td>
				<td>${cl.licenseplate}</td>
				<td>${cl.availability}</td>
				<td>${cl.cost}</td>
				

			</tr>
		</c:forEach>
	</table>
  </body>
</html>
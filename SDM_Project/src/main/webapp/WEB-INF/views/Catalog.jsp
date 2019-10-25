<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<form action="${contextPath}/system/catalog" method="post">
		<c:forEach var="type" items="${types}">
			<input type="checkbox" name="${type}" value="${type }">${type }</br>
		</c:forEach>
		<select name="sort">
			<option value="year">Sort By year</option>
			<option value="type">Sort By Vehicle</option>
		</select>
		<label>Vehicles Less Than Year:</label>
		<input name="lessthanyear" type="number" min="0" value="0"/> 
		<input type="submit" , value="Filter">
	</form>
	<table border="1">
		<th>serialno</th>
		<th>vehicleid</th>
		<th>type</th>
		<th>make</th>
		<th>model</th>
		<th>year</th>
		<th>color</th>
		<th>License Plate</th>
		<th>availability</th>
		<th>cost</th>
		<c:if test="${from =='reserve'}">
			<th>Options</th>
		</c:if>
		<c:forEach var="cl" items="${message}" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td><a
					href="${contextPath}/system/catalog/viewDetail?idparam=${cl.vehicleId}">${cl.vehicleId}</a>
				</td>
				<td>${cl.type}</td>
				<td>${cl.make}</td>
				<td>${cl.model}</td>
				<td>${cl.year}</td>
				<td>${cl.color}</td>
				<td>${cl.licensePlate}</td>
				<td>${cl.availability}</td>
				<td>${cl.cost}</td>
			<c:if test="${(from =='reserve' && cl.availability=='YES')}">
					<td><a
						href="${contextPath }/reservation/reserve?licensePlate=${cl.licensePlate}">Reserve/Rent</a>
					</td>
				</c:if>
				<c:if test="${cl.availability=='NO' && from=='reserve'}">
					<td>NA</td>
				</c:if>

			</tr>
		</c:forEach>
	</table>
	<a href="${contextPath }/clerk/login">Back</a>
	<a href="${contextpath }/root/">Logout</a>
</body>
</html>
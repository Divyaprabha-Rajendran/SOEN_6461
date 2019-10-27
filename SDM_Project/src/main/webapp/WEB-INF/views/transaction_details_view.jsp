<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	page import="java.util.*, java.text.*"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction Details View Dashboard</title>
</head>
<body>
 <%@ include file="header.jsp" %>  
<p>Please select search criteria:</p>
<form:form action="${contextPath}/transaction/transactionFiltering"  name="formname" method="post">
<table>
			<tr>
				<td> 
				<input type="radio" name="criteriaOption" value="userId" onclick="ShowUser()">Client License Number
				</td>
			</tr>
			<tr>
				<td> 
				<input type="radio" name="criteriaOption" value="vehicleId"  id="vehicleId" onclick="ShowVehicle()">Vehicle License Plate
				</td>
				<td> 
				<input type="checkbox" name="vehicleDate" id="vehicleDate" value="vehicleDate" onclick="ShowDateFunction()" >Check availability With Date
				</td>
			</tr>
			<tr>
				<td> 
				<input type="radio" name="criteriaOption" value="rentedVehicles" onclick="ShowRentedVehicles()">Vehicles currently out
				</td>
			</tr>
			<tr>
				<td> 
				<input type="radio" name="criteriaOption" value="dueDateOption" onclick="ShowDate()">Due Date
				</td>
			</tr>
			<tr>
			<td> 
				<p>Enter Search value:<p>
				</td>
				<td> 
				<input type="text" placeholder="Enter Data" disabled pattern="[A-Z]{1}-[0-9]{4}-[0-9]{6}-[0-9]{2}" 
				id="searchData" name="searchData" size="30" required="required">
				</td>
				
				
			</tr>
			<tr>
				<td> 
				<p>Enter Date:<p>
				</td>
				<td><input type="date" name="Date" id="Date" required="required"  disabled></td>
			</tr>
			<tr>
			<td><input type="submit"  id="mySubmit" disabled>
				</td>
			</tr>

</table>
	
	</form:form>
<h2>Transaction History</h2>
	<table width="800" border="1">
		<th>Reservation Id</th>
		<th>Start Date</th>
		<th>Due Date</th>
		<th>License Number</th>
		<th>License Plate</th>
		<th>Status</th>
		<th>Cost</th>
		<c:forEach var="transaction" items="${transactionDetails}">
			<tr>
				<td>${transaction.reservationId }</td>
				<td>${transaction.startdate }</td>
				<td>${transaction.duedate }</td>
				<td>${transaction.licenseNumber }</td>
				<td>${transaction.licensePlate }</td>
				<td>${transaction.status }</td>
				<td>${transaction.cost }</td>
				
			</tr>
		</c:forEach>
	</table>
<c:if test="${errorMsg != null}">
<h2>${errorMsg}</h2>
</c:if>
		<c:if test="${AvailableMsg != null}">
<h2>${AvailableMsg}</h2>
</c:if>
	
	<script type="text/javascript">
	function ShowUser() {
	    
	    document.formname.searchData.disabled=false;
	    document.getElementById("mySubmit").disabled = false;
	    document.getElementById("searchData").placeholder = "A-1234-123456-12";
	    document.getElementById("searchData").pattern = "[A-Z]{1}-[0-9]{4}-[0-9]{6}-[0-9]{2}";
	    document.formname.Date.disabled=true;
	    document.getElementById("vehicleDate").checked = false;
	}
	function ShowVehicle() {
		 document.formname.searchData.disabled=false;
		 var x = document.getElementById("vehicleDate").checked;
		 
		 if(x==new Boolean(true)){
			 document.formname.Date.disabled=false;	 
		 }else{
			 document.formname.Date.disabled=true;	 
		 }
		 
	
		    document.getElementById("mySubmit").disabled = false;
		    document.getElementById("searchData").placeholder = "AAA 000";
		    document.getElementById("searchData").pattern = "[A-Z]{3}\\s{1}[0-9]{3}";
	}
	function ShowRentedVehicles() {
	    

	    document.getElementById("mySubmit").disabled = false;
		document.formname.searchData.disabled=true;
		 document.formname.Date.disabled=true;
		 document.getElementById("vehicleDate").checked = false;
		 document.getElementById("searchData").placeholder = "Enter Data";
	}
	function ShowDate() {
		document.formname.searchData.disabled=true;
		 document.formname.Date.disabled=false;
		 document.getElementById("vehicleDate").checked = false;
		 document.getElementById("searchData").placeholder = "Enter Data";
		    document.getElementById("mySubmit").disabled = false;
	}
	
	function ShowDateFunction() {
		if(document.getElementById("vehicleId").checked) {
			document.formname.Date.disabled=false;	 
			}else{
				document.formname.Date.disabled=true;	
			}
	}

	</script>
	<a href="${contextPath}/clerk/login">Back</a>
	<a href="${contextPath}/transaction/transactionDetails">Clear Search</a>
	</body>
</html>
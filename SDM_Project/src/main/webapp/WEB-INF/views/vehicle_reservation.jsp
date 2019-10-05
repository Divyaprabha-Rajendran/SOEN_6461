<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@	page import="java.util.*, java.text.*"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p hidden="hidden" id="userData">${user }</p>
	<p>${message }</p>
	<form action="${contextPath}/root/reservation/reserve" method="get">
		<table width="800" border="1">
			<tr>
				<td height="40" colspan="2"><font size="5 "> Vehicle Id
						: </font></td>
				<td>${vehicleDetails.vehicleId }</td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Vehicle
						Type : </font></td>
				<td>${vehicleDetails.type }</td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Number
						Plate : </font></td>
				<input type="text" hidden="hidden" name="licensePlate" value="${vehicleDetails.licensePlate }">
				<td>${vehicleDetails.licensePlate }</td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Start Date
						: </font></td>
				<td><input type="date" name="startDate" id="startDate"
					required="required" onchange=dateChangeCalculation()
					value=${client.startDate }></td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> End Date :
				</font></td>

				<td><input type="date" name="endDate" id="endDate"
					required="required" onchange=dateChangeCalculation()
					value=${client.endDate }></td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> Cost</font></td>
				<p hidden="hidden" id="cost_hidden">${vehicleDetails.cost }</p>
				<td><input type="number" id="cost" name="cost"></td>
			</tr>
			<tr>
				<td height="40" colspan="2"><font size="5 "> User
						License</font></td>
				<td><input type="textId" name="licenseNumber" id="userLicense"
					required="required" onchange="showUserDetails()"
					pattern="[A-Z]{1}-[0-9]{4}-[0-9]{6}-[0-9]{2}"
					, placeholder="A-1234-123456-12"></td>
			</tr>
		</table>
		<a href="${contextPath }/root/system/catalog?from=reserve">Back</a>
		<input type="submit"  id="submit" value="Submit">
		<pre id="user"></pre>
	</form>

	<script type="text/javascript">
		var date = new Date();
		var d = (1900 + date.getYear()) + "-" + (1 + date.getMonth()) + "-"
				+ (date.getDate() < 10 ? '0' + date.getDate() : date.getDate());
		document.getElementById('startDate').setAttribute('min', d);
		document.getElementById('endDate').setAttribute('min', d);
		function dateChangeCalculation() {
			
			var startDate = document.getElementById("startDate");
			document.getElementById('endDate').setAttribute('min', d);
			var endDate = document.getElementById("endDate");
			if(startDate['value'] != ""){
				var d1 = new Date();
				var d2 = new Date(startDate['value']);
				var same = d1.getTime() >= d2.getTime();
				var text = same? "Rent" : "Reserve";
				document.getElementById("submit").value = text;
				var d = (1900 + d2.getYear()) + "-" + (1 + d2.getMonth()) + "-"
				+ ((1+d2.getDate()) < 10 ? '0' + (d2.getDate()+1) : (1+d2.getDate()));
				document.getElementById('endDate').setAttribute('min', d);
			}
			var cost = parseInt(document.getElementById("cost_hidden").textContent);
			if (startDate['value'] != "" && endDate['value'] != "") {
				var date1 = new Date(startDate['value']);
				var date2 = new Date(endDate['value']);
				var Difference_In_Time = date2.getTime() - date1.getTime();

				var Difference_In_Days = Difference_In_Time
						/ (1000 * 3600 * 24);

				document.getElementById("cost")['value'] = cost* Difference_In_Days;
			}
		}

		function showUserDetails() {
			var userDetails = document.getElementById('userData').textContent;
			var typedContent = document.getElementById('userLicense')['value'];
			var placeContent = document.getElementById('user');
			if (typedContent.length == 16) {
				placeContent.textContent = "";
				userDetails = userDetails.replace('{', '');
				userDetails = userDetails.replace('}', '');
				usersData = userDetails.split(", ");
				var len = usersData.length;
				for (var i = 0; i < len; i++) {
					var user = usersData[i].replace("=", ',');
					var values = user.split(",");
					if (values[0].indexOf(typedContent) != -1) {
						var data = "First Name : " + values[1] + "\n"
								+ "Last Name : " + values[2] + "\n"
								+ "Phone No :" + values[3];
						placeContent.textContent = data;
					}
				}
			}
		}
	</script>
</body>
</html>
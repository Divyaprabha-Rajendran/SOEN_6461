<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Page Title</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>

.header {
  padding: 20px;
  background: #1abc9c;
  color: black;
}

</style>

<body>

<div class="header">
  <h1 align="left">Vehicle Renting System</h1>
  <h3 align="left">User:<%= session.getAttribute("userName") %></h3>
</div>


</body>
</html>
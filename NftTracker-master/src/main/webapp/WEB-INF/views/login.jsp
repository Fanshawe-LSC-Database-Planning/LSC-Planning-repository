<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"
	charset="ISO-8859-1">
<title>LSC Planning Database</title>
<style>
Body {
	background-image: url('day-planner-828611_1920.jpg');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	font-family: Calibri, Helvetica, sans-serif;
    width: 500px;
	background-color: white;
	margin: 0 auto;
	align-content: center;
}

button {
	background-color: #921d1d;
	width: 100%;
	color: white;
	padding: 15px;
	margin: 10px 0px;
	border: none;
	cursor: pointer;
}

form {
	border: 10px solid #f1f1f1;
}

input[type=text], input[type=password] {
	width: 100%;
	margin: 8px 0;
	padding: 12px 20px;
	display: inline-block;
	border: 1px solid black;
	box-sizing: border-box;
}

button:hover {
	opacity: 0.7;
}

.contain123 {
	padding: 25px;
	background-color:lightgray;
}
</style>

</head>
<body>
	<div style="text-align: center">
		<img src="fanshawe_logo.png" width="300" height="200"/>
		<h1>LSC Planning Database</h1>

		<form:form name="loginForm" modelAttribute="apiUser"
				   action="performLogin" method='POST' style="text-align: center">
			<div class="contain123" align="left">
				<label>Username :</label>
				<form:input path="username" id="username" name="username" />
				<label>Password : </label>
				<form:input type="password" path="password" name="password" />
				<form:button type="submit">Login</form:button>
			</div>
		</form:form>
		<p style="color: red;" id="message">${loginMessage}</p>
	</div>
</body>
</html>

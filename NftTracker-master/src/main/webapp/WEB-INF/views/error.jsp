<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	background-color: #95c2de;
}

.mainbox {
	background-color: #95c2de;
	margin: auto;
	height: 600px;
	width: 600px;
	position: relative;
}

.msg {
	text-align: center;
	font-family: 'Nunito Sans', sans-serif;
	font-size: 1.6rem;
	position: absolute;
	left: 10%;
	top: 45%;
	width: 100%;
}

a {
	text-decoration: none;
	color: white;
}

a:hover {
	text-decoration: underline;
}
</style>
<title>LSC Planning Database</title>
<link
	href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@600;900&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/4b9ba14b0f.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<%
		if (session.getAttribute("userId") == null && session.getAttribute("username") == null) {
		response.sendRedirect("endSession");
	}
	%>
	<div class="mainbox">

		<div class="msg">
			The page you are looking for is temporarily unavailable.
			<p>
				Let's go back to <a style="color: #921d1d;"
					href="${pageContext.request.contextPath}/home">Home</a>
			</p>
		</div>
	</div>
</body>
</html>
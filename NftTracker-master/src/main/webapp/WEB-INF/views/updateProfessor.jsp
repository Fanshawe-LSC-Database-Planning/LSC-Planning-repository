<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Karma">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
body {
	font-family: "Lato", sans-serif;
}

.sidenav {
	display: none;
	height: 100%;
	width: 330px;
	height: 1000px;
	position: fixed;
	z-index: 1;
	top: 0;
	left: 0;
	background-color: white;
	overflow-x: hidden;
	padding-top: 60px;
}

.sidenav a {
	padding: 8px 8px 8px 32px;
	text-decoration: none;
	font-size: 16px;
	color: black;
	display: block;
	width: 200px;
}

.sidenav a:hover {
	color: grey;
}

.sidenav .closebtn {
	position: absolute;
	top: 0;
	right: -125px;
	font-size: 20px;
	margin-left: 50px;
}

@media screen and (max-height: 450px) {
	.sidenav {
		padding-top: 15px;
	}
	.sidenav a {
		font-size: 12px;
	}
}

.dropbtn {
	background-color: #4CAF50;
	color: white;
	padding: 12px;
	font-size: 12px;
	border: none;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f1f1f1;
	min-width: 170px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
	left: 150px;
	top: 10px;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
	width: 177px;
}

.dropdown-content a:hover {
	background-color: #ddd;
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown:hover .dropbtn {
	background-color: #3e8e41;
}

.responsive {
	width: 100%;
	height: auto;
}

.dropbtn {
	background-color: #4CAF50;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f1f1f1;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #ddd;
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown:hover .dropbtn {
	background-color: #3e8e41;
}

html, body {
	min-height: 100%;
}

body, div, form, input, select, textarea, label, p {
	padding: 0;
	margin: 0;
	outline: none;
	font-family: Roboto, Arial, sans-serif;
	font-size: 14px;
	color: #666;
	line-height: 22px;
}

body {
	background-image: url('./professorpage1.jpg');
	background-size: cover;
}

h1 {
	position: absolute;
	margin: 0;
	font-size: 38px;
	color: #fff;
	z-index: 2;
	line-height: 83px;
}

textarea {
	width: calc(100% - 12px);
	padding: 5px;
}

.testbox {
	display: flex;
	justify-content: center;
	align-items: center;
	height: inherit;
	padding: 20px;
}

form {
	width: 100%;
	padding: 20px;
	border-radius: 6px;
	background: #fff;
	box-shadow: 0 0 8px #669999;
}

.banner {
	position: relative;
	height: 300px;
	background-image: url('./professorpage2.jpeg');
	background-size: cover;
	display: flex;
	justify-content: center;
	align-items: center;
	text-align: center;
}

.banner::after {
	content: "";
	background-color: rgba(0, 0, 0, 0.2);
	position: absolute;
	width: 100%;
	height: 100%;
}

input, select, textarea {
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 3px;
}

input[type="date"] {
	padding: 4px 5px;
}

textarea {
	width: calc(100% - 12px);
	padding: 5px;
}

.item:hover p, .item:hover i, .question:hover p, .question label:hover,
	input:hover::placeholder {
	color: #669999;
}

.item input:hover, .item select:hover, .item textarea:hover {
	border: 1px solid transparent;
	box-shadow: 0 0 3px 0 #669999;
	color: #669999;
}

.item {
	position: relative;
	margin: 10px 0;
}

.item span {
	color: red;
}

input[type="date"]::-webkit-inner-spin-button {
	display: none;
}

.item i, input[type="date"]::-webkit-calendar-picker-indicator {
	position: absolute;
	font-size: 20px;
	color: #a3c2c2;
}

.item i {
	right: 1%;
	top: 30px;
	z-index: 1;
}

[type="date"]::-webkit-calendar-picker-indicator {
	right: 1%;
	z-index: 2;
	opacity: 0;
	cursor: pointer;
}

.question span {
	margin-left: 30px;
}

.question-answer label {
	display: block;
}

.flax {
	display: flex;
	justify-content: space-around;
}

.btn-block {
	margin-top: 10px;
	text-align: center;
}

button {
	width: 150px;
	padding: 10px;
	border: none;
	border-radius: 5px;
	background: #921d1d;
	font-size: 16px;
	color: #fff;
	cursor: pointer;
}

button:hover {
	background: #37b1cc;
}

@media ( min-width : 568px) {
	.name-item, .city-item {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
	}
	.name-item input, .name-item div {
		width: calc(50% - 20px);
	}
	.name-item div input {
		width: 97%;
	}
	.name-item div label {
		display: block;
		padding-bottom: 5px;
	}
}
</style>
<script>
	function validateMandateFormParameters() {
		if (document.getElementById('firstName').value == ''
				|| document.getElementById('lastName').value == ''
				|| document.getElementById('emailId').value == ''
				|| document.getElementById('empType').value == '') {
			alert('Please update atleast the mandatory parameters!');
		} else {
			document.getElementById('updateFacultyForm').action = '${pageContext.request.contextPath}/updateFacultyInSystem/'
					+ document.getElementById('professorId').innerText;
			document.getElementById('updateFacultyForm').submit();
		}
	}
	window.setTimeout(function() {
		var message = document.getElementById('errorMessage');
		if (message != null) {
			message.style.display = 'none';
		}
	}, 5000);
</script>
<title>LSC Planning Database</title>
</head>
<body style="margin-top: 50px;">
	<%
		if (session.getAttribute("userId") == null && session.getAttribute("username") == null) {
		response.sendRedirect("endSession");
	}
	%>

	<div class="w3-top">
		<div class="w3-bar w3-white w3-padding w3-card"
			style="letter-spacing: 0px;">
			<div id="mySidenav" class="sidenav">
				<div class="logo-wrapper waves-light" style="margin-left: 30px;">
					<img src="nav_bar_logo.png" />
				</div>

				<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>

				<br>
				<div class="dropdown">
					<a href="#"><i class="fa fa-user"
						style="font-size: 16px; color: black; margin-right: 10px;"> </i>Faculty
					</a>
					<div class="dropdown-content">
						<a href="${pageContext.request.contextPath }/createProfessor">Add
							Faculty</a> <a
							href="${pageContext.request.contextPath }/professorsToUpdate">Update
							Faculty</a> <a href="${pageContext.request.contextPath }/professors">Assign
							Courses</a>
					</div>
				</div>
				<br>
				<div class="dropdown">
					<div>
						<a href="#"><i class="fa fa-reorder"
							style="font-size: 16px; color: black; margin-right: 10px;"> </i>Term</a>
						<div class="dropdown-content">
							<a href="${pageContext.request.contextPath }/addTerm">Create
								Term </a> <a href="terms">Manage Term</a>

						</div>
					</div>
				</div>
				<br>
				<div class="dropdown">
					<div>
						<a href="#"> <i class="fa fa-table"
							style="font-size: 16px; color: black; margin-right: 10px;"> </i>Term
							Sheet
						</a>
						<div class="dropdown-content">
							<a
								href="${pageContext.request.contextPath }/termsForCreatingTermSheet">Create
								Term Sheet</a> <a
								href="${pageContext.request.contextPath }/viewTermSheets">Manage
								Term Sheet</a>
						</div>
					</div>
				</div>
				<br>
				<div class="dropdown">
					<div>
						<a href="#"> <i class="fa fa-book"
							style="font-size: 16px; color: black; margin-right: 10px;"></i>Program
						</a>
						<div class="dropdown-content">
							<a href="${pageContext.request.contextPath }/addProgram">Add
								Program</a> <a
								href="${pageContext.request.contextPath }/viewPrograms">Edit/Delete
								Program</a>
						</div>
					</div>
				</div>
				<br>
				<div class="dropdown">
					<div>
						<a href="#"> <i class="fa fa-edit"
							style="font-size: 16px; color: black; margin-right: 10px;"></i>Course
						</a>
						<div class="dropdown-content">
							<a href="${pageContext.request.contextPath }/viewProgramLevels">Manage
								Course</a>
						</div>
					</div>
				</div>
				<br> <a href="${pageContext.request.contextPath }/settings">
					<i class="fa fa-cog fa-spin"
					style="font-size: 16px; margin-right: 10px;"></i>Settings
				</a> <br> <br> <br> <br>
			</div>


			<span style="font-size: 30px; cursor: pointer" onclick="openNav()">&#9776;
			</span>

			<script>
				function openNav() {
					document.getElementById("mySidenav").style.display = "block";
				}

				function closeNav() {
					document.getElementById("mySidenav").style.display = "none";
				}
			</script>
			<div class="w3-right w3-hide-small">
				<a href="${pageContext.request.contextPath}/home"
					class="w3-bar-item w3-button"><i class="fa"
					style="font-size: 16px; margin-right: 5px;"></i>Home</a><a href="#"
					class="w3-bar-item w3-button"><i class="fa fa-user"
					style="font-size: 16px; margin-right: 5px;"></i>Welcome <%=request.getSession().getAttribute("username")%></a>
				<div class="dropdown">
					<div>
						<a href="${pageContext.request.contextPath}/performLogout"
							class="w3-bar-item w3-button"><i class="fa"
							style="font-size: 16px; margin-right: 5px;"></i>Logout</a> <br>
					</div>
				</div>

			</div>

		</div>

	</div>

	<header class="w3-display-container w3-content w3-wide"
		style="max-width: 1600px; min-width: 500px" id="home">

		<div class="testbox">
			<form:form method="POST" modelAttribute="professor"
				name="updateFacultyForm" id="updateFacultyForm"
				style="width:60%;letter-spacing:0px;">
				<div class="banner">
					<h1>Update Faculty details</h1>
				</div>
				<br />
				<label id="professorId" style="display: none" hidden="true">${professor.professorId}</label>
				<fieldset>
					<legend>Update a Faculty:</legend>
					<div class="item">
						<form:label path="firstName">First Name<span>*</span>
						</form:label>
						<form:input path="firstName" id="firstName"
							cssStyle="width:100%;padding:10px" value="${professor.firstName}" />
					</div>
					<div class="item">
						<form:label path="lastName">Last Name:<span>*</span>
						</form:label>
						<form:input path="lastName" id="lastName"
							value="${professor.lastName}" cssStyle="width:100%;padding:10px" />
					</div>
					<div class="item">
						<form:label path="emailId">Email:<span>*</span>
						</form:label>
						<form:input path="emailId" id="emailId" name="emailId"
							cssStyle="width:100%;padding:10px" type="email"
							value="${professor.emailId }" />
					</div>
					<div class="item">
						<form:label path="contactNumber">Contact Number:
						</form:label>
						<form:input path="contactNumber" id="contactNumber"
							name="contactNumber" maxlength="10" type="tel"
							pattern="[0-9]{10}" value="${professor.contactNumber}"
							cssStyle="width:100%;padding:10px" />
					</div>
					<div class="item">
						<label>Employee Id: </label>
						<form:input path="employeeId" id="employeeId" name="employeeId"
							value="${professor.employeeId}" type="text"
							style="width: 100%; padding: 10px" />
					</div>
					<div class="item">
						<form:label path="empType">Employee Type:<span>*</span>
						</form:label>
					</div>
					<form:radiobutton path="empType" id="empType" name="empType"
						value="Full-time" label="Full-Time"
						checked="${professor.empType == 'Full-time' ? 'checked' : '' }"></form:radiobutton>
					<br>
					<form:radiobutton path="empType" id="empType" name="empType"
						value="Part-time" label="Part-time"
						checked="${professor.empType == 'Part-time' ? 'checked' : '' }"></form:radiobutton>
					<br>
					<form:radiobutton path="empType" id="empType" name="empType"
						value="Partial Load" label="Partial Load"
						checked="${professor.empType == 'Partial Load' ? 'checked' : '' }"></form:radiobutton>
					<div class="item">
						<form:label path="address">Address:</form:label>
						<form:input path="address" value="${professor.address}"
							id="address" name="address" cssStyle="width:100%;padding:10px" />
					</div>
					<div class="item">
						<form:label path="city">City:</form:label>
						<form:input path="city" value="${professor.city}" id="city"
							name="city" cssStyle="width:100%;padding:10px" />
					</div>
					<div class="item">
						<form:label path="province">Province:</form:label>
						<form:input path="province" cssStyle="width:100%;padding:10px"
							id="province" name="province" value="${professor.province }" />
					</div>
					<div class="item">
						<form:label path="postalCode">Postal Code:</form:label>
						<form:input path="postalCode" cssStyle="width:100%;padding:10px"
							id="postalCode" name="postalCode" value="${professor.postalCode}" />
					</div>
				</fieldset>
				<div class="btn-block">
					<button type="button" onclick="validateMandateFormParameters()">Update</button>
					<a href="professorsToUpdate"><button style="margin-left: 50px"
							type="button">Go Back</button></a>
					<p style="color: red;" id="message">${errorMessage}</p>
				</div>
			</form:form>
		</div>
	</header>
</body>
</html>

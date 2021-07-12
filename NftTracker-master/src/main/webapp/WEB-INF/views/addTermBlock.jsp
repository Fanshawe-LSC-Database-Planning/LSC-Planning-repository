<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

body {
	background-image: url('./professorpage1.jpg');
	background-size: cover;
}

html, body {
	min-height: 100%;
}

body, div, form, input, select, textarea, label {
	padding: 0;
	margin: 0;
	outline: none;
	font-family: Roboto, Arial, sans-serif;
	font-size: 14px;
	color: #666;
	line-height: 22px;
}

h1 {
	position: absolute;
	margin: 0;
	font-size: 50px;
	color: #fff;
	z-index: 2;
	line-height: 83px;
}

legend {
	padding: 10px;
	font-family: Roboto, Arial, sans-serif;
	font-size: 18px;
	color: #fff;
	background-color: #921d1d;
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
	box-shadow: 0 0 8px #006622;
}

.banner {
	position: relative;
	height: 250px;
	background-image: url('./termblock.jpeg');
	background-size: cover;
	display: flex;
	justify-content: center;
	align-items: center;
	text-align: center;
}

.banner::after {
	content: "";
	background-color: rgba(0, 0, 0, 0.4);
	position: absolute;
	width: 100%;
	height: 100%;
}

input, select, textarea {
	margin-bottom: 5px;
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
	color: #006622;
}

.item input:hover, .item select:hover, .item textarea:hover {
	border: 1px solid transparent;
	box-shadow: 0 0 3px 0 #006622;
	color: #006622;
}

.item {
	position: relative;
	margin: 10px 0;
}

.item span {
	color: red;
}

.item i {
	right: 1%;
	top: 30px;
	z-index: 1;
}

.week {
	display: flex;
	justify-content: space-between;
}

.columns {
	display: flex;
	justify-content: space-between;
	flex-direction: row;
	flex-wrap: wrap;
}

.columns div {
	width: 48%;
}

.question span {
	margin-left: 30px;
}

.question-answer label {
	display: block;
}

.question span {
	margin-left: 30px;
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
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	function validate() {
		var programSelected = document.getElementById('programName').value;
		$.ajax({
			type : 'GET',
			url : '${pageContext.request.contextPath }/loadLevelByProgram/'
					+ programSelected,
			success : function(result) {
				var result = JSON.parse(result);
				var s = '';
				for (var i = 0; i < result.length; i++) {
					s += '<option value="' + result[i] + '">' + result[i]
							+ '</option>';
				}

				document.getElementById('levelName').innerHTML = s;
			}
		});
	}

	function saveTermBlock1() {
		if (document.getElementById('programName').value == ''
				|| document.getElementById('levelName').value == ''
				|| document.getElementById('totalWeeks').value == '') {
			alert('Please fill all the parameters!');
		} else {
			document.getElementById('addTermBlockToTermsheetForm').action = '${pageContext.request.contextPath}/addTermBlockToTermsheet/'
					+ document.getElementById('termName').innerText
					+ "/"
					+ document.getElementById('termSheetId').innerText
					+ "/"
					+ document.getElementById('termSheetName').innerText;
			document.getElementById('addTermBlockToTermsheetForm').submit();
		}
	}

	function goBackToEditTermSheet() {
		document.getElementById('addTermBlockToTermsheetForm').action = '${pageContext.request.contextPath}/editTermSheet/'
				+ document.getElementById('termName').innerText
				+ "/"
				+ document.getElementById('termSheetId').innerText
				+ "/"
				+ document.getElementById('termSheetName').innerText;
		document.getElementById('addTermBlockToTermsheetForm').submit();
	}
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
			<form:form style="width: 70%; letter-spacing: 0px;"
				class="form-horizontal" modelAttribute="termBlock"
				id="addTermBlockToTermsheetForm" method="post">
				<div class="banner">
					<h1>Add a Program</h1>
				</div>
				<br />
				<br />
				<fieldset>
					<legend>Program Menu</legend>


					<div class="columns">
						<div class="item">
							Term: <b><label id="termName" style="color: #921d1d;">${termName}</label></b>
						</div>
						<div class="item">
							Termsheet: <b><label id="termSheetName"
								style="color: #921d1d;">${termSheetName}</label></b> <label
								id="termSheetId" style="color: #921d1d; display: none;"
								hidden="true">${termSheetId}</label>
						</div>

						<div class="item" style="width: 100%">
							Select Program : <span>*</span>
							<form:select path="programName" id="programName"
								onchange="validate()" name="programName" class="form-control">
								<option value=""></option>
								<c:forEach var="program" items="${programs}">
									<option value="${program.key}"><c:out
											value="${program.key}" /></option>
								</c:forEach>
							</form:select>
						</div>
						<div class="item">
							Select Level : <span>*</span>
							<form:select path="levelName" id="levelName" name="levelName">
							</form:select>
						</div>

						<div class="question"></div>
						<div class="item">
							<label for="checkindate">Term Starting Date <span>*</span></label>
							<input type="date" id="termStartDate" name="termStartDate"
								required="required" /> <i class="fas fa-calendar-alt"></i>
						</div>
						<div class="item">
							<label for="checkoutdate">Term Ending Date <span>*</span></label>
							<input type="date" id="termEndDate" name="termEndDate"
								required="required" /> <i class="fas fa-calendar-alt"></i>
						</div>

						<div class="item" style="width: 100%">
							<label for="totalWeeks">Total Weeks :<span>*</span></label>
							<form:input id="totalWeeks" path="totalWeeks" type="text" />
						</div>
					</div>
				</fieldset>
				<div class="btn-block">
					<button id="saveTermBlock" name="saveTermBlock"
						onclick="saveTermBlock1()">Save Program</button>
					<button type="button" onclick="goBackToEditTermSheet()">Back</button>
				</div>
			</form:form>
		</div>
	</header>
</body>
</html>

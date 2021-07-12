<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>LSC Planning Database</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Karma">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="UTF-8">
<script src="jquery.min.js"></script>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
	font-family: "Lato", sans-serif;
}

body, h1, h2, h3, h4, h5, h6 {
	font-family: "Raleway", sans-serif
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
</style>
<script type="text/javascript">
function deleteProgram(programId) {
	$.ajax({
		type : 'POST',
		url : '${pageContext.request.contextPath}/deleteProgram'+ "/"
		+ programId,
		success : function(result) {
			if(result=="true"){
				alert("Program Delete successfully");
				document.getElementById('goBackToViewProgramsForm').submit();
				}else{
					alert("some error occured while deleting program");
					}
		}
	});
}

function deleteProgramLevel(programId, programLevelId) {
	document.getElementById('deleteProgramLevelForm'+programLevelId).action = '${pageContext.request.contextPath}/deleteProgramLevel'
			+ "/"
			+ programId+"/"+programLevelId;
	document.getElementById('deleteProgramLevelForm'+programLevelId).submit();
}


function addProgramLevel(programId) {
	if (document.getElementById('levelName').value == '') {
		alert('Please enter the level Name!');
	} else{
	document.getElementById('addProgramLevelForm').action = '${pageContext.request.contextPath}/addProgramLevel'
			+ "/"
			+ programId;
	document.getElementById('addProgramLevelForm').submit();
}}


</script>
</head>
<body>
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
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>


	<div class="w3-main" style="margin: 50px">


		<header id="portfolio">
			<div class="w3-container">
				<div class="w3-section w3-bottombar w3-padding-16">
					<form method="get" id="goBackToViewProgramsForm"
						action="viewPrograms">
						<button class="w3-button w3-white w3-hide-small" type="submit">
							<i class="fa fa-chevron-left" style="margin-right: 10px;"></i>Go
							back
						</button>
					</form>

					<button class="w3-button w3-white w3-hide-small"
						onclick="deleteProgram(${program.programId})">
						<i class="fa fa-trash-o" style="margin-right: 10px;"></i>Delete
						Program
					</button>
					<br> <br>
					<form id="addProgramLevelForm" method="post">
						<input type="text" name="levelName" id="levelName">
						<button class="w3-button w3-white w3-hide-small"
							onclick="addProgramLevel(${program.programId})" type="button">
							<i class="fa fa-plus" style="margin-right: 10px;"></i>Add Level
						</button>
						<br> <label style="color: red;"><b>${addprogramLevelMessage}</b></label>
					</form>
					<br> <br> <label for="fname">Program name:
						${program.programName}</label><br> <label for="fname">Program
						Code: ${program.programCode}</label><br>
				</div>
			</div>
		</header>
		<c:forEach var="level" items="${program.levels}" varStatus="iterator">
			<form id="deleteProgramLevelForm${level.programLevelId}"
				method="post">
				<div class="w3-third w3-container w3-margin-bottom">
					<div class="w3-container w3-white">
						<div
							style="border: 1.0px; border-style: solid; border-color: grey; padding: 1em; height: 180px;">
							<p>
								<b>${level.levelName}</b>
							</p>
							<button style="margin-left: 10px; margin-bottom: 20px;"
								type="button"
								onclick="deleteProgramLevel(${program.programId},${level.programLevelId})">Delete</button>
						</div>
					</div>
				</div>
			</form>
		</c:forEach>
	</div>
</body>
</html>

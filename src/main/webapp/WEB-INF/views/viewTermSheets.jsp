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

	<title>LSC Planning Database</title>
	<style>
		body {
			font-family: "Lato", sans-serif;
		}
		ul.breadcrumb {
			list-style: none ;
			background-color: transparent;
			position: fixed;
			margin-left: 40px;
			margin-top: 5px;

		}
		ul.breadcrumb li {
			display: inline;
			font-size: 15px;
		}
		ul.breadcrumb li+li:before {
			padding: 8px;
			color: black;
			content: "/\00a0";
		}
		ul.breadcrumb li a {
			color: white;
			text-decoration: none;
		}
		ul.breadcrumb li a:hover {
			color: black;
			text-decoration: underline;
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
			background-color: lightcyan;
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

		body, div, form, input, select, p {
			padding: 0;
			margin: 0;
			outline: none;
			font-family: Roboto, Arial, sans-serif;
			font-size: 14px;
			color: #666;
			line-height: 22px;
		}

		body {
			background-image: url('./college.jpg');
			background-size: cover;
		}

		h1 {
			position: absolute;
			margin: 0;
			font-size: 32px;
			color: #fff;
			z-index: 2;
		}

		h2 {
			font-weight: 400;
		}

		.testbox {
			display: flex;
			justify-content: center;
			align-items: center;
			height: inherit;
			padding: 20px;
		}

		form {
			width: 70%;
			padding: 10px;
			border-radius: 6px;
			background: #fff;
			box-shadow: 0 0 20px 0 #095484;
		}

		.banner {
			position: relative;
			height: 250px;
			background-image: url('./termsheet.jpeg');
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

		input, select {
			margin-bottom: 10px;
			border: 1px solid #ccc;
			border-radius: 3px;
		}

		input {
			width: calc(100% - 10px);
			padding: 5px;
		}

		select {
			width: 100%;
			padding: 7px 0;
			background: transparent;
		}

		.item:hover p, .item:hover i, .question:hover p, .question label:hover,
		input:hover::placeholder, a {
			color: #095484;
		}

		.item input:hover, .item select:hover {
			border: 1px solid transparent;
			box-shadow: 0 0 6px 0 #095484;
			color: #095484;
		}

		.item {
			position: relative;
			margin: 10px 0;
		}

		input[type="date"]::-webkit-inner-spin-button {
			display: none;
		}

		.item i, input[type="date"]::-webkit-calendar-picker-indicator {
			position: absolute;
			font-size: 20px;
			color: #a9a9a9;
		}

		.item i {
			right: 2%;
			top: 30px;
			z-index: 1;
		}

		[type="date"]::-webkit-calendar-picker-indicator {
			right: 1%;
			z-index: 2;
			opacity: 0;
			cursor: pointer;
		}

		input[type=checkbox] {
			display: none;
		}

		label.check {
			position: relative;
			display: inline-block;
			margin: 5px 20px 10px 0;
			cursor: pointer;
		}

		.question span {
			margin-left: 30px;
		}

		span.required {
			margin-left: 0;
			color: red;
		}

		label.check:before {
			content: "";
			position: absolute;
			top: 2px;
			left: 0;
			width: 16px;
			height: 16px;
			border-radius: 2px;
			border: 1px solid #095484;
		}

		input[type=checkbox]:checked+.check:before {
			background: #095484;
		}

		label.check:after {
			content: "";
			position: absolute;
			top: 6px;
			left: 4px;
			width: 8px;
			height: 4px;
			border: 3px solid #fff;
			border-top: none;
			border-right: none;
			transform: rotate(-45deg);
			opacity: 0;
		}

		input[type=checkbox]:checked+label:after {
			opacity: 1;
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
			background: #095484;
			font-size: 16px;
			color: #fff;
			cursor: pointer;
		}

		button:hover {
			background: #0666a3;
		}

		@media ( min-width : 568px) {
			.name-item, .city-item {
				display: flex;
				flex-wrap: wrap;
				justify-content: space-between;
			}
			.name-item input, .city-item input {
				width: calc(50% - 20px);
			}
			.city-item select {
				width: calc(50% - 8px);
			}
		}

		.metod {
			display: flex;
		}

		input[type=radio] {
			display: none;
		}

		label.radio {
			position: relative;
			display: inline-block;
			margin-right: 20px;
			text-indent: 32px;
			cursor: pointer;
		}

		label.radio:before {
			content: "";
			position: absolute;
			top: -1px;
			left: 0;
			width: 17px;
			height: 17px;
			border-radius: 50%;
			border: 2px solid #1c87c9;
		}

		label.radio:after {
			content: "";
			position: absolute;
			width: 8px;
			height: 4px;
			top: 5px;
			left: 5px;
			border-bottom: 3px solid #1c87c9;
			border-left: 3px solid #1c87c9;
			transform: rotate(-45deg);
			opacity: 0;
		}

		input[type=radio]:checked+label:after {
			opacity: 1;
		}

		button {
			width: 200px;
			padding: 10px;
			border: none;
			border-radius: 5px;
			background: #921d1d;
			font-size: 14px;
			font-weight: 600;
			color: #fff;
		}

		button:hover {
			background: #37b1cc;
		}
	</style>
	<script>
		$(function() {
			$('select').chosen();
		})

		function validateSheetSelectionAndSubmitForm() {
			if (document.getElementById('termSheetId').value == '') {
				alert("Please select the sheet !");
			} else {
				document.getElementById('editTermsheetForm').action = 'editTermSheet';
				document.getElementById('editTermsheetForm').submit();
			}
		}

		function viewSubmitForm() {
			if (document.getElementById('termSheetId').value == '') {
				alert("Please select the sheet !");
			} else {
				document.getElementById('editTermsheetForm').action = 'manageTermSheet';
				document.getElementById('editTermsheetForm').submit();
			}
		}

		function deleteSubmitForm() {
			if (document.getElementById('termSheetId').value == '') {
				alert("Please select the sheet !");
			} else {
				document.getElementById('editTermsheetForm').action = 'deleteTermSheet';
				document.getElementById('editTermsheetForm').submit();
			}
		}
	</script>
</head>
<body>
<%
	if (session.getAttribute("userId") == null && session.getAttribute("username") == null) {
		response.sendRedirect("endSession");
	}
%>
<div class="w3-top">
	<div class="w3-bar w3-red w3-padding w3-card"
		 style="letter-spacing: 0px;">
		<div class="w3-bar w3-red w3-padding w3-card"
			 style="letter-spacing: 0px;">
			<div style="color:white" class="w3-centre">
				<ul class="breadcrumb">
					<li><a href="http://localhost:8080/nft-tracker/home">Home</a></li>
					<li><a href="http://localhost:8080/nft-tracker/createProfessor">Add Faculty</a></li>
					<li><a href="http://localhost:8080/nft-tracker/addTerm">Create Term</a></li>
					<li><a href="http://localhost:8080/nft-tracker/termsForCreatingTermSheet">Create Termsheet</a></li>
					<li><a href="http://localhost:8080/nft-tracker/addProgram">Add Program</a></li>
					<li><a href="http://localhost:8080/nft-tracker/viewPrograms">Select Program</a></li>
					<li><a href="http://localhost:8080/nft-tracker/viewProgramLevels">Select Level</a></li>
					<br>
					<li><a href="http://localhost:8080/nft-tracker/professors">Select Faculty</a></li>
					<li>Select Termsheet</li>
				</ul></div>
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
		<div class="w3-right w3-red w3-hide-small">
			<a href="${pageContext.request.contextPath}/home"
			   class="w3-bar-item w3-button"><i class="fa"
												style="font-size: 16px; margin-right: 5px;"></i>Home</a><a href="#"
																										   class="w3-bar-item w3-button"><i class="fa fa-user"
																																			style="font-size: 16px; margin-right: 5px;"></i>Welcome <%=request.getSession().getAttribute("username")%></a>
			<div class="dropdown">
				<div style="color: white">
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
	<div class="testbox" style="letter-spacing: 0px; margin-top: 50px;">
		<form:form id="editTermsheetForm" modelAttribute="termSheet"
				   method="post">
			<div class="banner">
				<h1>Select a Term Sheet</h1>
			</div>
			<div class="item">
				<p style="margin-bottom: 3px;">
					<b>Select Term</b>
				</p>

				<form:select path="termSheetId" required="required">
					<option value=""></option>
					<c:forEach var="termSheet" items="${termSheets}">
						<option value="${termSheet.termSheetId}"><c:out
								value="${termSheet.termName} : ${termSheet.termSheetName}" /></option>
					</c:forEach>

				</form:select>
			</div>

			<button class="button" style="margin-left: 20px;" type="button"
					onclick="viewSubmitForm()">View TermSheet</button>
			<button class="button" style="margin-left: 100px;" type="button"
					onclick="validateSheetSelectionAndSubmitForm()">Edit
				TermSheet</button>
			<button class="button" style="margin-left: 100px;" type="button"
					onclick="deleteSubmitForm()">Delete TermSheet</button>

		</form:form>
	</div>

	<div class="item"></div>
	<p style="color: red;" id="message">${message}</p>
</header>

</body>
</html>

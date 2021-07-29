<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
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
	<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
			src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
	<style>
		body {
			font-family: "Lato", sans-serif;
			background: fixed;
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
			background-image: url('./settings.jpg');
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

		input {
			width: calc(100% - 10px);
			padding: 5px;
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

		input[type=radio], input[type=checkbox] {
			display: none;
		}

		label.radio {
			position: relative;
			display: inline-block;
			margin: 5px 20px 15px 0;
			cursor: pointer;
		}

		.question span {
			margin-left: 30px;
		}

		.question-answer label {
			display: block;
		}

		label.radio:before {
			content: "";
			position: absolute;
			left: 0;
			width: 17px;
			height: 17px;
			border-radius: 50%;
			border: 2px solid #ccc;
		}

		input[type=radio]:checked+label:before, label.radio:hover:before {
			border: 2px solid #669999;
		}

		label.radio:after {
			content: "";
			position: absolute;
			top: 6px;
			left: 5px;
			width: 8px;
			height: 4px;
			border: 3px solid #669999;
			border-top: none;
			border-right: none;
			transform: rotate(-45deg);
			opacity: 0;
		}

		input[type=radio]:checked+label:after {
			opacity: 1;
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
	<title>LSC Planning Database</title>
	<script type="text/javascript">
		function saveSettings() {
			var email = document.getElementById("email").value;
			var password = document.getElementById("password").value;
			var smtpHost = document.getElementById("smtpHost").value;
			var smtpPort = document.getElementById("smtpPort").value;
			var partTimeHours = document.getElementById("partTimeHours").value;
			var partialLoadHours = document.getElementById("partialLoadHours").value;
			$.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/saveSettings',
				data : {
					email : email,
					password : password,
					smtpHost : smtpHost,
					smtpPort : smtpPort,
					partTimeHours : partTimeHours,
					partialLoadHours : partialLoadHours
				},
				beforeSend : function() {
					// Show image container
					$("#loader").show();
				},
				success : function(result) {
					$("#loader").hide();
					if (result == "true") {
						alert("Settings updated successfully");
					} else {
						alert("some error occured..");
					}
				},
				error : function(error) {
					$("#loader").hide();
					alert("some error occured..");
				},
				complete : function(data) {
					$("#loader").hide();
				}
			});
		}
	</script>
</head>
<body style="margin-top: 50px;">
<div id="loader"
	 style="display: none; width: 100%; height: 100%; top: 100px; left: 0px; position: fixed; z-index: 10000; text-align: center;">
	<img src="${pageContext.request.contextPath }/loading.gif" />
</div>

<div class="w3-top">
	<div class="w3-bar w3-red w3-padding w3-card"
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
		<div class="w3-right w3-red w3-hide-small">
			<a href="${pageContext.request.contextPath}/home"
			   class="w3-bar-item w3-button"><i class="fa"
												style="font-size: 16px; margin-right: 5px;"></i>Home</a>
			<a href="#"
			   class="w3-bar-item w3-button"><i class="fa fa-user" style="font-size: 16px; margin-right: 5px;"></i>Welcome <%=request.getSession().getAttribute("username")%></a>
			<a href="${pageContext.request.contextPath}	/createUser"
			   class="w3-bar-item w3-button"><i class="fa"
												style="font-size: 16px; margin-right: 5px;"></i>Manage Users</a>
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
	<div class="testbox">
		<form style="width: 60%; letter-spacing: 0px;"
			  action="${pageContext.request.contextPath}/saveSettings">
			<div class="banner"></div>
			<br />
			<fieldset>
				<legend>Credentials</legend>
				<div class="item">
					<label for="email">Username<span>*</span></label> <input
						id="email" type="email" name="email" required
						value="${fromEmailUsername }" />
				</div>
				<div class="item">
					<label for="Password"> Password<span>*</span></label> <input
						id="password" type="text" name="password" required
						value="${fromEmailPassword }" />
				</div>

			</fieldset>

			<fieldset>
				<legend>Mail Server Settings</legend>

				<div class="item">
					<label for="smtpHost"> SMTP Host<span>*</span></label> <input
						id="smtpHost" type="text" name="smtpHost" required
						value="${smtpHost }" />
				</div>
				<div class="item">
					<label for="smtpPort"> SMTP Port Number<span>*</span></label> <input
						id="smtpPort" type="text" name="smtpPort" required
						value="${smtpPort }" />
				</div>
			</fieldset>
			<fieldset>
				<legend>Employment Hours</legend>
				<div class="item">
					<label for="partTimeHours"> Part-time Hours<span>*</span></label>
					<input id="partTimeHours" type="text" name="partTimeHours"
						   required value="${partTimeHours }" />
				</div>
				<div class="item">
					<label for="partialLoadHours"> Partial-load Hours<span>*</span></label>
					<input id="partialLoadHours" type="text" name="partialLoadHours"
						   required value="${partialLoadHours }" />
				</div>
			</fieldset>
			<div class="btn-block">
				<button type="button" onclick="saveSettings()">Save</button>
				<br> <br> <b><label style="color: red;">
				${message}</label></b>
			</div
		</form>

	</div>
</header>
</body>
</html>

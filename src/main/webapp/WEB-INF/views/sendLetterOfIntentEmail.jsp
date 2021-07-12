<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*,javax.mail.*"%>
<%@ page import = "javax.mail.internet.*,javax.activation.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" name="viewport"
	content="multipart/form-data, charset=utf-8, width=device-width, initial-scale=1" />
<style>
button:hover {
	background-color: #004489;
}

.button {
	background-color: #921d1d;
	color: white;
	padding: 12px 28px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
	border-radius: 10px;
	border: 2px solid #006fbf;
}

button:hover {
	background: #37b1cc;
}

.footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: red;
	color: white;
	text-align: center;
}

body {
	font-family: arial;
	line-height: 18px;
	font-size: 14px;
}

ul li {
	margin-bottom: 5px;
}

ul {
	margin: 0px;
	padding: 0px 20px;
}
</style>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/manageTermsheet.css">


<script type="text/javascript"
	src="${pageContext.request.contextPath}/bootstrap-select.min.js">
	
</script>
<script type="text/javascript">
	function sendLetterOfIntent() {
		var loi = document.getElementById("letterOfIntentText").innerHTML;
		document.getElementById("body").value = loi;
		var attachmentForm = new FormData(document
				.getElementById("uploadAttachmentForm"));
		$
				.ajax({
					type : 'POST',
					enctype : 'multipart/form-data',
					url : '${pageContext.request.contextPath}/sendLetterOfIntent',
					processData : false,
					contentType : false,
					cache : false,
					data : attachmentForm,
					beforeSend : function() {
						//alert("before");
						$("#loader").show();
					},
					success : function(result) {
						$("#loader").hide();
						if (result == "true") {
							alert("Email sent successfully");
							document.getElementById("emailSentStatus").value = "Status updated";
							document.getElementById(
									"backToViewSummaryAfterEmailSentForm")
									.submit();
						} else {
							alert("some error occured..");
						}
					},
					error : function(error) {
						$("#loader").hide();
						alert("some error occured..");
					},
					complete : function(data) {
						//alert("complete");
						$("#loader").hide();
					}
				});
	}
</script>
<title>LSC Planning Database</title>
</head>
<body
	style="background-color: White; margin-left: 10px; margin-right: 10px;">
	<div id="loader"
		style="display: none; width: 100%; height: 100%; top: 100px; left: 0px; position: fixed; z-index: 10000; text-align: center;">
		<img src="${pageContext.request.contextPath }/loading.gif" />
	</div>

	<div
		style="margin-left: 10px; margin-right: 10px; background-color: white">

		<div>
			<fieldset style="background-color: gainsboro; border-style: none;">

				<h1 style="text-align: center; color: firebrick;">New Hire LOI
					Email</h1>
			</fieldset>
		</div>
		<form
			action="${pageContext.request.contextPath}/backToViewSummaryAfterEmailSent"
			method="post" id="backToViewSummaryAfterEmailSentForm"
			name="backToViewSummaryAfterEmailSentForm">
			<input type="text" id="termSheetId1" style="display: none;"
				name="termSheetId" value="${termSheetId}" hidden="true"> <input
				type="text" id="emailSentStatus" style="display: none;"
				name="emailSentStatus" value="" hidden="true">
			<button type="submit" class="button">back</button>
		</form>
		<button class="button" type="button" onclick="sendLetterOfIntent()">Send</button>
		<div style="background-color: White; margin: 20px;" id="emailDiv">
			<form enctype="multipart/form-data" id="uploadAttachmentForm"
				name="uploadAttachmentForm" method="post">

				<br> <input type="text" hidden="true" style="display: none;"
					id="body" name="body"> <input type="text" hidden="true"
					style="display: none;" id="professorId" name="professorId"
					value="${professor.professorId}"> <input type="text"
					hidden="true" style="display: none;" id="termSheetId"
					name="termSheetId" value="${termSheetId}"> <label
					for="from">From:</label> <input value="${fromEmail}"
					style="margin-bottom: 10px; border: 1px; border-style: solid; border-color: #6e7376; width: 95%"
					type="email" multiple id="from" name="from"><br> <label
					for="emailDiv">To:</label> <input value="${professor.emailId}"
					style="margin-bottom: 10px; border: 1px; border-style: solid; border-color: #6e7376; width: 95%"
					type="email" id="to" name="to" multiple><br> <label
					for="emailDiv">Cc:</label> <input
					style="margin-bottom: 10px; border: 1px; border-style: solid; border-color: #6e7376; width: 95%"
					type="email" id="cc" name="cc" multiple><br> <label
					for="emailDiv">Bcc:</label> <input
					style="margin-bottom: 10px; border: 1px; border-style: solid; border-color: #6e7376; width: 94.5%"
					type="email" id="bcc" name="bcc" multiple><br> <label
					for="subject">Subject:</label> <input name="subject"
					value="Letter of Intent"
					style="margin-bottom: 10px; border: 1px; border-style: solid; border-color: #6e7376; width: 92.5%"
					type="text" id="subject"><br> <label for="subject">Attachment
					: </label><input type="file" name="attachment" id="attachment" /><br>
				<br>
				<div style="border-style: ridge;" contenteditable="true"
					id="letterOfIntentText">
					<h2>${date}</h2>
					<h3>Dear Interested ${professor.firstName}
						${professor.lastName},</h3>

					<p>Thank you for your expressed interest in teaching for
						Fanshawe College at London South Campus.</p>

					<p>London South Campus, is comprised of primarily International
						students. The following five programs are offered at the campus:</p>
					<ul>
						<li>Business Management</li>
						<li>Agri-Business</li>
						<li>Business and Information Systems Architecture</li>
						<li>Retirement Residence Management</li>
						<li>Health Care Administration Management</li>
					</ul>

					<p>As a campus, London South prides itself for hiring excellent
						faculty to lead and facilitate the courses in the above mentioned
						programs.</p>

					<p>At this time, I am considering both your education, and
						experience, to help London South build one of the best student
						experiences that Fanshawe has to offer. As such, you have been
						short-listed and will probably receive a teaching contract in the
						near future.</p>

					<p>
						For my planning purposes, please express any teaching
						restrictions, (unavailable days/times of the week) that could
						impact your ability to teach. Courses are currently offered fully
						online, and run Monday - Friday from 6:00 a.m. - 12:00 p.m. This
						can be done by contacting my assistant, Ms. Randi Zevenbergen, via
						email (rzevenbergen@fanshawec.ca) by <b>"date"</b>.
					</p>

					<p>Please note, this notice does not confirm future employment
						with Fanshawe College. You will be contacted again, with a
						specific proposed teaching assignment, should we move forward with
						your teaching candidacy in the hiring process.</p>

					<p>Sincerely,</p>

					<p>
						Nord K. Mensah, PhD <br> Associate Dean<br> London South
						Campus
					</p>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	function sendTeachingNotification() {
		var teachingNotification = document
				.getElementById("teachingNotificationText").innerHTML;
		document.getElementById("body").value = teachingNotification;
		var attachmentForm = new FormData(document
				.getElementById("uploadAttachmentForm"));

		$
				.ajax({
					type : 'POST',
					url : '${pageContext.request.contextPath }/sendTeachingNotification',
					processData : false,
					contentType : false,
					cache : false,
					data : attachmentForm,
					beforeSend : function() {
						// Show image container
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

				<h1 style="text-align: center; color: firebrick;">Teaching
					Notification Email</h1>
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
		<button class="button" type="button"
			onclick="sendTeachingNotification()">Send</button>
		<div style="background-color: White; margin: 20px;" id="emailDiv">
			<form enctype="multipart/form-data" id="uploadAttachmentForm"
				name="uploadAttachmentForm" method="post">
				<input type="text" hidden="true" style="display: none;" id="body"
					name="body"> <input type="text" hidden="true"
					style="display: none;" id="professorId" name="professorId"
					value="${professor.professorId}"> <input type="text"
					hidden="true" style="display: none;" id="termSheetId"
					name="termSheetId" value="${termSheetId}"> <label
					for="from">From:</label> <input value="${fromEmail}"
					style="margin-bottom: 10px; border: 1px; border-style: solid; border-color: #6e7376; width: 95%"
					type="email" multiple id="from" name="from"><br> <label
					for="emails">To:</label> <input value="${professor.emailId}"
					style="margin-bottom: 10px; border: 1px; border-style: solid; border-color: #6e7376; width: 95%"
					type="email" id="to" name="to" multiple><br> <label
					for="emails">Cc:</label> <input
					style="margin-bottom: 10px; border: 1px; border-style: solid; border-color: #6e7376; width: 95%"
					type="email" id="cc" name="cc" multiple><br> <label
					for="emails">Bcc:</label> <input
					style="margin-bottom: 10px; border: 1px; border-style: solid; border-color: #6e7376; width: 94.5%"
					type="email" id="bcc" name="bcc" multiple><br> <label
					for="subject">Subject:</label> <input name="subject"
					value="Teaching Notification"
					style="margin-bottom: 10px; border: 1px; border-style: solid; border-color: #6e7376; width: 92.5%"
					type="text" id="subject"><br> <label for="attachment">Attachment
					: </label> <input type="file" name="attachment" id="attachment" />
				<div style="border-style: ridge;" contenteditable="true"
					id="teachingNotificationText">
					<h2
						style="background-color: #921d1d; color: #fff; padding: 10px; line-height: 24px; font-size: 18px;">
						London South Campus<br> Teaching Invitation
					</h2>
					<h3>Good Morning ${professor.firstName} ${professor.lastName}:</h3>

					<p>I am pleased to inform you of the following teaching
						invitation for the 21W term at the London South Campus. We look
						forward to working with you in the upcoming semester.</p>

					<p>
						<strong>Please note: Course offerings are fully online
							and run Monday - Friday, from 6 a.m. - 12 p.m.</strong>
					</p>

					<table width="100%;" style="border: 1px solid #000">
						<tr style="background-color: #921d1d;" cellpadding="0px"
							cellspacing="0px;">
							<td style="text-transform: uppercase; font-weight: bold;">Program</td>
							<td style="text-transform: uppercase; font-weight: bold;">COURSE
								CODE</td>
							<td style="text-transform: uppercase; font-weight: bold;">SECTION</td>
							<td style="text-transform: uppercase; font-weight: bold;">COURSE
								TITLE</td>
							<TD style="text-transform: uppercase; font-weight: bold;">HOURS</TD>
							<td style="text-transform: uppercase; font-weight: bold;">CURRICULUM
								CONTACT</td>
							<TD style="text-transform: uppercase; font-weight: bold;">COORDINATOR</TD>
						</tr>
						<c:forEach items="${programToCourse}" var="programCourse">
							<c:set value="${programCourse.key}" var="courseInfo" />
							<tr>
								<td>${programCourse.value}</td>
								<td>${courseInfo.courseCode}</td>
								<td>${courseInfo.sectionName}</td>
								<td>${courseInfo.courseName}</td>
								<td>${courseInfo.courseCredit}</td>
								<td></td>
								<td></td>
							</tr>
						</c:forEach>
					</table>

					<h3 style="font-style: italic;">Please click "reply all" when
						you confirm your commitment to this teaching invitation. Upon
						receipt of your confirmation, we will prepare your contract.</h3>

					<h3 style="font-style: italic;">It is important to note that
						the courses listed in this invitation are subject to change or
						cancellation due to changes in enrollment and/or scheduling.</h3>

					<table width="100%;" style="border: 1px solid #000">
						<tr style="background-color: #921d1d;" cellpadding="0px"
							cellspacing="0px;">
							<td style="text-transform: uppercase; font-weight: bold;">Program</td>
							<td style="text-transform: uppercase; font-weight: bold;">COURSE
								CODE</td>
							<td style="text-transform: uppercase; font-weight: bold;">SECTION</td>
						</tr>
						<tr>
							<td>Proof of Credentials</td>
							<td>Transcripts of highest academic credential and
								professional credential (if applicable)</td>
							<td>Submit directly from granting institution to our
								Associate Dean, Nord Mensah</td>
						</tr>
						<tr>
							<td>Textbook & Curriculum</td>
							<td><p style="margin: 0">Desk copies of course resources
									are provided for you</p>
								<p>The curriculum is available on FOL</p></td>
							<td>
								<p style="margin: 0">Please email your curriculum contact to
									obtain the textbook/resources.</p>
								<p>Once you receive a username and password from Randi
									Zevenbergen, you will have access to your course curriculum and
									content on FanshaweOnline (FOL).</p>
							</td>
						</tr>

					</table>
					<h3>If you have any questions, please contact Randi
						Zevenbergen. We look forward to a busy and exciting winter
						semester!</h3>

				</div>
				<br>

			</form>
		</div>
	</div>
</body>
</html>
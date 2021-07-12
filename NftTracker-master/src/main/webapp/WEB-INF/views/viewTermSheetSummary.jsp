<%@page import="java.util.ArrayList"%>
<%@page import="com.fanshawe.nfttracker.api.entities.CourseInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.fanshawe.nfttracker.api.entities.TermSheet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.18/js/bootstrap-select.min.js">
</script>
<title>LSC Planning Database</title>
<style>
table, td, th {
	border: 1px solid black;
}

table {
	width: 100%;
	background-color: White;
}

label {
	padding: 2px;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	font-size: small;
	color: black;
}

.button {
	background-color: #921d1d;
	color: white;
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
</style>
<script type="text/javascript">
	function loiEmailRequest() {
		
		const rbs = document.querySelectorAll('input[name="professorId"]');
        let selectedValue;
        for (const rb of rbs) {
            if (rb.checked) {
                selectedValue = rb.value;
                break;
            }
        }
		if (selectedValue === undefined) {
			alert('Please select a professor !');
		} else {
			document.getElementById("emailRequestForm").action = '${pageContext.request.contextPath}/loiEmailRequest'
					+ "/" +selectedValue +"/"+document.getElementById("termSheetId1").innerText;
			document.getElementById("emailRequestForm").submit();
		}
	}
	function teachingNotificationEmailRequest() {
		const rbs = document.querySelectorAll('input[name="professorId"]');
        let selectedValue;
        for (const rb of rbs) {
            if (rb.checked) {
                selectedValue = rb.value;
                break;
            }
        }
		if (selectedValue === undefined) {
			alert('Please select a professor !');
		} else {
			document.getElementById("emailRequestForm").action = '${pageContext.request.contextPath}/teachingNotificationEmailRequest'
					+ "/" + selectedValue+"/"+document.getElementById("termSheetId1").innerText;
			document.getElementById("emailRequestForm").submit();
		}
	}


	function sendContractEmailRequest() {
		const rbs = document.querySelectorAll('input[name="professorId"]');
        let selectedValue;
        for (const rb of rbs) {
            if (rb.checked) {
                selectedValue = rb.value;
                break;
            }
        }
		if (selectedValue === undefined) {
			alert('Please select a professor !');
		} else if(document.getElementById("empType"+selectedValue).innerText=='Full-time'){
			alert('Full-Time contracts are handled by Fanshawe Main campus, you can update the status by marking it!');
		}else{
			document.getElementById("emailRequestForm").action = '${pageContext.request.contextPath}/sendContractEmailRequest'
				+ "/" + selectedValue+"/"+document.getElementById("termSheetId1").innerText+"/"+document.getElementById("empType"+selectedValue).innerText;
		document.getElementById("emailRequestForm").submit();
			}
	}


	function updateStatus(button) {
		const rbs = document.querySelectorAll('input[name="professorId"]');
        let selectedValue;
        for (const rb of rbs) {
            if (rb.checked) {
                selectedValue = rb.value;
                break;
            }
        }
		if (selectedValue === undefined) {
			alert('Please select a professor !');
		} else{

			$
			.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath }/updateProfessorStatus'+ "/" + selectedValue+"/"+document.getElementById("termSheetId1").innerText+"/"+ button.id,
				success : function(result) {
					if (result == "true") {
						document.getElementById("updateStatusForm").action = '${pageContext.request.contextPath}/viewTermSheetSummary'
							+ "/" +document.getElementById("termSheetId1").innerText;
					document.getElementById("updateStatusForm").submit();
					} else {
						alert("some error occured..");
					}
				},
				error : function(error) {
					console.log('error', error);
					//alert(error);
				}
			});
			
			}
	}
</script>
</head>
<body
	style="background-color: white; margin-top: 10px; margin-right: 10px; margin-left: 10px; text-align: center; border-style: solid; border-width: 1px; border-left-width: 3px; border-right-width: 3px; border-color: #e6e6e6; background-color: #f0f0f0;">
	<%
		if (session.getAttribute("userId") == null && session.getAttribute("username") == null) {
		response.sendRedirect("endSession");
	}
	%>

	<div>

		<div>
			<div>
				<h1
					style="text-align: center; background-color: White; color: firebrick; background-color: #f0f0f0;">LSC
					Planning Database</h1>
				<a href="${pageContext.request.contextPath}/home"><button
						class="button" type="button">Home</button></a> <a
					href="${pageContext.request.contextPath}/logout"><button
						class="button">Logout</button></a>
				<form action="${pageContext.request.contextPath}/backToTermSheet"
					method="post">
					<input type="text" id="termSheetId" style="display: none;"
						name="termSheetId" value="${sheet.termSheetId}" hidden="true">
					<button type="submit" class="button">back</button>
				</form>
				<br> <label style="color: red;">${emailSentStatus}</label>

			</div>
			<div>

				<div>
					<br> <label style="display: none;" hidden="true"
						id="termSheetId1">${sheet.termSheetId}</label> <label
						style="align-content: center; border: thin; color: black; font-weight: bold; font-size: medium;">
						<c:out value="${sheet.termName}" /> : <c:out
							value="${sheet.termSheetName}" />
					</label>
					<form:form modelAttribute="termBlock" method="post"
						id="emailRequestForm">
						<table>
							<tr style="background-color: #cce6ff; font-weight: bold;">
								<td>Select</td>
								<td>Faculty</td>
								<td>Employment Type</td>
								<td>Course Code</td>
								<td>Course Name</td>
								<td>Course Credit</td>
								<td>Total Allocated Hours</td>
								<td>Current Status</td>
							</tr>
							<c:forEach var="professorToCourse"
								items="${professorToCourseMap}" varStatus="iterator">
								<c:forEach var="courseInfo" items="${professorToCourse.value}"
									varStatus="iterator1">
									<tr style="background-color: #f5f5f0">
										<input type="text"
											name="listOfCourses[${iterator1.index}].courseInfoId"
											value="${courseInfo.courseInfoId}" style="display: none;"
											hidden="true" />
										<c:if test="${iterator1.index==0}">
											<th rowspan="${professorToCourse.value.size()}"><input
												type="radio" id="professorId" name="professorId"
												value="${professorToCourse.key.professorId}" /></th>
											<th rowspan="${professorToCourse.value.size()}">${professorToCourse.key.firstName}
												${professorToCourse.key.lastName}</th>
											<th rowspan="${professorToCourse.value.size()}"
												id="empType${professorToCourse.key.professorId}">
												${professorToCourse.key.empType}</th>
										</c:if>
										<td><label> ${courseInfo.courseCode}</label></td>
										<td><label id="courseName${courseInfo.courseInfoId}">
												${courseInfo.courseName}</label></td>
										<td><label> ${courseInfo.courseCredit}</label></td>
										<c:if test="${iterator1.index==0}">
											<c:set value="${professorToCourse.key.professorId}"
												var="professorId"></c:set>
											<th rowspan="${professorToCourse.value.size()}"><label>
													${sheet.professorAllotedHours[professorId]}</label></th>
										</c:if>

										<td><c:forEach
												items="${courseInfo.professorStatus[professorId]}"
												var="professorStatus">
												<c:choose>
													<c:when test="${professorStatus=='blocked'}">
														<input type="checkbox" title="Blocked"
															onclick="this.checked=!this.checked;"
															style="filter: hue-rotate(0deg);" checked="checked" />
														<!-- <input type="checkbox"
																onclick="this.checked=!this.checked;" />
															<input type="checkbox"
																onclick="this.checked=!this.checked;" />
															<input type="checkbox"
																onclick="this.checked=!this.checked;" />
															<input type="checkbox"
																onclick="this.checked=!this.checked;" /> -->
													</c:when>
													<c:when test="${professorStatus=='newHire'}">
														<input type="checkbox"
															onclick="this.checked=!this.checked;"
															style="filter: hue-rotate(0deg);" checked="checked"
															title="Blocked" />
														<input type="checkbox"
															onclick="this.checked=!this.checked;"
															style="filter: hue-rotate(140deg);" checked="checked"
															title="LOI Sent" />
														<!-- <input type="checkbox"
																onclick="this.checked=!this.checked;" />
															<input type="checkbox"
																onclick="this.checked=!this.checked;" />
															<input type="checkbox"
																onclick="this.checked=!this.checked;" /> -->
													</c:when>
													<c:when test="${professorStatus=='teachingNotification'}">

														<input type="checkbox" title="Blocked"
															onclick="this.checked=!this.checked;"
															style="filter: hue-rotate(0deg);" checked="checked" />
														<input type="checkbox"
															onclick="this.checked=!this.checked;" title="LOI Sent"
															style="filter: hue-rotate(140deg);" checked="checked" />
														<input type="checkbox"
															onclick="this.checked=!this.checked;"
															title="Notification Sent"
															style="filter: hue-rotate(210deg);" checked="checked" />
														<!-- <input type="checkbox"
																onclick="this.checked=!this.checked;" />
															<input type="checkbox"
																onclick="this.checked=!this.checked;" /> -->
													</c:when>
													<c:when test="${professorStatus=='contractSentToFaculty'}">
														<input type="checkbox" title="Blocked"
															onclick="this.checked=!this.checked;"
															style="filter: hue-rotate(0deg);" checked="checked" />
														<input type="checkbox" title="LOI Sent"
															onclick="this.checked=!this.checked;"
															style="filter: hue-rotate(140deg);" checked="checked" />
														<input type="checkbox" title="Notification Sent"
															onclick="this.checked=!this.checked;"
															style="filter: hue-rotate(210deg);" checked="checked" />
														<input type="checkbox" style="filter: hue-rotate(250deg);"
															onclick="this.checked=!this.checked;" checked="checked"
															title="Contract Sent" />
														<!-- <input type="checkbox"
																onclick="this.checked=!this.checked;" /> -->
													</c:when>
													<c:when test="${professorStatus=='contractSentToHR'}">
														<!-- <input type="checkbox"
																onclick="this.checked=!this.checked;" checked="checked" />
															<input type="checkbox"
																onclick="this.checked=!this.checked;" checked="checked" />
															<input type="checkbox"
																onclick="this.checked=!this.checked;" checked="checked" />
															<input type="checkbox"
																onclick="this.checked=!this.checked;" checked="checked" /> -->

														<input type="checkbox" title="Blocked"
															onclick="this.checked=!this.checked;"
															style="filter: hue-rotate(0deg);" checked="checked" />
														<input type="checkbox" title="LOI Sent"
															onclick="this.checked=!this.checked;"
															style="filter: hue-rotate(140deg);" checked="checked" />
														<input type="checkbox" title="Notification Sent"
															onclick="this.checked=!this.checked;"
															style="filter: hue-rotate(210deg);" checked="checked" />
														<input type="checkbox" style="filter: hue-rotate(250deg);"
															title="Contract Sent"
															onclick="this.checked=!this.checked;" checked="checked" />
														<input type="checkbox"
															onclick="this.checked=!this.checked;"
															style="filter: hue-rotate(78deg);" checked="checked"
															title="Contract Sent To HR" />
													</c:when>
												</c:choose>
											</c:forEach></td>

									</tr>
								</c:forEach>

								<br>

							</c:forEach>

						</table>
					</form:form>
					<br>
				</div>
				<div>
					<button class="button" type="button" onclick="loiEmailRequest()">Send
						Letter of Intent</button>
					<button class="button" type="button"
						onclick="teachingNotificationEmailRequest()">Send
						Teaching Notification</button>
					<button class="button" type="button"
						onclick="sendContractEmailRequest()">Send Contract</button>
					<br>
					<form method="post" id="updateStatusForm">
						<button style="background-color: red;margin: auto" class="button" type="button" onclick="updateStatus(this)"
							id="loi">Marked as LOI sent</button>
						<button style="background-color: darkolivegreen;margin: auto" class="button" id="teachingNotification" type="button"
							onclick="updateStatus(this)">Marked as notified</button>
						<button style="background-color: green;margin: auto"  class="button" type="button" id="contractToFaculty"
							onclick="updateStatus(this)">Marked as contract sent</button>
						<button style="background-color: mediumorchid;margin: auto"  class="button" type="button" onclick="updateStatus(this)"
							id="contractToHR">Marked as contract sent to HR</button>
					</form>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
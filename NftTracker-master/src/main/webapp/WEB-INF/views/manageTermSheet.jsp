<%@page import="java.util.ArrayList"%>
<%@page import="com.fanshawe.nfttracker.api.entities.CourseInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.fanshawe.nfttracker.api.entities.TermSheet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-select.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/manageTermsheet.css">
<script src="${pageContext.request.contextPath}/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap-select.min.js"></script>
<style>
table, td {
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
<title>LSC Planning Database</title>
<script type="text/javascript">
	function viewSummary(termSheetId) {
		document.getElementById("viewTermSheetSummaryForm").action = '${pageContext.request.contextPath}/viewTermSheetSummary'+"/"
				+ termSheetId;
		document.getElementById("viewTermSheetSummaryForm").submit();
	
	}
	function addSection(button) {
		document.getElementById("addSectionForm").action = '${pageContext.request.contextPath}/addSectionsToTermBlock'
				+ button.id;
		document.getElementById("addSectionForm").submit();

	}

	function submitTermBlockForm(termBlockId) {
		document.getElementById("saveTermBlockDeleteCourseForm"+termBlockId).action = '${pageContext.request.contextPath}/saveTermBlock'
				+ "/" + document.getElementById("termSheetId").innerText;
		document.getElementById("saveTermBlockDeleteCourseForm"+termBlockId).submit();

	}

	function deleteCourseFromTermSheet(termBlockId) {
		if ($('input[name="programName"]:checked').length == 0) {
			alert('Please select courses to delete !');
		} else {
			document.getElementById("saveTermBlockDeleteCourseForm"+termBlockId).action = '${pageContext.request.contextPath}/deleteCoursesFromTermSheet'
					+ "/" + document.getElementById("termSheetId").innerText;
			document.getElementById("saveTermBlockDeleteCourseForm"+termBlockId).submit();
		}
	}

	function blockProfessor(button,termBlockId, courseCredit) {
		document.getElementById("saveTermBlockDeleteCourseForm"+termBlockId).action = '${pageContext.request.contextPath}/blockProfessorForCourse'
				+ "/"
				+ document.getElementById("termSheetId").innerText
				+ "/"
				+ button.id+"/"+courseCredit;
		document.getElementById("saveTermBlockDeleteCourseForm"+termBlockId).submit();
	}

	function unBlockProfessor(button,termBlockId,courseCredit) {
		document.getElementById("saveTermBlockDeleteCourseForm"+termBlockId).action = '${pageContext.request.contextPath}/unblockProfessorForCourse'
				+ "/"
				+ document.getElementById("termSheetId").innerText
				+ "/"
				+ button.id+"/"+courseCredit;
		document.getElementById("saveTermBlockDeleteCourseForm"+termBlockId).submit();
	}

	
	function checkProfessorHours(termSheetId,courseInfoId,courseCredit, termBlockId) {
		var selected = [];
	  for (var option of document.getElementById('assignedProfessors'+courseInfoId).options) {
	    if (option.selected) {
	      selected.push(option.value);
	    }
	  }
	  if(selected!=''){
		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath }/checkProfessorHours/'
					+termSheetId+"/"+courseInfoId+"/"+courseCredit,
			data:{assignedProfessors:selected.toString()},
			success : function(result) {
				if(result=="true"){

				document.getElementById("saveTermBlockDeleteCourseForm"+termBlockId).action = '${pageContext.request.contextPath}/blockProfessorForCourse'
						+ "/"
						+ termSheetId
						+ "/"
						+ courseInfoId
						+"/"
						+courseCredit;
				document.getElementById("saveTermBlockDeleteCourseForm"+termBlockId).submit();	
					}else{
						if(confirm(result+",do you want to override?")){
							document.getElementById("saveTermBlockDeleteCourseForm"+termBlockId).action = '${pageContext.request.contextPath}/blockProfessorForCourse'
								+ "/"
								+ termSheetId
								+ "/"
								+ courseInfoId
								+"/"
								+courseCredit;
						document.getElementById("saveTermBlockDeleteCourseForm"+termBlockId).submit();
							
							}						}
			}
		});}else{
			alert("Please select atleast a professor to block");}
	}

</script>
</head>
<body
	style="background-color: white; margin-top: 10px; margin-right: 10px; margin-left: 10px; text-align: center; border-style: solid; border-width: 1px; border-left-width: 3px; border-right-width: 3px; border-color: #e6e6e6;">
	<%
		if (session.getAttribute("userId") == null && session.getAttribute("username") == null) {
		response.sendRedirect("endSession");
	}
	%>
	<div style="background-color: #f0f0f0;">
		<div>
			<div>
				<h1
					style="text-align: center; background-color: White; color: firebrick; background-color: #f0f0f0;">LSC
					Planning Database</h1>
				<a href="${pageContext.request.contextPath}/home"><button
						class="button" type="button">Home</button></a> <a
					href="${pageContext.request.contextPath}/viewTermSheets"><button
						class="button" type="button">Back</button></a> <a
					href="${pageContext.request.contextPath}/download/${sheet.termSheetId}"><button
						class="button" type="button">Export</button></a> <a
					href="${pageContext.request.contextPath}/logout"><button
						class="button" type="button">Logout</button></a>
				<form:form modelAttribute="termSheet" method="post"
					id="viewTermSheetSummaryForm">
					<button class="button" type="button"
						onclick="viewSummary(${sheet.termSheetId})">View Summary</button>
				</form:form>
				<br>
				<div>
					<br> <label
						style="align-content: center; border: thin; color: black; font-weight: bold; font-size: medium;">
						<c:out value="${sheet.termName}" /> : <c:out
							value="${sheet.termSheetName}" />

					</label> <label id="termSheetId" style="display: none;" hidden="true"><c:out
							value="${sheet.termSheetId}" /></label>
					<c:forEach var="termCourseInfo" items="${sheet.listOfTermBlocks}"
						varStatus="iterator">
						<form:form modelAttribute="termCourse" method="post"
							id="saveTermBlockDeleteCourseForm${termCourseInfo.termBlockId}">
							<input type="text" name="termBlockId"
								value="${termCourseInfo.termBlockId}" hidden="true">
							<table>
								<tr
									style="background-color: #f9dcd2; align-content: center; color: white; font-weight: bold;">
									<td colspan="11"><form:label for="program"
											path="programName">${termCourseInfo.programName}</form:label>-
										<form:label for="level" path="levelName">${termCourseInfo.levelName}</form:label>
										<form:label for="totalWeeks" path="totalWeeks">${termCourseInfo.totalWeeks}
											Weeks</form:label> <form:label for="termStartDate" path="termStartDate">${termCourseInfo.termStartDate}
											</form:label> <form:label for="termEndDate" path="termEndDate">${termCourseInfo.termEndDate}
											</form:label></td>
								</tr>
								<tr style="background-color: #cce6ff; font-weight: bold;">
									<td>Select</td>
									<td>Course Name</td>
									<td>CourseCode</td>
									<td>Section</td>
									<td>Seats</td>
									<td>Program Block</td>
									<td>Credits</td>
									<td>Scheduled Requests</td>
									<td>Faculty</td>
									<td>Allocate/Deallocate</td>
									<td>Status</td>
								</tr>

								<c:forEach var="courseInfo"
									items="${termCourseInfo.listOfCourses}" varStatus="iterator1">
									<tr style="background-color: #f5f5f0">
										<td style="width: 55px;"><form:checkbox
												path="programName" id="programName"
												value="${courseInfo.courseInfoId}" /> <input type="text"
											name="listOfCourses[${iterator1.index}].courseInfoId"
											value="${courseInfo.courseInfoId}" hidden="true"></td>
										<td style="width: 170px;"><form:label
												path="listOfCourses[${iterator1.index}].courseName"
												id="courseName${courseInfo.courseInfoId}">
										${courseInfo.courseName}</form:label></td>

										<td style="width: 80px;"><form:label
												path="listOfCourses[${iterator1.index}].courseCode">
										${courseInfo.courseCode}</form:label></td>
										<td style="width: 55px;"><input type="text"
											id="sectionName" style="width: 55px;"
											name="listOfCourses[${iterator1.index}].sectionName"
											value="${courseInfo.sectionName}" /></td>
										<td style="width: 55px;"><form:input type="text"
												id="numberOfStudents" style="width: 55px;"
												path="listOfCourses[${iterator1.index}].numberOfStudents"
												value="${courseInfo.numberOfStudents}" /></td>
										<td style="width: 80px;"><form:input type="text"
												id="programBlock" style="width: 80px;"
												value="${courseInfo.programBlock}"
												path="listOfCourses[${iterator1.index}].programBlock" /></td>
										<td style="width: 55px;"><form:label style="width: 55px;"
												path="listOfCourses[${iterator1.index}].courseCredit">
										${courseInfo.courseCredit}</form:label></td>
										<td style="width: 350px; padding: 0px"><form:input
												type="text" id="scheduledRequest" style="width: 350px;"
												value="${courseInfo.scheduledRequest}"
												path="listOfCourses[${iterator1.index}].scheduledRequest" /></td>
										<td style="width: 170px;"><c:set var="courseCode"
												value="${courseInfo.courseCode}" /> <c:set var="courseId"
												value="${courseInfo.courseInfoId}" /> <c:choose>
												<c:when test="${courseInfo.assignedProfessors.size()==0}">
													<form:select required="required"
														path="listOfCourses[${iterator1.index}].assignedProfessors"
														id="assignedProfessors${courseInfo.courseInfoId}"
														multiple="multiple" class="selectpicker"
														data-style="btn-primary" data-max-options="2">
														<c:forEach var="professor"
															items="${courseSuggestedProfessor[courseCode]}">
															<option value="${professor.professorId}"><c:out
																	value="${professor.firstName} ${professor.lastName}" /></option>
														</c:forEach>
													</form:select>
												</c:when>
												<c:otherwise>
													<form:label
														path="listOfCourses[${iterator1.index}].assignedProfessors">
														<c:forEach var="professor"
															items="${courseAssignedProfessor[courseId]}"
															varStatus="professorIterator">
															<c:out
																value="${professor.firstName} ${professor.lastName}" />
															<c:if test="${not professorIterator.last}">,</c:if>
														</c:forEach>
													</form:label>
												</c:otherwise>
											</c:choose></td>
										<td style="width: 70px;"><c:choose>

												<c:when test="${courseInfo.assignedProfessors.size()==0}">
													<input
														style="background-color: deepskyblue; color: white; height: 25px; width: 65px"
														type="submit" value="Allocate"
														onclick="checkProfessorHours(${sheet.termSheetId},${courseInfo.courseInfoId},${courseInfo.courseCredit},${termCourseInfo.termBlockId})"
														id="${courseInfo.courseInfoId}">
												</c:when>
												<c:otherwise>
													<input
														style="background-color: deepskyblue; color: white; height: 25px; width: 80px"
														type="submit" value="Deallocate"
														onclick="unBlockProfessor(this,${termCourseInfo.termBlockId},${courseInfo.courseCredit})"
														id="${courseInfo.courseInfoId}">
												</c:otherwise>
											</c:choose></td>

										<td style="width: 190px;"><c:forEach var="professor1"
												items="${courseAssignedProfessor[courseId]}"
												varStatus="professorIterator1">

												<c:set value="${professor1.professorId}" var="a" />
												<c:forEach items="${courseInfo.professorStatus[a]}"
													var="professorStatus">
													<c:out
														value="${professor1.firstName} ${professor1.lastName}"></c:out>
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
															<input type="checkbox"
																style="filter: hue-rotate(250deg);"
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
															<input type="checkbox"
																style="filter: hue-rotate(250deg);"
																title="Contract Sent"
																onclick="this.checked=!this.checked;" checked="checked" />
															<input type="checkbox"
																onclick="this.checked=!this.checked;"
																style="filter: hue-rotate(78deg);" checked="checked"
																title="Contract Sent To HR" />
														</c:when>
													</c:choose>
												</c:forEach>
												<br>
											</c:forEach></td>
									</tr>

								</c:forEach>

							</table>
							<br>
							<button type="submit" name="Save" class="button"
								onclick="submitTermBlockForm(${termCourseInfo.termBlockId})">Save</button>

							<button class="button" type="button" name="deleteCourse"
								id="deleteCourse"
								onclick="deleteCourseFromTermSheet(${termCourseInfo.termBlockId})">Delete
								Course</button>
						</form:form>
						<form method="post" id="addSectionForm" name="addSectionForm">
							<button class="button" type="button" name="Add Section"
								id="/${sheet.termSheetId}/${termCourseInfo.termBlockId}"
								onclick="addSection(this)">Add Section</button>
						</form>
						<br>
						<p id=""></p>
						<br>
					</c:forEach>
					<br>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
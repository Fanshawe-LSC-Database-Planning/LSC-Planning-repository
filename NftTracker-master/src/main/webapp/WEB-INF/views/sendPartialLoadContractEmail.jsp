<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LSC Planning Database</title>
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
	function sendPartialLoadContract() {

		var partTimeContract = document
				.getElementById("partialLoadContractText").innerHTML;
		document.getElementById("body").value = partTimeContract;
		var attachmentForm = new FormData(document
				.getElementById("uploadAttachmentForm"));
		$
				.ajax({
					type : 'POST',
					url : '${pageContext.request.contextPath }/sendPartialLoadContractEmail',
					processData : false,
					contentType : false,
					cache : false,
					data : attachmentForm,
					beforeSend : function() {
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

				<h1 style="text-align: center; color: firebrick;">Partial Load
					Contract Email</h1>
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
			onclick="sendPartialLoadContract()">Send</button>
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
					value="Contract to Hire"
					style="margin-bottom: 10px; border: 1px; border-style: solid; border-color: #6e7376; width: 92.5%"
					type="text" id="subject"><br> <label for="attachment">Attachment
					: </label><input type="file" name="attachment" id="attachment" /> <br>
				<br>
				<div style="border-style: ridge;" contenteditable="true"
					id="partialLoadContractText">
					<div
						style="float: right; width: 100%; text-align: right; margin: 10px 0;">
						<img
							src="${pageContext.request.contextPath}/partTimeContractLogo.png" />
					</div>
					<div style="width: 49%; display: inline-block;">
						<h2 style="text-decoration: underline;">Personal and
							Confidential</h2>
						<p>
							<b>${date}</b>
						</p>
					</div>

					<div style="width: 49%; display: inline-block;">
						<div
							style="width: 300px; border: 1px solid #000; float: right; padding: 10px;">
							<p style="text-decoration: underline;">
								<em>To be completed by division</em>
							</p>
							<p>
								Employee ID: <input type="text" style="width: 65%; float: right"
									value="${professor.employeeId}" />
							</p>
							<p>
								GL Account: <input type="text" style="width: 65%; float: right" />
							</p>
							<p>
								# of Hours: <input type="text" style="width: 65%; float: right" />
							</p>


							<p style="width: 49%; display: inline-block">
								<input type="checkbox" onclick="this.checked=!this.checked;"
									id="vehicle1" name="vehicle1" value="Post-Secondary"> <label>Post
									Secondary</label>
							</p>
							<p style="width: 49%; display: inline-block">
								<input type="checkbox" id="vehicle2"
									onclick="this.checked=!this.checked;" name="vehicle2"
									value="Non-Post-Secondary"> <label>Non-Post
									Secondary</label>
							</p>
							<p style="width: 49%; display: inline-block">
								<input type="checkbox" onclick="this.checked=!this.checked;"
									id="vehicle3" name="vehicle3" value="Credit"> <label>Credit</label>
							</p>
							<p style="width: 49%; display: inline-block">
								<input type="checkbox" id="vehicle4"
									onclick="this.checked=!this.checked;" name="vehicle4"
									value="Non-Credit"> <label>Non-Credit</label>
							</p>
						</div>
					</div>

					<p>
						Ms./Mr. <b>[First and Last Name]<br /> [Insert address]<br />
							[City, Province]<br /> [Postal Code]<br /></b>
					</p>
					<p>
						Dear Ms./Mr. <b>[insert Last Name]:</b>
					</p>

					<p>Fanshawe College is pleased to offer you employment on the
						terms and conditions outlined below.</p>

					<p>
						Your role will be as <b>[Professor, Counsellor, Librarian]</b> in
						the [faculty name] in the School of <b>[insert name]</b>,
						commencing on <b>[insert date]</b> and ending without further
						notice on <b>[insert date]</b> with duties, terms and conditions
						as outlined in the Ontario Colleges of Applied Arts and Technology
						Academic Collective Agreement, a copy of which is enclosed. Please
						note Article 26 (Partial Load Employees), in particular. The
						College reserves the right to assign you teaching within any
						school and at any campus operated by the College.
					</p>

					<p>
						This is a partial-load appointment with remuneration as outlined
						in the collective agreement, starting at <b>[$insert hourly
							rate]</b> per teaching contact hour, <b>[Step xx,]</b> as indicated
						in the enclosed salary calculation form. The hourly rate includes
						vacation pay and is paid for all teaching contact hours taught and
						compensates for other time spent on ancillary duties including,
						but not limited to class preparation, assignment/exam grading
						(including final exam) invigilation, uploading end of term grades
						by (insert final date), student communication, and meeting
						attendances outside of classroom hours. (If applicable, note any
						exclusions during this period – for example, study week) Academic
						Staff are paid bi-weekly by direct bank deposit.
					</p>

					<p>This offer of employment is conditional upon there being
						sufficient enrolment and or availability of work, which is solely
						at the discretion on the College. Nothing in this offer should be
						construed as guaranteeing any particular number of hours per week.
						You agree that if work is not available or there is not sufficient
						enrolment, this employment agreement is void and no compensation
						or obligations will be provided to you from the College. The
						College will inform you if there is not sufficient enrollment or
						availability of work prior to the scheduled start date or planned
						commencement of the term of this agreement.</p>

					<p>If during the term of this appointment you wish to resign or
						if the College finds it necessary to terminate your employment, a
						written notice of 30 days is required. The College may terminate
						your employment at any time for cause without notice or pay in
						lieu of notice.</p>

					<p>You will receive vacation and public holiday pay
						entitlements pursuant to the Employment Standards Act, 2000.</p>

					<p>You agree that your accrued statutory vacation pay will be
						paid out each pay period. You further agree that the College may
						provide you with statutory holiday pay for statutory holidays that
						fall on a day that would not ordinarily be a working day for you
						or that fall on a day that you are on vacation, instead of
						providing you with a substitute day off with statutory holiday
						pay.</p>

					<p>You agree that the College is entitled to recover any
						overpayment that may have been made to you under this or any other
						contract between you and the College. You agree that the College
						will be entitled to offset any such overpayment against any wages
						otherwise owed to you on the pay period following notice to you of
						the overpayment. Should your employment be severed prior to the
						repayment in full of any overpayment, you agree that any
						outstanding monies owed due at the date of termination will be
						deducted from any monies owing to you by the College, including
						final wages, outstanding vacation pay, termination and/or
						severance pay.</p>

					<p>This offer is conditional upon the College's receipt of
						evidence of your highest academic credential directly from the
						granting agency and our assessment of Canadian equivalency, where
						applicable. This documentation is to be received by the College
						prior to your effective date of hire, or in any event, within
						fourteen (14) days of the date of this letter. If for any reason
						this documentation cannot be received within the above-noted
						timeframe, please contact the Chief Human Resources Officer, who
						will determine an appropriate course of action.</p>

					<p>If upon receipt of your academic credentials, the College
						determines that you do not hold the requisite credentials or that
						you in any way misrepresented your credentials, this offer is null
						and void. If you have commenced employment, the foregoing shall
						constitute grounds for the immediate termination of your
						employment.</p>

					<p>You are not permitted to accept other appointments or
						contracts within the College without the advance written
						permission of your manager. This confirms you have advised that
						you currently [are/are not] engaged in other employment or
						contractual relations with Fanshawe College [as follows: insert
						other teaching assignments, support work or vendor/contractual
						relationships].</p>

					<p>
						You are required to review and comply with all College policies
						and procedures, as established from time to time. These policies
						and procedures can be accessed on the Fanshawe College web site at
						www.fanshawec.ca/about-us/corporate-information/college-policies.</a>
					</p>

					<p>
						Fanshawe College has an accommodation process in place to provide
						support for employees with disabilities. Please refer to the
						following College policies, available on the College website at <a
							href="www.fanshawec.ca/about-us/corporate-information/college-policies"
							target="_blank">www.fanshawec.ca/about-us/corporate-information/college-policies</a>:
					</p>

					<ul>
						<li>P203 Accessibility for Employees with Disabilities</li>
						<li>P204 Accommodation for Employees with Disabilities</li>
					</ul>

					<p>As a College employee you are eligible to join the College
						of Applied Arts & Technology Pension Plan (CAAT Pension Plan), a
						defined benefit pension plan. Please visit the Plan's website
						(www.caatpension.ca) for details about the Plan, how you may join,
						and factors to consider before joining.</p>

					<strong>Documentation:</strong>
					<ul>
						<li>Member's handbook -
							(https://www.caatpension.ca/members/member-resources)</li>
						<li>Enrolment form -
							(https://www.caatpension.ca/caat/assets/documents/member
							forms/enrolment_dbplus_enr-101_08.2020-e.pdf) [NOTE: Please enter
							spaces as underscores]</li>
					</ul>
					<p>
						<em><strong>Important:</strong> If you would prefer to
							receive these CAAT documents in paper format, please contact
							Human Resources at extension 4246 for copies. Otherwise, you will
							be deemed to have consented to receive these documents in
							electronic format.</em>
					</p>

					<p>
						<strong>Options: Elect one option and place your initials
							by the appropriate box</strong>
					</p>

					<ul style="list-style: none; margin: 0; padding: 0;">
						<li><span
							style="border-top: 1px solid #000; display: inline-block; vertical-align: top; margin-top: 14px;">initial</span><input
							type="checkbox" id="vehicle2" name="vehicle2"
							value="Non-Post Secondary"
							style="display: inline-block; vertical-align: top;">
							<p style="display: inline-block; width: 90%;">
								<font style="text-decoration: underline">I elect to
									become a member</font> of the Colleges of Applied Arts & Technology
								Pension Plan. My completed and signed enrolment form is attached
								to this contract.
							</p></li>

						<li><span
							style="border-top: 1px solid #000; display: inline-block; vertical-align: top; margin-top: 14px;">initial</span><input
							type="checkbox" id="vehicle2" name="vehicle2"
							value="Non-Post-Secondary"
							style="display: inline-block; vertical-align: top;">
							<p style="display: inline-block; width: 90%;">
								I have received information with respect to my right to become a
								Member of the Colleges of Applied Arts & Technology Pension
								Plan, and by signing this offer letter, I confirm that <font
									style="text-decoration: underline;">I do not wish to
									become a member at this time.</font> I understand that if I apply to
								become a Member at a later date, it will be under the terms of
								the Plan in effect at that time. If I am eligible to join when I
								apply, my membership will be effective from my enrolment date
								and will not be retroactive.
							</p></li>

						<li><span
							style="border-top: 1px solid #000; display: inline-block; vertical-align: top; margin-top: 14px;">initial</span><input
							type="checkbox" id="vehicle2" name="vehicle2"
							value="Non-Post-Secondary"
							style="display: inline-block; vertical-align: top;">
							<p style="display: inline-block; width: 90%;">
								<font style="text-decoration: underline">I am currently a
									member</font> of the CAAT Pension Plan at this College or at another
								Initials College; I have made pension contributions in the past
								at this College or at another College.
							</p></li>
					</ul>
					<p>If you have not taught a course at Fanshawe or have not
						previously completed Orientation for College Teaching, it is
						strongly recommended you enroll in this workshop. You may be
						required to attend training related to Health and Safety.</p>

					<p>In order to administer the employment relationship and
						conduct normal College business, it may be necessary for the
						College to collect, use and disclose personal information about
						you, subject to the requirements of the law.</p>

					<p>Should any provision of this Agreement be determined void or
						unenforceable, it shall not be deemed to affect or impair the
						validity of any other provision.</p>

					<p>
						Please contact <b>[insert hiring Manager]</b> at <b>[insert
							phone number]</b> to provide a signed copy of this letter no later
						than [insert date]. A signed copy is required to initiate your
						pay.
					</p>

					<p>I trust you will find this position both challenging and
						rewarding. To get you started on your journey, please visit our
						Teaching and Learning website at www.fanshawec.ca/teaching.</p>

					<p>Yours truly,</p>



					<p>
						<b>[Hiring Manager's Name]<br /> [Hiring Manager's Title,
							Insert School name/Area/Dept]
						</b>
					</p>


					<p>cc: Human Resources</p>

					<p>I hereby accept employment on the terms and conditions noted
						herein and confirm receipt of a copy of the position description
						form. I confirm my election/the information provided with respect
						to the CAAT Pension Plan, above. I further confirm that I [am/am
						not] presently engaged in a contractual relationship with Fanshawe
						College [as follows: insert other teaching assignments, support
						work or vendor/contractual relationships]. I confirm that I am
						legally entitled to work in Canada.</p>

				</div>
				<br>
			</form>
		</div>
	</div>
</body>
</html>
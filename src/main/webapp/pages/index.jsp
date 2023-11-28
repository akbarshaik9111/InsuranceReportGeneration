<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>INSURANCE REPORT</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h2>REPORT APPLICATION</h2>
		<form:form action="search" modelAttribute="search" method="post">
			<table>
				<tr>
					<td>PLAN NAME:</td>
					<td>
						<form:select path="planNames">
							<form:option value="">--Select--</form:option>
							<form:options items="${names}"/>
						</form:select>
					</td>
					
					<td>PLAN STATUS:</td>
					<td>
						<form:select path="planStatus">
							<form:option value="">--Select--</form:option>
							<form:options items="${status}"/>
						</form:select>
					</td>
					
					<td>GENDER:</td>
					<td>
						<form:select path="gender">
							<form:option value="">--Select--</form:option>
							<form:option value="MALE">MALE</form:option>
							<form:option value="FE-MALE">FE-MALE</form:option>
						</form:select>
					</td>
				</tr>
				
				<tr>
					<td>PLAN START DATE: </td>
					<td>
						<form:input path="planStartDate" type="date"/>
					</td>
					
					<td>PLAN END DATE: </td>
					<td>
						<form:input path="planEndDate" type="date"/>
					</td>
				</tr>
				<tr>
					<td>
						<a href="/" value="reset" class="btn btn-secondary">RESET</a>
						<form:button class="btn btn-primary">SEARCH</form:button>
					</td>
				</tr>
				
			</table>
			<hr/>
				<table class="table table-striped table-hover table-success">
					<thead>
						<tr>
							<th>S.NO</th>
							<th>CITIZEN NAME</th>
							<th>GENDER</th>
							<th>PLAN NAME</th>
							<th>PLAN STATUS</th>
							<th>PLAN START DATE</th>
							<th>PLAN END DATE</th>
							<th>BENEFIT AMOUNT</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${plans}" var="plan" varStatus="index">
							<tr>
								<td>${index.count}</td>
								<td>${plan.citizenName}</td>
								<td>${plan.gender}</td>
								<td>${plan.planNames}</td>
								<td>${plan.planStatus}</td>
								<td>${plan.planStartDate}</td>
								<td>${plan.planEndDate}</td>
								<td>${plan.benefitAmount}</td>
							</tr>
						</c:forEach>
						<tr>
							<c:if test="${empty plans}">
								<td colspan="8" style="text-align: center;">NO RECORDS FOUND</td>
							</c:if>
						</tr>
						
					</tbody>
				</table>
			<hr/>
			EXPORT: <a href="excel">EXCEL</a> <a href="pdf">PDF</a>
			
		</form:form>
	</div>
	



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>

</body>
</html>
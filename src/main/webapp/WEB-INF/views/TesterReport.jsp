<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp"%>
</head>
<body>


	<div class="container mt-3">

		<h1>Add Tester Form</h1>
		<a href="addTester" class="btn btn-primary"> Add Tester </a>
		<div class="row">

			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Required</th>
						<th scope="col">Req MinLength Integer</th>
						<th scope="col">MinLength Long Optional</th>
						<th scope="col">Req MinLength</th>
						<th scope="col">Email</th>
						<th scope="col">MinLength</th>
						<th scope="col">MinLength Integer</th>
						<th scope="col">MaxLength</th>
						<th scope="col">Pattern</th>
						<th scope="col">Required Email</th>
						<th scope="col">Required Long</th>
						<th scope="col">Pattern Max Length</th>
						<th scope="col">Required Pattern</th>
						<th scope="col">Integer</th>
						<th scope="col">Long</th>
						<th scope="col">Required MinLength Long</th>
						<th scope="col">Long</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tester" items="${tester}">
						<tr>
							<td class="table-plus">${tester.id}</td>
							<td>${tester.required}</td>
							<td>${tester.requiredMinLengthInteger}</td>
							<td>${tester.minLengthLongOptional}</td>
							<td>${tester.requiredMinLength}</td>
							<td>${tester.email}</td>
							<td>${tester.minLengthVar}</td>
							<td>${tester.minLengthInteger}</td>
							<td>${tester.maxLengthVar}</td>
							<td>${tester.pattern}</td>
							<td>${tester.requiredEmail}</td>
							<td>${tester.requiredLong}</td>
							<td>${tester.patternMaxLength}</td>
							<td>${tester.requiredPattern}</td>
							<td>${tester.integerVar}</td>
							<td>${tester.longVar}</td>
							<td>${tester.requiredMinLengthLong}</td>
							<td><a href="editTester/${tester.id}" class="btn btn-warning">
									Edit </a></td>
							<td><a href="deleteTester/${tester.id}"
								class="btn btn-danger"> Delete </a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
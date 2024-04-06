<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp"%>
</head>
<body>


	<div class="container mt-3">

		<h1>Add Employee Form</h1>
		<form:form action="insertEmployee" method="post" modelAttribute="insertEmployee">
			<form:errors path="*" cssClass="error" element="div"/>
			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="name">Name</label> 
						<form:input path="name" id="name" class="form-control" placeholder="Enter Name"/>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="designation">Designation</label> 
						<form:input path="designation" id="designation" class="form-control" placeholder="Enter Designation"/>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="department">Department</label> 
						<form:input path="department" id="department" class="form-control" placeholder="Enter Department"/>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="salary">Salary</label> 
						<form:input path="salary" id="salary" class="form-control" placeholder="Enter Salary"/>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="address">Address</label>
						<form:textarea path="address" id="address" class="form-control" rows="5" placeholder="Enter Address"/>
					</div>
				</div>
			</div>

			<input type="submit" class="btn btn-primary" value="Submit"/>
			<%-- <a href="${pageContext.request.contextPath }/"
				class="btn btn-warning"> Back </a>
			<button type="submit" class="btn btn-primary">Submit</button> --%>
		</form:form>

	</div>

</body>
</html>
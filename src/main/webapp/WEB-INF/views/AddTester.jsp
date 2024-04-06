<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp"%>
<script src="../../js/attachmentFunctions.js"></script>
</head>
<body>


	<div class="container mt-3">

		<h1>Add Tester Form</h1>
		<form:form action="insertTester" method="post" modelAttribute="insertTester">
			<form:errors path="*" cssClass="error" element="div"/>
			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="name">Required</label> 
						<form:input path="required" id="required" class="form-control" placeholder="Enter Required"/>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="designation">Required MinLength Integer</label> 
						<form:input path="requiredMinLengthInteger" id="requiredMinLengthInteger" class="form-control" placeholder="Enter Required MinLength Integer"/>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="designation">MinLength Long Optional</label> 
						<form:input path="minLengthLongOptional" id="minLengthLongOptional" class="form-control" placeholder="Enter MinLength Long Optional"/>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="department">Required Min Length</label> 
						<form:input path="requiredMinLength" id="requiredMinLength" class="form-control" placeholder="Enter Required Min Length"/>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="salary">Email</label> 
						<form:input path="email" id="email" class="form-control" placeholder="Enter Email"/>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="department">Min Length (Not Required)</label> 
						<form:input path="minLengthVar" id="minLengthVar" class="form-control" placeholder="Enter Min Length"/>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="salary">MinLength Integer</label> 
						<form:input path="minLengthInteger" id="minLengthInteger" class="form-control" placeholder="Enter MinLength Integer"/>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="department">Max Length</label> 
						<form:input path="maxLengthVar" id="maxLengthVar" class="form-control" placeholder="Enter Required Max Length"/>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="salary">Pattern</label> 
						<form:input path="pattern" id="pattern" class="form-control" placeholder="Enter Pattern"/>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="department">Required Email</label> 
						<form:input path="requiredEmail" id="requiredEmail" class="form-control" placeholder="Enter Required Email"/>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="salary">Required Long</label> 
						<form:input path="requiredLong" id="requiredLong" class="form-control" placeholder="Enter Required Long"/>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="department">Pattern Max Length</label> 
						<form:input path="patternMaxLength" id="patternMaxLength" class="form-control" placeholder="Enter Pattern Max Length"/>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="salary">Required Pattern</label> 
						<form:input path="requiredPattern" id="requiredPattern" class="form-control" placeholder="Enter Required Pattern"/>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="department">Integer</label> 
						<form:input path="integerVar" id="integerVar" class="form-control" placeholder="Integer"/>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="salary">Long</label> 
						<form:input path="longVar" id="longVar" class="form-control" placeholder="Enter Long"/>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="department">Required MinLength Long</label> 
						<form:input path="requiredMinLengthLong" id="requiredMinLengthLong" class="form-control" placeholder="Required MinLength Long"/>
					</div>
				</div>
			</div>

<%-- 			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="address">Address</label>
						<form:textarea path="address" id="address" class="form-control" rows="5" placeholder="Enter Address"/>
					</div>
				</div>
			</div> --%>
			
			
        <div class="panel-body" id ="input_fields_wrap"> 
            <div class="form-group form-group-sm multipleAttachments initialAttachment docUp">
                <div class="col-sm-3 docInp">          
                    <label for="documentType" class="control-label"><bean:message bundle="eForm" key="input.label.documentType"/></label>           
			        <select name="documentType[0]" id="docType0" class="form-control docinput">
                        <option value = ""></option>
                    </select>
                </div>
				<div class="col-sm-3 fileInp">
				    <label for="file" class="control-label"><bean:message bundle="eForm" key="input.label.uploadFile"/></label>
				    <div class="input-group input-group-sm">                       
                        <label class="input-group-btn">
                            <span class="btn btn-primary">
				                Browse <input type="file" class="fileinput" name="file[0]" style="display:none"/>
				            </span>
				        </label>
				        <input type="text" class="form-control" readonly>
				    </div>             
                </div>
                <div class="col-sm-3">
                    <br>
                    <div><a href="#" id="switch-clear" class="clearDocument" >Clear</a></div>               
                </div>
            </div>
			<p style="color:red;display:none;" id="docExistsFileDne"><bean:message bundle="eForm" key="input.error.attachment.docExistsFileDne"/></p>
			<p style="color:red;display:none;" id="fileExistsDocDne"><bean:message bundle="eForm" key="input.error.attachment.fileExistsDocDne"/></p>
			<p style="color:red;display:none;" id="sizeWarning"><bean:message bundle="eForm" key="input.error.file.tooBig" arg0="<%= (Integer.parseInt(System.getProperty("portal.hhs828.attachment.maxSize", "15728640")) / (1024 * 1024)) + "MB"%>"/></p>
			<p style="color:red;display:none;" id="extensionWarning"><bean:message bundle="eForm" key="input.error.file.notpdf"/></p> 
			<p style="color:red;display:none;" id="duplicateDocType"><bean:message bundle="eForm" key="input.error.attachment.duplicateDocType"/></p>
        </div>
        <div style="padding-left: 20px">
            <button class="btn btn-primary add_field_button">Add More Files</button><br><br>
        </div>

			<input type="submit" class="btn btn-primary" value="Submit"/>
			<%-- <a href="${pageContext.request.contextPath }/"
				class="btn btn-warning"> Back </a>
			<button type="submit" class="btn btn-primary">Submit</button> --%>
		</form:form>

	</div>

</body>
</html>
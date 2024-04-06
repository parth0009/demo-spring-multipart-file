<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Attachment Panel begin -->
<div class="accordion-item" role="tab" id="headingAttachmentPanel">
    <h4 class="accordion-header">
        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseAttachmentPanel" aria-expanded="true" aria-controls="#collapseAttachmentPanel">
            Attachments
        </button>               
    </h4>
    <div id="collapseAttachmentPanel" class="accordion-collapse collapse show" role="tabpanel" aria-labelledby="headingAttachmentPanel">
        <div class="accordion-body" id="input_fields_wrap">
            <div class="form-group form-group-sm row g-3 mb-3 multipleAttachments initialAttachment docUp">
                <div class="col-sm-3 docInp">          
                    <label for="documentType" class="form-label">Document Type</label>           
			        <select name="documentType[0]" id="docType0" class="form-select form-select-sm docinput">
                        <option value = ""></option>
                    </select>
                </div>
				<div class="col-sm-3 fileInp">
				    <label for="file" class="form-label">Upload File</label>
				    <div class="input-group input-group-sm">                       
                        <label class="input-group-btn">
                            <span class="btn btn-primary">
				                Browse <input type="file" class="fileinput" name="fileMap[0]" style="display:none"/>
				            </span>
				        </label>
				        <input type="text" class="form-control form-control-sm" readonly>
				    </div>             
                </div>
                <div class="col-sm-3">
                    <br>
                    <div><a href="#" id="switch-clear" class="clearDocument" >Clear</a></div>               
                </div>
            </div>
			<p style="color:red;display:none;" id="docExistsFileDne">Doc Exists File DNE</p>
			<p style="color:red;display:none;" id="fileExistsDocDne">File Exists Doc DNE</p>	
			<p style="color:red;display:none;" id="sizeWarning">
			<p style="color:red;display:none;" id="extensionWarning">Not a PDF</p> 
            <p style="color:red;display:none;" id="duplicateDocType">Duplicate Doc Type</p>			
        </div>
        <div style="padding-left: 20px">
            <button class="btn btn-primary add_field_button">Add More Files</button><br><br>
        </div>
    </div>
</div>
<!--  Attachment panel end -->
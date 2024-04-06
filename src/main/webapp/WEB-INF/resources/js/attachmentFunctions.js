//Start - SCMS-777 Multiple document upload functions.
// SCMS-777 Below functions are mainly used for the multiple document upload logic. Mainly for getting the required document Types for Service requestor
// and generating the attachment div elements for the default document types.

// Document Type variables
var docTypes;

// SCMS-777 The below gobal variables are used for the multiple attachment logic. 
// Defining x = -1 because of a tricky logic where the attachment element index has to start with 0 and to generate the first element it was needed.
var x = -1; //initlal text box count
var docUpFirstElem$;
var docTypes;
var isApplicantInfoReadOnly;
var isApplicantInfoWritable;
var existingAttachmentArray = [];

// Since we have a limit of only 11 documents allowed to be uploaded, the below function prevents user from adding more fields.
function toggleDisabledAddMoreField() {
    if (x >= 10) {
        $(".add_field_button").attr("disabled", true);
    } else {
        $(".add_field_button").attr("disabled", false);
    }
}

// Clicking on Add More Files will trigger the below function. 
function onClickAddMoreFile(e) {
    e.preventDefault();
    var max_fields = 11; //maximum document types allowed to select

    var wrapper = $("#input_fields_wrap"); //Fields wrapper

    if (x < max_fields) {
        //max input box allowed
        x++; //text box increment
        var cloneElement = cloneDocUpElement();
        var clearElement = cloneElement.find('#switch-clear');
        clearElement.removeClass('clearDocument');
        clearElement.addClass('removeDocument');
        clearElement.text('Remove');

        $(wrapper).append(cloneElement);
        // If document types are not populating, need to call populateDocTypeSelect(x) here. It should work fine without it though.
        toggleDisabledAddMoreField();
    }
}

// resetAttachments will be called when there is a onChange event on requestType or applicantType fields
// This is needed so that we can retrieve required document types based on the new change and populate the document type fields.
// Different from onClickAddMoreFile() - The initial options loaded will not have Remove option, instead they will only have option to clear the fields. 
// We can pass in a parameter to onClickAddMoreFile and handle it that way, but this is more cleaner to understand.  
function resetAttachments() {
	var max_fields = 11;
	var wrapper = $("#input_fields_wrap"); //Fields wrapper
	
	if (x < max_fields) {
		x++;
		var cloneElement = cloneDocUpElement();
        var clearElement = cloneElement.find('#switch-clear');
        clearElement.addClass('clearDocument');
        clearElement.removeClass('removeDocument');
        clearElement.text('Clear');
        
        $(wrapper).append(cloneElement);
        toggleDisabledAddMoreField();
	}
}

// onRemoveDocument gets called when the user click on Remove besides the attachment div to remove that specific element from the page. 
function onRemoveDocument(e) {
    e.preventDefault();
    if ($(this).hasClass("clearDocument")) {
        // With the current div struction, this was the best solution that I was able to come up with.
        // Instead of using .parent().parent().parent(), there is another way .closest() or .parents() that we can use, but need to look more into that. For now this should work.
        // TODO: Parth - Try to replace with .closest() in the near future. 
        $(this).parent().parent().parent().find("input[type=file]").val("");
        $(this).parent().parent().parent().find("input[type=text]").val("");
    } else {
        $(this).parents(".form-group").remove();
        $(".docUp").each(function(index, value) {
            $(this)
            .find("select")
            .attr("name", "documentType[" + index + "]");
            $(this)
            .find("select")
            .attr("id", "docType[" + index + "]");
            $(this)
            .find("input[type=file]")
            .attr("name", "fileMap[" + index + "]");
        });

        // Since we are removing an element from the attachment div, we need to correct to counter for x. 
        x--;
    }

    toggleDisabledAddMoreField();
}

// Method for file input handling.
function onFileChange() {
    var input = $(this),
    label = input.val().replace(/\\/g, "/").replace(/.*\//, "");
    input.trigger("fileselect", [1, label]);
}

// Method for file input handling.
function onFileSelect(event, numFiles, label) {
    var input = $(this).parents(".input-group").find(":text"),
    log = numFiles > 1 ? numFiles + " files selected" : label;

    if (input.length) {
        input.val(log);
    } else {
        if (log) alert(log);
    }
}

// cloneDocUpElement will clone the first attachment element.
function cloneDocUpElement(value) {
    var docUpCloneElement = docUpFirstElement.clone();
    var selectField = docUpCloneElement.find('.docinput');
    var options = selectField.find('option');

    if (!options) {
        return docUpCloneElement;  
    }
    
    var browseField = docUpCloneElement.find('.fileInp input.fileinput');
    var optionsArray = options.toArray();

    selectField.attr('name', "documentType[" + x + "]");
    selectField.attr('id', "docType" + x);
    browseField.attr('name', "fileMap[" + x + "]");

    for (var i = 0; i < options.length; i++) {
        let option$ = optionsArray[i];
        let optionValue = option$.value;

        if (optionValue === value) {
        	option$.setAttribute('selected', true);
            selectField.attr('readonly', true);
            selectField.find('option:not(:selected)').attr('disabled',true);
            break;
        }
    }
    return docUpCloneElement;
}

/**
 * HHS-34452 We're validating multiple documents upload on HRCO and Service Requestor pages
 * Having this in a separate js file to possible use this in future if needed. 
 * 
 * @returns {Boolean}
 */
function validateAttachments(maxFileSize){
    var isError = false;
    var docTypeObject = $('.docinput');
    var fileObject = $('.fileinput');

    var duplicateDocType = false;
	var docExistsFileDne = false;
	var fileExistsDocDne = false;
    var extensionWarning = false;
    var sizeWarning = false;
    for(var i=0; i < docTypeObject.length; i++) {
    	//Throws validation for duplicate Document Type
        for (var k=i+1; k < docTypeObject.length; k++) {
            if ((docTypeObject[i].value == docTypeObject[k].value) && "" != docTypeObject[i].value) {
                duplicateDocType = true;
                isError = true;
                break;
            }
        }
        
        //Throws validation error if docType exists but file doesn't and vice versa
        if(docTypeObject[i].value && !fileObject[i].value) {
            docExistsFileDne = true;
            isError = true;
            break;
        }
        if(!docTypeObject[i].value && fileObject[i].value) {
            fileExistsDocDne = true;
            isError = true;
            break;
        }      
        
        // Throws validations for file extensions and size
        for(var j=0; j < fileObject.length; j++) {
            var fileExtension = fileObject[j].value.split('.').pop();
            if(fileExtension != 'pdf' && fileExtension != 'PDF' && fileExtension !=''){
                extensionWarning = true;
                isError = true;
                break;
            }
            if(fileObject[j].value != "" && fileObject[j].files[0].size > maxFileSize){
                sizeWarning = true;
                isError = true;
                break;
            }
        }
    }

    if (isError) {

        hideErrors();

		if (duplicateDocType) {
			$('#duplicateDocType').show();
		} 
		if (docExistsFileDne) {
			$('#docExistsFileDne').show();	
		} 
		if (fileExistsDocDne) {
			$('#fileExistsDocDne').show();
		} 
		if (extensionWarning) {
			$('#extensionWarning').show();
		} 
		if (sizeWarning) {
			$('#sizeWarning').show();
		}

        $('#submitConfirm').modal('toggle')
        $('html, body').animate({
            scrollTop: $("#headingAttachmentPanel").position().top
        }, 500);
    }

    return isError;
}
var initialRequiredDocTypes;
$(document).ready(function() {
	alert("working");
	//SCMS-777 Multiple Attachment Upload logic
	// Start - New Attachment Logic.
	var body = $("body");
	var add_button = $(".add_field_button"); //Add button ID	
	add_button.click(onClickAddMoreFile);
	
	body.delegate(".clearDocument, .removeDocument", "click", onRemoveDocument);
	body.delegate(".fileinput", "change", onFileChange);
	body.delegate(":file", "fileselect", onFileSelect);
	
	// Perform the below logic only if there are existing attachments uploaded to the form and assign the existing values to the empty array.
	// This is so that we can remove the option from the mandatory list. 
	if ($('#attachmentTable').length) {
		var i = 0;
		var tableBody = document.getElementById('attachmentTableBody');
		$("#attachmentTableBody tr").each(function() {
		    var val = $(tableBody.rows[i].cells[0]).text();
		    i++;
		    
		    existingAttachmentArray.push(val);
		});
	}
	
	
	// populating first document type dropdown for dynamic document upload
	var firstDocType = 0;
	retrieveDocTypes(firstDocType);            	
	// End - New Attachment Logic.
});



function retrieveDocTypes(id) {
	var data = {
        docTypes: [
            // Add your mock document types here
            {label: "OF-306", value: "OF-306"},
            {label: "OF-8", value: "OF-8"},
            {label: "Resume", value: "Resume"},
			{label: "CV", value: "CV"},
			{label: "Position Designation Tool", value: "PDT"}
        ]
    };
	  docTypes = data.docTypes;
	   populateDocTypeSelect(id);
	   // Additional logic for populating the multiple attachment divs before calling initRequiredDocuments().
	   const inputFieldWrapElement = $('#input_fields_wrap');
	   docUpFirstElement = inputFieldWrapElement.children().first().clone();
	   initRequiredDocuments();

}


//SCMS-777 This function retrieves the required doc Types required on service requestor page based on requestType and ApplicantType.
// Called from initRequiredDocuments, and the callback function helps to resume the other logic once the result is available.

function retrieveRequiredDocTypes(callback) {
var data = {
        requiredDocTypes: "OF-306,OF-8"
    };
            initialRequiredDocTypes = data.requiredDocTypes;
            // This is necessary to perform additional logic once we have the data.
            callback();
}

function hideErrors() {
	$('#duplicateDocType').hide();
	$('#docExistsFileDne').hide();
	$('#fileExistsDocDne').hide();
	$('#extensionWarning').hide();
	$('#sizeWarning').hide();
}

function populateDocTypeSelect(docTypeId){
    var documentTypeId = document.getElementById('docType'+ docTypeId);
    if(documentTypeId.options.length <= 1){
        $.each(docTypes, function() {  
            $(documentTypeId).append(new Option(this.label, this.value)); 
        });
    }
}


/*
 * initRequiredDocument - Retrieves the required document types by calling retrieveRequiredDocTypes() that calls the servlet and performs additional logic
 * of filling up the docUp elements with the required document types. 
 */
function initRequiredDocuments(init) {

    var requiredDocPropertyString = "portal." + $('#formType').val() + ".required.documentTypes.";

    // retrieveRequiredDocTypes calls the servlet, gets the data and then does a callback, returns here to do the additional processing.
	retrieveRequiredDocTypes(function() {

		if (!initialRequiredDocTypes) {
            // Only reset attachments when there is an on change event called from change of request Type or applicantType fields. 
			if (init) {
				resetAttachments();
			} else {
				// We do not load any pre selected documents on HRCO so x needs to be 0 and not -1. Hence x++
				x++;
			}
			return;
		}
        // The request document types data retured is in the string format "Resume,OF-306,OF-8" etc so doing a .split on it to iterate.
		initialRequiredDocTypes = initialRequiredDocTypes.split(',');
        if (!initialRequiredDocTypes.length) return;
        
        // Tricky part: We need to determine the existing attachments on the page, and remove them from the result array so that we don't force the user to upload those again.
        var removeItemFromResult;
        initialRequiredDocTypes.forEach(function(value) {
        	removeItemFromResult = value;
        	if (existingAttachmentArray.includes(value)) {
	        	initialRequiredDocTypes = jQuery.grep(initialRequiredDocTypes, function(value) {
	        		return value != removeItemFromResult;
	        	});
	        }
        });
        
        // Now we check again if initialRequiredDocTypes is empty or not. If it is empty we return.
        if (!initialRequiredDocTypes.length) return;
        
        
        var inputFieldWrapElement = $('#input_fields_wrap');
        initialRequiredDocTypes.forEach(function(value) {
            x++;
            var elem = cloneDocUpElement(value);
            inputFieldWrapElement.append(elem);
        });

        if (!init) {
        	inputFieldWrapElement.find('.docUp:first').remove();
        }
	});
}
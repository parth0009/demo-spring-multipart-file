<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<bean:define id="applicantTo"  name="applicantTo" type="com.deloitte.idms.eForm.transferobjects.EFormTO" scope="session" />
<c:set var="date_format"><fmt:bundle basename="com.deloitte.idms.portal.eForm.EForm"><fmt:message key="date.format"/></fmt:bundle></c:set>
<div class="col-sm-12"> 
<a role="button" data-bs-toggle="collapse" href="#viewAttachments" aria-expanded="false" aria-controls="viewAttachments">
  View Attachments  
</a>
<div class="collapse" id="viewAttachments">
 <div class="row g-3 mb-3">            
    <table id="attachmentTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Document Type</th>
                <th>Uploaded By</th>
                <th>Uploaded On</th>
                <c:if test="${not empty param.includeDownloadLink}">
                    <th>Download</th>
                </c:if>
            </tr>
        </thead>
        <tbody id="attachmentTableBody">
            <c:if test="${not empty applicantTo.attachments}">
              <c:forEach var="attachment" items="${applicantTo.attachments}">
                  <tr>
                      <td class="documentTypeCell">${attachment.documentType}</td>
                      <td>${attachment.uploadedBy}</td>
                      <td><fmt:formatDate value="${attachment.uploadedOn}" pattern="${date_format}" /></td>
                      <c:if test="${not empty param.includeDownloadLink}">
	                      <td>                              
	                        <a href="/idms/portal/attachment/downloadAttachment.do?fileId=${attachment.idString}&hhsid=${applicantTopersonId}"><img src="/idms/portal/static/public/images/pdf-document.gif" alt="Download PDF"  />Download</a>
	                      </td>
                      </c:if>
                  </tr>
              </c:forEach>
          </c:if>
          </tbody>
      </table>
      </div>
  </div>
</div> 
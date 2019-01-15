<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

</head>

<body>
<display:table pagesize="5" class="report" name="reports" 
	requestURI="${requestURI}" id="row">
	
	<security:authorize access="hasRole('REFEREE')">
	<spring:message code="report.moment" var="moment" />
	<display:column  title="${moment}">
		${row.moment}
	</display:column>
	
	<spring:message code="report.description" var="description" />
	<display:column property="description" title="${description}"/>
	
	
	<spring:message code="report.attachment" var="attachment" />
	<display:column property="attachment" title="${attachment}"/>
	
	<jstl:if test="${row.isFinal==false}">
	<display:column>
		<form action="report/referee/edit" method="post"> 
		<input type="hidden" id="complaintId" name="complaintId" value="${row.id}" /> 
		<input type="submit" name="edit"
		value="<spring:message code="report.edit" />" /> 
  	 	</form>
	</display:column>
	</jstl:if>		
	<display:column>
		<form action="note/customer,handyWorker,referee/create.do" method="post">
		<input type="hidden" id="reportId" name="reportId" value="${reportId}" /> 
		<input type="submit" name="create"
		value="<spring:message code="note.create" />" /> 
  	 	</form>
	</display:column>	
	
	</security:authorize>

</display:table>

<input type="button" name="Back" value="<spring:message code="report.back" />"
			onclick="javascript: relativeRedir('complaint/referee/listSelfAssigned.do');" />&nbsp;
 
		<input type="button" name="create"
		value="<spring:message code="report.create" />"
		onclick="javascript: relativeRedit('report/referee/create.do');"/>
</body>



</html>
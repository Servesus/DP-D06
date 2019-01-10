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
	<security:authorize access="hasRole('HANDYWORKER')">
<body>

<display:table pagesize="5" class="phase" name="phases" 
	requestURI="${requestURI}" id="row">
	
	<display:column>
	<input type="button" name="show"
		value="<spring:message code="phase.show" />"
		onclick="javascript: relativeRedir('phase/handyWorker/show.do?phaseId=${row.id}');" />
	</display:column>
	
	<spring:message code="phase.title" var="title" />
	<display:column property="title" title="${title}"/>

	<spring:message code="phase.description" var="description" />
	<display:column property="description" title="${description}"/>
	
	<spring:message code="phase.startM" var="phase.startM" />
	<display:column property="startMoment" title="${startMoment}" format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message code="phase.finishM" var="phase.finishM" />
	<display:column property="finishMoment" title="${finishMoment}" format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message code="phase.numero" var="phase.numero" />
	<display:column property="number" title="${number}" sortable="true"/>
	
</display:table>
<input type="button" name="create"
		value="<spring:message code="phase.create" />"
		onclick="javascript: relativeRedir('phase/handyWorker/create.do?fixUpTaskId=${row.fixUpTask.id}');" />
</body>
	</security:authorize>
</html>
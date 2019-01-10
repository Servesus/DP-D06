<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



	<security:authorize access="hasRole('HANDYWORKER')">

	<p>
	<spring:message code="phase.title"/>:
	<jstl:out value="${phase.title}"></jstl:out> 
	</p>
	
	<p>
	<spring:message code="phase.description"/>:
	<jstl:out value="${phase.description}"></jstl:out>
	</p>
	
	<p>
	<spring:message code="phase.startM"/>:
	<jstl:out value="${phase.startMoment}"></jstl:out>
	</p>
	
	<p>
	<spring:message code="phase.finishM"/>:
	<jstl:out value="${phase.finishMoment}"></jstl:out>
	</p>
	
	<p>
	<spring:message code="phase.numero"/>:
	<jstl:out value="${phase.number}"></jstl:out>
	</p>
	
	<input type="button" name="edit"
		value="<spring:message code="phase.edit" />"
		onclick="javascript: relativeRedir('phase/handyWorker/edit.do?phaseId=${phase.id}');" />&nbsp; 
	
	<input type="button" name="back"
		value="<spring:message code="phase.back" />"
		onclick="javascript: relativeRedir('phase/handyWorker/list.do?fixUpTaskId=${phase.fixUpTask.id}');" />
</security:authorize>

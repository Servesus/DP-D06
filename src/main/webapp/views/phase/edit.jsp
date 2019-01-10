<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">
<form:form action="phase/handyWorker/edit.do" modelAttribute="phase">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="fixUpTask"/>

	<form:label path="title">
		<spring:message code="phase.title"/>
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title"/>
	<br />
	
	
	<form:label path="description">
		<spring:message code="phase.description"/>
	</form:label>
	<form:textarea path="description"/>
	<form:errors cssClass="error" path="description"/>
	<br />
	
	<form:label path="startMoment">
		<spring:message code="phase.startM" />:
	</form:label>
	<form:input path="startMoment" format="{0,date,dd/MM/yyyy HH:mm}"/>
	<form:errors cssClass="error" path="startMoment" />
	<br />
	
	<form:label path="finishMoment">
		<spring:message code="phase.finishM" />:
	</form:label>
	<form:input path="finishMoment" format="{0,date,dd/MM/yyyy HH:mm}"/>
	<form:errors cssClass="error" path="finishMoment" />
	<br />
	
	<form:label path="number">
		<spring:message code="phase.numero" />:
	</form:label>
	<form:input path="number"/>
	<form:errors cssClass="error" path="number" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="phase.save" />"/>
		
		<jstl:if test="${phase.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="phase.delete" />" />
		</jstl:if>

	<input type="button" name="cancel"
		value="<spring:message code="phase.cancel" />"
		onclick="javascript: relativeRedir('phase/handyWorker/list.do?fixUpTaskId=${phase.fixUpTask.id}');" />
	<br />
	
</form:form>
</security:authorize>
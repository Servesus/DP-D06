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
<form:form action="miscRecord/handyWorker/edit.do" modelAttribute="miscRecord">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="title">
		<spring:message code="miscRecord.title"/>
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title"/>
	<br />
	
	<form:label path="attachment">
		<spring:message code="miscRecord.attachment"/>
	</form:label>
	<form:input path="attachment"/>
	<form:errors cssClass="error" path="attachment"/>
	<br />
	
	<form:label path="comments">
		<spring:message code="miscRecord.comments"/>
	</form:label>
	<form:textarea path="comments"/>
	<form:errors cssClass="errors" path="comments"/>
	<br />
	
	
	<input type="submit" name="save"
		value="<spring:message code="miscRecord.save" />" />&nbsp; 
	<jstl:if test="${miscRecord.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="miscRecord.delete" />" />
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="miscRecord.cancel" />"
		onclick="javascript: relativeRedir('miscRecord/handyWorker/list.do');" />
	<br />
	
	
</form:form>
</security:authorize>
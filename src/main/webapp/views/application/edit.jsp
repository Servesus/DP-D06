<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">
<form:form action ="application/handyWorker/edit.do" modelAttribute="application">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="handyWorker" />
	<form:hidden path="fixUpTask" />
	<form:hidden path="status"/>
	<form:hidden path="customerComments"/>
	
	<form:label path="price">
	<spring:message code="application.price"/>
	</form:label>
	<form:input path="price"/>
	<form:errors cssClass="error" path="price"/>
	<br/>
	
	<form:label path="hwComments">
	<spring:message code="application.comment"/>
	</form:label>
	<form:textarea path="hwComments"/>
	<form:errors cssClass="error" path="hwComments" />
	<br />
	
	<input type="submit" name="saveHandyWorker"
		value="<spring:message code="application.create" />" />&nbsp; 
	
	<input type="button" name="back"
		value="<spring:message code="application.back" />"
		onclick="javascript: relativeRedir('fixUpTask/handyWorker/findAll.do?fixUpTaskId=${application.fixUpTask.id}');" /> 
	<br />
	
</form:form>
</security:authorize>
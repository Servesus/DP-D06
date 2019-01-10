<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">
<form:form action ="configuration/administrator/edit.do" modelAttribute="configuration">
	
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="maxResults">
		<spring:message code="configuration.edit.maxResults" />:
	</form:label>
	<br />
	<form:input path="maxResults" />
	<form:errors cssClass="error" path="maxResults" />
	<br />
	
	<form:label path="maxTime">
		<spring:message code="configuration.edit.maxTime" />:
	</form:label>
	<br />
	<form:input path="maxTime"/>
	<form:errors cssClass="error" path="maxTime" />
	
	<form:label path="pageName">
		<spring:message code="configuration.edit.pageName" />
	</form:label>
	<br />
	<form:input path="pageName"/>
	<form:errors cssClass="error" path="pageName" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="configuration.edit.submit" />" />&nbsp; 
	
	<input type="button" name="cancel"
		value="<spring:message code="configuration.edit.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />
	
</form:form>
</security:authorize>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('REFEREE')">

<form:form action="report/referee/edit.do" modelAttribute="referee">
<jstl:if test="${report.id == 0 }">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment"/>

	
	<form:label path="description">
		<spring:message code="report.description"/>
	</form:label>
	<form:textarea path="description"/>
	<form:errors cssClass="error" path="description"/>
	<br/>
	
	<form:label path="attachment">
		<spring:message code="report.attachment"/>
	</form:label>
	<form:input path="attachment"/>
	<form:errors cssClass="error" path="attachment"/>
	<br />
	
	<input type="submit" name="draft"
		value="<spring:message code="report.draft" />" />&nbsp; 
	<input type="submit" name="final"
		value="<spring:message code="report.final" />" />&nbsp; 
	
	<input type="button" name="cancel"
		value="<spring:message code="report.cancel" />"
		onclick="javascript: relativeRedir('complaint/referee/listSelfAssigned.do');" />
	<br />
	
	</jstl:if>
</form:form>
</security:authorize>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">
<form:form action="warranty/administrator/edit.do" modelAttribute="warranty">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="title">
		<spring:message code="warranty.title"/>
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title"/>
	<br />
	
	<form:label path="terms">
		<spring:message code="warranty.terms"/>
	</form:label>
	<form:input path="terms"/>
	<form:errors cssClass="error" path="terms"/>
	<br />
	
	<form:label path="applicableLaws">
		<spring:message code="warranty.applicableLaws"/>
	</form:label>
	<form:input path="applicableLaws"/>
	<form:errors cssClass="error" path="applicableLaws"/>
	<br />
	
	<input type="submit" name="draft"
		value="<spring:message code="warranty.draft" />" />&nbsp; 
	<input type="submit" name="final"
		value="<spring:message code="warranty.final" />" />&nbsp; 
	<jstl:if test="${warranty.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="warranty.delete" />" />
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="warranty.cancel" />"
		onclick="javascript: relativeRedir('warranty/administrator/list.do');" />
	<br />
	
	
</form:form>
</security:authorize>
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
<form:form action="handyWorker/personalRecord/edit.do" modelAttribute="personalRecord">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="name">
		<spring:message code="personalRecord.name" />
	</form:label>
	<form:input path="name"/>
	<form:errors cssClass="error" path="name"/>
	<br />
	
	<form:label path="surname">
		<spring:message code="personalRecord.surname"/>
	</form:label>
	<form:input path="surname"/>
	<form:errors cssClass="error" path="surname"/>
	<br />
	
	<form:label path="middleName">
		<spring:message code="personalRecord.middleName" />
	</form:label>
	<form:input path="middleName"/>
	<br />
	
	<form:label path="email">
		<spring:message code="personalRecord.email"/>
	</form:label>
	<form:input path="email"/>
	<form:errors cssClass="error" path="email"/>
	<br />
	
	<form:label path="phone">
		<spring:message code="personalRecord.phone"/>
	</form:label>
	<form:input path="phone"/>
	<form:errors cssClass="error" path="phone"/>
	<br />
	
	<form:label path="photo">
		<spring:message code="personalRecord.photo"/>
	</form:label>
	<form:input path="photo"/>
	<form:errors cssClass="error" path="photo"/>
	
	<form:label path="linkedInProfile">
		<spring:message code="personalRecord.linkedInProfile"/>
	</form:label>
	<form:input path="linkedInProfile"/>
	<form:errors cssClass="error" path="linkedInProfile"/>
	
	<input type="submit" name="save"
		value="<spring:message code="personalRecord.save" />" />&nbsp; 
	<jstl:if test="${personalRecord.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="personalRecord.delete" />" />
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="personalRecord.cancel" />"
		onclick="javascript: relativeRedir('master.page');" />
	<br />
	
	
</form:form>
</security:authorize>
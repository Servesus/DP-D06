<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasAnyRole('HANDYWORKER,CUSTOMER,ADMIN,REFEREE')">
<form:form action="socialProfile/handyWorker,customer,admin,referee/edit.do" modelAttribute="socialProfile">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="nick">
		<spring:message code="socialProfile.nick"/>
	</form:label>
	<form:input path="nick"/>
	<form:errors cssClass="error" path="nick"/>
	<br />	
	
	<form:label path="socialNetName">
		<spring:message code="socialProfile.socialNetName"/>
	</form:label>
	<form:input path="socialNetName"/>
	<form:errors cssClass="error" path="socialNetName"/>
	<br />
	
	<form:label path="socialNetProfLink">
		<spring:message code="socialProfile.socialNetProfLink"/>
	</form:label>
	<form:input path="socialNetProfLink"/>
	<form:errors cssClass="error" path="socialNetProfLink"/>
	<br />
	
	<form:label path="name">
		<spring:message code="socialProfile.name"/>
	</form:label>
	<form:input path="name"/>
	<form:errors cssClass="error" path="name"/>
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="socialProfile.save" />" />&nbsp; 
	<jstl:if test="${profile.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="socialProfile.delete" />" />
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="socialProfile.cancel" />"
		onclick="javascript: relativeRedir('socialProfile/handyWorker,customer,admin,referee/list.do');" />
	<br />
	
	
</form:form>
</security:authorize>
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
<form:form action="professionalRecord/handyWorker/edit.do" modelAttribute="professionalRecord">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="companyName">
		<spring:message code="professionalRecord.companyName"/>
	</form:label>
	<form:input path="companyName"/>
	<form:errors cssClass="error" path="companyName"/>
	<br />	
	
	<form:label path="jobBeggining">
		<spring:message code="professionalRecord.jobBeggining"/>
	</form:label>
	<form:input path="jobBeggining"/>
	<form:errors cssClass="error" path="jobBeggining"/>
	<br />
	
	<form:label path="jobEnding">
		<spring:message code="professionalRecord.jobEnding"/>
	</form:label>
	<form:input path="jobEnding"/>
	<form:errors cssClass="error" path="jobEnding"/>
	<br />
	
	<form:label path="rol">
		<spring:message code="professionalRecord.rol"/>
	</form:label>
	<form:input path="rol"/>
	<form:errors cssClass="error" path="rol"/>
	<br />
	
	<form:label path="attachment">
		<spring:message code="professionalRecord.attachment"/>
	</form:label>
	<form:input path="attachment"/>
	<form:errors cssClass="error" path="attachment"/>
	<br />
	
	<form:label path="comment">
		<spring:message code="professionalRecord.comment"/>
	</form:label>
	<form:textarea path="comment"/>
	<form:errors cssClass="error" path="comment"/>
	<br />
	
	
	<input type="submit" name="save"
		value="<spring:message code="professionalRecord.save" />" />&nbsp; 
	<jstl:if test="${professionalRecord.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="professionalRecord.delete" />" />
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="professionalRecord.cancel" />"
		onclick="javascript: relativeRedir('professionalRecord/handyWorker/list.do');" />
	<br />
	
	
</form:form>
</security:authorize>
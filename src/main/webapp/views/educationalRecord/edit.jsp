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
<form:form action="educationalRecord/handyWorker/edit.do" modelAttribute="educationalRecord">
	<form:hidden path="id" />
	<form:hidden path="version" />

	
	<form:label path="diplomasTitle">
		<spring:message code="educationalRecord.title"/>
	</form:label>
	<form:input path="diplomasTitle"/>
	<form:errors cssClass="error" path="diplomasTitle"/>
	<br />
	
	<form:label path="studiesBeggining">
		<spring:message code="educationalRecord.studiesBeg"/>
	</form:label>
	<form:input path="studiesBeggining"/>
	<form:errors cssClass="error" path="studiesBeggining"/>
	<br />
	
	<form:label path="studiesEnding">
		<spring:message code="educationalRecord.studiesEnding"/>
	</form:label>
	<form:input path="studiesEnding"/>
	<form:errors cssClass="error" path="studiesEnding"/>
	<br />
	
	<form:label path="institution">
		<spring:message code="educationalRecord.institution"/>
	</form:label>
	<form:input path="institution"/>
	<form:errors cssClass="error" path="institution"/>
	<br />
	
	<form:label path="attachment">
		<spring:message code="educationalRecord.attachment"/>
	</form:label>
	<form:input path="attachment"/>
	<form:errors cssClass="error" path="attachment"/>
	<br />
	
	<form:label path="comments">
		<spring:message code="educationalRecord.comments"/>
	</form:label>
	<form:textarea path="comments"/>
	<form:errors cssClass="error" path="comments"/>
	<br />
	
	
	
	<input type="submit" name="save"
		value="<spring:message code="educationalRecord.save" />" />&nbsp; 
	<jstl:if test="${educationalRecord.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="educationalRecord.delete" />" />
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="educationalRecord.cancel" />"
		onclick="javascript: relativeRedir('educationalRecord/handyWorker/list.do');" />
	<br />
	
	
</form:form>
</security:authorize>
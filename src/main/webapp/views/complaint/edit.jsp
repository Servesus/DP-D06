<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('CUSTOMER')">
<form:form action="complaint/customer/edit.do" modelAttribute="complaint">
<jstl:if test="${complaint.id == 0 }">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker"/>
	<form:hidden path="moment"/>
	<form:hidden path="customer"/>
	<form:hidden path="fixUpTasks"/>
	<form:hidden path="reports"/>

	
	<form:label path="description">
		<spring:message code="complaint.description"/>
	</form:label>
	<form:textarea path="description"/>
	<form:errors cssClass="error" path="description"/>
	<br />
	
	<br/>
	<p><spring:message code="complaint.message.attachment"/><p>
	<br/>
	
	<form:label path="attachment">
		<spring:message code="complaint.attachment"/>
	</form:label>
	<form:textarea path="attachment"/>
	<form:errors cssClass="error" path="attachment"/>
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="complaint.save" />" 
		onclick="return confirm('<spring:message code="complaint.confirm" />')" />
	
	<input type="button" name="cancel"
		value="<spring:message code="complaint.cancel" />"
		onclick="javascript: relativeRedir('fixUpTask/customer/findOne.do?fixUpTaskId=${complaint.fixUpTasks.id}');" />
	<br />
	
	</jstl:if>
</form:form>
</security:authorize>
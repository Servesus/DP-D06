<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('CUSTOMER')">
<form:form action ="application/handyWorker/save.do" modelAtribute="application">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="customerComments" />
	<form:hidden path="handyWorker" />
	<form:hidden path="fixUpTask" />
		
	<form:label path="title">
		<spring:message code="application.create.title" />:
	</form:label>
	<br />

	<form:label path="price">
		<spring:message code="application.create.price" />:
	</form:label>
	<form:input path="price" />
	<form:errors cssClass="error" path="price" />
	<br />
	
	<form:label path="hwComments">
		<spring:message code="application.create.hwcomments" />:
	</form:label>
	<form:textarea path="hwComments" />
	<form:errors cssClass="error" path="hwComments" />
	<br />
	
	<jstl:if test="${customer.userAccount.authority == 'CUSTOMER' }">
	<form:label path="status">
		<spring:message code="application.status" />:
	</form:label>
	<form:input path="status" />
	<form:errors cssClass="error" path="status" />
	<br />
	</jstl:if>
	<input type="submit" name="save"
		value="<spring:message code="application.create" />" />&nbsp; 
	
	<input type="button" name="back"
		value="<spring:message code="application.back" />"
		onclick="javascript: relativeRedir('profile/action-1.jsp');" /> 
	<br />
	
</form:form>
</security:authorize>
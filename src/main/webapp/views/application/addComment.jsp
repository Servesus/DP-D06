<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('CUSTOMER')">
<form:form action ="application/customer/edit.do" modelAttribute="application">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="handyWorker" />
	<form:hidden path="fixUpTask" />
	<form:hidden path="hwComments"/>
	<form:hidden path="price"/>
	<form:hidden path="status"/>
	
	<form:textarea path="customerComments" />
	<form:errors cssClass="error" path="customerComments" />
	<br />
	
	<input type="submit" name="saveCustomer"
		value="<spring:message code="application.newComment" />" />&nbsp; 
	
	<input type="button" name="back"
		value="<spring:message code="application.back" />"
		onclick="javascript: relativeRedir('application/customer/show.do?applicationId=${row.id}');" /> 
	<br />
	
</form:form>
</security:authorize>
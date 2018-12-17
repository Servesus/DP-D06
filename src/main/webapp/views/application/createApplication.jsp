<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">
<form:form action ="application/handyWorker/create.do" modelAtribute="application">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="status" />
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
	
	<input type="submit" name="create"
		value="<spring:message code="application.create" />" />&nbsp; 
	
	<input type="button" name="cancel"
		value="<spring:message code="announcement.cancel" />"
		onclick="javascript: relativeRedir('master.page');" />    <!-- Aqui va el link del return a pagina de HW -->
	<br />
	
</form:form>
</security:authorize>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

</head>
<body>
<form:form action="message/customer,handyWorker,referee,administrator/create.do" modelAttribute="message">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="recipient">
		<spring:message code="message.recipient" />:
	</form:label>
	<form:input path="recipient" />
	<form:errors cssClass="error" path="recipient" />
	<br />
	
	<form:select id="priority" path="priority">
		<form:option value="NEUTRAL" label="NEUTRAL" />
		<form:option value="LOW" label="LOW" />
		<form:option value="HIGH" label="HIGH" />		
		<form:options items="${messages}" itemValue="id" itemLabel="priority" />
	</form:select>
	
	<form:label path="subject">
		<spring:message code="message.subject" />:
	</form:label>
	<form:input path="subject" />
	<form:errors cssClass="error" path="subject" />
	<br />
	
	<form:label path="body">
		<spring:message code="message.body" />:
	</form:label>
	<form:textarea path="body" />
	<form:errors cssClass="error" path="body" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="message.save" />" />&nbsp; 
	
	<input type="button" name="cancel"
		value="<spring:message code="message.cancel" />"
		onclick="javascript: relativeRedir('message/edit.do');" /> <!-- TODITOTODO -->
	<br />
 	
</form:form>
</body>
</html>
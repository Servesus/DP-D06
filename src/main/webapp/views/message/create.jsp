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
<form:form action="message/customer,handyWorker,referee,administrator/create.do" modelAttribute="mesage">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sender"/>
	<form:hidden path= "sendDate"/>
	<form:hidden path="recipient"/>
	 
	<spring:message code="mesage.recipient" />:
	<input id="recipients" name="recipients" /> 
	<jstl:set var="recipients" value="recipients"/>
	<br />
	
	<spring:message code="mesage.priority" />:
	<form:select path="priority">		
			
			<form:option value="0" label="NEUTRAL" />
			<form:option value="-1" label="LOW" />
			<form:option value="1" label="HIGH" />	
	</form:select>
	<form:errors cssClass="error" path="priority" />
	<br />
	
	<form:label path="subject">
		<spring:message code="mesage.subject" />:
	</form:label>
	<form:input path="subject" />
	<form:errors cssClass="error" path="subject" />
	<br />
	
	<form:label path="tags">
		<spring:message code="mesage.tags" />:
	</form:label>
	<form:input path="tags" />
	<form:errors cssClass="error" path="tags" />
	<br />
	
	<form:label path="body">
		<spring:message code="mesage.body" />:
	</form:label>
	<form:textarea path="body" />
	<form:errors cssClass="error" path="body" />
	<br />
	
	<input type="submit" name="send"
		value="<spring:message code="mesage.send" />" />&nbsp; 
	
	<input type="button" name="cancel"
		value="<spring:message code="mesage.cancel" />"
		onclick="javascript: relativeRedir('box/customer,handyWorker,referee,administrator/list.do');" /> 
	<br />
 	
</form:form>
</body>
</html>
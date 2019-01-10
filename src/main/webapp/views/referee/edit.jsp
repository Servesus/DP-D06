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
<security:authorize access="hasRole('REFEREE')">
${message}
<spring:message code="referee.firstMessage" />
<form:form action="referee/referee/edit.do" modelAttribute="referee">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="profiles" />
	<form:hidden path="boxes" />
	<form:hidden path="isSuspicious" />
	<form:hidden path="isBanned"/>
	<form:hidden path="userAccount.authorities"/>
	<form:hidden path="userAccount.username" />
	<form:hidden path="userAccount.password" />
	<form:hidden path="userAccount.id" />
	<form:hidden path="userAccount.version" />
	<form:hidden path="reports"/>
 
	<form:label path="name">
		<spring:message code="referee.name" />*:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<form:label path="middleName">
		<spring:message code="referee.middleName" />:
	</form:label>
	<form:input path="middleName" />
	<form:errors cssClass="error" path="middleName" />
	<br />
	
	<form:label path="surname">
		<spring:message code="referee.surname" />*:
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />
	
	<form:label path="photo">
		<spring:message code="referee.photo" />:
	</form:label>
	<form:input path="photo" />
	<form:errors cssClass="error" path="photo" />
	<br />
	
	<form:label path="email">
		<spring:message code="referee.email" />:
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />
	
	<form:label path="phoneNumber">
		<spring:message code="referee.phoneNumber" />:
	</form:label>
	<form:input path="phoneNumber" />
	<form:errors cssClass="error" path="phoneNumber" />
	<br />
	
	<form:label path="address">
		<spring:message code="referee.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="referee.save" />" />&nbsp; 
	
	<input type="button" name="back"
		value="<spring:message code="referee.back" />"
		onclick="javascript: relativeRedir('/');" />
	<br />
 	
</form:form>
</security:authorize>
</body>
</html>
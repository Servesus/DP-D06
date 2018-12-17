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

<spring:message code="message.sender" />
	<display:table name="${message.sender}" id="row">
</display:table>

<spring:message code="message.subject" />
	<display:table name="${message.subject}" id="row">
</display:table>

<spring:message code="message.priority" />
	<display:table name="${message.priority}" id="row">
</display:table>

<spring:message code="message.body" />
	<display:table name="${message.body}" id="row">
</display:table>

<jstl:if test="${message.id != 0 }">
	<input type="submit" name="delete"
		value="<spring:message code="message.delete" />"
		onclick="return confirm('<spring:message code="message.confirm.delete" />')" />&nbsp;
</jstl:if>

<input type="button" name="Back" value="<spring:message code="message.list" />"
			onclick="javascript: relativeRedir('message/list.do');" />


</body>
</html>
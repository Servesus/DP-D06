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

<p>
	<b><spring:message code="mesage.sender"/>:</b> ${sender} <br />
</p>

<p>
	<b><spring:message code="mesage.tags"/>:</b> ${tags} <br />
</p>

<p>
	<b><spring:message code="mesage.subject"/>:</b> ${subject} <br />
</p>

<p>
	<b><spring:message code="mesage.sendDate"/>:</b> ${sendDate} <br />
</p>

<p>
	<b><spring:message code="mesage.priority"/>:</b>
	<jstl:choose>
		<jstl:when test="${priority == 0}">
			<spring:message code="mesage.neutral"/><br />
		</jstl:when>
		<jstl:when test="${priority == -1}">
			<spring:message code="mesage.low" /><br />
		</jstl:when>
		<jstl:when test="${priority == 1}">
			<spring:message code="mesage.high"/><br />
		</jstl:when>
	</jstl:choose>
</p>

<p>
	<b><spring:message code="mesage.body"/>:</b> ${body} <br />
</p>



</body>
</html>
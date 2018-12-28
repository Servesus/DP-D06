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
<title><spring:message code="personalRecord.title"/></title>
<body>
	<p><spring:message code="personalRecord.name"/>: <jstl:out value="${personalRecord.name}"/></p>
	<p><spring:message code="personalRecord.middleName"/>: <jstl:out value="${personalRecord.middleName}"/></p>
	<p><spring:message code="personalRecord.surname"/>: <jstl:out value="${personalRecord.surname}"/></p>
	<p><spring:message code="personalRecord.email"/>: <jstl:out value="${personalRecord.email}"/></p>
	<p><spring:message code="personalRecord.phone"/>: <jstl:out value="${personalRecord.phone}"/></p>
	<p><spring:message code="personalRecord.photo"/>: <jstl:out value="${personalRecord.photo}"/></p>
	<p><spring:message code="personalRecord.linkedInProfile"/>: <jstl:out value="${personalRecord.linkedInProfile}"/></p>
	
	<input type="button" name="edit"
		value="<spring:message code="personalRecord.edit" />"
		onclick="javascript: relativeRedir('personalRecord/handyWorker/edit.do?personalRecordId=${personalRecord.id}');" />
	<br />
</body>
</html>
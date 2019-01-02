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
<form:form action="box/customer,handyWorker,referee,administrator/edit.do" modelAttribute="box">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="isSystem"/>
	
	<form:label path="name">
		<spring:message code="box.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="box.save" />" />&nbsp; 
	
	<jstl:if test="${box.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="box.delete" />"/>&nbsp;
	</jstl:if>
	
	<input type="button" name="cancel"
		value="<spring:message code="box.cancel" />"
		onclick="javascript: relativeRedir('box/customer,handyWorker,referee,administrator/list.do');" />
	<br />
 	
</form:form>
</body>
</html>
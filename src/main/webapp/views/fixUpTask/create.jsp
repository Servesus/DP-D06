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
<security:authorize access="hasRole('CUSTOMER')">
<form:form action="fixUpTask/customer/create.do" modelAttribute="fixUpTask">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker" />
	<form:hidden path="phases" />
	<form:hidden path="applications" />
	<form:hidden path="complaints" />
	<form:hidden path="customer" />
 
 	<form:label path="startDate">
		<spring:message code="fixUpTask.startDate" />:
	</form:label>
	<form:input path="startDate" format="{0,date,dd/MM/yyyy HH:mm}"/>
	<form:errors cssClass="error" path="startDate" />
	<br />
	
	<form:label path="description">
		<spring:message code="fixUpTask.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	
	<form:label path="address">
		<spring:message code="fixUpTask.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />
	
	<form:label path="maxPrice">
		<spring:message code="fixUpTask.maxPrice" />:
	</form:label>
	<form:input path="maxPrice" />
	<form:errors cssClass="error" path="maxPrice" />
	<br />
	
	<form:label path="estimatedDate">
		<spring:message code="fixUpTask.estimatedDate" />:
	</form:label>
	<form:input path="estimatedDate" format="{0,date,dd/MM/yyyy HH:mm}"/>
	<form:errors cssClass="error" path="estimatedDate" />
	<br />
	
	<form:label path="warranty">
		<spring:message code="fixUpTask.warranty" />:
	</form:label>
	<form:select id="warranties" path="warranty">
		<form:option value="0" label="----" />		
		<form:options items="${warranties}" itemValue="id" itemLabel="title" />
	</form:select>
	
	<br />
	
	<form:label path="category">
		<spring:message code="fixUpTask.category" />:
	</form:label>
	<form:select id="categories" path="category">
		<form:option value="0" label="----" />		
		<form:options items="${categories}" itemValue="id" itemLabel="nameEN"/>
	</form:select>
	
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="fixUpTask.save" />" />&nbsp; 
	<jstl:if test="${fixUpTask.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="fixUpTask.delete" />"
			onclick="return confirm('<spring:message code="fixUpTask.confirm.delete" />')" />&nbsp;
	</jstl:if>
		
		<input type="button" name="cancel"
		value="<spring:message code="fixUpTask.cancel" />"
		onclick="javascript: relativeRedir('fixUpTask/customer/findAll.do');" />
	<br />
 	
</form:form>
</security:authorize>
</body>
</html>
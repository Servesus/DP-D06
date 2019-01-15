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

<display:table pagesize="5" class="complaint" name="complaints" 
	requestURI="${requestURI}" id="row">
	
	<security:authorize access="hasRole('CUSTOMER')">
	<display:column> <a href="complaint/customer/show.do?complaintId=${row.id}">
	<spring:message code="complaint.show" /></a>
	</display:column>
	
	<spring:message code="complaint.moment" var="moment" />
	<display:column property="moment" title="${moment}" format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message code="complaint.description" var="description" />
	<display:column property="description" title="${description}"/>
	
	<spring:message code="complaint.fixUpTask.id" var="id" />
	<display:column property="fixUpTasks.id" title="${id}"/>

	</security:authorize>
	
	<security:authorize access="hasRole('REFEREE')">
	<display:column> 
	<form action="complaint/referee/selfAssign.do" method="post">
		<input type="hidden" id="complaintId" name="complaintId" value="${row.id}" /> 
		<input type="submit" name="selfAssign"
		value="<spring:message code="complaint.selfAssign" />" /> 
  	 	</form>
	</display:column>
	
	<spring:message code="complaint.moment" var="moment" />
	<display:column property="moment" title="${moment}" format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message code="complaint.description" var="description" />
	<display:column property="description" title="${description}"/>
	
	<spring:message code="complaint.fixUpTask.id" var="id" />
	<display:column property="fixUpTasks.id" title="${id}"/>
	
	<spring:message code="complaint.customer" var="customer" />
	<display:column property="customer.name" title="${customer}"/>
	
	</security:authorize>
	
	<security:authorize access="hasRole('HANDYWORKER')">	
	<spring:message code="complaint.moment" var="moment" />
	<display:column property="moment" title="${moment}" format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message code="complaint.description" var="description" />
	<display:column property="description" title="${description}"/>
	
	<spring:message code="complaint.fixUpTask.id" var="id" />
	<display:column property="fixUpTasks.id" title="${id}"/>
	
	<spring:message code="complaint.customer" var="customer" />
	<display:column property="customer.name" title="${customer}"/>

	</security:authorize>
	</display:table>
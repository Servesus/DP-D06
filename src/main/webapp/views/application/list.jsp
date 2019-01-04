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

<display:table pagesize="5" class="application" name="applications" 
	requestURI="${requestURI}" id="row">
	
	<security:authorize access="hasRole('CUSTOMER')">
	<display:column> <a href="application/customer/show.do?applicationId=${row.id}">
	<spring:message code="application.findOne" /></a> </display:column>
	
	<spring:message code="application.moment" var="moment" />
	<display:column property="moment" title="${moment}" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="application.price" var="price" />
	<display:column property="price" title="${price}"/>
	
	<spring:message code="application.status" var="status" />
	<display:column property="status" title="${status}"/>

	<spring:message code="application.handyWorker" var="handyWorker.make" />
	<display:column property="handyWorker.make" title="${handyWorker.make}"/>
	
	</security:authorize>
	
	<security:authorize access="hasRole('HANDYWORKER')">
	<display:column> <a href="application/handyWorker/show.do?applicationId=${row.id}">
	<spring:message code="application.findOne" /></a>
	</display:column>
	
	<spring:message code="application.moment" var="moment" />
	<display:column property="moment" title="${moment}" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="application.price" var="price" />
	<display:column property="price" title="${price}"/>
	
	<spring:message code="application.status" var="status"/>
	<display:column property="status" title="${status}">
	<jstl:choose>
		<jstl:when test="${application.status == 1}">
		<spring:message code="application.accepted"/>
		</jstl:when>
		<jstl:when test="${application.status == 0}">
			<spring:message code="application.pending"/>
		</jstl:when>
		<jstl:when test="${application.status == -1}">
			<spring:message code="application.rejected"/>
		</jstl:when>
	</jstl:choose>
	</display:column>

	<spring:message code="application.handyWorker" var="make"/>
	<display:column property="handyWorker.mame" title="${handyWorker.make}"/>
	</security:authorize>

</display:table>
</body>
</html>
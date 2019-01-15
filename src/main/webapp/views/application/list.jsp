<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
.ACCEPTED{
  background-color: green;
}
.REJECTED{
  background-color: orange;
}
.PENDING{
  background-color: grey;
}
</style>

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
	
		<jsp:useBean id="now" class="java.util.Date"/>
			<jstl:if test="${row.status == 0}">
				<jstl:set var="css" value=""></jstl:set>
			</jstl:if>
			<jstl:if test="${row.fixUpTask.estimatedDate < now}">
				<jstl:set var="css" value="PENDING"></jstl:set>
			</jstl:if>
		<jstl:if test="${row.status == 1}">
		<jstl:set var="css" value="ACCEPTED"></jstl:set>
		</jstl:if>
		<jstl:if test="${row.status == -1}">
			<jstl:set var="css" value="REJECTED"></jstl:set>
		</jstl:if>
	
	<security:authorize access="hasRole('CUSTOMER')">
	<display:column> <a href="application/customer/show.do?applicationId=${row.id}">
	<spring:message code="application.findOne" /></a>
	</display:column>
	
	<spring:message code="application.moment" var="moment" />
	<display:column property="moment" title="${moment}" format="{0,date,dd/MM/yyyy HH:mm}"
	class="${css}"/>

	<spring:message code="application.price" var="price"/>
	<display:column class="${css}" title="${price}"><jstl:out value="${row.price}"></jstl:out>
	&nbsp; (${row.price * (1+(priceVAT)/100)})</display:column>
	
	<spring:message code="application.handyWorker.make" var="make"/>
	<display:column title="${make}" class="${css}">
	<jstl:out value="${row.handyWorker.make}"></jstl:out>
	</display:column>
	
	
	</security:authorize>
	
	<security:authorize access="hasRole('HANDYWORKER')">
	<display:column> <a href="application/handyWorker/show.do?applicationId=${row.id}">
	<spring:message code="application.findOne" /></a>
	</display:column>
	
	<spring:message code="application.moment" var="moment" />
	<display:column property="moment" title="${moment}" format="{0,date,dd/MM/yyyy HH:mm}"
	class="${css}"/>

	<spring:message code="application.price" var="price"/>
	<display:column class="${css}" title="${price}"><jstl:out value="${row.price}"></jstl:out>
	&nbsp; (${row.price * (1+(priceVAT)/100)})</display:column>
	
	<spring:message code="application.status" var="status"/>
	<display:column title="${status}" class="${css}">
	<jstl:choose>
		<jstl:when test="${row.status == 1}">
		<spring:message code="application.accepted"/>
		</jstl:when>
		<jstl:when test="${row.status == 0}">
			<spring:message code="application.pending"/>
		</jstl:when>
		<jstl:when test="${row.status == -1}">
			<spring:message code="application.rejected"/>
		</jstl:when>
	</jstl:choose>
	</display:column>
	</security:authorize>

</display:table>
</body>
</html>
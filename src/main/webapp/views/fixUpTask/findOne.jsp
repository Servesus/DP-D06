<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('CUSTOMER')">
<b><spring:message code="fixUpTask.startDate" /></b> ${fixUpTask.startDate} <br/>
<b><spring:message code="fixUpTask.description" /></b> ${fixUpTask.description} <br/>
<b><spring:message code="fixUpTask.address" /></b> ${fixUpTask.address} <br/>
<b><spring:message code="fixUpTask.maxPrice" /></b> ${fixUpTask.maxPrice} <br/>
<b><spring:message code="fixUpTask.estimatedDate" /></b> ${fixUpTask.estimatedDate} <br/>
<b><spring:message code="fixUpTask.warranty" /></b> ${fixUpTask.warranty.title} <br/>
<b><spring:message code="fixUpTask.category" /></b>
<jstl:if test="${lang=='es' }">
			${fixUpTask.category.nameES}
	</jstl:if>
	<jstl:if test="${lang=='en' }">
			${fixUpTask.category.nameEN}
	</jstl:if>
	
</security:authorize>


<security:authorize access="hasRole('HANDYWORKER')">
<b><spring:message code="fixUpTask.startDate" /></b> ${fixUpTask.startDate} <br/>
<b><spring:message code="fixUpTask.description" /></b> ${fixUpTask.description} <br/>
<b><spring:message code="fixUpTask.address" /></b> ${fixUpTask.address} <br/>
<b><spring:message code="fixUpTask.maxPrice" /></b> ${fixUpTask.maxPrice} <br/>
<b><spring:message code="fixUpTask.estimatedDate" /></b> ${fixUpTask.estimatedDate} <br/>
<b><spring:message code="fixUpTask.warranty" /></b> ${fixUpTask.warranty.title} <br/>
<b><spring:message code="fixUpTask.category" /></b>
<jstl:if test="${lang=='es' }">
			${fixUpTask.category.nameES}
	</jstl:if>
	<jstl:if test="${lang=='en' }">
			${fixUpTask.category.nameEN}
	</jstl:if>
</security:authorize>



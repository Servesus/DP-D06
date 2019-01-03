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



<security:authorize access="hasRole('HANDYWORKER')">

	<display:table>
	<spring:message code="application.moment" var="moment" />
	<display:column property="moment" title="${moment}" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="application.price" var="price" />
	<display:column property="price" title="${price}"/>
	
	<spring:message code="application.status" var="status" />
	<display:column property="status" title="${status}"/>

	<spring:message code="application.handyWorker" var="make" />
	<display:column property="handyWorker.make" title="${handyWorker.make}"/>
	
	<spring:message code="application.hwComments" var="hwComments"/> 
	<display:column property="hwComments" title="${hwComments }" sortable="true"/>
	
	<spring:message code="application.fixUpTask" var ="fixUpTask"/>
	<display:column property="fixUpTask.id" title="${fixUpTask.id }"/>

<jstl:if test="${status == 1}">
<spring:message code="application.customerComments" />
<display:column property="${customerComments}" sortable="true"/>
</jstl:if>
</display:table>

<jstl:if test="${ status==1}">
<a href="phase/handyWorker/create.do"><spring:message code="phase.create" /></a>
</jstl:if>
</security:authorize>
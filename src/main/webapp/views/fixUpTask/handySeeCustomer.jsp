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

<spring:message code="customer.name" /> ${customer.name} <br/>
<spring:message code="customer.surname" /> ${customer.surname} <br/>
<spring:message code="customer.email" /> ${customer.email} <br/>
<spring:message code="customer.phoneNumber" /> ${customer.phoneNumber} <br/>
<spring:message code="customer.id" /> ${customer.id} <br/>

<spring:message code="customer.creditCards" />
<display:table name="${customer.creditCards}" id="row">
<display:column property="holderName" titleKey="customer.creditCards.holderName" />
<display:column property="brandName" titleKey="customer.creditCards.brandName" />
<display:column property="number" titleKey="customer.creditCards.number" />
</display:table>

<spring:message code="customer.complaints" />
<display:table name="${customer.complaints}" id="row">
<display:column property="moment" titleKey="customer.complaints.moment" />
<display:column property="description" titleKey="customer.complaints.description" />
</display:table>

<spring:message code="customer.fixUpTasks" />
<display:table name="${customer.fixUpTasks}" id="row">
<display:column property="startDate" titleKey="customer.fixUpTasks.startDate" />
<display:column property="description" titleKey="customer.fixUpTasks.description" />
<display:column property="maxPrice" titleKey="customer.fixUpTasks.maxPrice" />
</display:table>

<input type="button" name="cancel"
		value="<spring:message code="fixUpTask.back" />"
		onclick="javascript: relativeRedir('fixUpTask/handyWorker/customersHandyWorker.do');" />
</security:authorize>


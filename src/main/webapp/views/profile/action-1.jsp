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

<input type="button" name="Edit PD" value="<spring:message code="customer.editPD" />"
			onclick="javascript: relativeRedir(customer/customer/edit.do');" />
</security:authorize>


<security:authorize access="hasRole('ADMIN')">

<spring:message code="administrator.name" /> ${administrator.name} <br/>
<spring:message code="administrator.surname" /> ${administrator.surname} <br/>
<spring:message code="administrator.email" /> ${administrator.email} <br/>
<spring:message code="administrator.phoneNumber" /> ${administrator.phoneNumber} <br/>
<spring:message code="administrator.id" /> ${administrator.id} <br/>

<spring:message code="administrator.categories" />
<display:table name="${administrator.categories}" id="row">
<display:column property="name" titleKey="administrator.categories" />
</display:table>

<input type="button" name="Edit PD" value="<spring:message code="administrator.editPD" />"
			onclick="javascript: relativeRedir(administrator/edit.do');" />

</security:authorize>


<security:authorize access="hasRole('HANDYWORKER')">

<spring:message code="handyWorker.name" /> ${handyWorker.name} <br/>
<spring:message code="handyWorker.surname" /> ${handyWorker.surname} <br/>
<spring:message code="handyWorker.email" /> ${handyWorker.email} <br/>
<spring:message code="handyWorker.phoneNumber" /> ${handyWorker.phoneNumber} <br/>
<spring:message code="handyWorker.id" /> ${handyWorker.id} <br/>
<spring:message code="handyWorker.make" /> ${handyWorker.make} <br/>


<spring:message code="handyWorker.applications" />
<display:table name="${handyWorker.applications}" id="row">
<display:column property="moment" titleKey="handyWorker.applications.moment" />
<display:column property="status" titleKey="handyWorker.applications.status" />
<display:column property="price" titleKey="handyWorker.applications.price" />
</display:table>

<spring:message code="handyWorker.phases" />
<display:table name="${handyWorker.phases}" id="row">
<display:column property="title" titleKey="handyWorker.phases.title" />
<display:column property="description" titleKey="handyWorker.phases.description" />
<display:column property="number" titleKey="handyWorker.phases.number" />
</display:table>

<input type="button" name="Edit PD" value="<spring:message code="handyWorker.editPD" />"
			onclick="javascript: relativeRedir(handyWorker/edit.do');" />

</security:authorize>

<security:authorize access="hasRole('REFEREE')">

<spring:message code="referee.name" /> ${referee.name} <br/>
<spring:message code="referee.surname" /> ${referee.surname} <br/>
<spring:message code="referee.email" /> ${referee.email} <br/>
<spring:message code="referee.phoneNumber" /> ${referee.phoneNumber} <br/>
<spring:message code="referee.id" /> ${referee.id} <br/>


<spring:message code="referee.reports" />
<display:table name="${referee.reports}" id="row">
<display:column property="moment" titleKey="referee.reports.moment" />
<display:column property="description" titleKey="referee.reports.description" />
<display:column property="attachment" titleKey="referee.reports.attachment" />
<display:column property="isFinal" titleKey="referee.reports.isFinal" />
</display:table>

<input type="button" name="Edit PD" value="<spring:message code="referee.editPD" />"
			onclick="javascript: relativeRedir(referee/edit.do');" />

</security:authorize>
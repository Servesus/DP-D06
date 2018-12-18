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





<spring:message code="fixUpTask.startDate" /> ${fixUpTask.startDate} <br/>
<spring:message code="fixUpTask.description" /> ${fixUpTask.description} <br/>
<spring:message code="fixUpTask.address" /> ${fixUpTask.address} <br/>
<spring:message code="fixUpTask.maxPrice" /> ${fixUpTask.maxPrice} <br/>
<spring:message code="fixUpTask.estimatedDate" /> ${fixUpTask.estimatedDate} <br/>
<spring:message code="fixUpTask.warranty" /> ${fixUpTask.warranty} <br/>
<spring:message code="fixUpTask.category" /> ${fixUpTask.category} <br/>

<spring:message code="fixUpTask.applications" />
<input type="button" name="Applications" value="<spring:message code="fixUpTask.applications" />"
			onclick="javascript: relativeRedir('application/customer/findAll.do');" />

<spring:message code="fixUpTask.complaints" />
<display:table name="${fixUpTask.complaints}" id="row">
<display:column property="moment" titleKey="fixUpTask.complaints.moment" />
<display:column property="description" titleKey="fixUpTask.complaints.description" />
<display:column property="customer" titleKey="fixUpTask.complaints.customer" />
</display:table>

<spring:message code="fixUpTask.phases" />
<display:table name="${fixUpTask.phases}" id="row">
<display:column property="title" titleKey="fixUpTask.phases.title" />
<display:column property="description" titleKey="fixUpTask.phases.description" />
<display:column property="number" titleKey="fixUpTask.phases.number" />
</display:table>


<input type="submit" name="delete"
			value="<spring:message code="fixUpTask.delete" />"
			onclick="return confirm('<spring:message code="fixUpTask.confirm.delete" />')" />&nbsp;

<input type="button" name="cancel" value="<spring:message code="fixUpTask.cancel" />"
			onclick="javascript: relativeRedir('fixUpTask/customer/findAll.do');" />

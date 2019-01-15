<%--
 * index.jsp
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


<p><spring:message code="welcome.greeting.prefix" /> ${name}<spring:message code="welcome.greeting.suffix" /></p>
<p>${welcome}</p>
<p><spring:message code="welcome.greeting.current.time" /> ${moment}</p> 

<security:authorize access="isAnonymous()">
<input type="button" name="registerCustomer"
		value="<spring:message code="welcome.register.customer" />"
		onclick="javascript: relativeRedir('customer/create.do');" />&nbsp;

<input type="button" name="registerHandyWorker"
		value="<spring:message code="welcome.register.handyWorker" />"
		onclick="javascript: relativeRedir('handyWorker/create.do');" />

</security:authorize>


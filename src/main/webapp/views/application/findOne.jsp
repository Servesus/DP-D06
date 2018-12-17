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


<spring:message code="application.moment" /> ${application.moment} <br/>
<spring:message code="application.price" /> ${application.price} <br/>
<spring:message code="application.status" /> ${application.status} <br/>
<spring:message code="application.handyWorker" /> ${application.handyWorker.make} <br/>


<jstl:if test="${application.status == 1}">
<spring:message code="application.customerComments" />
<display:table name="${application.customerComments}" id="row">
</display:table>
</jstl:if>

<spring:message code="application.hwComments" />
<display:table name="${application.hwComments}" id="row">
</display:table>

<jstl:if test="${application.status == 0}">
<input type="submit" name="Accept" value="<spring:message code="application.accept" />"
			onclick="javascript: relativeRedir('application/customer/accept.do');" />
<input type="submit" name="Reject" value="<spring:message code="application.reject" />"
			onclick="javascript: relativeRedir('application/customer/reject.do');" />
</jstl:if>
<input type="button" name="Back" value="<spring:message code="application.back" />"
			onclick="javascript: relativeRedir('application/customer/findAll.do');" />

</security:authorize>
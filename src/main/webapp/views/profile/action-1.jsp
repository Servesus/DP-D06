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


<input type="button" name="socialProfiles" value="<spring:message code="customer.socialProfiles" />"
			onclick="javascript: relativeRedir('/socialProfile/handyWorker,customer,admin,referee/list.do');" />

<input type="button" name="Edit PD" value="<spring:message code="customer.editPD" />"
			onclick="javascript: relativeRedir('customer/customer/editPersonalData.do');" />
</security:authorize>


<security:authorize access="hasRole('ADMIN')">

<spring:message code="administrator.name" /> ${administrator.name} <br/>
<spring:message code="administrator.surname" /> ${administrator.surname} <br/>
<spring:message code="administrator.email" /> ${administrator.email} <br/>
<spring:message code="administrator.phoneNumber" /> ${administrator.phoneNumber} <br/>
<spring:message code="administrator.id" /> ${administrator.id} <br/>


<input type="button" name="Edit PD" value="<spring:message code="handyWorker.editPD" />"
			onclick="javascript: relativeRedir('administrator/administrator/edit.do');" />
<input type="button" name="socialProfiles" value="<spring:message code="administrator.socialProfiles" />"
			onclick="javascript: relativeRedir('/socialProfile/handyWorker,customer,admin,referee/list.do');" />

</security:authorize>


<security:authorize access="hasRole('HANDYWORKER')">

<spring:message code="handyWorker.name" />: ${handyWorker.name} <br/>
<spring:message code="handyWorker.surname" />: ${handyWorker.surname} <br/>
<spring:message code="handyWorker.email" />: ${handyWorker.email} <br/>
<spring:message code="handyWorker.phoneNumber" />: ${handyWorker.phoneNumber} <br/>
<spring:message code="handyWorker.make" />: ${handyWorker.make} <br/>


<spring:message code="handyWorker.applications" />:
<display:table name="${handyWorker.applications}" id="row">
<display:column property="moment" titleKey="handyWorker.applications.moment" />
<display:column property="status" titleKey="handyWorker.applications.status" />
<display:column property="price" titleKey="handyWorker.applications.price" />
</display:table>


<input type="button" name="socialProfiles" value="<spring:message code="handyWorker.socialProfiles" />"
			onclick="javascript: relativeRedir('/socialProfile/handyWorker,customer,admin,referee/list.do');" />

<input type="button" name="Edit PD" value="<spring:message code="handyWorker.editPD" />"
			onclick="javascript: relativeRedir('/handyWorker/handyWorker/edit.do');" />

</security:authorize>

<security:authorize access="hasRole('REFEREE')">

<spring:message code="referee.name" /> ${referee.name} <br/>
<spring:message code="referee.surname" /> ${referee.surname} <br/>
<spring:message code="referee.email" /> ${referee.email} <br/>
<spring:message code="referee.phoneNumber" /> ${referee.phoneNumber} <br/>

<input type="button" name="socialProfiles" value="<spring:message code="referee.socialProfiles" />"
			onclick="javascript: relativeRedir('/socialProfile/handyWorker,customer,admin,referee/list.do');" />
<input type="button" name="Edit PD" value="<spring:message code="handyWorker.editPD" />"
			onclick="javascript: relativeRedir('/referee/referee/edit.do');" />
</security:authorize>
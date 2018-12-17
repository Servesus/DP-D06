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
<security:authorize access="hasRole('HANDYWORKER')">
<body>
<%-- se muestran todas las fix que estan en el metodo findAll de fixUpTaskService --%>

<display:table pagesize="5" class="fixUpTask" name="fixUpTasks" 
	requestURI="fixUpTask/handyWorker/findAll.do" id="row">
		
		<spring:message code="fixUpTask.startDate" var="startDate" />
	<display:column property="startDate" title="${startDate}" sortable="false" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="fixUpTask.description" var="description" />
	<display:column property="description" title="${description}" sortable="false"/>

	<spring:message code="fixUpTask.id" var="id" />
	<display:column property="id" title="${id}" sortable="false"/>

</display:table>


<%-- Se ven las fixUpTask por Customer con el metodo CustomerPorFixUpTask en customerService --%>
<display:table pagesize="5" class="customer" name="fixCustomers" 
	requestURI="fixUpTask/handyWorker/findAll.do" id="row">
		
		<spring:message code="fixCustomer.assigned" var="assigned" />
	<display:column property="assigned" title="${assigned}" sortable="false"/>
	
</display:table>
<%-- Se muestran todos los customer con el metodo findAll de customerService --%>

<display:table pagesize="5" class="customer" name="customers" 
	requestURI="fixUpTask/handyWorker/findAll.do" id="row">
		
		<spring:message code="fixUpTask.customer.name" var="name" />
	<display:column property="name" title="${name}" sortable="false" />

	<spring:message code="fixUpTask.customer.id" var="id" />
	<display:column property="id" title="${id}" sortable="false"/>

	<display:column>
		<a href="customer/findOne.do?customerId=${row.id}">
  	 <spring:message code="fixUpTask.customer.customer" /> </a>
	</display:column>

</display:table>


</body>
</security:authorize>
</html>
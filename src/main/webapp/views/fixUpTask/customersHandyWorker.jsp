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
<%-- Se muestran todos los customer con el metodo findAll de customerService --%>

<display:table pagesize="5" class="customer" name="customers" 
	requestURI="fixUpTask/handyWorker/customers.do" id="row">
		
		<spring:message code="fixUpTask.customer.name" var="name" />
	<display:column property="name" title="${name}" sortable="false" />

	<spring:message code="fixUpTask.customer.id" var="id" />
	<display:column property="id" title="${id}" sortable="false"/>

	<display:column>
		<a href="customer/findOne.do?customerId=${row.id}">
  	 <spring:message code="fixUpTask.customer.customer" /> </a>
	</display:column>

</display:table>
<input type="button" name="cancel"
		value="<spring:message code="fixUpTask.cancel" />"
		onclick="javascript: relativeRedir('fixUpTask/handyWorker/findAllHandyWorker.do');" />

</body>
</security:authorize>
</html>
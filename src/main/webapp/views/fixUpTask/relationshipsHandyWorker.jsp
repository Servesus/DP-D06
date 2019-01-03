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
		
<%-- Se ven las fixUpTask por Customer con el metodo CustomerPorFixUpTask en customerService --%>
<display:table pagesize="5" class="customer" name="fixCustomers" 
	requestURI="fixUpTask/handyWorker/relationships.do" id="row">
		
		<spring:message code="fixCustomer.assigned" var="assigned" />
	<display:column property="assigned" title="${assigned}" sortable="false"/>
	
</display:table>
<input type="button" name="cancel"
		value="<spring:message code="fixUpTask.cancel" />"
		onclick="javascript: relativeRedir('fixUpTask/handyWorker/findAll.do');" />
</body>
</security:authorize>
</html>
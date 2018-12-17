<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">				   <!-- Esto no se que poner -->
<display:table pagesize="5" class="Finder" name="fixUpTaksFinder" requestURI="handyWorker/finder/fixUpTask/list.do" id="row">
	
	<!-- Action links -->

	<display:column> <a href="handyWorker/finder/fixUpTask/list.do?fixUpTaskId=${row.id}">
	<spring:message code="fixUpTask.findOne" /></a> </display:column>
	
	<!-- Attributes -->
	
	<spring:message code="fixUpTask.ticker" var="ticker" />
	<display:column property="ticker" title="${fixUpTask.ticker}" sortable="true"/>
	
	<spring:message code="fixUpTask.startDate" var="startDate" />
	<display:column property="startDate" title="${fixUpTask.dateStartDate}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>

</display:table>
</security:authorize>
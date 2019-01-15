<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">
<display:table pagesize="5" class="fixUpTask" name="fixUpTasks" requestURI="finder/handyWorker/list.do" id="row">
	
	<!-- Action links -->
	
	<!-- Attributes -->
	
	<spring:message code="fixUpTask.ticker" var="ticker" />
	<display:column property="ticker" title="${ticker}"/>
	
	<spring:message code="fixUpTask.startDate" var="startDate" />
	<display:column property="startDate" title="${startDate}" format="{0,date,dd/MM/yyyy HH:mm}"/>

</display:table>
</security:authorize>
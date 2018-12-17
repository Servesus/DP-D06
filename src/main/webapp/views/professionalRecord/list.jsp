<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">				   	<!-- esto habra que cambiarlo -->
<display:table pagesize="5" class="professionalRecord" name="professionalRecords" requestURI="professionalRecord/handyWorker/list.do" id="row">
	
	<!-- Action links -->

	<display:column> <a href="professionalRecord/handyWorker/edit.do?professionalRecordId=${row.id}">
	<spring:message code="prrecord.link" /></a> </display:column>
	
	<!-- Attributes -->
	
	<spring:message code="prrecord.company" var="companyName" />
	<display:column property="companyName" title="${companyName}"/>

</display:table>
</security:authorize>
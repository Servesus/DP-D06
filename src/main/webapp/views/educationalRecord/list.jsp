<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">				   	<!-- esto habra que cambiarlo -->
<display:table pagesize="5" class="educationalRecord" name="educationalRecords" requestURI="educationalRecord/handyWorker/list.do" id="row">
	
	<!-- Action links -->

	<display:column> <a href="educationalRecord/handyWorker/edit.do?educationalRecordId=${row.id}">
	<spring:message code="edrecord.link" /></a> </display:column>
	
	<!-- Attributes -->
	
	<spring:message code="edrecord.diploma" var="diplomasTitle" />
	<display:column property="diplomasTitle" title="${diplomasTitle}"/>

</display:table>
</security:authorize>
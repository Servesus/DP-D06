<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">				   
<display:table pagesize="5" class="educationalRecord" name="educationalRecord" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->

	<display:column> <a href="educationalRecord/handyWorker/edit.do?educationalRecordId=${row.id}">
	<spring:message code="educationalRecord.edit" /></a> </display:column>
	
	<!-- Attributes -->
	
	<spring:message code="educationalRecord.title" var="diplomasTitle" />
	<display:column property="diplomasTitle" title="${diplomasTitle}"/>

</display:table>
<div>
	<a href="educationalRecord/handyWorker/create.do"> <spring:message
				code="educationalRecord.create" />
	</a>
</div>
</security:authorize>
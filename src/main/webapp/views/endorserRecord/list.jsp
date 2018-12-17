<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">
<display:table name="endorserRecord" id="row" requestURI="handyWorker/endorserRecord/list.do" pagesize="5" class="displaytag">
	<display:column>
		<a href="handyWorker/endorserRecord/edit.do?endorserRecordId=${row.id}">
			<spring:message code="endorserRecord.edit"/>
		</a>
	</display:column>
	<spring:message code="endorserRecord.fullName" var="columnTitle"/>
	<display:column property="fullName" titleKey="columnTitle"/>
</display:table>
</security:authorize>
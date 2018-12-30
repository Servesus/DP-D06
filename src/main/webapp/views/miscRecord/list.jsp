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
<display:table name="miscRecord" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	<display:column>
		<a href="miscRecord/handyWorker/edit.do?recordId=${row.id}">
			<spring:message code="miscRecord.edit"/>
		</a>
	</display:column>
	<spring:message code="miscRecord.title" var="columnTitle"/>
	<display:column property="title" title="${columnTitle}"/>
</display:table>
<div>
	<a href="miscRecord/handyWorker/create.do"> <spring:message
				code="miscRecord.create" />
	</a>
</div>
</security:authorize>
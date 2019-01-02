<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">
<display:table name="warranty" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	<display:column>
	<jstl:if test="${row.isFinal == false }">
		<a href="warranty/administrator/edit.do?warrantyId=${row.id}">
			<spring:message code="warranty.edit"/>
		</a>
	</jstl:if>
	</display:column>
	<display:column>
		<a href="warranty/administrator/show.do?warrantyId=${row.id}">
			<spring:message code="warranty.view"/>
		</a>
	</display:column>
	<spring:message code="warranty.title" var="columnTitle"/>
	<display:column property="title" title="${columnTitle}"/>
</display:table>
<div>
	<a href="warranty/administrator/create.do"> <spring:message
				code="warranty.create" />
	</a>
</div>
</security:authorize>
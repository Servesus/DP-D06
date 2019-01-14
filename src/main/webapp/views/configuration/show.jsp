<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p>
	<spring:message code="configuration.edit.maxResults"/>:
	<br />
	<jstl:out value="${configuration.maxResults}"></jstl:out>
	<br />
</p>

<p>
	<spring:message code="configuration.edit.maxTime"/>:
	<br />
	<jstl:out value="${configuration.maxTime}"></jstl:out> 
	<br />
</p>

<p>
	<spring:message code="configuration.edit.pageName"/>:
	<br />
	<jstl:out value="${configuration.pageName}"></jstl:out> 
	<br />
</p>

<input type="button" name="edit PD"
		value="<spring:message code="configuration.edit" />"
		onclick="javascript: relativeRedir('/configuration/administrator/edit.do');" />
	<br />
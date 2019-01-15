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
</p>

<p>
	<spring:message code="configuration.edit.maxTime"/>:
	<br />
	<jstl:out value="${configuration.maxTime}"></jstl:out> 
</p>

<p>
	<spring:message code="configuration.edit.pageName"/>:
	<br />
	<jstl:out value="${configuration.pageName}"></jstl:out> 
</p>

<p>
	<spring:message code="configuration.edit.bannerURL"/>:
	<br />
	<jstl:out value="${configuration.bannerURL}"></jstl:out> 
</p>

<p>
	<spring:message code="configuration.edit.welcomeES"/>:
	<br />
	<jstl:out value="${configuration.welcomeES}"></jstl:out> 
</p>

<p>
	<spring:message code="configuration.edit.welcomeEN"/>:
	<br />
	<jstl:out value="${configuration.welcomeEN}"></jstl:out> 
</p>

<p>
	<spring:message code="configuration.edit.VATPercent"/>:
	<br />
	<jstl:out value="${configuration.VATPercent}"></jstl:out> 
</p>

<p>
	<spring:message code="configuration.edit.phoneCCode"/>:
	<br />
	<jstl:out value="${configuration.phoneCCode}"></jstl:out> 
</p>

<spring:message code="configuration.edit.spamWords" />:
<br/>
<display:table name="${configuration.spamWords}" id="row">
</display:table>
<br/>

<spring:message code="configuration.edit.cCardsMakes" />:
<br/>
<display:table name="${configuration.cCardsMakes}" id="row">
</display:table>
<br/>

<input type="button" name="edit PD"
		value="<spring:message code="configuration.edit" />"
		onclick="javascript: relativeRedir('/configuration/administrator/edit.do');" />
	<br />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">
<form:form action ="configuration/administrator/edit.do" modelAttribute="configuration">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="spamWords" />
	<form:hidden path="cCardsMakes" />

	<form:label path="maxResults">
		<spring:message code="configuration.edit.maxResults" />:
	</form:label>
	<br />
	<form:input path="maxResults" />
	<form:errors cssClass="error" path="maxResults" />
	<br />
	
	<form:label path="maxTime">
		<spring:message code="configuration.edit.maxTime" />:
	</form:label>
	<br />
	<form:input path="maxTime"/>
	<form:errors cssClass="error" path="maxTime" />
	<br/>
	
	<form:label path="pageName">
		<spring:message code="configuration.edit.pageName" />
	</form:label>
	<br />
	<form:input path="pageName"/>
	<form:errors cssClass="error" path="pageName" />
	<br />
	
	<form:label path="bannerURL">
		<spring:message code="configuration.edit.bannerURL" />
	</form:label>
	<br />
	<form:input path="bannerURL"/>
	<form:errors cssClass="error" path="bannerURL" />
	<br />
	
	<form:label path="welcomeES">
		<spring:message code="configuration.edit.welcomeES" />
	</form:label>
	<br />
	<form:input path="welcomeES"/>
	<form:errors cssClass="error" path="welcomeES" />
	<br />
	
	<form:label path="welcomeEN">
		<spring:message code="configuration.edit.welcomeEN" />
	</form:label>
	<br />
	<form:input path="welcomeEN"/>
	<form:errors cssClass="error" path="welcomeEN" />
	<br />
	
	<form:label path="VATPercent">
		<spring:message code="configuration.edit.VATPercent" />
	</form:label>
	<br />
	<form:input path="VATPercent"/>
	<form:errors cssClass="error" path="VATPercent" />
	<br />
	
	<form:label path="phoneCCode">
		<spring:message code="configuration.edit.phoneCCode" />
	</form:label>
	<br />
	<form:input path="phoneCCode"/>
	<form:errors cssClass="error" path="phoneCCode" />
	<br />
	
	<spring:message code="configuration.edit.spamWords" />:
	<br/>
	<input id="sW" name="sW" value="${spam}"/> 
	<jstl:set var="sW" value="sW"/>
	<br />
	
	<spring:message code="configuration.edit.cCardsMakes" />:
	<br/>
	<input id="cCM" name="cCM" value="${cards}"/> 
	<jstl:set var="cCM" value="cCM"/>
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="configuration.edit.submit" />" />&nbsp; 
	
	<input type="button" name="cancel"
		value="<spring:message code="configuration.edit.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />
	
</form:form>
</security:authorize>
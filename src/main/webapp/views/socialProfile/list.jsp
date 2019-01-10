<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasAnyRole('HANDYWORKER,CUSTOMER,ADMIN,REFEREE')">				   
<display:table pagesize="5" class="socialProfiles" name="socialProfiles" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->

	<display:column> <a href="socialProfile/handyWorker,customer,admin,referee/edit.do?socialProfileId=${row.id}">
	<spring:message code="socialProfile.edit" /></a> </display:column>
	
	<!-- Attributes -->
	
	<spring:message code="socialProfile.nick" var="nick" />
	<display:column property="nick" title="${nick}"/>
	
	<spring:message code="socialProfile.socialNetName" var="socialNetName" />
	<display:column property="socialNetName" title="${socialNetName}"/>
	
	<spring:message code="socialProfile.socialNetProfLink" var="socialNetProfLink" />
	<display:column property="socialNetProfLink" title="${socialNetProfLink}"/>
	
	<spring:message code="socialProfile.name" var="name" />
	<display:column property="name" title="${name}"/>

</display:table>
<div>
	<a href="socialProfile/handyWorker,customer,admin,referee/create.do"> 
	<spring:message	code="socialProfile.create" />
	</a>
</div>
</security:authorize>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">
<form:form action ="finder/handyWorker/edit.do" modelAttribute="finder">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="rangeStart" />
	<form:hidden path="rangeFinish" />

	<form:label path="singleKeyWord">
		<spring:message code="finder.update.keyword" />:
	</form:label>
	<form:input path="singleKeyWord" />
	<form:errors cssClass="error" path="singleKeyWord" />
	<br />
	
	<form:label path="dateStartRange">
		<spring:message code="finder.update.startdate" />:
	</form:label>
	<form:input path="dateStartRange" />
	<form:errors cssClass="error" path="dateStartRange" />
	<br />
	
	<form:label path="dateFinishRange">
		<spring:message code="finder.update.finishdate" />:
	</form:label>
	<form:input path="dateFinishRange" />
	<form:errors cssClass="error" path="dateFinishRange" />
	<br />
	
	<form:label path="lastUpdate">
		<spring:message code="finder.update.lastupdate" />:
	</form:label>
	<form:input path="lastUpdate" />
	<form:errors cssClass="error" path="lastUpdate" />
	<br />
	 
	<form:label path="categoryName">
		<spring:message code="finder.update.category" />:
	</form:label>
	<form:select id="categoryName" path="categoryName">
		<form:option value="0" label="----" />		
		<form:options items="${categoryName}" itemValue="id"
			itemLabel="name" />
	</form:select>
	<form:errors cssClass="error" path="categoryName" />
	<br />
	
	<form:label path="warrantyTitle">
		<spring:message code="finder.update.warranty" />:
	</form:label>
	<form:select id="warrantyTitle" path="warrantyTitle">
		<form:option value="0" label="----" />		
		<form:options items="${warrantyTitle}" itemValue="id"
			itemLabel="title" />
	</form:select>
	<form:errors cssClass="error" path="warrantyTitle" />
	<br />
	
	<input type="submit" name="confirm"
		value="<spring:message code="finder.update.update" />" />&nbsp; 
	
	<input type="button" name="cancel"
		value="<spring:message code="finder.update.cancel" />"
		onclick="javascript: relativeRedir('master.page');" />
	<br />
	
</form:form>
</security:authorize>
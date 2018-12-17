<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">
<form:form action ="handyworker/finder/edit.do" modelAtribute="finder">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="rangeStart" />
	<form:hidden path="rangeFinish" />
		
	<form:label path="title">
		<spring:message code="finder.update.title" />:
	</form:label>
	<br />

	<form:label path="singleKeyword">
		<spring:message code="finder.update.keyword" />:
	</form:label>
	<form:input path="singleKeyword" />
	<form:errors cssClass="error" path="singleKeyword" />
	<br />
	
	<form:label path="dateStartRange">
		<spring:message code="finder.update.startdate" />:
	</form:label>
	<form:textarea path="dateStartRange" />
	<form:errors cssClass="error" path="dateStartRange" />
	<br />
	
	<form:label path="dateFinishRange">
		<spring:message code="finder.update.finishdate" />:
	</form:label>
	<form:textarea path="dateFinishRange" />
	<form:errors cssClass="error" path="dateFinishRange" />
	<br />
	
	<form:label path="lastUpdate">
		<spring:message code="finder.update.lastupdate" />:
	</form:label>
	<form:textarea path="lastUpdate" />
	<form:errors cssClass="error" path="lastUpdate" />
	<br />
	
	<form:label path="categoryName">
		<spring:message code="finder.update.category" />:
	</form:label>
	<form:select id="category" path="category">
		<form:option value="0" label="----" />		
		<form:options items="${category.name}" itemValue="id"
			itemLabel="name" />
	</form:select>
	<form:errors cssClass="error" path="category" />
	
	<form:label path="warrantyTitle">
		<spring:message code="finder.update.warranty" />:
	</form:label>
	<form:select id="warranty" path="warranty">
		<form:option value="0" label="----" />		
		<form:options items="${warranty.title}" itemValue="id"
			itemLabel="title" />
	</form:select>
	<form:errors cssClass="error" path="warranty" />
	
	<input type="submit" name="confirm"
		value="<spring:message code="finder.update.update" />" />&nbsp; 
	
	<input type="button" name="cancel"
		value="<spring:message code="announcement.cancel" />"
		onclick="javascript: relativeRedir('');" />    <!-- Aqui va el link del return a pagina de HW -->
	<br />
	
</form:form>
</security:authorize>
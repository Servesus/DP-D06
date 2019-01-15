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
	<form:hidden path="lastUpdate"/>
	<form:hidden path="fixUpTask"/>

	<form:label path="singleKeyWord">
		<spring:message code="finder.update.keyword" />:
	</form:label>
	<form:input path="singleKeyWord" />
	<form:errors cssClass="error" path="singleKeyWord" />
	<br />
	
	<form:label path="dateStartRange">
		<spring:message code="finder.update.daterange" />:
		<spring:message code="finder.update.between"/>
	</form:label>
	<form:input path="dateStartRange" placeholder="dd/MM/yyyy HH:mm"/>
	<form:errors cssClass="error" path="dateStartRange" />
	
	<form:label path="dateFinishRange">
		<spring:message code="finder.update.and" />
	</form:label>
	<form:input path="dateFinishRange" placeholder="dd/MM/yyyy HH:mm"/>
	<form:errors cssClass="error" path="dateFinishRange" />
	<br />
	
	<form:label path="rangeStart">
		<spring:message code="finder.update.pricerange" />:
		<spring:message code="finder.update.between"/>
	</form:label>
	<form:input path="rangeStart" placeholder="0"/>
	<form:errors cssClass="error" path="rangeStart" />
	
	<form:label path="rangeFinish">
		<spring:message code="finder.update.and" />
	</form:label>
	<form:input path="rangeFinish" placeholder="99999"/>
	<form:errors cssClass="error" path="rangeFinish" />
	<br />
	 
	<form:label path="categoryName">
		<spring:message code="finder.update.category" />:
	</form:label>
	<form:select path="categoryName">
		<form:option value="" label="----" />		
		<form:options items="${cNames}"
			/>
	</form:select>
	<form:errors cssClass="error" path="categoryName" />
	<br />
	
	<form:label path="warrantyTitle">
		<spring:message code="finder.update.warranty" />:
	</form:label>
	<form:select path="warrantyTitle">
		<form:option value="" label="----" />		
		<form:options items="${wTitles}"
			/>
	</form:select>
	<form:errors cssClass="error" path="warrantyTitle" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="finder.update.update" />" />&nbsp; 
	
	<br />
	
</form:form>
</security:authorize>
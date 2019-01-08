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
<form:form action="category/administrator/edit.do" modelAttribute="category">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="childs"/>
	
	<form:label path="nameEN">
		<spring:message code="category.nameEN"/>
	</form:label>
	<form:input path="nameEN"/>
	<form:errors cssClass="error" path="nameEN"/>
	<br />
	
	<form:label path="nameES">
		<spring:message code="category.nameES"/>
	</form:label>
	<form:input path="nameES"/>
	<form:errors cssClass="error" path="nameES"/>
	<br />
	
	<jstl:if test="${lang == 'en' }">
	<form:label path="parents">
		<spring:message code="category.parent"/>
	</form:label>
	<form:select path="parents">	
		<form:options items="${cNames}" itemValue="id" itemLabel="nameEN"
			/>
	</form:select>
	<form:errors cssClass="error" path="parents" />
	<br />
	</jstl:if>
	
	<jstl:if test="${lang == 'es' }">
	<form:label path="parents">
		<spring:message code="category.parent"/>
	</form:label>
	<form:select path="parents">	
		<form:options items="${cNames}" itemValue="id" itemLabel="nameES"
			/>
	</form:select>
	<form:errors cssClass="error" path="parents" />
	<br />
	</jstl:if>
	
	
	<input type="submit" name="save"
		value="<spring:message code="category.save" />" />&nbsp; 
	<jstl:if test="${category.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="category.delete" />" />
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="category.cancel" />"
		onclick="javascript: relativeRedir('category/administrator/list.do');" />
	<br />
	
	
</form:form>
</security:authorize>
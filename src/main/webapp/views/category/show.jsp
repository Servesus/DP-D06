<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:if test="${lang=='en' }">
<b><spring:message code="category.name"/>:</b> ${nameEN} <br />
</jstl:if>

<jstl:if test="${lang=='es' }">
<b><spring:message code="category.name"/>:</b> ${nameES} <br />
</jstl:if>

<jstl:if test="${lang=='es' }">
<b><spring:message code="category.parent"/>:</b> ${parent.nameES} <br />
</jstl:if>
<jstl:if test="${lang=='en' }">
<b><spring:message code="category.parent"/>:</b> ${parent.nameES} <br />
</jstl:if>

<b><spring:message code="category.childs"/>:</b>
<jstl:if test="${lang == 'en' }">
<jstl:forEach var="childs"  items="${childs}">
		${childs.nameEN}
		<br/>
</jstl:forEach>
</jstl:if>

<jstl:if test="${lang == 'es' }">
<jstl:forEach var="childs"  items="${childs}">
		${childs.nameES}
		<br/>
</jstl:forEach>
</jstl:if>

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
<display:table pagesize="5" name="actors" requestURI="${requestURI }" id="row">

	<spring:message code="actor.username" var="username" />
	<display:column title="${username }"> ${row.userAccount.username}
	</display:column>
	
	<jstl:if test="${row.isBanned == false }">
	<display:column>
		<form action="actor/administrator/ban.do" method="post">
		<input type="hidden" id="actor" name="actor" value="${row.id}" /> 
		<input type="submit" name="delete"
		value="<spring:message code="actor.ban" />" /> 
  	 	</form>
	</display:column>	
	</jstl:if>

</display:table>
</security:authorize>
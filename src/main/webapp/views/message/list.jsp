<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

</head>

<body>
<display:table pagesize="5" class="displaytag" name="messages" 
	requestURI="${requestURI}" id="row">
	
		
	<spring:message code="message.sender" var="sender" />
	<display:column  title="${sender}">
		${row.sender.name}
	</display:column>
	
	<spring:message code="message.subject" var="subject" />
	<display:column property="subject" title="${subject}"/>
	
	
	<spring:message code="message.sendDate" var="sendDate" />
	<display:column property="sendDate" title="${sendDate}"/>
	
	<spring:message code="message.priority" var="columnTitle" />
	<display:column title="${columnTitle }" sortable="true">
	<jstl:choose>
		<jstl:when test="${row.priority == 0}">
			<spring:message code="message.neutral"/>
		</jstl:when>
		<jstl:when test="${row.priority == -1}">
			<spring:message code="message.low" />
		</jstl:when>
		<jstl:when test="${row.priority == 1}">
			<spring:message code="message.high"/>
		</jstl:when>
	</jstl:choose>
	</display:column>
		

	<display:column>
		<a href="message/customer,handyWorker,referee,administrator/show.do?messageId=${row.id}">
  	 		<spring:message code="message.view" /> </a>
	</display:column>
	<display:column>
		<a href="box/message/delete.do"> <!-- TODO -->
  	 		<spring:message code="message.delete" /> </a>
	</display:column>		

</display:table>

<input type="button" name="Back" value="<spring:message code="message.back" />"
			onclick="javascript: relativeRedir('actor/box/list.do');" />
</body>

</html>
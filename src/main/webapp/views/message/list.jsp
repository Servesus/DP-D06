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
	
		
	<spring:message code="mesage.sender" var="sender" />
	<display:column  title="${sender}" sortable="true">
		${row.sender.name}
	</display:column>
	
	<spring:message code="mesage.subject" var="subject" />
	<display:column property="subject" title="${subject}" sortable="true"/>
	
	
	<spring:message code="mesage.sendDate" var="sendDate" />
	<display:column property="sendDate" title="${sendDate}" sortable="true"/>
	
	<spring:message code="mesage.priority" var="columnTitle" />
	<display:column title="${columnTitle }" sortable="true">
	<jstl:choose>
		<jstl:when test="${row.priority == 0}">
			<spring:message code="mesage.neutral"/>
		</jstl:when>
		<jstl:when test="${row.priority == -1}">
			<spring:message code="mesage.low" />
		</jstl:when>
		<jstl:when test="${row.priority == 1}">
			<spring:message code="mesage.high"/>
		</jstl:when>
	</jstl:choose>
	</display:column>
		

	<display:column>
		<a href="message/customer,handyWorker,referee,administrator/show.do?messageId=${row.id}">
  	 		<spring:message code="mesage.view" /> </a>
	</display:column>
	<display:column>
		<form action="message/customer,handyWorker,referee,administrator/move.do" method="post">
		<input type="hidden" id="boxId" name="boxId" value="${boxId}" /> 
		<input type="hidden" id="mesage" name="mesage" value="${row.id}" /> 
		<select name="box" >
		<jstl:forEach var="item" items="${boxes}">
		<option value="${item.id}"> ${item.name }</option>
		</jstl:forEach>
		</select>
		<input type="submit" name="move"
		value="<spring:message code="mesage.move" />" />&nbsp; 
  	 	</form>
	</display:column>		
	<display:column>
		<form action="message/customer,handyWorker,referee,administrator/edit.do" method="post">
		<input type="hidden" id="boxId" name="boxId" value="${boxId}" /> 
		<input type="hidden" id="mesage" name="mesage" value="${row.id}" /> 
		<input type="submit" name="delete"
		value="<spring:message code="mesage.delete" />" />&nbsp; 
  	 	</form>
	</display:column>	

</display:table>

<input type="button" name="Back" value="<spring:message code="mesage.back" />"
			onclick="javascript: relativeRedir('box/customer,handyWorker,referee,administrator/list.do');" />
			
<input type="button" name="New Message" value="<spring:message code="mesage.create" />"
			onclick="javascript: relativeRedir('message/customer,handyWorker,referee,administrator/create.do');" />
</body>



</html>
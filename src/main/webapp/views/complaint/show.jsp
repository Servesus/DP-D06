<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



	<security:authorize access="hasRole('CUSTOMER')">

	<p>
	<spring:message code="complaint.moment"/>:
	<jstl:out value="${complaint.moment}"></jstl:out> 
	</p>
	
	<p>
	<spring:message code="complaint.fixUpTask.id"/>:
	<jstl:out value="${complaint.fixUpTasks.id}"></jstl:out>
	</p>
	
	<spring:message code="complaint.reports"/>:
				<ul>
					<jstl:forEach items="${complaint.reports}" var="report">
						<li>
							<jstl:out value="${report.description}"/>
							<a href="report/customer/create.do?reportId=${report.id}">
							<spring:message code="complaint.report.show" /></a>
						</li>
					</jstl:forEach>
				</ul>
				
	<spring:message code="complaint.attachment"/>:
				<ul>
					<jstl:forEach items="${complaint.attachment}" var="attachment">
						<li>
							<jstl:out value="${attachment}"/>
						</li>
					</jstl:forEach>
				</ul>
	
	
	<input type="button" name="back"
		value="<spring:message code="complaint.back" />"
		onclick="javascript: relativeRedir('complaint/customer/list.do?fixUpTaskId=${complaint.fixUpTasks.id}');" />
</security:authorize>

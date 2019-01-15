<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



	<security:authorize access="hasRole('HANDYWORKER')">

	<div>
	<p>
	<spring:message code="application.moment"/>:
	<jstl:out value="${application.moment}"></jstl:out> 
	</p>
	
	<p>
	<spring:message code="application.price"/>:
	<jstl:out value="${application.price}"></jstl:out> &nbsp; (${application.price * (1+(priceVAT)/100)})
	</p>
	
	<p>
	<spring:message code="application.status"/>:
	<jstl:choose>
		<jstl:when test="${application.status == 1}">
			<spring:message code="application.accepted"/>
		</jstl:when>
		<jstl:when test="${application.status == 0}">
			<spring:message code="application.pending" />
		</jstl:when>
		<jstl:when test="${application.status == -1}">
			<spring:message code="application.rejected" />
		</jstl:when>
	</jstl:choose>
	</p>
	
	<spring:message code="application.hwComments"/>:
				<ul>
					<jstl:forEach items="${application.hwComments}" var="hwComment">
						<li>
							<jstl:out value="${hwComment}"/>
						</li>
					</jstl:forEach>
				</ul>
	
	<p>
	<spring:message code="application.fixUpTask"/>:
	<jstl:out value="${application.fixUpTask.id}"></jstl:out> 
	</p>
	
	<jstl:if test="${application.status == 1}">
	<spring:message code="application.customerComments"/>:
				<ul>
					<jstl:forEach items="${application.customerComments}" 
					var="customerComment">
						<li>
							<jstl:out value="${customerComment}"/>
						</li>
					</jstl:forEach>
				</ul>
	</jstl:if>
</div>
	<jstl:if test="${application.status == 1}">
	<input type= button name="create"
	value="<spring:message code="phase.workplan" />"
	onclick="javascript: relativeRedir('phase/handyWorker/list.do?fixUpTaskId=${application.fixUpTask.id}');"/>
	</jstl:if>
	
	<input type="button" name="back"
		value="<spring:message code="application.back" />"
		onclick="javascript: relativeRedir('application/handyWorker/list.do?fixUpTaskId=${application.fixUpTask.id}');" />
</security:authorize>




<security:authorize access="hasRole('CUSTOMER')">

	<div>
	<p>
	<spring:message code="application.moment"/>:
	<jstl:out value="${application.moment}"></jstl:out> 
	</p>
	
	<p>
	<spring:message code="application.price"/>:
	<jstl:out value="${application.price}"></jstl:out> &nbsp; (${application.price * (1+(priceVAT)/100)})
	</p>
	
	<p>
	<spring:message code="application.status"/>:
	<jstl:choose>
		<jstl:when test="${application.status == 1}">
			<spring:message code="application.accepted"/>
		</jstl:when>
		<jstl:when test="${application.status == 0}">
			<spring:message code="application.pending" />
		</jstl:when>
		<jstl:when test="${application.status == -1}">
			<spring:message code="application.rejected" />
		</jstl:when>
	</jstl:choose>
	</p>
	
	<p>
	<spring:message code="application.handyWorker.make"/>:
	<jstl:out value="${application.handyWorker.make}"></jstl:out> 
	</p>
	
	<spring:message code="application.hwComments"/>:
				<ul>
					<jstl:forEach items="${application.hwComments}" var="hwComment">
						<li>
							<jstl:out value="${hwComment}"/>
						</li>
					</jstl:forEach>
				</ul>
	
	<p>
	<spring:message code="application.fixUpTask"/>:
	<jstl:out value="${application.fixUpTask.id}"></jstl:out> 
	</p>
	
	<jstl:if test="${application.status == 1 || application.status == -1}">
	<spring:message code="application.customerComments"/>:
				<ul>
					<jstl:forEach items="${application.customerComments}" 
					var="customerComment">
						<li>
							<jstl:out value="${customerComment}"/>
						</li>
					</jstl:forEach>
				</ul>
	</jstl:if>
</div>

	<jstl:if test="${application.status == 0}">
	<input type= button name="application.accept"
	value="<spring:message code="application.accept" />"
	onclick="javascript: relativeRedir('application/customer/accept.do?applicationId=${application.id}');"/>
	<input type= button name="application.reject"
	value="<spring:message code="application.reject" />"
	onclick="javascript: relativeRedir('application/customer/reject.do?applicationId=${application.id}');"/>
	</jstl:if>
	
	
	<input type="button" name="back"
		value="<spring:message code="application.back" />"
		onclick="javascript: relativeRedir('application/customer/list.do?fixUpTaskId=${application.fixUpTask.id}');" />
</security:authorize>
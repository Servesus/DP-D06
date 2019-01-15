<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('CUSTOMER')">
<form:form action ="application/customer/accept.do" modelAttribute="application">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="handyWorker" />
	<form:hidden path="fixUpTask" />
	<form:hidden path="status"/>
	<form:hidden path="hwComments"/>
	<form:hidden path="price"/>
	<form:hidden path="creditCard"/>
	
	<form:label path="customerComments">
	<spring:message code="application.comment"/>
	</form:label>
	<form:textarea path="customerComments"/>
	<form:errors cssClass="error" path="customerComments" />
	<br />
	
	<form:label path="creditCard.holderName">
	<spring:message code="application.holderName"/>
	</form:label>
	<form:input path="creditCard.holderName"/>
	<form:errors cssClass="error" path="creditCard.holderName"/>
	<br />
	
	<form:label path="creditCard.brandName">
		<spring:message code="application.brandName" />:
	</form:label>
	<form:select id="creditCard.brandName" path="creditCard.brandName">	
		<form:options items="${creditCards}"
			/>
	</form:select>
	<br />
	
	<form:label path="creditCard.number">
	<spring:message code="application.number"/>
	</form:label>
	<form:input path="creditCard.number"/>
	<form:errors cssClass="error" path="creditCard.number"/>
	<br />
	<form:label path="creditCard.month">
	<spring:message code="application.month"/>
	</form:label>
	<form:input path="creditCard.month"/>
	<form:errors cssClass="error" path="creditCard.month"/>
	<br />
	<form:label path="creditCard.expirationYear">
	<spring:message code="application.expirationYear"/>
	</form:label>
	<form:input path="creditCard.expirationYear"/>
	<form:errors cssClass="error" path="creditCard.expirationYear"/>
	<br />
	<form:label path="creditCard.cvv">
	<spring:message code="application.cvv"/>
	</form:label>
	<form:input path="creditCard.cvv"/>
	<form:errors cssClass="error" path="creditCard.cvv"/>
	
	
	<input type="submit" name="save"
		value="<spring:message code="application.save" />" />&nbsp; 
	
		<input type="button" name="back"
		value="<spring:message code="application.back" />"
		onclick="javascript: relativeRedir('application/customer/list.do?fixUpTaskId=${application.fixUpTask.id}');" />
	
</form:form>
</security:authorize>
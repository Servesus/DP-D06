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
<security:authorize access="hasRole('ADMIN')">

<b><spring:message code="admin.Q1" /></b>
<br></br> 
<b><spring:message code="admin.AvgOfFixUpTasksPerUser" /></b> ${AvgOfFixUpTasksPerUser} <br/>
<b><spring:message code="admin.MinOfFixUpTasksPerUser" /></b> ${MinOfFixUpTasksPerUser} <br/>
<b><spring:message code="admin.MaxOfFixUpTasksPerUser" /></b> ${MaxOfFixUpTasksPerUser} <br/>
<b><spring:message code="admin.StddevOfFixUpTasksPerUser" /></b> ${StddevOfFixUpTasksPerUser} <br/>
<br></br> 
<b><spring:message code="admin.Q2" /></b>
<br></br> 
<b><spring:message code="admin.AvgApplicationsPerFixUpTask" /></b> ${AvgApplicationsPerFixUpTask} <br/>
<b><spring:message code="admin.MaxApplicationsPerFixUpTask" /></b> ${MaxApplicationsPerFixUpTask} <br/>
<b><spring:message code="admin.MinApplicationsPerFixUpTask" /></b> ${MinApplicationsPerFixUpTask} <br/>
<b><spring:message code="admin.StddevApplicationsPerFixUpTask" /></b> ${StddevApplicationsPerFixUpTask} <br/>
<br></br> 
<b><spring:message code="admin.Q3" /></b>
<br></br>
<b><spring:message code="admin.AvgMaxPricePerFixUpTask" /></b> ${AvgMaxPricePerFixUpTask} <br/>
<b><spring:message code="admin.MaxMaxPricePerFixUpTask" /></b> ${MaxMaxPricePerFixUpTask} <br/>
<b><spring:message code="admin.MinMaxPricePerFixUpTask" /></b> ${MinMaxPricePerFixUpTask} <br/>
<b><spring:message code="admin.StddevMaxPricePerFixUpTask" /></b> ${StddevMaxPricePerFixUpTask} <br/>
<br></br> 
<b><spring:message code="admin.Q4" /></b>
<br></br>
<b><spring:message code="admin.AvgPriceOfferedOfApplication" /></b> ${AvgPriceOfferedOfApplication} <br/>
<b><spring:message code="admin.MinPriceOfferedOfApplication" /></b> ${MinPriceOfferedOfApplication} <br/>
<b><spring:message code="admin.MaxPriceOfferedOfApplication" /></b> ${MaxPriceOfferedOfApplication} <br/>
<b><spring:message code="admin.StddevPriceOfferedOfApplciation" /></b> ${StddevPriceOfferedOfApplciation} <br/>
<br></br> 
<b><spring:message code="admin.Q5" /></b>
<br></br>
<b><spring:message code="admin.RatioOfPendingApplications" /></b> ${RatioOfPendingApplications} <br/>
<br></br> 
<b><spring:message code="admin.Q6" /></b>
<br></br>
<b><spring:message code="admin.RatioOfAcceptedApplications" /></b> ${RatioOfAcceptedApplications} <br/>
<br></br> 
<b><spring:message code="admin.Q7" /></b>
<br></br>
<b><spring:message code="admin.RatioOfRejectedApplications" /></b> ${RatioOfRejectedApplications} <br/>
<br></br> 
<b><spring:message code="admin.Q8" /></b>
<br></br>
<b><spring:message code="admin.RatioOfPendingApplicationsCanNotChangeStatus" /></b> ${RatioOfPendingApplicationsCanNotChangeStatus} <br/>
<br></br> 
<b><spring:message code="admin.Q9" /></b>
<br></br>
<b><spring:message code="admin.CustomerMoreAcceptedThanAvg" /></b>
<jstl:forEach var="CustomerMoreAcceptedThanAvg"  items="${CustomerMoreAcceptedThanAvg}">
		<jstl:out value="${CustomerMoreAcceptedThanAvg.name}"></jstl:out>
	</jstl:forEach>
<br></br> 
<b><spring:message code="admin.Q10" /></b>
<br></br>
<b><spring:message code="admin.HwMoreAcceptedThanAvg" /></b>
<jstl:forEach var="HwMoreAcceptedThanAvg"  items="${HwMoreAcceptedThanAvg}">
		<jstl:out value="${HwMoreAcceptedThanAvg.name}"></jstl:out>
	</jstl:forEach>
<br></br> 
<b><spring:message code="admin.Q11" /></b>
<br></br>
<b><spring:message code="admin.AvgComplaintsPerFixUpTask" /></b> ${AvgComplaintsPerFixUpTask} <br/>
<b><spring:message code="admin.MaxComplaintsPerFixUpTask" /></b> ${MaxComplaintsPerFixUpTask} <br/>
<b><spring:message code="admin.MinComplaintsPerFixUpTask" /></b> ${MinComplaintsPerFixUpTask} <br/>
<b><spring:message code="admin.StddevComplaintsPerFixUpTask" /></b> ${StddevComplaintsPerFixUpTask} <br/>
<br></br> 
<b><spring:message code="admin.Q12" /></b>
<br></br>
<b><spring:message code="admin.AvgNotesPerRefereeReport" /></b> ${AvgNotesPerRefereeReport} <br/>
<b><spring:message code="admin.MaxNotesPerRefereeReport" /></b> ${MaxNotesPerRefereeReport} <br/>
<b><spring:message code="admin.MinNotesPerRefereeReport" /></b> ${MinNotesPerRefereeReport} <br/>
<b><spring:message code="admin.StddevNotesPerRefereeReport" /></b> ${StddevNotesPerRefereeReport} <br/>
<br></br> 
<b><spring:message code="admin.Q13" /></b>
<br></br>
<b><spring:message code="admin.RatioFixUpTaskWithComplaint" /></b> ${RatioFixUpTaskWithComplaint} <br/>
<br></br> 
<b><spring:message code="admin.Q14" /></b>
<br></br>
<b><spring:message code="admin.Top3CustomersOfComplaints" /></b>
<jstl:forEach var="Top3CustomersOfComplaints"  items="${Top3CustomersOfComplaints}">
		<jstl:out value="${Top3CustomersOfComplaints.name}"></jstl:out>
	</jstl:forEach>
<br></br> 
<b><spring:message code="admin.Q15" /></b>
<br></br>
<b><spring:message code="admin.Top3HandyWorkerOfComplaints" /></b>
<jstl:forEach var="Top3HandyWorkerOfComplaints"  items="${Top3HandyWorkerOfComplaints}">
		<jstl:out value="${Top3HandyWorkerOfComplaints.name}"></jstl:out>
	</jstl:forEach>
<br></br> 
</security:authorize>
</body>
</html>
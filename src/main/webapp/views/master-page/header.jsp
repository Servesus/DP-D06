<%--
 * header.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div>
	<a href="#"><img src="${configuration.bannerURL}" alt="${configuration.pageName}, Inc." height="150" width="400" /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>	
					<li><a href="warranty/administrator/list.do"><spring:message code="master.page.administrator.warranty" /></a></li>
					<li><a href="category/administrator/list.do"><spring:message code="master.page.administrator.category" /></a></li>
					<li><a href="administrator/administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
					<li><a href="administrator/create.do"><spring:message code="master.page.administrator.registerAdmin" /></a></li>
					<li><a href="referee/administrator/create.do"><spring:message code="master.page.administrator.registerReferee" /></a></li>
					<li><a href="message/administrator/createbroadcast.do"><spring:message code="master.page.administrator.broadcast" /></a></li>	
					<li><a href="actor/administrator/suspiciouslist.do"><spring:message code="master.page.actor.suspicious" /></a></li>
					<li><a href="actor/administrator/bannedlist.do"><spring:message code="master.page.actor.banned" /></a></li>				
				</ul>
			</li>
			<li><a class="fNiv" href="configuration/administrator/show.do"><spring:message code="master.page.administrator.configuration" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="fixUpTask/customer/findAll.do"><spring:message code="master.page.customer.findAll" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('REFEREE')">
			<li><a class="fNiv"><spring:message	code="master.page.referee" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="complaint/referee/listSelfAssigned.do"><spring:message code="master.page.referee.listSelfAssigned" /></a></li>
					<li><a href="complaint/referee/listAll.do"><spring:message code="master.page.referee.listAll" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('HANDYWORKER')">
			<li><a class="fNiv"><spring:message	code="master.page.handyWorker" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="fixUpTask/handyWorker/findAll.do"><spring:message code="master.page.handyWorker.findAll" /></a></li>
					<li><a href="application/handyWorker/list.do"><spring:message code="master.page.application.list" /></a></li>
					<li><a href="educationalRecord/handyWorker/list.do"><spring:message code="master.page.handyWorker.listEducationalRecord" /></a></li>
					<li><a href="endorserRecord/handyWorker/list.do"><spring:message code="master.page.handyWorker.listEndorser" /></a></li>
					<li><a href="professionalRecord/handyWorker/list.do"><spring:message code="master.page.handyWorker.listProfessional" /></a></li>
					<li><a href="miscRecord/handyWorker/list.do"><spring:message code="master.page.handyWorker.listMisc" /></a></li>
					<li><a href="personalRecord/handyWorker/edit.do"><spring:message code="master.page.handyWorker.edit" /></a></li>
					<li><a href="finder/handyWorker/list.do"><spring:message code="master.page.handyWorker.listFinder" /></a></li>
					<li><a href="finder/handyWorker/edit.do"><spring:message code="master.page.handyWorker.editFinder" /></a></li>
					<li><a href="complaint/handyWorker/list.do"><spring:message code="master.page.handyWorker.list" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/action-1.do"><spring:message code="master.page.profile.action.1" /></a></li>
					<li><a href="box/customer,handyWorker,referee,administrator/list.do"><spring:message code="master.page.box.list" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>


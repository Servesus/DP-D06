/*
 * ProfileController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.AdministratorService;
import services.CustomerService;
import services.HandyWorkerService;
import services.RefereeService;
import domain.Administrator;
import domain.Application;
import domain.Category;
import domain.Complaint;
import domain.CreditCard;
import domain.Customer;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Phase;
import domain.Referee;
import domain.Report;

@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private CustomerService			customerService;
	@Autowired
	private RefereeService			refereeService;
	@Autowired
	private HandyWorkerService		handyWorkerService;


	// Action-1 ---------------------------------------------------------------		

	@RequestMapping(value = "/action-1", method = RequestMethod.GET)
	public ModelAndView action1() {
		final ModelAndView result = new ModelAndView("profile/action-1");
		if (LoginService.getPrincipal().getAuthorities().contains("ADMIN")) {
			Administrator administrador1;
			final Collection<String> categories = new ArrayList<>();
			Collection<Category> categoriesCollection = new ArrayList<>();

			administrador1 = this.administratorService.findOne(LoginService.getPrincipal().getId());
			categoriesCollection = administrador1.getCategories();
			for (final Category c : categoriesCollection)
				categories.add(c.getName());

			result.addObject("administrator.name", administrador1.getName());
			result.addObject("administrator.surname", administrador1.getSurname());
			result.addObject("administrator.email", administrador1.getEmail());
			result.addObject("administrator.phoneNumber", administrador1.getPhoneNumber());
			result.addObject("administrator.id", administrador1.getId());
			result.addObject("administrator.categories", categories);

		} else if (LoginService.getPrincipal().getAuthorities().contains("CUSTOMER")) {
			Customer customer1;
			final Collection<Complaint> complaints = new ArrayList<Complaint>();
			Collection<Complaint> complaintsCollection = new ArrayList<Complaint>();
			final Collection<CreditCard> creditCards = new ArrayList<CreditCard>();
			Collection<CreditCard> creditCardCollection = new ArrayList<CreditCard>();
			final Collection<FixUpTask> fixUpTasks = new ArrayList<FixUpTask>();
			Collection<FixUpTask> fixUpTaskCollection = new ArrayList<FixUpTask>();

			customer1 = this.customerService.findOne(LoginService.getPrincipal().getId());
			complaintsCollection = customer1.getComplaints();
			creditCardCollection = customer1.getCreditCards();
			fixUpTaskCollection = customer1.getFixUpTasks();
			creditCards.addAll(creditCardCollection);
			complaints.addAll(complaintsCollection);
			fixUpTasks.addAll(fixUpTaskCollection);

			result.addObject("customer.name", customer1.getName());
			result.addObject("customer.surname", customer1.getSurname());
			result.addObject("customer.email", customer1.getEmail());
			result.addObject("customer.phoneNumber", customer1.getPhoneNumber());
			result.addObject("customer.id", customer1.getId());
			result.addObject("customer.creditCards", complaints);
			result.addObject("customer.complaints", complaints);
			result.addObject("customer.fixUpTasks", fixUpTasks);

		} else if (LoginService.getPrincipal().getAuthorities().contains("REFEREE")) {
			Referee referee1;

			final Collection<Report> reports = new ArrayList<Report>();
			Collection<Report> reportsCollection = new ArrayList<Report>();

			referee1 = this.refereeService.findOne(LoginService.getPrincipal().getId());
			reportsCollection = referee1.getReports();

			reports.addAll(reportsCollection);

			result.addObject("referee.name", referee1.getName());
			result.addObject("referee.surname", referee1.getSurname());
			result.addObject("referee.email", referee1.getEmail());
			result.addObject("referee.phoneNumber", referee1.getPhoneNumber());
			result.addObject("referee.id", referee1.getId());
			result.addObject("referee.reports", reports);

		} else if (LoginService.getPrincipal().getAuthorities().contains("HANDYWORKER")) {
			HandyWorker handyWorker1;

			final Collection<Application> applications = new ArrayList<Application>();
			Collection<Application> applicationsCollection = new ArrayList<Application>();
			final Collection<Phase> phases = new ArrayList<Phase>();
			Collection<Phase> phasesCollection = new ArrayList<Phase>();

			handyWorker1 = this.handyWorkerService.findOne(LoginService.getPrincipal().getId());
			applicationsCollection = handyWorker1.getApplications();
			phasesCollection = handyWorker1.getPhases();

			applications.addAll(applicationsCollection);
			phases.addAll(phasesCollection);

			result.addObject("handyWorker.name", handyWorker1.getName());
			result.addObject("handyWorker.surname", handyWorker1.getSurname());
			result.addObject("handyWorker.email", handyWorker1.getEmail());
			result.addObject("handyWorker.phoneNumber", handyWorker1.getPhoneNumber());
			result.addObject("handyWorker.id", handyWorker1.getId());
			result.addObject("handyWorker.make", handyWorker1.getMake());
			result.addObject("handyWorker.applications", applications);
			result.addObject("handyWorker.phases", phases);

		}
		return result;
	}
	// Action-2 ---------------------------------------------------------------		

	@RequestMapping("/action-3")
	public ModelAndView action3() {
		throw new RuntimeException("Oops! An *expected* exception was thrown. This is normal behaviour.");
	}

}

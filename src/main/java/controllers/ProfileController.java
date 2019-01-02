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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.ActorService;
import services.AdministratorService;
import services.CustomerService;
import services.HandyWorkerService;
import services.RefereeService;
import domain.Actor;
import domain.Administrator;
import domain.Customer;
import domain.HandyWorker;
import domain.Referee;

@Controller
@RequestMapping("profile")
public class ProfileController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private CustomerService			customerService;
	@Autowired
	private RefereeService			refereeService;
	@Autowired
	private HandyWorkerService		handyWorkerService;
	@Autowired
	private ActorService			actorService;


	// Action-1 ---------------------------------------------------------------		

	@RequestMapping(value = "/action-1", method = RequestMethod.GET)
	public ModelAndView action1() {
		final ModelAndView result = new ModelAndView("profile/action-1");
		final Actor user = this.actorService.getActorLogged();
		final UserAccount userAccount = LoginService.getPrincipal();

		if (userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN")) {
			Administrator administrador1;
			administrador1 = this.administratorService.findOne(user.getId());
			Assert.notNull(administrador1);
			result.addObject("administrator", administrador1);

		} else if (userAccount.getAuthorities().iterator().next().getAuthority().equals("CUSTOMER")) {
			Customer customer1;
			customer1 = this.customerService.findOne(user.getId());
			Assert.notNull(customer1);
			result.addObject("customer", customer1);

		} else if (userAccount.getAuthorities().iterator().next().getAuthority().equals("REFEREE")) {
			Referee referee1;
			referee1 = this.refereeService.findOne(user.getId());
			Assert.notNull(referee1);
			result.addObject("referee", referee1);

		} else if (userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER")) {
			HandyWorker handyWorker1;
			handyWorker1 = this.handyWorkerService.findOne(user.getId());
			Assert.notNull(handyWorker1);
			result.addObject("handyWorker", handyWorker1);

		}
		return result;
	}
	// Action-2 ---------------------------------------------------------------		

	@RequestMapping("/action-3")
	public ModelAndView action3() {
		throw new RuntimeException("Oops! An *expected* exception was thrown. This is normal behaviour.");
	}

}

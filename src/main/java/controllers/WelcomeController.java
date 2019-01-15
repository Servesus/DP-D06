/*
 * WelcomeController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ConfigurationService;
import domain.Configuration;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}


	// Index ------------------------------------------------------------------	

	@Autowired
	private ActorService			actorService;
	@Autowired
	private ConfigurationService	configurationService;


	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(required = false, defaultValue = "") String name) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;

		final SecurityContext context = SecurityContextHolder.getContext();
		final Authentication a = context.getAuthentication();

		if (!a.getName().equals("anonymousUser"))
			name = this.actorService.getActorLogged().getName();

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());

		final String language = LocaleContextHolder.getLocale().getLanguage();
		final Configuration conf = this.configurationService.findAll().get(0);

		result = new ModelAndView("welcome/index");
		if (language.equals("es"))
			result.addObject("welcome", conf.getWelcomeES());
		else if (language.equals("en"))
			result.addObject("welcome", conf.getWelcomeEN());
		result.addObject("name", name);
		result.addObject("moment", moment);

		return result;
	}
}

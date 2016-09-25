/**
 * The Main Controller for our web application.
 *
 * Handles the requests sent on for main pages
 */

package com.springwebapp.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController	{

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView hello()	{

		ModelAndView model = new ModelAndView();
		model.setViewName("index");

		model.addObject("title", "Hello World!");
		model.addObject("message", "My name is Prateek Mathur, and I am a programmer.");

		return model;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin()	{

		ModelAndView model = new ModelAndView();
		model.setViewName("admin");

		model.addObject("title", "Welcome Admin");
		model.addObject("message", "You can perform admin level actions here");

		return model;
	}

}

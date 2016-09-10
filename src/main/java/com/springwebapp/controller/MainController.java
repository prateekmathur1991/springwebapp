/**
 * The Main Controller for our web application.
 *
 * Handles the requests sent on for main pages
 */

package com.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController	{

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello(ModelMap model)	{
		model.put("title", "Hello World!");
		model.put("message", "My name is Prateek Mathur, and I am a programmer.");

		return "index";
	}

}

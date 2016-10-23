/**
 * The Main Controller for our web application.
 * <p>
 * Handles the requests sent on for main pages
 */

package com.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/")
    public ModelAndView hello() {

        ModelAndView model = new ModelAndView("index");

        model.addObject("title", "Hello World!");
        model.addObject("message", "My name is Prateek Mathur, and I am a programmer.");

        return model;
    }

    @GetMapping("/admin")
    public ModelAndView admin() {

        ModelAndView model = new ModelAndView("admin");

        model.addObject("title", "Welcome Admin");
        model.addObject("message", "You can perform admin level actions here");

        return model;
    }

}
/**
 * The Main Controller for our web application.
 * <p>
 * Handles the requests sent on for main pages
 */

package com.springwebapp.controller;

import com.springwebapp.authentication.AuthenticationService;
import com.springwebapp.utils.AppUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
public class MainController {

    @Autowired
    private AuthenticationService authenticationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public ModelAndView hello() {

        LOGGER.debug("hello() is executed, value {}", "springwebapp");

        LOGGER.error("This is Error message", new Exception("Testing"));

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

    @GetMapping("/logout")
    public ModelAndView logout()    {

        authenticationService.logout();
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/consumeRest")
    public @ResponseBody String consumeRest(HttpServletRequest request) {

        Client client = null;
        String entity = null;

        try {
            client = ClientBuilder.newClient(new ClientConfig());
            WebTarget target = client.target(AppUtils.getHostName(request));

            Response response = target.path("rest").path("getEmployees").request()
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .header("X-Username", "root")
                    .header("X-Password", "root@123")
                    .post(Entity.entity(null, MediaType.WILDCARD_TYPE));

            int status = response.getStatus();
            String accessToken = response.getHeaderString("X-Access-Token");

            if (status == HttpServletResponse.SC_OK && accessToken != null) {
                response = target.path("rest").path("getEmployees").request()
                        .accept(MediaType.APPLICATION_JSON_TYPE)
                        .header("X-Access-Token", accessToken)
                        .post(Entity.entity(null, MediaType.WILDCARD_TYPE));

                boolean hasEntity = response.hasEntity();
                entity = response.readEntity(String.class);
            }

            return entity;
        } catch (Exception e) {
            System.err.println(" Exception in MainController.consumeRest::" + e.getLocalizedMessage());
            return null;
        } finally {
            if (null != client) {
                client.close();
                client = null;
            }
        }
    }

}
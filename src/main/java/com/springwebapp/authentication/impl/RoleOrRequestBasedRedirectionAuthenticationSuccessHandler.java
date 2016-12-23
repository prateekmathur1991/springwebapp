package com.springwebapp.authentication.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

/**
 * A custom implementation of {@link AuthenticationSuccessHandler} that functions like a {@link SavedRequestAwareAuthenticationSuccessHandler},
 * by first searching for a {@link SavedRequest} in the request cache and redirecting to the target URL, 
 * if found, but redirects to a custom URL if no {@link SavedRequest} is found
 */

@Component
public class RoleOrRequestBasedRedirectionAuthenticationSuccessHandler implements AuthenticationSuccessHandler	{
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
			throws ServletException, IOException	{
		
		handle(request, response);
		clearAttributes(request);
		
	}
	
	private void handle(HttpServletRequest request, HttpServletResponse response) throws IOException	{
		SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);		
		if (savedRequest == null)	{
			
			this.redirectStrategy.sendRedirect(request, response, "/admin");
			return;
		}
		
		this.redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
	}
	
	private void clearAttributes(HttpServletRequest request)	{
		
		HttpSession session = request.getSession(false); 		
		if (session == null) { 			
			return; 
		} 		
		
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		session.removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
	}
}

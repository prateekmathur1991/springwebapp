package com.springwebapp.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

/**
 * A filter that intercepts all HTTP requests, and responsible for
 * checking either if each request has a valid token, or authenticating
 * the request if required.
 */
@Component
public class TokenAuthenticationFilter extends GenericFilterBean {

    private final static String TOKEN_HEADER = "X-Access-Token";
    private final static String USERNAME_HEADER = "X-Username";
    private final static String PASSWORD_HEADER = "X-Password";

    private final static String LOGOUT_LINK = "/logout";

    /**
     * Request attribute that indicates that this filter will not continue with the chain.
     * Handy after login/logout, etc.
     */
    private static final String REQUEST_ATTR_DO_NOT_CONTINUE = "MyAuthenticationFilter-doNotContinue";

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        boolean authenticated = checkToken(httpRequest, httpResponse);

        if (canRequestProcessingContinue(httpRequest) && httpRequest.getMethod().equals("POST")) {
            // If we're not authenticated, we don't bother with logout at all.
            // Logout does not work in the same request with login - this does not make sense,
            // because logout works with token and login returns it.
            if (authenticated) {
                checkLogout(httpRequest);
            }

            // Login works just fine even when we provide token that is valid up to this request,
            // because then we get a new one.
            checkLogin(httpRequest, httpResponse);
        }

        if (canRequestProcessingContinue(httpRequest)) {
            chain.doFilter(request, response);
        }
    }

    /** Returns true, if request contains valid authentication token. */
    private boolean checkToken(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        String token = httpRequest.getHeader(TOKEN_HEADER);
        if (token == null) {
            return false;
        }

        if (authenticationService.checkToken(token)) {
            System.out.println(" *** " + TOKEN_HEADER + " valid for: " +
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            return true;
        } else {
            System.out.println(" *** Invalid " + TOKEN_HEADER + ' ' + token);
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            doNotContinueWithRequestProcessing(httpRequest);
        }

        return false;
    }

    private void checkLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        String authorization = httpRequest.getHeader("Authorization");
        String username = httpRequest.getHeader(USERNAME_HEADER);
        String password = httpRequest.getHeader(PASSWORD_HEADER);

        if (authorization != null) {
            checkBasicAuthorization(authorization, httpResponse);
            doNotContinueWithRequestProcessing(httpRequest);
        } else if (username != null && password != null) {
            checkUsernameAndPassword(username, password, httpResponse);
            doNotContinueWithRequestProcessing(httpRequest);
        }
    }

    private void checkBasicAuthorization(String authorization, HttpServletResponse httpResponse) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(authorization);
        if (tokenizer.countTokens() < 2) {
            return;
        }
        if (!tokenizer.nextToken().equalsIgnoreCase("Basic")) {
            return;
        }

        String base64 = tokenizer.nextToken();
        String loginPassword = new String(Base64.decode(base64.getBytes(StandardCharsets.UTF_8)));

        System.out.println("loginPassword = " + loginPassword);
        tokenizer = new StringTokenizer(loginPassword, ":");
        System.out.println("tokenizer = " + tokenizer);
        checkUsernameAndPassword(tokenizer.nextToken(), tokenizer.nextToken(), httpResponse);
    }

    private void checkUsernameAndPassword(String username, String password, HttpServletResponse httpResponse) throws IOException {
        String token = authenticationService.authenticate(username, password);
        if (token != null) {
            httpResponse.setHeader(TOKEN_HEADER, token);
        } else {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private void checkLogout(HttpServletRequest httpRequest) {
        String currentLink = new UrlPathHelper().getPathWithinApplication(httpRequest);
        if (currentLink.equals(LOGOUT_LINK)) {
            String token = httpRequest.getHeader(TOKEN_HEADER);
            // we go here only authenticated, token must not be null
            authenticationService.logout(token);
            doNotContinueWithRequestProcessing(httpRequest);
        }
    }

    /**
     * This is set in cases when we don't want to continue down the filter chain. This occurs
     * for any {@link HttpServletResponse#SC_UNAUTHORIZED} and also for login or logout.
     */
    private void doNotContinueWithRequestProcessing(HttpServletRequest httpRequest) {
        httpRequest.setAttribute(REQUEST_ATTR_DO_NOT_CONTINUE, "");
    }

    private boolean canRequestProcessingContinue(HttpServletRequest httpRequest) {
        return httpRequest.getAttribute(REQUEST_ATTR_DO_NOT_CONTINUE) == null;
    }
}

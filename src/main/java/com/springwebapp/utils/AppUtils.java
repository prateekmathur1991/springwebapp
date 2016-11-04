package com.springwebapp.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Contains high level static methods useful throughout the application
 */
public class AppUtils {

    public static String getHostName(HttpServletRequest httpServletRequest) {
        try {
            String scheme = httpServletRequest.getScheme();
            String serverName = httpServletRequest.getServerName();
            Integer port = httpServletRequest.getServerPort();

            if (serverName.equals("localhost")) {
                return scheme + "://" + serverName + ":" + port;
            } else {
                return scheme + "://" + serverName;
            }
        } catch (Exception e) {
            System.err.println("Exception in AppUtils.getHostName: " + e.getLocalizedMessage());
            return null;
        }
    }
}

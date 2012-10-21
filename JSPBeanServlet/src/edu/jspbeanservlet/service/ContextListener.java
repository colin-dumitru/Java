package edu.jspbeanservlet.service;

import edu.jspbeanservlet.service.authentication.AuthenticationService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            AuthenticationService.instance().init();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            AuthenticationService.instance().destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

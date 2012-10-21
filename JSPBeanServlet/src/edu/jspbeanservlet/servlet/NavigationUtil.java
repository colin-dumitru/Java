package edu.jspbeanservlet.servlet;

import edu.jspbeanservlet.service.authentication.UserInformation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 10/16/12
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class NavigationUtil {
    public static void redirectToError(String message, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute("error", message);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }

    public static UserInformation checkLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UserInformation userInformation = (UserInformation) request.getSession().getAttribute("userInfo");

        if (userInformation == null) {
            redirectToError("You must be logged in to continue.", request, response);
        }
        return userInformation;
    }
}

package edu.jspbeanservlet.servlet;

import edu.jspbeanservlet.service.authentication.AuthenticationService;
import edu.jspbeanservlet.service.authentication.UserInformation;
import nl.captcha.Captcha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @java.lang.Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String enteredCaptcha = request.getParameter("enteredCaptcha");
        Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);

        if (!captcha.isCorrect(enteredCaptcha)) {
            NavigationUtil.redirectToError("Wrong captcha entered", request, response);
            return;
        }

        UserInformation userInformation = AuthenticationService.instance().registerUser(userName, password);

        if (userInformation == null) {
            NavigationUtil.redirectToError("User already exists", request, response);
        } else {
            request.getRequestDispatcher("login").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}

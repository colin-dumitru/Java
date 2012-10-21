package edu.jspbeanservlet.servlet;

import edu.jspbeanservlet.service.authentication.AuthenticationService;
import edu.jspbeanservlet.service.authentication.UserInformation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        Boolean rememberMe = Objects.equals("on", request.getParameter("rememberMe"));

        UserInformation userInformation = AuthenticationService.instance().authenticateUser(userName, password);
        request.getSession().setAttribute("userInfo", userInformation);

        if (userInformation == null) {
            NavigationUtil.redirectToError("Wrong username or password", request, response);
            return;
        }

        if (rememberMe) {
            addRememberMeCookie(response, userInformation);
        } else {
            removeRememberMeCookie(request);
        }
        request.getRequestDispatcher("compute").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie rememberCookie = getRememberCookie(request.getCookies());

        if (rememberCookie != null) {
            request.getSession().setAttribute("userInfo",
                    AuthenticationService.instance().getUserInfo(rememberCookie.getValue()));
            request.getRequestDispatcher("compute").forward(request, response);
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private Cookie getRememberCookie(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("rememberMe")) {
                return cookie;
            }
        }
        return null;
    }

    private void removeRememberMeCookie(HttpServletRequest request) {
        Cookie rememberMeCookie = getRememberCookie(request.getCookies());
        if (rememberMeCookie != null) {
            rememberMeCookie.setMaxAge(-1);
        }
    }

    private void addRememberMeCookie(HttpServletResponse response, UserInformation userInformation) {
        response.addCookie(new Cookie("rememberMe", userInformation.getUserName()));
    }
}

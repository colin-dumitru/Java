package edu.jspbeanservlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 * 10/21/12 - 11:04 AM
 */
@WebServlet(urlPatterns = "/result")
public class ResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (NavigationUtil.checkLogin(request, response) == null) {
            return;
        }
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}

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
 * 10/16/12 - 6:42 PM
 */
@WebServlet(urlPatterns = "/compute")
public class ComputeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (NavigationUtil.checkLogin(request, response) == null) {
            return;
        }
        request.getRequestDispatcher("compute.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (NavigationUtil.checkLogin(request, response) == null) {
            return;
        }
        request.getRequestDispatcher("compute.jsp").forward(request, response);
    }
}

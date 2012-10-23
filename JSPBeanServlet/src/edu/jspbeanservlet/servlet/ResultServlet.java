package edu.jspbeanservlet.servlet;

import edu.jspbeanservlet.model.ComputeModel;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (NavigationUtil.checkLogin(request, response) == null) {
            return;
        }
        ComputeModel model = (ComputeModel) request.getSession().getAttribute("computeModel");
        request.setAttribute("result", model.getResult());
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}

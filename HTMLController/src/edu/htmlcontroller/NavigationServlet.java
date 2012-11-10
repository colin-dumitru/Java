package edu.htmlcontroller;

import edu.htmlcontroller.service.NavigationService;

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
@WebServlet(name = "navigationServlet", urlPatterns = "/page/*")
public class NavigationServlet extends HttpServlet {
    private NavigationService navigationService;

    @Override
    public void init() throws ServletException {
        try {
            navigationService = NavigationService.fromFile("/pages.txt", getServletContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toPage = navigationService.getToPage(request);
        request.getRequestDispatcher(String.format("/%s", toPage)).forward(request, response);
    }
}

package edu.euler;

import edu.euler.service.EulerNumberService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
public class IndexServlet extends javax.servlet.http.HttpServlet {
    private EulerNumberService eulerNumberService;

    public void init(ServletConfig config) throws ServletException {
        eulerNumberService = new EulerNumberService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        Long decimals = extractDecimals(request);
        BigDecimal eulerNumber = eulerNumberService.getEulerNumber(decimals);

        if (eulerNumber == null) {
            redirectToError(request, response);
        } else {
            redirectToResponse(eulerNumber.setScale(decimals.intValue(), RoundingMode.HALF_UP), request, response);
        }
    }

    private void redirectToResponse(BigDecimal number, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute("number", number.toString());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void redirectToError(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute("error", "Invalid number specified");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private Long extractDecimals(HttpServletRequest request) {
        try {
            return Long.parseLong(request.getParameter("decimals"));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

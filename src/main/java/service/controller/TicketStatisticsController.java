package service.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.service.StatisticsService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/statistics")
public class TicketStatisticsController extends HttpServlet {

    private final StatisticsService statisticsService = new StatisticsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("statistics", statisticsService.getStatistics());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/statistics.jsp").forward(request, response);
    }
}

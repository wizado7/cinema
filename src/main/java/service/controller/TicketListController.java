package service.controller;


import dataaccess.entity.TicketGroupedByOrder;

import dataaccess.repository.TicketsRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.service.TicketsService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ticketList")
public class TicketListController extends HttpServlet {
    private final TicketsService ticketService = new TicketsService(new TicketsRepository());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            pageNumber = Integer.parseInt(pageParam);
        }

        String movieTitle = request.getParameter("movieTitle");
        String day = request.getParameter("day");
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        String time = request.getParameter("time");
        String seat = request.getParameter("seat");

        List<TicketGroupedByOrder> ticketGroups = null;
        long totalTickets = 0;
        try {
            ticketGroups = ticketService.getFullTicketInfo(pageNumber, movieTitle, day, month, year, time, seat);
            totalTickets = ticketService.getTotalTicketCount(movieTitle, day, month, year, time, seat);
            int totalPages = (int) Math.ceil((double) totalTickets / 7);
            request.setAttribute("ticketGroups", ticketGroups);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", pageNumber);
            request.getRequestDispatcher("/ticketList.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}




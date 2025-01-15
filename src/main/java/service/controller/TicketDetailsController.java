package service.controller;

import dataaccess.entity.TicketGroupedByOrder;
import dataaccess.entity.Tickets;
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

@WebServlet("/ticketDetails")
public class TicketDetailsController extends HttpServlet {
    private final TicketsService ticketsService;

    public TicketDetailsController() {
        this.ticketsService = new TicketsService(new TicketsRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        if (orderId != null) {
            try {

                List<TicketGroupedByOrder> tickets = ticketsService.getTicketsByOrderId(orderId);
                request.setAttribute("tickets", tickets);
                request.setAttribute("orderId", orderId);
                request.getRequestDispatcher("/ticketDetails.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Error retrieving tickets by order ID", e);
            }
        } else {
            response.sendRedirect("/ticketList");
        }
    }


}


package service.controller;


import dataaccess.repository.TicketsRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.service.TicketsService;

import java.io.IOException;

@WebServlet("/deleteTicket")
public class TicketDeleteController extends HttpServlet {
    private final TicketsService ticketsService;

    public TicketDeleteController() {
        this.ticketsService = new TicketsService(new TicketsRepository());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String movieId = request.getParameter("movieId");
        if (movieId != null) {

            ticketsService.delete(Integer.parseInt(movieId));
            response.sendRedirect("/movies");
        } else {
            response.sendRedirect("/movies");
        }
    }
}
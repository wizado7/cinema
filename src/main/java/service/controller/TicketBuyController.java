package service.controller;

import dataaccess.entity.*;
import dataaccess.repository.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/buyTicket")
public class TicketBuyController extends HttpServlet {

    private final CustomersRepository customersRepository = new CustomersRepository();
    private final TicketsRepository ticketsRepository = new TicketsRepository();
    private final OrdersRepository ordersRepository = new OrdersRepository();
    private final ScreeningsRepository screeningsRepository = new ScreeningsRepository();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String screeningIdParam = request.getParameter("screeningId");

        if (screeningIdParam == null || screeningIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Screening ID is required");
            return;
        }

        int screeningId = Integer.parseInt(screeningIdParam);

        Screenings screening = null;
        try {
            screening = screeningsRepository.findById(screeningId);
            request.setAttribute("screening", screening);
            List<Integer> bookedSeats = ticketsRepository.getBookedSeatsForScreening(screeningId);
            request.setAttribute("bookedSeats", bookedSeats);
            request.getRequestDispatcher("/buyTicket.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String screeningIdParam = request.getParameter("screeningId");
            String selectedSeatsParam = request.getParameter("selectedSeats");

            StringBuilder missingFields = new StringBuilder();

            if (screeningIdParam == null || screeningIdParam.isEmpty()) {
                missingFields.append("Screening ID is missing. ");
            }

            if (name == null || name.trim().isEmpty()) {
                missingFields.append("Name is missing. ");
            }

            if (selectedSeatsParam == null || selectedSeatsParam.isEmpty()) {
                missingFields.append("Selected seats are missing. ");
            }

            if (missingFields.length() > 0) {
                System.out.println("Required fields are missing: " + missingFields.toString());
                request.setAttribute("errorMessage", "The following fields are required: " + missingFields.toString());
                request.getRequestDispatcher("/buyTicket.jsp").forward(request, response);
                return;
            }

            int screeningId = Integer.parseInt(screeningIdParam);

            String[] selectedSeatsArray = selectedSeatsParam.split(",");
            List<Integer> selectedSeats = new ArrayList<>();
            for (String seat : selectedSeatsArray) {
                selectedSeats.add(Integer.parseInt(seat));
            }

            Customers customer = customersRepository.findByName(name);
            if (customer == null) {
                customer = new Customers();
                customer.setName(name);
                customer.setEmail(email != null ? email.trim() : null);
                customer.setPhone(phone != null ? phone.trim() : null);
                customersRepository.insert(customer);
            }

            Orders order = new Orders();
            order.setCustomerId(customer.getId());
            order.setOrderDate(LocalDateTime.now());

            double totalPrice = selectedSeats.size() * 10.0;
            order.setTotalPrice(totalPrice);
            ordersRepository.insert(order);

            int orderId = order.getId();

            for (int seatNumber : selectedSeats) {
                Tickets ticket = new Tickets();
                ticket.setScreeningId(screeningId);
                ticket.setOrderId(orderId);
                ticket.setSeatNumber(seatNumber);
                ticket.setPrice(BigDecimal.valueOf(10.0));
                ticket.setSaleTime(LocalDateTime.now());
                ticketsRepository.insert(ticket);
            }


            response.sendRedirect("/ticketList");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/buyTicket.jsp").forward(request, response);
        }
    }


}

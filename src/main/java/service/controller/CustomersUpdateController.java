package service.controller;

import dataaccess.entity.Customers;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.service.CustomersService;

import java.io.IOException;

@WebServlet("/customerUpdate")
public class CustomersUpdateController extends HttpServlet {
    private final CustomersService customersService = new CustomersService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerIdParam = request.getParameter("customerId");

        if (customerIdParam == null || customerIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid customerId parameter.");
            return;
        }

        try {
            int customerId = Integer.parseInt(customerIdParam);
            Customers customer = customersService.findById(customerId);

            if (customer == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer not found.");
                return;
            }

            request.setAttribute("customer", customer);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/editCustomer.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid customerId format.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching customer details.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int customerId = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            Customers customer = new Customers();
            customer.setId(customerId);
            customer.setName(name);
            customer.setEmail(email);
            customer.setPhone(phone);
            customersService.update(customer, customerId);

            response.sendRedirect("/customers");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating customer.");
        }
    }
}

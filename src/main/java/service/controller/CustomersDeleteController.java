package service.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.service.CustomersService;

import java.io.IOException;

@WebServlet("/deleteCustomer")
public class CustomersDeleteController extends HttpServlet {
    private final CustomersService customersService = new CustomersService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerId = request.getParameter("customerId");
        if (customerId != null) {

            customersService.delete(Integer.parseInt(customerId));
            response.sendRedirect("/customers");
        } else {
            response.sendRedirect("/customers");
        }
    }
}

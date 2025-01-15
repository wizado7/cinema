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

@WebServlet("/customersAdd")
public class CustomersAddController extends HttpServlet {
    private final CustomersService customersService = new CustomersService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customersAdd.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Customers newCustomer = new Customers();
        newCustomer.setName(name);
        newCustomer.setEmail(email);
        newCustomer.setPhone(phone);
        customersService.insert(newCustomer);
        response.sendRedirect("/customers");
    }
}

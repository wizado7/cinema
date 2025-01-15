package service.controller;


import dataaccess.entity.Customers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.service.CustomersService;

import java.io.IOException;

@WebServlet("/customers")
public class CustomersController extends HttpServlet {
    private final CustomersService customerService = new CustomersService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("customers", customerService.findAll());
        req.getRequestDispatcher("/customers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        Customers customer = new Customers();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customerService.insert(customer);

        resp.sendRedirect("/customers");
    }
}

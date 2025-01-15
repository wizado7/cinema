package service.controller;

import dataaccess.entity.Customers;
import dataaccess.entity.Movies;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.service.CustomersService;
import service.service.MoviesService;

import java.io.IOException;

@WebServlet("/movieAdd")
public class MovieAddController extends HttpServlet {
    private final MoviesService moviesService = new MoviesService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/moviesAdd.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        int duration = Integer.parseInt(request.getParameter("duration"));
        String genre = request.getParameter("genre");
        String rating = request.getParameter("rating");

        Movies movie = new Movies();
        movie.setTitle(title);
        movie.setDuration(duration);
        movie.setGenre(genre);
        movie.setRating(rating);
        moviesService.insert(movie);
        response.sendRedirect("/movies");
    }
}

package service.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.service.MoviesService;

import java.io.IOException;

@WebServlet("/deleteMovie")
public class MovieDeleteController extends HttpServlet {
    private final MoviesService moviesService = new MoviesService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String movieId = request.getParameter("movieId");
        if (movieId != null) {

            moviesService.delete(Integer.parseInt(movieId));
            response.sendRedirect("/movies");
        } else {
            response.sendRedirect("/movies");
        }
    }
}

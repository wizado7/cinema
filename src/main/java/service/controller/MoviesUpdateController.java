package service.controller;

import dataaccess.entity.Movies;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.service.MoviesService;

import java.io.IOException;

@WebServlet("/moviesUpdate")
public class MoviesUpdateController extends HttpServlet {
    private final MoviesService moviesService = new MoviesService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String movieIdParam = request.getParameter("movieId");
        int movieId = Integer.parseInt(movieIdParam);

        try {
            Movies movie = moviesService.findById(movieId);
            request.setAttribute("movie", movie);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/editMovie.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching movie details.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        int duration = Integer.parseInt(request.getParameter("duration"));
        String genre = request.getParameter("genre");
        String rating = request.getParameter("rating");

        try {
            Movies movie = new Movies();
            movie.setId(id);
            movie.setTitle(title);
            movie.setDuration(duration);
            movie.setGenre(genre);
            movie.setRating(rating);
            moviesService.update(movie, id);
            response.sendRedirect("/movies");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating movie.");
        }
    }
}

package service.controller;

import dataaccess.entity.Movies;
import dataaccess.entity.Screenings;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.service.MoviesService;
import service.service.ScreeningsService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/movieCard")
public class MovieCardController extends HttpServlet {
    private final MoviesService moviesService = new MoviesService();
    private final ScreeningsService screeningsService = new ScreeningsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int movieId = Integer.parseInt(request.getParameter("movieId"));
        Movies movie = moviesService.findById(movieId);
        List<Screenings> screenings = null;
        try {
            screenings = screeningsService.findByMovieId(movieId);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(screenings);
        request.setAttribute("movie", movie);
        request.setAttribute("screenings", screenings);
        request.getRequestDispatcher("movieCard.jsp").forward(request, response);
    }
}

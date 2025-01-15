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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/movies")
public class MoviesController extends HttpServlet {
    private final MoviesService moviesService = new MoviesService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                pageNumber = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                pageNumber = 1;
            }
        }

        List<Movies> movies = moviesService.getMoviesByPage(pageNumber);
        int totalMovies = moviesService.getTotalMoviesCount();
        System.out.println(movies);

        int pageSize = 10;
        int totalPages = (int) Math.ceil((double) totalMovies / pageSize);


        System.out.println("Total movies: " + totalMovies);
        System.out.println("Total pages: " + totalPages);


        request.setAttribute("movies", movies);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/movies.jsp");
        dispatcher.forward(request, response);

    }
}



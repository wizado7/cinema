package service.service;

import dataaccess.entity.Movies;
import dataaccess.repository.MoviesRepository;

import java.sql.SQLException;
import java.util.List;

public class MoviesService {

    private final MoviesRepository moviesRepository = new MoviesRepository();

    public void insert(Movies movie) {
        moviesRepository.insert(movie);
    }

    public void update(Movies movie, Integer id) {
        moviesRepository.update(movie, id);
    }

    public void delete(Integer id) {
        moviesRepository.delete(id);
    }

    public List<Movies> findAll() {
        return moviesRepository.findAll();
    }

    public Movies findById(Integer id) {
        return moviesRepository.findById(id);
    }


    public int getTotalMoviesCount() {
        try {
            return moviesRepository.getTotalMoviesCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Movies> getMoviesByPage(int pageNumber) {
        try {
            return moviesRepository.getMoviesByPage(pageNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package dataaccess.repository;

import dataaccess.crudoperation.CrudOperation;
import dataaccess.crudoperation.connectionmanager.ConnectionManager;
import dataaccess.entity.Movies;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoviesRepository {
    private static final CrudOperation<Movies, Integer> operation =
            new CrudOperation<>(Movies.class, new ConnectionManager());

    public void insert(Movies movie) {
        try {
            operation.insert(movie);
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException("Error inserting movie: " + e.getMessage(), e);
        }
    }

    public void delete(Integer id) {
        try {
            operation.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting movie with ID " + id + ": " + e.getMessage(), e);
        }
    }

    public void update(Movies movie, Integer id) {
        try {
            operation.update(movie, id);
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException("Error updating movie with ID " + id + ": " + e.getMessage(), e);
        }
    }

    public Movies findById(Integer id) {
        try {
            return operation.findById(id).orElse(null);
        } catch (SQLException | ReflectiveOperationException e) {
            throw new RuntimeException("Error finding movie with ID " + id + ": " + e.getMessage(), e);
        }
    }

    public List<Movies> findAll() {
        try {
            return operation.findAll();
        } catch (SQLException | ReflectiveOperationException e) {
            throw new RuntimeException("Error finding all movies: " + e.getMessage(), e);
        }
    }

    public List<Movies> getMoviesByPage(int pageNumber) throws SQLException {
        int pageSize = 10;
        int offset = (pageNumber - 1) * pageSize;

        String sql = "SELECT * FROM movies LIMIT ? OFFSET ?";
        return operation.executeCustomQuery(sql, ps -> {
            try {
                ps.setInt(1, pageSize);
                ps.setInt(2, offset);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, rs -> {
            Movies movie = new Movies();
            try {
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDuration(rs.getInt("duration"));
                movie.setGenre(rs.getString("genre"));
                movie.setRating(rs.getString("rating"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return movie;
        });
    }



    public int getTotalMoviesCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM movies";

        List<Integer> counts = operation.executeCustomQuery(sql,
                ps -> {},
                rs -> {
                    try {
                        return rs.getInt(1);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return counts.isEmpty() ? 0 : counts.get(0);
    }



}

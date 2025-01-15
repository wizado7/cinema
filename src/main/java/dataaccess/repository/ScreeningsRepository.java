package dataaccess.repository;

import dataaccess.crudoperation.CrudOperation;
import dataaccess.crudoperation.connectionmanager.ConnectionManager;
import dataaccess.entity.Screenings;

import java.sql.SQLException;
import java.util.List;

public class ScreeningsRepository {

    private static final CrudOperation<Screenings, Integer> operation =
            new CrudOperation<>(Screenings.class, new ConnectionManager());

    public void insert(Screenings screening) {
        try {
            operation.insert(screening);
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Screenings screening, Integer id) {
        try {
            operation.update(screening, id);
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer id) {
        try {
            operation.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Screenings findById(int screeningId) throws SQLException {
        String sql = "SELECT s.*, h.name AS hallName " +
                "FROM screenings s " +
                "JOIN halls h ON s.hall_id = h.id " +
                "WHERE s.id = ?";

        return operation.executeCustomQuery(sql, ps -> {
            try {
                ps.setInt(1, screeningId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, rs -> {
            Screenings screening = new Screenings();
            try {
                screening.setId(rs.getInt("id"));
                screening.setMovieId(rs.getInt("movie_id"));
                screening.setHallName(rs.getString("hallName"));
                screening.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
                screening.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return screening;
        }).stream().findFirst().orElse(null);
    }


    public List<Screenings> findAll() {
        try {
            return operation.findAll();
        } catch (SQLException | ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Screenings> findByMovieId(int movieId) throws SQLException {
        String sql = "SELECT s.*, h.name AS hallName, s.start_time, s.end_time " +
                "FROM screenings s " +
                "JOIN halls h ON s.hall_id = h.id " +
                "WHERE s.movie_id = ?";

        return operation.executeCustomQuery(sql, ps -> {
            try {
                ps.setInt(1, movieId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, rs -> {
            Screenings screening = new Screenings();
            try {
                screening.setId(rs.getInt("id"));
                screening.setMovieId(rs.getInt("movie_id"));
                screening.setHallName(rs.getString("hallName"));
                screening.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
                screening.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return screening;
        });
    }


}

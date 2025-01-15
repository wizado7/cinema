package dataaccess.repository;

import dataaccess.crudoperation.CrudOperation;
import dataaccess.crudoperation.connectionmanager.ConnectionManager;
import dataaccess.entity.Customers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StatisticsRepository {
    private static final CrudOperation<Customers, Integer> operation =
            new CrudOperation<>(Customers.class, new ConnectionManager());
    public Map<String, Object> getStatistics() throws SQLException {
        String totalTicketsQuery = "SELECT COUNT(*) AS total_tickets FROM tickets";
        String totalRevenueQuery = "SELECT SUM(price) AS total_revenue FROM tickets";
        String mostPopularMovieQuery =
                "SELECT m.title AS most_popular_movie " +
                        "FROM tickets t " +
                        "JOIN screenings s ON t.screening_id = s.id " +
                        "JOIN movies m ON s.movie_id = m.id " +
                        "GROUP BY m.title " +
                        "ORDER BY COUNT(*) DESC " +
                        "LIMIT 1";

        Map<String, Object> statistics = new HashMap<>();
        statistics.put("totalTickets", operation.executeScalar(totalTicketsQuery, ps -> {}));
        statistics.put("totalRevenue", operation.executeScalar(totalRevenueQuery, ps -> {}));
        statistics.put("mostPopularMovie", operation.executeScalar(mostPopularMovieQuery, ps -> {}));

        return statistics;
    }

}

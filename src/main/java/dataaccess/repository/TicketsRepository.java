package dataaccess.repository;

import dataaccess.crudoperation.CrudOperation;
import dataaccess.crudoperation.connectionmanager.ConnectionManager;
import dataaccess.entity.Customers;
import dataaccess.entity.TicketGroupedByOrder;
import dataaccess.entity.Tickets;
import jakarta.servlet.ServletException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketsRepository {
    private static final CrudOperation<Tickets, Integer> operation =
            new CrudOperation<>(Tickets.class, new ConnectionManager());

    public void insert(Tickets ticket) {
        try {
            operation.insert(ticket);
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

    public void update(Tickets ticket, Integer id) {
        try {
            operation.update(ticket, id);
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Tickets findByID(Integer id) {
        try {
            return operation.findById(id).get();
        } catch (SQLException | ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tickets> findAll() {
        try {
            return operation.findAll();
        } catch (SQLException | ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tickets> findByCustomerId(int customerId) {

        return null;
    }

    public List<Integer> getBookedSeatsForScreening(int screeningId) {
        String sql = "SELECT seat_number FROM tickets WHERE screening_id = ?";
        try {
            return operation.executeCustomQuery(
                    sql,
                    ps -> {
                        try {
                            ps.setInt(1, screeningId);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    rs -> {
                        try {
                            return rs.getInt("seat_number");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching booked seats for screening", e);
        }
    }



    public List<TicketGroupedByOrder> getFullTicketInfo(int pageNumber, String movieTitle, String day, String month, String year, String time, String seat) throws SQLException {
        int pageSize = 7;
        int offset = (pageNumber - 1) * pageSize;

        StringBuilder sql = new StringBuilder(
                "SELECT t.order_id, m.title AS movie_title, s.start_time AS screening_start_time, " +
                        "s.end_time AS screening_end_time, GROUP_CONCAT(t.seat_number ORDER BY t.seat_number) AS seat_numbers " +
                        "FROM tickets t " +
                        "JOIN screenings s ON t.screening_id = s.id " +
                        "JOIN movies m ON s.movie_id = m.id "
        );

        List<String> conditions = new ArrayList<>();

        // Добавляем условия для фильтрации
        if (movieTitle != null && !movieTitle.isEmpty()) {
            conditions.add("m.title LIKE ?");
        }
        if (day != null && !day.isEmpty()) {
            conditions.add("DAY(s.start_time) = ?");
        }
        if (month != null && !month.isEmpty()) {
            conditions.add("MONTH(s.start_time) = ?");
        }
        if (year != null && !year.isEmpty()) {
            conditions.add("YEAR(s.start_time) = ?");
        }
        if (time != null && !time.isEmpty()) {
            conditions.add("TIME(s.start_time) = ?");
        }
        if (seat != null && !seat.isEmpty()) {
            conditions.add("t.seat_number LIKE ?");
        }

        // Добавляем условия в запрос
        if (!conditions.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        sql.append(" GROUP BY t.order_id, m.title, s.start_time, s.end_time ");
        sql.append("LIMIT ? OFFSET ?");

        return operation.executeCustomQuery(sql.toString(), ps -> {
            int index = 1;
            if (movieTitle != null && !movieTitle.isEmpty()) {
                try {
                    ps.setString(index++, "%" + movieTitle + "%");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (day != null && !day.isEmpty()) {
                try {
                    ps.setInt(index++, Integer.parseInt(day));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (month != null && !month.isEmpty()) {
                try {
                    ps.setInt(index++, Integer.parseInt(month));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (year != null && !year.isEmpty()) {
                try {
                    ps.setInt(index++, Integer.parseInt(year));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (time != null && !time.isEmpty()) {
                try {
                    ps.setString(index++, time);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (seat != null && !seat.isEmpty()) {
                try {
                    ps.setString(index++, "%" + seat + "%");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                ps.setInt(index++, pageSize);
                ps.setInt(index, offset);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, rs -> {
            TicketGroupedByOrder ticketGroup = new TicketGroupedByOrder();
            try {
                ticketGroup.setOrderId(rs.getInt("order_id"));
                ticketGroup.setSeatNumbers(rs.getString("seat_numbers"));
                ticketGroup.setMovieTitle(rs.getString("movie_title"));
                ticketGroup.setScreeningStartTime(rs.getTimestamp("screening_start_time").toLocalDateTime());
                ticketGroup.setScreeningEndTime(rs.getTimestamp("screening_end_time").toLocalDateTime());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return ticketGroup;
        });
    }


    public long getTotalTicketCount(String movieTitle, String day, String month, String year, String time, String seat) throws SQLException {
        StringBuilder countQuery = new StringBuilder(
                "SELECT COUNT(DISTINCT t.order_id) AS total_count FROM tickets t " +
                        "JOIN screenings s ON t.screening_id = s.id " +
                        "JOIN movies m ON s.movie_id = m.id "
        );

        List<String> conditions = new ArrayList<>();

        if (movieTitle != null && !movieTitle.isEmpty()) {
            conditions.add("m.title LIKE ?");
        }
        if (day != null && !day.isEmpty()) {
            conditions.add("DAY(s.start_time) = ?");
        }
        if (month != null && !month.isEmpty()) {
            conditions.add("MONTH(s.start_time) = ?");
        }
        if (year != null && !year.isEmpty()) {
            conditions.add("YEAR(s.start_time) = ?");
        }
        if (time != null && !time.isEmpty()) {
            conditions.add("TIME(s.start_time) = ?");
        }
        if (seat != null && !seat.isEmpty()) {
            conditions.add("t.seat_number LIKE ?");
        }

        if (!conditions.isEmpty()) {
            countQuery.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        Object result = operation.executeScalar(countQuery.toString(), ps -> {
            int index = 1;
            if (movieTitle != null && !movieTitle.isEmpty()) {
                try {
                    ps.setString(index++, "%" + movieTitle + "%");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (day != null && !day.isEmpty()) {
                try {
                    ps.setInt(index++, Integer.parseInt(day));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (month != null && !month.isEmpty()) {
                try {
                    ps.setInt(index++, Integer.parseInt(month));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (year != null && !year.isEmpty()) {
                try {
                    ps.setInt(index++, Integer.parseInt(year));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (time != null && !time.isEmpty()) {
                try {
                    ps.setString(index++, time);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (seat != null && !seat.isEmpty()) {
                try {
                    ps.setString(index++, "%" + seat + "%");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        if (result instanceof Long) {
            return (Long) result;
        } else {
            return 0L;
        }
    }


    public List<TicketGroupedByOrder> getTicketsByOrderId(String orderId) throws SQLException {
        String sql = """
        SELECT 
            t.id AS ticket_id, 
            t.order_id, 
            m.id AS movie_id, 
            m.title AS movie_title, 
            s.start_time AS screening_start_time, 
            s.end_time AS screening_end_time, 
            GROUP_CONCAT(t.seat_number ORDER BY t.seat_number) AS seat_numbers 
        FROM tickets t
        JOIN screenings s ON t.screening_id = s.id
        JOIN movies m ON s.movie_id = m.id
        WHERE t.order_id = ?
        GROUP BY t.id, t.order_id, m.id, m.title, s.start_time, s.end_time
    """;

        return operation.executeCustomQuery(sql, ps -> {
            try {
                ps.setString(1, orderId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, rs -> {
            TicketGroupedByOrder ticketGroup = new TicketGroupedByOrder();
            try {
                ticketGroup.setTicketId(rs.getInt("ticket_id"));
                ticketGroup.setOrderId(rs.getInt("order_id"));
                ticketGroup.setMovieId(rs.getInt("movie_id"));
                ticketGroup.setMovieTitle(rs.getString("movie_title"));
                ticketGroup.setScreeningStartTime(rs.getTimestamp("screening_start_time").toLocalDateTime());
                ticketGroup.setScreeningEndTime(rs.getTimestamp("screening_end_time").toLocalDateTime());
                ticketGroup.setSeatNumbers(rs.getString("seat_numbers"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return ticketGroup;
        });
    }

}


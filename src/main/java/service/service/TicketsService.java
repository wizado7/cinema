package service.service;

import dataaccess.entity.TicketGroupedByOrder;
import dataaccess.repository.TicketsRepository;

import java.sql.SQLException;
import java.util.List;

public class TicketsService {
    private final TicketsRepository ticketsRepository;

    public TicketsService(TicketsRepository ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }

    public void delete(Integer id) {
        ticketsRepository.delete(id);
    }
    public long getTotalTicketCount(String movieTitle, String day, String month, String year, String time, String seat) throws SQLException {
        return ticketsRepository.getTotalTicketCount(movieTitle, day, month, year, time, seat);
    }

    public List<TicketGroupedByOrder> getFullTicketInfo(int pageNumber, String movieTitle, String day, String month, String year, String time, String seat) throws SQLException {
        return ticketsRepository.getFullTicketInfo(pageNumber, movieTitle, day, month, year, time, seat);
    }

    public List<TicketGroupedByOrder> getTicketsByOrderId(String orderId) throws SQLException {
        return ticketsRepository.getTicketsByOrderId(orderId);
    }
}

package service.service;


import dataaccess.repository.StatisticsRepository;

import java.sql.SQLException;
import java.util.Map;

public class StatisticsService {
    private final StatisticsRepository repository = new StatisticsRepository();

    public Map<String, Object> getStatistics() throws SQLException {
        return repository.getStatistics();
    }
}

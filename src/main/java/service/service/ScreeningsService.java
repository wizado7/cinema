package service.service;

import dataaccess.entity.Screenings;
import dataaccess.repository.ScreeningsRepository;

import java.sql.SQLException;
import java.util.List;

public class ScreeningsService {

    private final ScreeningsRepository screeningsRepository = new ScreeningsRepository();


    public void insert(Screenings screening) {
        screeningsRepository.insert(screening);
    }


    public void update(Screenings screening, Integer id) {
        screeningsRepository.update(screening, id);
    }


    public void delete(Integer id) {
        screeningsRepository.delete(id);
    }


    public Screenings findById(Integer id) throws SQLException {
        return screeningsRepository.findById(id);
    }


    public List<Screenings> findAll() {
        return screeningsRepository.findAll();
    }


    public List<Screenings> findByMovieId(Integer movieId) throws ReflectiveOperationException, SQLException {
        return screeningsRepository.findByMovieId(movieId);
    }
}

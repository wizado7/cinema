package dataaccess.repository;

import dataaccess.crudoperation.CrudOperation;
import dataaccess.crudoperation.connectionmanager.ConnectionManager;
import dataaccess.entity.Halls;

import java.sql.SQLException;
import java.util.List;

public class HallsRepository {
    private static final CrudOperation<Halls, Integer> operation =
            new CrudOperation<>(Halls.class, new ConnectionManager());

    public void insert(Halls hall) {
        try {
            operation.insert(hall);
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

    public void update(Halls hall, Integer id) {
        try {
            operation.update(hall, id);
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Halls findById(Integer id) {
        try {
            return operation.findById(id).get();
        } catch (SQLException | ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Halls> findAll() {
        try {
            return operation.findAll();
        } catch (SQLException | ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}

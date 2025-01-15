package dataaccess.repository;

import dataaccess.crudoperation.CrudOperation;
import dataaccess.crudoperation.connectionmanager.ConnectionManager;
import dataaccess.entity.Orders;

import java.sql.SQLException;
import java.util.List;

public class OrdersRepository {
    private static final CrudOperation<Orders, Integer> operation =
            new CrudOperation<>(Orders.class, new ConnectionManager());

    public void insert(Orders order) {
        try {
            operation.insert(order);
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

    public void update(Orders order, Integer id) {
        try {
            operation.update(order, id);
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Orders findById(Integer id) {
        try {
            return operation.findById(id).get();
        } catch (SQLException | ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Orders> findAll() {
        try {
            return operation.findAll();
        } catch (SQLException | ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}

package dataaccess.repository;

import dataaccess.crudoperation.CrudOperation;
import dataaccess.crudoperation.connectionmanager.ConnectionManager;
import dataaccess.entity.Customers;

import java.sql.SQLException;
import java.util.List;

public class CustomersRepository {
    private static final CrudOperation<Customers, Integer> operation =
            new CrudOperation<>(Customers.class, new ConnectionManager());

    public void insert(Customers customers) {
        try {
            operation.insert(customers);
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

    public void update(Customers customers, Integer id) {
        try {
            operation.update(customers, id);
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Customers findById(Integer id) {
        try {
            return operation.findById(id).get();
        } catch (SQLException | ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customers> findAll() {
        try {
            return operation.findAll();
        } catch (SQLException | ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public Customers findByName(String name) throws SQLException {
        String sql = "SELECT * FROM customers WHERE name = ?";
        return operation.executeCustomQuery(
                sql,
                ps -> {
                    try {
                        ps.setString(1, name);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                },
                rs -> {
                    Customers customer = new Customers();
                    try {
                        customer.setId(rs.getInt("id"));
                        customer.setName(rs.getString("name"));
                        customer.setEmail(rs.getString("email"));
                        customer.setPhone(rs.getString("phone"));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return customer;
                }
        ).stream().findFirst().orElse(null);
    }




}

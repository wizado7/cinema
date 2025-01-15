package dataaccess.crudoperation;

import dataaccess.crudoperation.connectionmanager.ConnectionManager;
import dataaccess.crudoperation.util.CrudUtil;
import dataaccess.crudoperation.util.SQLQuery;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class CrudOperation<T, ID> {
    private final Class<?> entityCLass;
    private final ConnectionManager connectionManager;

    public CrudOperation(Class<?> entityCLass, ConnectionManager connectionManager) {
        this.entityCLass = entityCLass;
        this.connectionManager = connectionManager;
    }

    public void insert(T entity) throws SQLException, IllegalAccessException {
        String tableName = CrudUtil.getTableName(entityCLass);
        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        for (Field field : entityCLass.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(entity);
            if (value != null) {
                columns.add(CrudUtil.getColumnName(field));
                values.add(value);
            }
        }

        String columnList = String.join(", ", columns);
        String placeholders = String.join(", ", columns.stream().map(c -> "?").toList());
        String sql = SQLQuery.SQL_INSERT(tableName, columnList, placeholders);

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            for (int i = 0; i < values.size(); i++) {
                preparedStatement.setObject(i + 1, values.get(i));
            }

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Field idField = entityCLass.getDeclaredField("id");
                    idField.setAccessible(true);
                    idField.set(entity, generatedKeys.getInt(1));
                }
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public Optional<T> findById(ID id) throws SQLException, ReflectiveOperationException {
        String tableName = CrudUtil.getTableName(entityCLass);
        String idColumn = CrudUtil.getIdColumn(entityCLass);
        String sql = SQLQuery.SQL_FIND_BY_ID(tableName, idColumn);

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setObject(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToEntity(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    public void update(T entity, ID id) throws SQLException, IllegalAccessException {
        String tableName = CrudUtil.getTableName(entityCLass);
        String idColumn = CrudUtil.getIdColumn(entityCLass);
        List<String> setClauses = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        for (Field field : entityCLass.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(entity);
            if (value != null) {
                setClauses.add(CrudUtil.getColumnName(field) + " = ?");
                values.add(value);
            }
        }

        String setClause = String.join(", ", setClauses);
        String sql = SQLQuery.SQL_UPDATE(tableName, setClause, idColumn);

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (int i = 0; i < values.size(); i++) {
                preparedStatement.setObject(i + 1, values.get(i));
            }
            preparedStatement.setObject(values.size() + 1, id);

            preparedStatement.executeUpdate();
        }
    }

    public void delete(ID id) throws SQLException {
        String tableName = CrudUtil.getTableName(entityCLass);
        String idColumn = CrudUtil.getIdColumn(entityCLass);
        String sql = SQLQuery.SQL_DELETE(tableName, idColumn);

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public List<T> findAll() throws SQLException, ReflectiveOperationException {
        List<T> entities = new ArrayList<>();
        String tableName = CrudUtil.getTableName(entityCLass);
        String sql = SQLQuery.SQL_FIND_ALL(tableName);

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
        }

        return entities;
    }

    public <R> List<R> executeCustomQuery(String query, Consumer<PreparedStatement> statementPrep, Function<ResultSet,
            R> resultMapper)
            throws SQLException {
        List<R> results = new ArrayList<>();
        Connection connection = null;

        try {
            connection = connectionManager.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                statementPrep.accept(preparedStatement);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        results.add(resultMapper.apply(resultSet));
                    }
                }
            }
        } finally {
            if (connection != null) {
                connectionManager.closeConnection(connection);
            }
        }
        return results;
    }


    private T mapResultSetToEntity(ResultSet resultSet) throws SQLException, ReflectiveOperationException {
        T entity = (T) entityCLass.getDeclaredConstructor().newInstance();
        for (Field field : entityCLass.getDeclaredFields()) {
            field.setAccessible(true);
            String columnName = CrudUtil.getColumnName(field);
            Object value = resultSet.getObject(columnName);
            field.set(entity, value);
        }
        return entity;
    }

    public Object executeScalar(String query, Consumer<PreparedStatement> statementPrep) throws SQLException {
        Connection connection = null;
        Object result = null;

        try {
            connection = connectionManager.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                statementPrep.accept(preparedStatement);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        result = resultSet.getObject(1);
                    }
                }
            }
        } finally {
            if (connection != null) {
                connectionManager.closeConnection(connection);
            }
        }

        return result;
    }

}

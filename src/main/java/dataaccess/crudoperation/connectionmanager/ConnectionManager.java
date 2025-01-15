package dataaccess.crudoperation.connectionmanager;

import dataaccess.crudoperation.config.ApplicationConfig;
import dataaccess.crudoperation.config.LoadConfig;
import dataaccess.crudoperation.exception.InitializeConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionManager {

    private static final ApplicationConfig applicationConfig = LoadConfig.load();
    private static final String URL = applicationConfig.getDatabaseConfig().getUrl();
    private static final String PASSWORD = applicationConfig.getDatabaseConfig().getPassword();
    private static final String USER_NAME = applicationConfig.getDatabaseConfig().getUsername();
    private static final String DRIVER = applicationConfig.getDatabaseConfig().getDriverClassName();

    private final BlockingQueue<Connection> connectionPool;

    public ConnectionManager() {
        this.connectionPool = new LinkedBlockingDeque<>(150);
        initializeConnectionPool(50);
    }

    private void initializeConnectionPool(int initialPoolSize) {
        try {

            Class.forName(DRIVER);

            for (int i = 0; i < initialPoolSize; i++) {
                Connection connection = DriverManager.getConnection(
                        URL,
                        USER_NAME,
                        PASSWORD
                );
                connectionPool.offer(connection);
            }
        } catch (SQLException e) {
            throw new InitializeConnectionException("Failed to initialize connection pool", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            return connectionPool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to acquire a connection from the pool", e);
        }
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connectionPool.put(connection);
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException("Failed to release connection back to the pool", e);
        }
    }
}

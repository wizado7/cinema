package dataaccess.crudoperation.config;

public class TestConfig {

    public static void main(String[] args) {
        ApplicationConfig config = LoadConfig.load();

        System.out.println("URL: " + config.getDatabaseConfig().getUrl());
        System.out.println("PASS: " + config.getDatabaseConfig().getPassword());
        System.out.println("USERNAME: " + config.getDatabaseConfig().getUsername());
        System.out.println("Driver: " + config.getDatabaseConfig().getDriverClassName());
    }
}

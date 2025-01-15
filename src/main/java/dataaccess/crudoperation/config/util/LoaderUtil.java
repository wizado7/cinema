package dataaccess.crudoperation.config.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import dataaccess.crudoperation.config.ApplicationConfig;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Properties;

public class LoaderUtil {

    public static final String CONFIG_FILE_PATH_JSON = "config/application-config.json";
    public static final String CONFIG_FILE_PATH_YAML = "config/application-config.yml";
    public static final String CONFIG_FILE_PATH_PROPERTIES = "config/application-config.properties";

    public static ApplicationConfig loadFromJson(ApplicationConfig config) {
        return abstractLoad(config, "JSON", CONFIG_FILE_PATH_JSON);
    }

    public static ApplicationConfig loadFromYaml(ApplicationConfig config) {
        return abstractLoad(config, "YAML", CONFIG_FILE_PATH_YAML);
    }

    public static ApplicationConfig loadFromProperties(ApplicationConfig config) {
        Properties properties = new Properties();

        try {
            URL propertiesFileUrl = LoaderUtil.class.getClassLoader().getResource(CONFIG_FILE_PATH_PROPERTIES);
            if (propertiesFileUrl != null) {
                properties.load(propertiesFileUrl.openStream());

                populateConfig(config, properties);
                config.setLoaded(true);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return config;
    }

    private static ApplicationConfig abstractLoad(ApplicationConfig config, String file, String filePath) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            URL jsonFileUrl = LoaderUtil.class.getClassLoader().getResource(filePath);
            if (jsonFileUrl != null) {
                config = mapper.readValue(new File(jsonFileUrl.getFile()), ApplicationConfig.class);
                config.setLoaded(true);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return config;
    }

    private static void populateConfig(Object configObject, Properties properties) {
        for (Field field : configObject.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            String propertyKey = configObject.getClass().getSimpleName().toLowerCase() + "." + field.getName();
            String propertyValue = properties.getProperty(propertyKey);

            try {
                if (propertyValue != null) {
                    setFieldValue(field, configObject, propertyValue);
                } else if (!field.getType().isPrimitive()) {
                    Object subConfig = field.getType().getDeclaredConstructor().newInstance();
                    populateConfig(subConfig, properties);
                    field.set(configObject, subConfig);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error setting field: " + field.getName(), e);
            }
        }
    }

    private static void setFieldValue(Field field, Object instance, String value) throws IllegalAccessException {
        if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
            field.set(instance, Integer.parseInt(value));
        } else if (field.getType().equals(long.class) || field.getType().equals(Long.class)) {
            field.set(instance, Long.parseLong(value));
        } else if (field.getType().equals(boolean.class) || field.getType().equals(Boolean.class)) {
            field.set(instance, Boolean.parseBoolean(value));
        } else {
            field.set(instance, value);
        }
    }
}

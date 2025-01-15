package dataaccess.crudoperation.config;

import dataaccess.crudoperation.config.util.LoaderUtil;

public class LoadConfig {
    public static ApplicationConfig load() {
        ApplicationConfig config = new ApplicationConfig();

        config = LoaderUtil.loadFromJson(config);
        if (config.isLoaded()) {
            return config;
        }

        config = LoaderUtil.loadFromYaml(config);
        if (config.isLoaded()) {
            return config;
        }

        config = LoaderUtil.loadFromProperties(config);
        if (config.isLoaded()) {
            return config;
        }

        return config;
    }
}

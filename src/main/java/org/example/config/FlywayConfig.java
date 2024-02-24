package org.example.config;

import lombok.SneakyThrows;
import org.flywaydb.core.Flyway;

import java.util.Properties;

public class FlywayConfig {

    @SneakyThrows
    public static Flyway setupFlyway() {
        Properties props = new Properties();
        props.load(FlywayConfig.class.getClassLoader().getResourceAsStream("flyway.properties"));

        return Flyway.configure()
                .dataSource(props.getProperty("flyway.url"),
                        props.getProperty("flyway.user"),
                        props.getProperty("flyway.password"))
                .load();
    }
}

package org.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;

import java.util.Properties;

public class HikariCPConfig {

    @SneakyThrows
    public HikariDataSource setupDataSource() {
        Properties props = new Properties();
        props.load(getClass().getClassLoader().getResourceAsStream("hikari.properties"));

        HikariConfig config = new HikariConfig(props);
        return new HikariDataSource(config);
    }
}

package com.example.ems.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Exercise 9: Customizing Data Source Configuration.
 *
 * Spring Boot auto-configures the PRIMARY datasource entirely from the
 * spring.datasource.* properties in application.properties - no bean needed.
 *
 * This class shows the pattern for wiring up an ADDITIONAL (secondary) data
 * source when an application needs to talk to more than one database, using
 * externalized properties under app.datasource.secondary.*. It is opt-in and
 * disabled by default (app.datasource.secondary.enabled=false) so the demo
 * app runs on H2 alone unless a second datasource is explicitly configured.
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.secondary")
    public HikariDataSource secondaryDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}

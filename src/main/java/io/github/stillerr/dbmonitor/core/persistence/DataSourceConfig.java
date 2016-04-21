package io.github.stillerr.dbmonitor.core.persistence;


import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSourceConfig {

    @Bean
    public DataSource dataSource(Environment environment) {
        Map<String, Object> jdbcProperties = new HashMap<>();

        jdbcProperties.put(JdbcDataSourceFactory.JDBC_DRIVER_CLASS_NAME, environment.getProperty(JdbcDataSourceFactory.JDBC_DRIVER_CLASS_NAME));
        jdbcProperties.put(JdbcDataSourceFactory.JDBC_URL_PROPERTY, environment.getProperty(JdbcDataSourceFactory.JDBC_URL_PROPERTY));
        jdbcProperties.put(JdbcDataSourceFactory.JDBC_USERNAME_PROPERTY, environment.getProperty(JdbcDataSourceFactory.JDBC_USERNAME_PROPERTY));
        jdbcProperties.put(JdbcDataSourceFactory.JDBC_PASSWORD_PROPERTY, environment.getProperty(JdbcDataSourceFactory.JDBC_PASSWORD_PROPERTY));
        jdbcProperties.put(JdbcDataSourceFactory.JDBC_DRIVER_CLASS_NAME, environment.getProperty(JdbcDataSourceFactory.JDBC_DRIVER_CLASS_NAME));
        jdbcProperties.put(DataSourceFactory.JDBC_RUN_VALIDATION_QUERY, environment.getProperty(DataSourceFactory.JDBC_RUN_VALIDATION_QUERY, "true"));

        return new DataSourceFactory().getDataSource(jdbcProperties);
    }

}
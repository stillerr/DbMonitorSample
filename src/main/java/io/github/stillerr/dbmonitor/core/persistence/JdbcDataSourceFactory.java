package io.github.stillerr.dbmonitor.core.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.util.Map;

public class JdbcDataSourceFactory {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public static final String JDBC_DRIVER_CLASS_NAME = "datasource.driverClassName";
    public static final String JDBC_URL_PROPERTY = "datasource.url";
    public static final String JDBC_USERNAME_PROPERTY = "datasource.username";
    public static final String JDBC_PASSWORD_PROPERTY = "datasource.password";


    public DataSource getDataSource(Map<String, Object> jdbcProperties) {
        logger.debug("Trying DataSource based on properties");
        String driverClassName = (String)jdbcProperties.get(JDBC_DRIVER_CLASS_NAME);
        if (driverClassName == null) {
            throw new IllegalArgumentException("Missing driver class name, check the application.properties file!");
        }
        try {
            Class.forName(driverClassName);
        }
        catch (ClassNotFoundException cnfe) {
            throw new IllegalArgumentException("Invalid driver class name, the application.properties file: " + driverClassName);
        }
        String url = (String)jdbcProperties.get(JDBC_URL_PROPERTY);
        String username = (String)jdbcProperties.get(JDBC_USERNAME_PROPERTY);
        if (username == null) {
            throw new IllegalArgumentException("Missing username, check the application.properties file!");
        }
        String password =  (String)jdbcProperties.get(JDBC_PASSWORD_PROPERTY);
        if (password == null) {
            throw new IllegalArgumentException("Missing password, check the application.properties file!");
        }
        logger.debug("Using DataSource based on properties, url: {}, " + "username: {}", url, username);
        return new SingleConnectionDataSource(url, username, password, true);
    }
}

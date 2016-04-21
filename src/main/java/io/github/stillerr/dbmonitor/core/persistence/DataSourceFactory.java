package io.github.stillerr.dbmonitor.core.persistence;

import io.github.stillerr.dbmonitor.core.util.DatabaseDialect;
import io.github.stillerr.dbmonitor.core.util.DialectFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Map;

public class DataSourceFactory {

    public static final String JDBC_RUN_VALIDATION_QUERY = "jdbc.runValidationQuery";

    public DataSource getDataSource(Map<String, Object> jdbcProperties) {
        DataSource dataSource = null;
        if (definedInProperties(jdbcProperties)) {
            dataSource = new JdbcDataSourceFactory().getDataSource(jdbcProperties);
        }
        if (runValidationQuery(jdbcProperties)) {
            runValidationQuery(dataSource);
        }
        return dataSource;
    }

    private boolean runValidationQuery(Map<String, Object> jdbcProperties) {
        return Boolean.parseBoolean((String)jdbcProperties.get(JDBC_RUN_VALIDATION_QUERY));
    }

    private boolean definedInProperties(Map<String, Object> jdbcProperties) {
        return jdbcProperties.containsKey(JdbcDataSourceFactory.JDBC_URL_PROPERTY);
    }

    private void runValidationQuery(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        DatabaseDialect databaseDialect = new DialectFactory().createDialect(dataSource);
        jdbcTemplate.execute(databaseDialect.validationQuery());
    }
}

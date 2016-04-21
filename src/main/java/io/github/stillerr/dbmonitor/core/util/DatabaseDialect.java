package io.github.stillerr.dbmonitor.core.util;

import org.springframework.jdbc.core.JdbcTemplate;

public interface DatabaseDialect {

    void disableConstraints(JdbcTemplate jdbcTemplate);

    void enableConstraints(JdbcTemplate jdbcTemplate);

    String validationQuery();
}

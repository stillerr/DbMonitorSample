package io.github.stillerr.dbmonitor.core.util;

import org.springframework.jdbc.core.JdbcTemplate;

public class H2Dialect implements DatabaseDialect {

    @Override
    public void disableConstraints(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");
    }

    @Override
    public void enableConstraints(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");
    }

    @Override
    public String validationQuery() {
        return "select 1";
    }
}

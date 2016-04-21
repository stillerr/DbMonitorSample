package io.github.stillerr.dbmonitor.core.util;

import javax.sql.DataSource;

public class DialectFactory {

    public DatabaseDialect createDialect(DataSource dataSource) {
        return new H2Dialect();

    }
}

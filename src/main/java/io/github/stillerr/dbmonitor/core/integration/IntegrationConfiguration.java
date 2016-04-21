package io.github.stillerr.dbmonitor.core.integration;


import io.github.stillerr.dbmonitor.eventlistener.service.JpaBusinessEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;

import javax.sql.DataSource;

public class IntegrationConfiguration {

    public static final String POLLER_SELECT = "SELECT * FROM BUSINESS_EVENTS" +
            " WHERE id > (SELECT MAX(id) FROM PROCESSED_BUSINESS_EVENTS) " +
            " OR (SELECT MAX(id) FROM PROCESSED_BUSINESS_EVENTS) IS NULL";

    @Autowired
    private JpaBusinessEventListener businessEventListener;

    @Value("${database.poller.periodMs}")
    private Long pollerPeriodMs;

    @Bean
    public MessageSource<?> jdbcAdapter(DataSource dataSource) {
        return new JdbcPollingChannelAdapter(dataSource, POLLER_SELECT);
    }

    @Bean
    public IntegrationFlow jdbcFlow(MessageSource<?> jdbcAdapter) {
        return IntegrationFlows
                .from(jdbcAdapter, e -> e.poller(p -> p.fixedRate(pollerPeriodMs)))
                .handle(businessEventListener)
                .get();
    }

}

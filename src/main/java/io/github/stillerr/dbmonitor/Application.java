package io.github.stillerr.dbmonitor;

import io.github.stillerr.dbmonitor.core.integration.IntegrationConfiguration;
import io.github.stillerr.dbmonitor.core.persistence.DataSourceConfig;
import io.github.stillerr.dbmonitor.core.persistence.JpaConfiguration;
import io.github.stillerr.dbmonitor.core.websocket.WebSocketConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.InputStream;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan({
        "io.github.stillerr.dbmonitor.**.controller",
        "io.github.stillerr.dbmonitor.**.service",
        "io.github.stillerr.dbmonitor.**.repository"
})
@IntegrationComponentScan
@Import({DataSourceConfig.class, JpaConfiguration.class, IntegrationConfiguration.class, WebSocketConfig.class})
public class Application extends SpringBootServletInitializer {

    // mvn spring-boot:run
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}

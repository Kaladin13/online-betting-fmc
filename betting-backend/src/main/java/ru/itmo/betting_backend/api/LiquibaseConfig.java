package ru.itmo.betting_backend.api;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiquibaseConfig {

    @Bean
    public SpringLiquibase liquibase(HikariDataSource hikariDataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase/changelog.xml");
        liquibase.setDataSource(hikariDataSource);
        return liquibase;
    }
}

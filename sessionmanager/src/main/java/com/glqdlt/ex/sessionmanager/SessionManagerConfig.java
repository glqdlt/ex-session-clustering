package com.glqdlt.ex.sessionmanager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @see <a href='https://www.baeldung.com/spring-session-jdbc'>https://www.baeldung.com/spring-session-jdbc</a>
 * @see <a href='https://jojoldu.tistory.com/171'>https://jojoldu.tistory.com/171</a>
 * schema script ==> org.springframework.session.jdbc.schema-mysql.sql
 */
@Configuration
@EnableJdbcHttpSession
public class SessionManagerConfig extends AbstractHttpSessionApplicationInitializer {

    @Bean(name = "sessionManagerDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/session?serverTimezone=UTC")
                .username("root")
                .password("1234")
                .build();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Autowired DataSource sessionManagerDataSource) {
        return new DataSourceTransactionManager(sessionManagerDataSource);
    }
}

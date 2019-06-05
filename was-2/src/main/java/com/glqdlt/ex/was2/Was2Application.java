package com.glqdlt.ex.was2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
@ComponentScan(basePackages = {"com.glqdlt.ex.*"})
@SpringBootApplication
public class Was2Application {

	public static void main(String[] args) {
		SpringApplication.run(Was2Application.class, args);
	}

}

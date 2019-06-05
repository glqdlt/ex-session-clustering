package com.glqdlt.ex.was1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.glqdlt.ex.*"})
@SpringBootApplication
public class Was1Application {

	public static void main(String[] args) {
		SpringApplication.run(Was1Application.class, args);
	}

}

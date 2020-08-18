package com.maha.catalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * This is the main class for the application.
 */

@SpringBootApplication
@ComponentScan(basePackages = { "com.maha.catalogue" })
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class WatchCatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchCatalogueApplication.class, args);
	}

}

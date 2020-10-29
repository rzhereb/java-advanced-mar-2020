package com.oktenweb.javaadvanced;

import com.oktenweb.javaadvanced.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class JavaAdvancedMar2020Application {

	public static void main(String[] args) {
		SpringApplication.run(JavaAdvancedMar2020Application.class, args);
	}

}

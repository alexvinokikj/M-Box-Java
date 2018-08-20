package com.app.MBox;

import com.fasterxml.classmate.AnnotationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EntityScan(basePackages = {"com.app.MBox.core.model"})
public class MBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(MBoxApplication.class, args);

	}
}

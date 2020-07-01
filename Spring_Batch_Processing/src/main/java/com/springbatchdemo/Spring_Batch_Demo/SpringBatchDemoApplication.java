package com.springbatchdemo.Spring_Batch_Demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableBatchProcessing
@EnableSwagger2
@ComponentScan(basePackages="com.springbatchdemo.Spring_Batch_Demo.*")
public class SpringBatchDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchDemoApplication.class, args);
	}

}

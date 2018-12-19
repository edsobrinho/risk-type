package com.zagwork.risktype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.zagwork.risktype.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.zagwork.risktype"})
public class RiskTypeApp {

	public static void main(String[] args) {
		SpringApplication.run(RiskTypeApp.class, args);
	}
}

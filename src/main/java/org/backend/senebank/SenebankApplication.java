package org.backend.senebank;

import org.backend.senebank.config.EnvironmentConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SenebankApplication {

    public static void main(String[] args) {
        EnvironmentConfig.loadEnv();
        SpringApplication.run(SenebankApplication.class, args);
    }

}

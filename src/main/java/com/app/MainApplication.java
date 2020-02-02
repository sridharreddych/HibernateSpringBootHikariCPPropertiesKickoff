package com.app;

import com.app.repository.NumberRepository;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private static final ExecutorService executor = Executors.newFixedThreadPool(25);

    private final NumberRepository numberRepository;

    public MainApplication(NumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            while (true) {
                executor.execute(numberRepository);

                Thread.sleep((int) (Math.random() * 250));
            }

        };
    }
}
/*
 * How To Customize HikariCP Settings Via Properties

If you use the spring-boot-starter-jdbc or spring-boot-starter-data-jpa "starters", you automatically get a dependency to HikariCP

Note: The best way to tune the connection pool parameters consist in using Flexy Pool by Vlad Mihalcea. Via Flexy Pool you can find the optim settings that sustain high-performance of your connection pool.

Description: This is a kickoff application that set up HikariCP via application.properties only. The jdbcUrl is set up for a MySQL database. For testing purposes, the application uses an ExecutorServicefor simulating concurrent users. Check the HickariCP report revealing the connection pool status.

Key points:

in application.properties, rely on spring.datasource.hikari.* to configure HikariCP
Output sample:
 */

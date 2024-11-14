package com.alura.LiterAlura.initializer;

import com.alura.LiterAlura.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.alura.LiterAlura.model")
@EnableJpaRepositories("com.alura.LiterAlura.repository")
@ComponentScan(basePackages = "com.alura.LiterAlura")
public class LiteraluraApplication implements CommandLineRunner {

    @Autowired
    private Principal principal;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            if (principal != null) {
                principal.run(args);  // Llamar al método run() de Principal
            } else {
                System.out.println("La clase Principal no está inicializada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

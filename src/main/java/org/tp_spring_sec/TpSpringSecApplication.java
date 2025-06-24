package org.tp_spring_sec;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.tp_spring_sec.entities.Product;
import org.tp_spring_sec.repositories.ProductRepository;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication()
public class TpSpringSecApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpSpringSecApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Product product1 = Product.builder().name("product1").price(10.20).description("pppppp11111").build();
            Product product2 = Product.builder().name("product2").price(50.0).description("pppppp22222").build();
            productRepository.save(product1);
            productRepository.save(product2);
        };
    }
}

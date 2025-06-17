package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Configuration
@ComponentScan(basePackages = "org.example")
@EnableJpaRepositories(basePackages = {
        "org.example.repository",
        "org.example.user"
})
@EntityScan(basePackages = "org.example")
public class AppConfig {

    @Bean
    public String customerName() {
        return "DefaultCustomer";
    }
}

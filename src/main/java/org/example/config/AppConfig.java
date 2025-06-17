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
        "org.example.user" // 🔧 Ezt kell hozzáadni!
})
@EntityScan(basePackages = "org.example") // 🔧 Szükséges, hogy az Entity osztályokat is lássa
public class AppConfig {

    @Bean
    public String customerName() {
        return "DefaultCustomer";
    }
}

package tn.camps.tuncamps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "tn.camps.tuncamps",exclude = SecurityAutoConfiguration.class)
@EnableWebSecurity
@EnableJpaRepositories
@EnableWebMvc
public class TunCampsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TunCampsApplication.class, args);
        System.out.println("Hello Hello ");
    }

}

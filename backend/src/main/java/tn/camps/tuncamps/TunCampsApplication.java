package tn.camps.tuncamps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement

public class TunCampsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TunCampsApplication.class, args);
        System.out.println("Hello Hello ");
    }

}

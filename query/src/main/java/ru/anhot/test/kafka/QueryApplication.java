package ru.anhot.test.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class QueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryApplication.class, args);
    }

}

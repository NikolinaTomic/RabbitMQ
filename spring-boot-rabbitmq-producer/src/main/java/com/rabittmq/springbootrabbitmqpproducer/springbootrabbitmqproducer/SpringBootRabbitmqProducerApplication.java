package com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class SpringBootRabbitmqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRabbitmqProducerApplication.class, args);
    }

}

package com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class SpringBootRabbitmqProducerApplication {

    public static void main(String[] args) throws IOException, TimeoutException {
//		ConnectionFactory factory = new ConnectionFactory();
//		try (Connection connection = factory.newConnection()) {
//			Channel channel = connection.createChannel();
//			var queueName = "hello world";
//			channel.queueDeclare(queueName, false, false, false, null);
//
//			String message = "Message from: " + LocalDateTime.now();
//			channel.basicPublish("", queueName, false, null, message.getBytes());
//
//			System.out.println("!!!Message has been sent!!!");
//		}
        SpringApplication.run(SpringBootRabbitmqProducerApplication.class, args);
    }

}

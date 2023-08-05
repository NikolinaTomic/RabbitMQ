package com.rabittmq.springbootrabbitmqproducer.springbootrabbitmqconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class SpringBootRabbitmqConsumerApplication {

    public static void main(String[] args) throws IOException, TimeoutException {
//		ConnectionFactory factory = new ConnectionFactory();
//		Connection connection = factory.newConnection();
//		Channel channel = connection.createChannel();
//		var queueName = "hello world";
//		channel.queueDeclare(queueName, false, false, false, null);
//
//		channel.basicConsume(queueName, true, (s, delivery) -> {
//			String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
//			System.out.println("Received message: " + message);
//		}, consumerTag -> {
//		});
        SpringApplication.run(SpringBootRabbitmqConsumerApplication.class, args);
    }

}

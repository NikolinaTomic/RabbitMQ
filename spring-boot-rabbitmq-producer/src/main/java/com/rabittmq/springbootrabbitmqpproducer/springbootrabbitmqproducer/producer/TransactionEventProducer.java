package com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.producer;

import com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model.TransactionEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransactionEventProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    private RabbitTemplate rabbitTemplate;

    public TransactionEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Send message to exchange using routing key
    public void send(TransactionEvent transactionEvent) {
        rabbitTemplate.convertAndSend(exchange, routingKey, transactionEvent);
    }
}

package com.rabittmq.springbootrabbitmqproducer.springbootrabbitmqconsumer.consumer;

import com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model.TransactionEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionEventConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(TransactionEvent event) {
        System.out.println("Uid : " + event.getTransaction().getUid());
        System.out.println("Amount : " + event.getTransaction().getAmount());
        System.out.println("From : " + event.getTransaction().getFromAccount());
        System.out.println("To : " + event.getTransaction().getToAccount());
        System.out.println("Time : " + event.getTransaction().getTransactionDateTime());
        System.out.println("==========================");
    }
}

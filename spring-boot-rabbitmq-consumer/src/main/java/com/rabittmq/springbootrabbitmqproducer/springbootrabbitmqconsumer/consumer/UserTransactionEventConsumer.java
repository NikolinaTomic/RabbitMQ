package com.rabittmq.springbootrabbitmqproducer.springbootrabbitmqconsumer.consumer;

import com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model.UserTransactionEvent;
import com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model.UserType;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

@Service
public class UserTransactionEventConsumer {

    private final HashMap<UUID, BigDecimal> transactionsEventHashMap = new HashMap<>();
    private int counter = 0;
    private Instant start;
    private Instant end;

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(UserTransactionEvent event) {
        if (counter == 0) {
            start = Instant.now();
        }
        counter++;

        var amount = event.getUserTransaction().getAmount();
        var userType = event.getUserTransaction().getUserType();
        var account = event.getUserTransaction().getAccount();
        if (transactionsEventHashMap.containsKey(account)) {
            var value = transactionsEventHashMap.get(account);
            if (userType.equals(UserType.SENDER)) {
                value = value.subtract(amount);
            } else {
                value = value.add(amount);
            }
            transactionsEventHashMap.put(account, value);
        } else {
            if (userType.equals(UserType.SENDER)) {
                transactionsEventHashMap.put(account, amount.negate());
            } else {
                transactionsEventHashMap.put(account, amount);
            }
        }

        if (counter % 2 == 0) {
            end = Instant.now();
            System.out.println("Num: " + counter);
            System.out.println("Elapsed time seconds: " + Duration.between(start, end).toSeconds());
            System.out.println("Elapsed time nanoseconds: " + Duration.between(start, end).toNanos());
            System.out.println("==========================");
            // write map to csv file after processing
        }
    }
}

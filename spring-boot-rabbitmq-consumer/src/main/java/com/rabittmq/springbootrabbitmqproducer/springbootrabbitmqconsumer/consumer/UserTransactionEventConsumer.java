package com.rabittmq.springbootrabbitmqproducer.springbootrabbitmqconsumer.consumer;

import com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model.UserTransactionEvent;
import com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model.UserType;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserTransactionEventConsumer {

    private final HashMap<UUID, BigDecimal> transactionsEventHashMap = new HashMap<>();
    private final String filePath = "../../../RabbitMqTransactionsResult.csv";
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

        if (counter % 40000000 == 0) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write("AccountId,Amount");
                writer.newLine();

                for (Map.Entry<UUID, BigDecimal> entry : transactionsEventHashMap.entrySet()) {
                    writer.write(entry.getKey() + "," + entry.getValue());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            end = Instant.now();
            System.out.println("RabbitMQ application elapsed time in seconds: " + Duration.between(start, end).toSeconds());
        }
    }
}

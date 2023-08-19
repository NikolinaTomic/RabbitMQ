package com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model.Transaction;
import com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model.UserTransaction;
import com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model.UserTransactionEvent;
import com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model.UserType;
import com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.producer.UserTransactionEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final String fileName = "Transactions1000.csv";
    @Autowired
    UserTransactionEventProducer userTransactionEventProducer;

    @PostMapping
    public void sendTransactions() throws CsvValidationException, IOException, URISyntaxException {
        URL resourceUrl = getClass().getClassLoader().getResource(fileName);
        Path filePath = Paths.get(resourceUrl.toURI());
        CSVReader reader =
                new CSVReaderBuilder(new FileReader(filePath.toString())).build();
        var fields = reader.readNext();
        while (fields != null) {
            var transaction = Transaction.builder()
                    .uid(UUID.fromString(fields[0]))
                    .fromAccount(UUID.fromString(fields[1]))
                    .toAccount(UUID.fromString(fields[2]))
                    .amount(new BigDecimal(fields[3]))
                    .transactionDateTime(fields[4])
                    .build();

            // Double entry accounting
            var buyerUserTransaction = UserTransaction.builder()
                    .uid(transaction.getUid())
                    .userType(UserType.RECIPIENT)
                    .account(transaction.getToAccount())
                    .amount(transaction.getAmount())
                    .build();
            userTransactionEventProducer.send(
                    UserTransactionEvent.builder()
                            .eventUid(UUID.randomUUID())
                            .userTransaction(buyerUserTransaction)
                            .build());

            var sellerUserTransaction = UserTransaction.builder()
                    .uid(transaction.getUid())
                    .userType(UserType.SENDER)
                    .account(transaction.getFromAccount())
                    .amount(transaction.getAmount())
                    .build();
            userTransactionEventProducer.send(
                    UserTransactionEvent.builder()
                            .eventUid(UUID.randomUUID())
                            .userTransaction(sellerUserTransaction)
                            .build());

            fields = reader.readNext();
        }

    }
}

package com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model.Transaction;
import com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model.TransactionEvent;
import com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.producer.TransactionEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    TransactionEventProducer transactionEventProducer;

    @PostMapping
    public void sendTransactions() throws IOException {
        try (
                Reader reader = new FileReader("../../../Transactions.csv")
        ) {
            CsvToBean<Transaction> csvToBean = new CsvToBeanBuilder<Transaction>(reader)
                    .withType(Transaction.class)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Transaction> transactions = csvToBean.parse();
            transactions.stream()
                    .map(transaction -> new TransactionEvent(UUID.randomUUID(), transaction))
                    .forEach(transactionEvent -> transactionEventProducer.send(transactionEvent));
        }
    }
}

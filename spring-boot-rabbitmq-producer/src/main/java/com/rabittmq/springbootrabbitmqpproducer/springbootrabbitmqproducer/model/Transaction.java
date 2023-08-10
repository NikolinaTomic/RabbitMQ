package com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {

    @CsvBindByPosition(position = 0)
    private UUID uid;
    @CsvBindByPosition(position = 1)
    private UUID fromAccount;
    @CsvBindByPosition(position = 2)
    private UUID toAccount;
    @CsvBindByPosition(position = 3)
    private BigDecimal amount;
    @CsvBindByPosition(position = 4)
    private String transactionDateTime;
}

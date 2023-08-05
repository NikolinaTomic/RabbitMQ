package com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model;

import com.opencsv.bean.CsvBindByPosition;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

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

    public Transaction(UUID uid, UUID fromAccount, UUID toAccount, BigDecimal amount, String transactionDateTime) {
        this.uid = uid;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.transactionDateTime = transactionDateTime;
    }

    public Transaction() {
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public UUID getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(UUID fromAccount) {
        this.fromAccount = fromAccount;
    }

    public UUID getToAccount() {
        return toAccount;
    }

    public void setToAccount(UUID toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }
}

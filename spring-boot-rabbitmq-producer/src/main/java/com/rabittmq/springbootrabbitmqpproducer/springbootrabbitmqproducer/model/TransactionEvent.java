package com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model;

import java.io.Serializable;
import java.util.UUID;

public class TransactionEvent implements Serializable {
    private UUID eventUid;
    private Transaction transaction;

    public TransactionEvent() {
    }

    public TransactionEvent(UUID eventUid, Transaction transaction) {
        this.eventUid = eventUid;
        this.transaction = transaction;
    }

    public UUID getEventUid() {
        return eventUid;
    }

    public void setEventUid(UUID eventUid) {
        this.eventUid = eventUid;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}

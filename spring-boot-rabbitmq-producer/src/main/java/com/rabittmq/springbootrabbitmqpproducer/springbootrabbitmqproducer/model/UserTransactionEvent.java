package com.rabittmq.springbootrabbitmqpproducer.springbootrabbitmqproducer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTransactionEvent implements Serializable {
    private UUID eventUid;
    private UserTransaction userTransaction;
}

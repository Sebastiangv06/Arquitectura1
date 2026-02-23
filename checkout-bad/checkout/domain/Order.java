package com.checkout.domain;

import java.util.UUID;


public class Order {

    
    public String status; // "CREATED", "PAID", "REJECTED"
    public String paymentReference;
    public String paymentMethodUsed;

    public final String id;
    public final Money total;

    public Order(Money total) {
        this.id = UUID.randomUUID().toString();
        this.total = total;
        this.status = "CREATED";
    }
}

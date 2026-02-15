package com.checkout.domain.model;


import java.util.UUID;

public class Order {
  public enum Status { CREATED, PAID, REJECTED }

  private final String id;
  private Money total;
  private Status status;
  private Payment payment;

  public Order(Money total) {
    this.id = UUID.randomUUID().toString();
    this.total = total;
    this.status = Status.CREATED;
  }

  public void markPaid(Payment payment) {
    this.status = Status.PAID;
    this.payment = payment;
  }

  public void markRejected() {
    this.status = Status.REJECTED;
  }

  public String id() { return id; }
  public Money total() { return total; }
  public Status status() { return status; }
  public Payment payment() { return payment; }
}


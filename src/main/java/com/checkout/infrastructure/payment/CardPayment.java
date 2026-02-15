package com.checkout.infrastructure.payment;


import com.checkout.application.ports.PaymentMethod;
import com.checkout.domain.model.Money;

import java.util.UUID;

public class CardPayment implements PaymentMethod {
  @Override public String code() { return "CARD"; }

  @Override
  public PaymentResult pay(String orderId, Money amount) {
    boolean approved = amount.amount().doubleValue() <= 3_000_000; // regla simple
    return approved
      ? new PaymentResult(true, "CARD-" + UUID.randomUUID(), "Aprobado")
      : new PaymentResult(false, null, "Cupo insuficiente");
  }
}


package com.checkout.infrastructure.payment;


import com.checkout.application.ports.PaymentMethod;
import com.checkout.domain.model.Money;

import java.util.UUID;

public class PsePayment implements PaymentMethod {
  @Override public String code() { return "PSE"; }

  @Override
  public PaymentResult pay(String orderId, Money amount) {
    return new PaymentResult(true, "PSE-" + UUID.randomUUID(), "Aprobado por PSE");
  }
}


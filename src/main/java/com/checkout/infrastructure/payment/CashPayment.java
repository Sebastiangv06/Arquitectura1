package com.checkout.infrastructure.payment;


import com.checkout.application.ports.PaymentMethod;
import com.checkout.domain.model.Money;

public class CashPayment implements PaymentMethod {
  @Override public String code() { return "CASH"; }

  @Override
  public PaymentResult pay(String orderId, Money amount) {
    return new PaymentResult(true, "CASH-DELIVERY", "Pago contraentrega registrado");
  }
}


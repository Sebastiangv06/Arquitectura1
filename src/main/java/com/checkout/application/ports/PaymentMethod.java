package com.checkout.application.ports;


import com.checkout.domain.model.Money;

public interface PaymentMethod {
  String code();

  PaymentResult pay(String orderId, Money amount);

  record PaymentResult(boolean approved, String reference, String message) {
  }
}


package com.checkout.application.ports;


public interface PaymentMethodRegistry {
  PaymentMethod byCode(String code);
}


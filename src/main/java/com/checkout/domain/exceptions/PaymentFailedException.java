package com.checkout.domain.exceptions;


public class PaymentFailedException extends BusinessException {
  public PaymentFailedException(String message) { super(message); }
}


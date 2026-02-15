package com.checkout.infrastructure.config;


import com.checkout.application.CheckoutService;
import com.checkout.application.ports.OrderRepository;
import com.checkout.application.ports.PaymentMethod;
import com.checkout.application.ports.PaymentMethodRegistry;
import com.checkout.infrastructure.payment.CardPayment;
import com.checkout.infrastructure.payment.CashPayment;
import com.checkout.infrastructure.payment.PsePayment;
import com.checkout.infrastructure.persistence.InMemoryOrderRepository;

import java.util.Map;

public class AppConfig {

  public CheckoutService checkoutService() {
    PaymentMethod card = new CardPayment();
    PaymentMethod pse = new PsePayment();
    PaymentMethod cash = new CashPayment();

    PaymentMethodRegistry registry = new MapPaymentRegistry(
      Map.of(card.code(), card, pse.code(), pse, cash.code(), cash)
    );

    OrderRepository repo = new InMemoryOrderRepository();
    return new CheckoutService(registry, repo);
  }
}


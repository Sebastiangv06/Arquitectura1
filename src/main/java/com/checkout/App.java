package com.checkout;


import com.checkout.domain.model.Money;
import com.checkout.domain.model.Order;
import com.checkout.infrastructure.config.AppConfig;


public class App {
  public static void main(String[] args) {
    var service = new AppConfig().checkoutService();

    Order order = new Order(Money.of(250000, "COP"));
    var result = service.checkout(order, "PSE");

    System.out.println("OrderId: " + result.orderId());
    System.out.println("Status: " + result.status());
    System.out.println("Ref: " + result.reference());
  }
}

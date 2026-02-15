package com.checkout.application;

import com.checkout.application.ports.OrderRepository;
import com.checkout.application.ports.PaymentMethod;
import com.checkout.application.ports.PaymentMethodRegistry;
import com.checkout.domain.model.Money;
import com.checkout.domain.model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CheckoutServiceTest {

  @Test
  void should_pay_order_successfully() {
    PaymentMethod fakePayment = new PaymentMethod() {
      @Override public String code() { return "FAKE"; }
      @Override public PaymentResult pay(String orderId, Money amount) {
        return new PaymentResult(true, "OK-123", "OK");
      }
    };

    PaymentMethodRegistry registry = code -> fakePayment;

    OrderRepository repo = new OrderRepository() {
      @Override public void save(Order order) { /* no-op */ }
      @Override public Order findById(String id) { return null; }
    };

    CheckoutService service = new CheckoutService(registry, repo);

    Order order = new Order(Money.of(10000, "COP"));
    var result = service.checkout(order, "FAKE");

    assertEquals("PAID", result.status());
    assertEquals("OK-123", result.reference());
    assertNotNull(order.payment());
  }
}

package com.checkout.application;


import com.checkout.application.ports.OrderRepository;
import com.checkout.application.ports.PaymentMethod;
import com.checkout.application.ports.PaymentMethodRegistry;
import com.checkout.domain.exceptions.PaymentFailedException;
import com.checkout.domain.model.Order;
import com.checkout.domain.model.Payment;

public class CheckoutService {

  private final PaymentMethodRegistry registry;
  private final OrderRepository repository;

  public CheckoutService(PaymentMethodRegistry registry, OrderRepository repository) {
    this.registry = registry;
    this.repository = repository;
  }

  public Result checkout(Order order, String paymentCode) {
    PaymentMethod method = registry.byCode(paymentCode);
    var payResult = method.pay(order.id(), order.total());

    if (!payResult.approved()) {
      order.markRejected();
      repository.save(order);
      throw new PaymentFailedException("Pago rechazado: " + payResult.message());
    }

    order.markPaid(new Payment(method.code(), order.total(), payResult.reference()));
    repository.save(order);

    return new Result(order.id(), order.status().name(), payResult.reference());
  }

  public record Result(String orderId, String status, String reference) {}
}


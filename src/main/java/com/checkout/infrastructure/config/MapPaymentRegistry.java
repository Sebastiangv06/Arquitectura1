package com.checkout.infrastructure.config;


import com.checkout.application.ports.PaymentMethod;
import com.checkout.application.ports.PaymentMethodRegistry;
import com.checkout.domain.exceptions.BusinessException;

import java.util.Map;

public class MapPaymentRegistry implements PaymentMethodRegistry {
  private final Map<String, PaymentMethod> methods;

  public MapPaymentRegistry(Map<String, PaymentMethod> methods) {
    this.methods = methods;
  }

  @Override
  public PaymentMethod byCode(String code) {
    PaymentMethod method = methods.get(code);
    if (method == null) throw new BusinessException("Método no soportado: " + code);
    return method;
  }
}


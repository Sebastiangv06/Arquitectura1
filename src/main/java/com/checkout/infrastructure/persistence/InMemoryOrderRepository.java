package com.checkout.infrastructure.persistence;


import com.checkout.application.ports.OrderRepository;
import com.checkout.domain.exceptions.BusinessException;
import com.checkout.domain.model.Order;

import java.util.HashMap;
import java.util.Map;

public class InMemoryOrderRepository implements OrderRepository {
  private final Map<String, Order> db = new HashMap<>();

  @Override public void save(Order order) { db.put(order.id(), order); }

  @Override
  public Order findById(String id) {
    Order order = db.get(id);
    if (order == null) throw new BusinessException("Orden no existe: " + id);
    return order;
  }
}


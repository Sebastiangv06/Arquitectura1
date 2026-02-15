package com.checkout.application.ports;


import com.checkout.domain.model.Order;

public interface OrderRepository {
  void save(Order order);
  Order findById(String id);
}

